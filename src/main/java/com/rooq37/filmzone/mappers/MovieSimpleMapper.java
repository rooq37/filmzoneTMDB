package com.rooq37.filmzone.mappers;

import com.rooq37.filmzone.dtos.MovieSimpleDTO;
import info.movito.themoviedbapi.model.MovieDb;

public class MovieSimpleMapper extends MovieMapper {

    protected MovieSimpleDTO movieDTO;

    public MovieSimpleMapper(MovieDb movie){
        super(movie);
        movieDTO = new MovieSimpleDTO();
    }

    public MovieSimpleDTO getMovieSimpleDTO(){
        movieDTO.setMovieId(movie.getId());
        movieDTO.setTitle(movie.getTitle());
        movieDTO.setCover(getCover());
        movieDTO.setCategories(getCategories());
        movieDTO.setDescription(movie.getOverview());
        movieDTO.setReleaseDate(movie.getReleaseDate());
        movieDTO.setTmdbVoteAvg(movie.getVoteAverage());
        movieDTO.setTmdbVoteCount(movie.getVoteCount());

        return movieDTO;
    }

    protected String getCountry(){
        return String.join(", ", getCountries());
    }

}
