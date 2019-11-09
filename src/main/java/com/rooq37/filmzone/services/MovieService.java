package com.rooq37.filmzone.services;

import com.rooq37.filmzone.commons.CastPair;
import com.rooq37.filmzone.commons.MovieListElement;
import com.rooq37.filmzone.entities.*;
import com.rooq37.filmzone.movies.editMovieForm.EditMovieForm;
import com.rooq37.filmzone.movies.editMovieForm.ImageFile;
import com.rooq37.filmzone.movies.movieDetails.*;
import com.rooq37.filmzone.movies.movies.MoviesFilterForm;
import com.rooq37.filmzone.repositories.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class MovieService {

    @Autowired
    private HelperService helperService;
    @Autowired
    private CommentRepository commentRepository;
    @Autowired
    private MoviePersonRepository moviePersonRepository;
    @Autowired
    private MovieRepository movieRepository;
    @Autowired
    private RatingRepository ratingRepository;
    @Autowired
    private UserService userService;
    @Autowired
    private MediaRepository mediaRepository;
    @Autowired
    private FileSaverService fileSaverService;

    public MovieSummary getMovieSummary(Long id){
        MovieSummary movieSummary = new MovieSummary();

        MovieEntity movie = movieRepository.findById(id).get();
        movieSummary.setTitle(movie.getTitle());
        movieSummary.setCover(helperService.getCover(movie));
        movieSummary.setCategories(helperService.getCategories(movie));

        movieSummary.setDescription(movie.getDescription());
        movieSummary.setDuration(movie.getDuration());
        movieSummary.setYear(movie.getYear());

        String directors = moviePersonRepository.findAllByMovieAndType(movie, "DIRECTOR").stream().
                map(moviePerson -> moviePerson.getPerson().getFullName()).collect(Collectors.joining(", "));
        movieSummary.setDirector(directors);

        String scenario = moviePersonRepository.findAllByMovieAndType(movie, "SCENARIO").stream().
                map(moviePerson -> moviePerson.getPerson().getFullName()).collect(Collectors.joining(", "));
        movieSummary.setScenario(scenario);

        String countries = movie.getCountries().stream().
                map(CountryEntity::getName).collect(Collectors.joining(", "));
        movieSummary.setCountry(countries);

        movieSummary.setAvgUsersRating(String.format("%.1f", helperService.countAverageRating(movie)));

        return movieSummary;
    }

    public MovieRating getMovieRating(Long id){
        MovieRating movieRating = new MovieRating();
        MovieEntity movie = movieRepository.findById(id).get();

        movieRating.setAvg(String.format("%.2f", helperService.countAverageRating(movie)));
        movieRating.setNumberOfPeopleWhoWatched(String.valueOf(ratingRepository.countByMovieAndValueGreaterThan(movie, 0)));
        movieRating.setNumberOfPeopleWhoWantToWatch(String.valueOf(ratingRepository.countByMovieAndValueIs(movie, 0)));

        return movieRating;
    }

    public MovieMedia getMovieMedia(Long id){
        MovieMedia movieMedia = new MovieMedia();
        MovieEntity movie = movieRepository.findById(id).get();

        movieMedia.setPhotos(helperService.getPictures(movie));
        List<MediaEntity> trailers = mediaRepository.findAllByMoviesEqualsAndType(movie, "TRAILER");
        movieMedia.setTrailerLink(!trailers.isEmpty() ? trailers.get(0).getValue() : "");

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

    public Page<MovieListElement> getMovieListElements(Pageable pageable, Integer sort, MoviesFilterForm moviesFilter){
        List<MovieListElement> movieElements = new ArrayList<>();
        if(sort >= 3 && sort <= 6) pageable = PageRequest.of(pageable.getPageNumber(), pageable.getPageSize(), setSortByDatabase(sort));
        List<MovieEntity> movieList = movieRepository.findMovieEntitiesByYearBetween(moviesFilter.getMinYear(), moviesFilter.getMaxYear(), pageable.getSort());

        for(MovieEntity movie : movieList)
            movieElements.add(helperService.getMovieListElement(movie));

        movieElements = movieElements.stream().filter(movie ->
                moviesFilter.checkIfCategoriesMatchFilter(movie.getCategories())
                        && moviesFilter.checkIfCountriesMatchFilter(movie.getCountries())
                        && movie.getAvgUsersRating() >= moviesFilter.getMinRate()
                        && movie.getAvgUsersRating() <= moviesFilter.getMaxRate()).collect(Collectors.toList());

        if(moviesFilter.getName() != null && !moviesFilter.getName().isEmpty())
            movieElements = movieElements.stream().filter(movie ->
                    movie.getTitle().toLowerCase().contains(moviesFilter.getName().toLowerCase())).collect(Collectors.toList());

        if(sort <= 2 || sort >= 7) sortMovieElementList(movieElements, sort);

        return new PageImpl<>(movieElements, pageable, movieElements.size());
    }

    private Sort setSortByDatabase(int sort){
        switch (sort){
            case 3: return Sort.by("title").descending();
            case 4: return Sort.by("title").ascending();
            case 5: return Sort.by("year").descending();
            case 6: return Sort.by("year").ascending();
        }
        return Sort.by(Sort.DEFAULT_DIRECTION);
    }

    private void sortMovieElementList (List<MovieListElement> movieElementList, Integer sort){
        switch (sort){
            case 1: movieElementList.sort(MovieListElement.avgUsersRatingComparator.reversed());
                break;
            case 2: movieElementList.sort(MovieListElement.avgUsersRatingComparator);
                break;
            case 7: movieElementList.sort(MovieListElement.numberOfPeopleWhoWatchedComparator.reversed());
                break;
            case 8: movieElementList.sort(MovieListElement.numberOfPeopleWhoWatchedComparator);
                break;
        }
    }

    public SingleUserRating getMovieRatingByUser(Long movieId, String userEmail){
        RatingEntity userRating = ratingRepository.findByUser_EmailAndMovie_Id(userEmail, movieId);
        return (userRating != null) ?
                new SingleUserRating(true, userRating.getValue()) : new SingleUserRating(false);
    }

    public void rateMovie(Long movieId, String userEmail, int newRating){
        RatingEntity userRating = ratingRepository.findByUser_EmailAndMovie_Id(userEmail, movieId);
        if(userRating == null){
            userRating = new RatingEntity();
            userRating.setMovie(movieRepository.findMovieEntityById(movieId));
            userRating.setUser(userService.getUserByEmail(userEmail));
        }
        userRating.setDate(new Date());
        userRating.setValue(newRating);
        ratingRepository.save(userRating);
    }

    public void addCommentToMovie(Long movieId, String userEmail, String content){
        CommentEntity commentEntity = new CommentEntity();
        commentEntity.setMovie(movieRepository.findMovieEntityById(movieId));
        commentEntity.setUser(userService.getUserByEmail(userEmail));
        commentEntity.setRating(0);
        commentEntity.setContent(content);
        commentEntity.setDate(new Date());
        commentRepository.save(commentEntity);
    }

    public MovieEntity saveMovie(EditMovieForm movieForm, ImageFile cover, List<ImageFile> pictures){
        MovieEntity movie = new MovieEntity();
        movie.setTitle(movieForm.getTitle());
        movie.setDescription(movieForm.getDescription());
        movie.setDuration(movieForm.getDuration());
        movie.setYear(movieForm.getProductionYear());
        movie = movieRepository.save(movie);
        helperService.saveMoviePersonEntities(movie, movieForm.getDirectors(), movieForm.getScenario(), movieForm.getCharacters());
        helperService.saveCategoryEntities(movie, movieForm.getCategories());
        helperService.saveCountryEntities(movie, movieForm.getCountries());
        try {
            fileSaverService.saveMediaEntity(cover, movie, "COVER");
            for(ImageFile image : pictures) fileSaverService.saveMediaEntity(image, movie, "PICTURE");
        } catch (IOException e) {
            e.printStackTrace();
        }

        return movie;
    }

}
