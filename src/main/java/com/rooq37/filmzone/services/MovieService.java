package com.rooq37.filmzone.services;

import com.rooq37.filmzone.commons.CastPair;
import com.rooq37.filmzone.commons.Image;
import com.rooq37.filmzone.entities.*;
import com.rooq37.filmzone.movies.movieDetails.MovieCast;
import com.rooq37.filmzone.movies.movieDetails.MovieMedia;
import com.rooq37.filmzone.movies.movieDetails.MovieRating;
import com.rooq37.filmzone.movies.movieDetails.MovieSummary;
import com.rooq37.filmzone.movies.movies.MovieShortSummary;
import com.rooq37.filmzone.movies.movies.MoviesFilterForm;
import com.rooq37.filmzone.repositories.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class MovieService {

    private static final String PICTURES_PATH = "../images/";

    @Autowired
    CategoryRepository categoryRepository;
    @Autowired
    CommentRepository commentRepository;
    @Autowired
    CountryRepository countryRepository;
    @Autowired
    MediaRepository mediaRepository;
    @Autowired
    MovieCategoryRepository movieCategoryRepository;
    @Autowired
    MovieCountryRepository movieCountryRepository;
    @Autowired
    MovieMediaRepository movieMediaRepository;
    @Autowired
    MoviePersonRepository moviePersonRepository;
    @Autowired
    MovieRepository movieRepository;
    @Autowired
    PersonRepository personRepository;
    @Autowired
    RatingRepository ratingRepository;
    @Autowired
    UserRepository userRepository;

    public List<String> getAllCategories(){
        return categoryRepository.findAll().stream().map(CategoryEntity::getName).collect(Collectors.toList());
    }

    public List<String> getAllCountries(){
        return countryRepository.findAll().stream().map(CountryEntity::getName).collect(Collectors.toList());
    }

    public MovieSummary getMovieSummary(Long id){
        MovieSummary movieSummary = new MovieSummary();

        MovieEntity movie = movieRepository.findById(id).get();
        movieSummary.setTitle(movie.getTitle());
        movieSummary.setCover(getCover(movie));
        movieSummary.setCategories(getCategories(movie));

        movieSummary.setDescription(movie.getDescription());
        movieSummary.setDuration(movie.getDuration());
        movieSummary.setYear(movie.getYear());

        String directors = moviePersonRepository.findAllByMovieAndType(movie, "DIRECTOR").stream().
                map(moviePerson -> moviePerson.getPerson().getFullName()).collect(Collectors.joining(", "));
        movieSummary.setDirector(directors);

        String scenario = moviePersonRepository.findAllByMovieAndType(movie, "SCENARIO").stream().
                map(moviePerson -> moviePerson.getPerson().getFullName()).collect(Collectors.joining(", "));
        movieSummary.setScenario(scenario);

        String countries = movieCountryRepository.findAllByMovie(movie).stream().
                map(movieCountry -> movieCountry.getCountry().getName()).collect(Collectors.joining(", "));
        movieSummary.setCountry(countries);

        movieSummary.setAvgUsersRating(String.format("%.1f", countAverageRating(movie)));

        return movieSummary;
    }

    public MovieRating getMovieRating(Long id){
        MovieRating movieRating = new MovieRating();
        MovieEntity movie = movieRepository.findById(id).get();

        movieRating.setAvg(String.format("%.2f", countAverageRating(movie)));
        movieRating.setNumberOfPeopleWhoWatched(String.valueOf(ratingRepository.countByMovieAndValueGreaterThan(movie, 0)));
        movieRating.setNumberOfPeopleWhoWantToWatch(String.valueOf(ratingRepository.countByMovieAndValueIs(movie, 0)));

        return movieRating;
    }

    public MovieMedia getMovieMedia(Long id){
        MovieMedia movieMedia = new MovieMedia();
        MovieEntity movie = movieRepository.findById(id).get();

        List<Image> photos = new ArrayList<>();
        for(MovieMediaEntity mm : movieMediaRepository.findAllByMovieAndMedia_Type(movie, "PICTURE"))
            photos.add(new Image(movie.getTitle(), PICTURES_PATH + mm.getMedia().getValue(), mm.getMedia().getAuthor()));

        movieMedia.setPhotos(photos);
        movieMedia.setTrailerLink(movieMediaRepository.findAllByMovieAndMedia_Type(movie, "TRAILER").get(0).getMedia().getValue());

        return movieMedia;
    }

    public MovieCast getMovieCast(Long id){
        MovieCast movieCast = new MovieCast();
        MovieEntity movie = movieRepository.findById(id).get();

        List<CastPair> cast = new ArrayList<>();
        for(MoviePersonEntity mp : moviePersonRepository.findAllByMovieAndType(movie, "ACTOR"))
            cast.add(new CastPair(mp.getPerson().getFullName(), mp.getRole()));

        movieCast.setCast(cast);

        return movieCast;
    }

    public Page<CommentEntity> getMovieComments(Long id, Pageable pageable) {
        MovieEntity movie = movieRepository.findById(id).get();
        Pageable paging = PageRequest.of(pageable.getPageNumber(), pageable.getPageSize(), pageable.getSort());

        return commentRepository.findAllByMovie(movie, paging);
    }

    public Page<MovieShortSummary> getMoviesShortSummary(Pageable pageable, Integer sort, MoviesFilterForm moviesFilter){
        List<MovieShortSummary> moviesShort = new ArrayList<>();
        List<MovieEntity> movieList = (sort >= 3 && sort <= 6)
                ? movieRepository.findMovieEntitiesByYearBetween(moviesFilter.getMinYear(), moviesFilter.getMaxYear(), pageable.getSort())
                : movieRepository.findMovieEntitiesByYearBetween(moviesFilter.getMinYear(), moviesFilter.getMaxYear());

        for(MovieEntity movie : movieList)
            moviesShort.add(getMovieShortSummary(movie));

        moviesShort = moviesShort.stream().filter(movie ->
                moviesFilter.checkIfCategoriesMatchFilter(movie.getCategories())
                        && moviesFilter.checkIfCountriesMatchFilter(movie.getCountries())
                        && movie.getAvgUsersRating() >= moviesFilter.getMinRate()
                        && movie.getAvgUsersRating() <= moviesFilter.getMaxRate()).collect(Collectors.toList());

        if(moviesFilter.getName() != null && !moviesFilter.getName().isEmpty())
            moviesShort = moviesShort.stream().filter(movie ->
                    movie.getTitle().toLowerCase().contains(moviesFilter.getName().toLowerCase())).collect(Collectors.toList());

        if(sort <= 2 || sort >= 7) sortMovieShortSummaryList(moviesShort, sort);

        return new PageImpl<>(moviesShort, pageable, moviesShort.size());
    }

    private void sortMovieShortSummaryList (List<MovieShortSummary> movieShortSummaryList, Integer sort){
        switch (sort){
            case 1: movieShortSummaryList.sort(MovieShortSummary.avgUsersRatingComparator.reversed());
                break;
            case 2: movieShortSummaryList.sort(MovieShortSummary.avgUsersRatingComparator);
                break;
            case 7: movieShortSummaryList.sort(MovieShortSummary.numberOfPeopleWhoWatchedComparator.reversed());
                break;
            case 8: movieShortSummaryList.sort(MovieShortSummary.numberOfPeopleWhoWatchedComparator);
                break;
        }
    }

    private MovieShortSummary getMovieShortSummary(MovieEntity movie){
        MovieShortSummary mss = new MovieShortSummary();

        mss.setId(movie.getId());
        mss.setTitle(movie.getTitle());
        mss.setCover(getCover(movie));
        mss.setCategories(getCategories(movie));
        mss.setCountries(getCountries(movie));
        mss.setDescription(movie.getDescription());
        mss.setYear(movie.getYear());
        mss.setAvgUsersRating(countAverageRating(movie));
        mss.setNumberOfPeopleWhoWatched(ratingRepository.countByMovieAndValueGreaterThan(movie, 0));
        mss.setNumberOfPeopleWhoWantToWatch(ratingRepository.countByMovieAndValueIs(movie, 0));

        return mss;
    }

    private double countAverageRating(MovieEntity movieEntity){
        List<Integer> ratings = ratingRepository.findAllByMovie(movieEntity).stream().map(RatingEntity::getValue).collect(Collectors.toList());
        return ratings.stream().mapToInt(Integer::intValue).average().getAsDouble();
    }

    private Image getCover(MovieEntity movieEntity){
        MediaEntity cover = movieMediaRepository.findAllByMovieAndMedia_Type(movieEntity, "COVER").get(0).getMedia();
        return new Image(movieEntity.getTitle(), PICTURES_PATH + cover.getValue(), cover.getAuthor());
    }

    private List<String> getCategories(MovieEntity movieEntity){
        return movieCategoryRepository.findAllByMovie(movieEntity).stream().
                map(movieCategory -> movieCategory.getCategory().getName()).collect(Collectors.toList());
    }

    private List<String> getCountries(MovieEntity movieEntity){
        return movieCountryRepository.findAllByMovie(movieEntity).stream().
                map(movieCountry -> movieCountry.getCountry().getName()).collect(Collectors.toList());
    }

}
