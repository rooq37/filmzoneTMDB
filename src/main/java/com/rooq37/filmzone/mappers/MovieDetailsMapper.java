package com.rooq37.filmzone.mappers;

import com.rooq37.filmzone.dtos.CharacterDTO;
import com.rooq37.filmzone.dtos.ImageDTO;
import com.rooq37.filmzone.dtos.MovieDetailsDTO;
import com.rooq37.filmzone.dtos.PersonDTO;
import com.rooq37.filmzone.entities.*;

import java.util.List;
import java.util.stream.Collectors;

public class MovieDetailsMapper extends MovieSimpleMapper {

    private static final String MEDIA_TYPE_PICTURE = "PICTURE";
    private static final String MEDIA_TYPE_TRAILER = "TRAILER";
    private static final String MOVIE_PERSON_TYPE_DIRECTOR = "DIRECTOR";
    private static final String MOVIE_PERSON_TYPE_SCENARIO = "SCENARIO";
    private static final String MOVIE_PERSON_TYPE_ACTOR = "ACTOR";

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

    private List<ImageDTO> getPictures(){
        return  movieEntity.getMedia().stream()
                .filter(mediaEntity -> mediaEntity.getType().equals(MEDIA_TYPE_PICTURE)).map(mediaEntity ->
                    new ImageDTO(movieEntity.getTitle(), PICTURES_PATH + mediaEntity.getValue(), mediaEntity.getAuthor()))
                .collect(Collectors.toList());
    }

    private String getTrailerUrl(){
        List<MediaEntity> trailers = movieEntity.getMedia().stream()
                .filter(mediaEntity -> mediaEntity.getType().equals(MEDIA_TYPE_TRAILER)).collect(Collectors.toList());
        if(trailers.isEmpty()){
            return "";
        }else{
            MediaEntity trailer = trailers.get(0);
            return trailer.getValue();
        }
    }

    private List<CharacterDTO> getCharacters(){
        return movieEntity.getPeople().stream()
                .filter(moviePersonEntity -> moviePersonEntity.getType().equals(MOVIE_PERSON_TYPE_ACTOR))
                .map(moviePersonEntity -> new CharacterDTO(new PersonDTO(moviePersonEntity.getPerson().getName(),
                        moviePersonEntity.getPerson().getSurname()), moviePersonEntity.getRole())).collect(Collectors.toList());
    }

}
