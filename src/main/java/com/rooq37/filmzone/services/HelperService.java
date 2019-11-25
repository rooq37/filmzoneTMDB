package com.rooq37.filmzone.services;

import com.rooq37.filmzone.dtos.MovieSimpleDTO;
import com.rooq37.filmzone.mappers.MovieSimpleMapper;
import com.rooq37.filmzone.repositories.RatingRepository;
import com.rooq37.filmzone.repositories.ViewRepository;
import info.movito.themoviedbapi.TmdbApi;
import info.movito.themoviedbapi.TmdbMovies;
import info.movito.themoviedbapi.model.MovieDb;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class HelperService {

    private static final String API_KEY = "5a46d5b61d76c153823d4be68aed3798";

    private static final String LANG_PL = "pl";

    @Autowired
    private ViewRepository viewRepository;
    @Autowired
    private RatingRepository ratingRepository;

    public MovieDb getBasicMovieDb(int movieId){
        TmdbApi api = new TmdbApi(API_KEY);
        TmdbMovies movies = api.getMovies();
        return movies.getMovie(movieId, LANG_PL);
    }

    public String getBasicImageUrl(){
        TmdbApi api = new TmdbApi(API_KEY);
        return api.getConfiguration().getBaseUrl() + "w500/";
    }

    public MovieSimpleDTO getMovieSimpleDTO(int movieId){
        MovieDb movie = getBasicMovieDb(movieId);
        MovieSimpleMapper msp = new MovieSimpleMapper(movie);
        MovieSimpleDTO movDTO = msp.getMovieSimpleDTO();
        movDTO.setFzVoteAvg(getFzVoteAvg(movieId));
        movDTO.setFzVoteCount(getFzVoteCount(movieId));
        movDTO.setFzNumberOfSearches(getFzNumberOfSearches(movieId));
        return movDTO;
    }

    public double getFzVoteAvg(int movieId){
        Double avg = ratingRepository.averageByTmdbMovieId(movieId);
        return (avg == null ? 0 : avg);
    }

    public int getFzVoteCount(int movieId){
        Integer count = ratingRepository.countByTmdbMovieId(movieId);
        return (count == null ? 0 : count);
    }

    public int getFzNumberOfSearches(int movieId){
        Integer searches = viewRepository.countByTmdbMovieId(movieId);
        return (searches == null ? 0 : searches);
    }

}
