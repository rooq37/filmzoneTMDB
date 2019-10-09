package com.rooq37.filmzone.services;

import com.rooq37.filmzone.commons.CastPair;
import com.rooq37.filmzone.commons.Image;
import com.rooq37.filmzone.entities.*;
import com.rooq37.filmzone.movies.movieDetail.MovieCast;
import com.rooq37.filmzone.movies.movieDetail.MovieMedia;
import com.rooq37.filmzone.movies.movieDetail.MovieRating;
import com.rooq37.filmzone.movies.movieDetail.MovieSummary;
import com.rooq37.filmzone.repositories.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
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

    public MovieSummary getMovieSummary(Long id){
        MovieSummary movieSummary = new MovieSummary();

        MovieEntity movie = movieRepository.findById(id).get();
        movieSummary.setTitle(movie.getTitle());

        MediaEntity cover = movieMediaRepository.findAllByMovieAndMedia_Type(movie, "COVER").get(0).getMedia();
        Image coverImage = new Image(movie.getTitle(), PICTURES_PATH + cover.getValue(), cover.getAuthor());
        movieSummary.setCover(coverImage);

        List<String> categories = movieCategoryRepository.findAllByMovie(movie).stream().
                map(movieCategory -> movieCategory.getCategory().getName()).collect(Collectors.toList());
        movieSummary.setCategories(categories);

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

        return commentRepository.findAllByMovie(movie, pageable);
    }

    private double countAverageRating(MovieEntity movieEntity){
        List<Integer> ratings = ratingRepository.findAllByMovie(movieEntity).stream().map(RatingEntity::getValue).collect(Collectors.toList());
        return ratings.stream().mapToInt(Integer::intValue).average().getAsDouble();
    }

}
