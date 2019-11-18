package com.rooq37.filmzone.mappers;

import com.rooq37.filmzone.dtos.MovieDetailsDTO;
import com.rooq37.filmzone.entities.*;

import java.util.List;
import java.util.stream.Collectors;

public class MovieDetailsMapper extends MovieSimpleMapper {

    public MovieDetailsMapper(MovieEntity movieEntity){
        super(movieEntity);
        movieDTO = new MovieDetailsDTO();
    }

    public MovieDetailsDTO getMovieDetailsDTO(){
        ((MovieDetailsDTO)movieDTO).setDuration(movieEntity.getDuration());
        ((MovieDetailsDTO)movieDTO).setDirector(getDirector());
        ((MovieDetailsDTO)movieDTO).setScenario(getScenario());
        ((MovieDetailsDTO)movieDTO).setPictures(getPictures());
        ((MovieDetailsDTO)movieDTO).setTrailerLink(getTrailerUrl());
        ((MovieDetailsDTO)movieDTO).setCharacters(getCharacters());
        return (MovieDetailsDTO) super.getMovieSimpleDTO();
    }

    private String getDirector(){
        List<MoviePersonEntity> directors = movieEntity.getPeople().stream()
                .filter(moviePersonEntity -> moviePersonEntity.getType().equals(MOVIE_PERSON_TYPE_DIRECTOR)).collect(Collectors.toList());

        return directors.stream().map(moviePerson -> moviePerson.getPerson().getFullName())
                .collect(Collectors.joining(", "));
    }

    private String getScenario(){
        List<MoviePersonEntity> scenarioAuthors = movieEntity.getPeople().stream()
                .filter(moviePersonEntity -> moviePersonEntity.getType().equals(MOVIE_PERSON_TYPE_SCENARIO)).collect(Collectors.toList());

        return scenarioAuthors.stream().map(moviePerson -> moviePerson.getPerson().getFullName())
                .collect(Collectors.joining(", "));
    }

}
