package com.rooq37.filmzone.junit.services;

import com.rooq37.filmzone.commons.Image;
import com.rooq37.filmzone.commons.MovieListElement;
import com.rooq37.filmzone.entities.*;
import com.rooq37.filmzone.repositories.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class HelperService {

    private static final String PICTURES_PATH = "../images/";

    @Autowired
    private MovieRepository movieRepository;
    @Autowired
    private ViewRepository viewRepository;
    @Autowired
    private MovieCategoryRepository movieCategoryRepository;
    @Autowired
    private RatingRepository ratingRepository;
    @Autowired
    private MovieMediaRepository movieMediaRepository;
    @Autowired
    private MovieCountryRepository movieCountryRepository;
    @Autowired
    private CategoryRepository categoryRepository;
    @Autowired
    private CountryRepository countryRepository;

    public List<MovieListElement> getAllMovieListElements(){
        List<MovieListElement> movieList = new ArrayList<>();
        List<MovieEntity> movies = movieRepository.findAll();

        for(MovieEntity movie : movies)
            movieList.add(getMovieListElement(movie));

        return movieList;
    }

    public MovieListElement getMovieListElement(MovieEntity movie){
        MovieListElement mle = new MovieListElement();

        mle.setId(movie.getId());
        mle.setTitle(movie.getTitle());
        mle.setCover(getCover(movie));
        mle.setCategories(getCategories(movie));
        mle.setCountries(getCountries(movie));
        mle.setDescription(movie.getDescription());
        mle.setYear(movie.getYear());
        mle.setAvgUsersRating(countAverageRating(movie));
        mle.setNumberOfPeopleWhoWatched(ratingRepository.countByMovieAndValueGreaterThan(movie, 0));
        mle.setNumberOfPeopleWhoWantToWatch(ratingRepository.countByMovieAndValueIs(movie, 0));
        mle.setNumberOfSearches(viewRepository.countByMovie(movie));

        return mle;
    }

    public double countAverageRating(MovieEntity movieEntity){
        List<Integer> ratings = ratingRepository.findAllByMovie(movieEntity).stream().map(RatingEntity::getValue).collect(Collectors.toList());
        return ratings.stream().mapToInt(Integer::intValue).average().getAsDouble();
    }

    public Image getCover(MovieEntity movieEntity){
        MediaEntity cover = movieMediaRepository.findAllByMovieAndMedia_Type(movieEntity, "COVER").get(0).getMedia();
        return new Image(movieEntity.getTitle(), PICTURES_PATH + cover.getValue(), cover.getAuthor());
    }

    public List<Image> getPictures(MovieEntity movieEntity){
        List<Image> photos = new ArrayList<>();
        for(MovieMediaEntity mm : movieMediaRepository.findAllByMovieAndMedia_Type(movieEntity, "PICTURE"))
            photos.add(new Image(movieEntity.getTitle(), PICTURES_PATH + mm.getMedia().getValue(), mm.getMedia().getAuthor()));

        return photos;
    }

    public List<String> getCategories(MovieEntity movieEntity){
        return movieCategoryRepository.findAllByMovie(movieEntity).stream().
                map(movieCategory -> movieCategory.getCategory().getName()).collect(Collectors.toList());
    }

    public List<String> getCountries(MovieEntity movieEntity){
        return movieCountryRepository.findAllByMovie(movieEntity).stream().
                map(movieCountry -> movieCountry.getCountry().getName()).collect(Collectors.toList());
    }

    public List<String> getAllCategories(){
        return categoryRepository.findAll().stream().map(CategoryEntity::getName).collect(Collectors.toList());
    }

    public List<String> getAllCountries(){
        return countryRepository.findAll().stream().map(CountryEntity::getName).collect(Collectors.toList());
    }

}
