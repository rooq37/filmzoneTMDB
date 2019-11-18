package com.rooq37.filmzone.mappers;

import com.rooq37.filmzone.dtos.CharacterDTO;
import com.rooq37.filmzone.dtos.ImageDTO;
import com.rooq37.filmzone.dtos.PersonDTO;
import com.rooq37.filmzone.entities.*;

import java.util.List;
import java.util.stream.Collectors;

public abstract class MovieMapper {

    private static final String MEDIA_TYPE_COVER = "COVER";
    private static final String MEDIA_TYPE_PICTURE = "PICTURE";
    private static final String MEDIA_TYPE_TRAILER = "TRAILER";

    private static final String DEFAULT_COVER_PATH = "../images/filmzone_default.png";
    private static final String DEFAULT_COVER_AUTHOR = "Filmzone";

    protected static final String MOVIE_PERSON_TYPE_DIRECTOR = "DIRECTOR";
    protected static final String MOVIE_PERSON_TYPE_SCENARIO = "SCENARIO";
    protected static final String MOVIE_PERSON_TYPE_ACTOR = "ACTOR";

    //protected static final String PICTURES_PATH = "../images/";
    protected static final String PICTURES_PATH = "../movie_media/";

    protected MovieEntity movieEntity;

    public MovieMapper(MovieEntity movieEntity){
        this.movieEntity = movieEntity;
    }

    protected ImageDTO getCover(){
        List<MediaEntity> covers = movieEntity.getMedia().stream()
                .filter(mediaEntity -> mediaEntity.getType().equals(MEDIA_TYPE_COVER)).collect(Collectors.toList());
        if(covers.isEmpty()){
            return new ImageDTO(movieEntity.getTitle(), DEFAULT_COVER_PATH, DEFAULT_COVER_AUTHOR) ;
        }else{
            MediaEntity cover = covers.get(0);
            return new ImageDTO(movieEntity.getTitle(), PICTURES_PATH + cover.getValue(), cover.getAuthor());
        }
    }

    protected List<String> getCategories(){
        return movieEntity.getCategories().stream().map(CategoryEntity::getName).collect(Collectors.toList());
    }

    protected List<String> getCountries(){
        return movieEntity.getCountries().stream().map(CountryEntity::getName).collect(Collectors.toList());
    }

    protected List<PersonDTO> getDirectors(){
        return movieEntity.getPeople().stream()
                .filter(moviePersonEntity -> moviePersonEntity.getType().equals(MOVIE_PERSON_TYPE_DIRECTOR))
                .map(moviePersonEntity -> new PersonDTO(moviePersonEntity.getPerson().getName(), moviePersonEntity.getPerson().getSurname()))
                .collect(Collectors.toList());
    }

    protected List<PersonDTO> getScenarios(){
        return movieEntity.getPeople().stream()
                .filter(moviePersonEntity -> moviePersonEntity.getType().equals(MOVIE_PERSON_TYPE_SCENARIO))
                .map(moviePersonEntity -> new PersonDTO(moviePersonEntity.getPerson().getName(), moviePersonEntity.getPerson().getSurname()))
                .collect(Collectors.toList());
    }

    protected List<ImageDTO> getPictures(){
        return  movieEntity.getMedia().stream()
                .filter(mediaEntity -> mediaEntity.getType().equals(MEDIA_TYPE_PICTURE)).map(mediaEntity ->
                        new ImageDTO(movieEntity.getTitle(), PICTURES_PATH + mediaEntity.getValue(), mediaEntity.getAuthor()))
                .collect(Collectors.toList());
    }

    protected String getTrailerUrl(){
        List<MediaEntity> trailers = movieEntity.getMedia().stream()
                .filter(mediaEntity -> mediaEntity.getType().equals(MEDIA_TYPE_TRAILER)).collect(Collectors.toList());
        if(trailers.isEmpty()){
            return "";
        }else{
            MediaEntity trailer = trailers.get(0);
            return trailer.getValue();
        }
    }

    protected List<CharacterDTO> getCharacters(){
        return movieEntity.getPeople().stream()
                .filter(moviePersonEntity -> moviePersonEntity.getType().equals(MOVIE_PERSON_TYPE_ACTOR))
                .map(moviePersonEntity -> new CharacterDTO(new PersonDTO(moviePersonEntity.getPerson().getName(),
                        moviePersonEntity.getPerson().getSurname()), moviePersonEntity.getRole())).collect(Collectors.toList());
    }

}
