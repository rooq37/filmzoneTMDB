package com.rooq37.filmzone.mappers;

import com.rooq37.filmzone.dtos.MovieDetailsDTO;
import com.rooq37.filmzone.entities.*;
import info.movito.themoviedbapi.model.MovieDb;

import java.util.List;
import java.util.stream.Collectors;

public class MovieDetailsMapper extends MovieSimpleMapper {

    public MovieDetailsMapper(MovieDb movie){
        super(movie);
        movieDTO = new MovieDetailsDTO();
    }

    public MovieDetailsDTO getMovieDetailsDTO(){
        ((MovieDetailsDTO)movieDTO).setDuration(movie.getRuntime());
        ((MovieDetailsDTO)movieDTO).setDirector(getDirector());
        ((MovieDetailsDTO)movieDTO).setScenario(getScenario());
        ((MovieDetailsDTO)movieDTO).setPictures(getPictures());
        ((MovieDetailsDTO)movieDTO).setTrailerLink(getTrailerUrl());
        ((MovieDetailsDTO)movieDTO).setCharacters(getCharacters());
        ((MovieDetailsDTO)movieDTO).setCountry(getCountry());
        return (MovieDetailsDTO) super.getMovieSimpleDTO();
    }

    private String getDirector(){
        return String.join(", ", getDirectors());
    }

    private String getScenario(){
        return String.join(", ", getScenarios());
    }

}
