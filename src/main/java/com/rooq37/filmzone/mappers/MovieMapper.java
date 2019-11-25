package com.rooq37.filmzone.mappers;

import com.rooq37.filmzone.dtos.ImageDTO;
import info.movito.themoviedbapi.model.*;
import info.movito.themoviedbapi.model.core.NamedIdElement;
import info.movito.themoviedbapi.model.people.PersonCast;

import java.util.List;
import java.util.stream.Collectors;

public abstract class MovieMapper {

    private static final String BASIC_YOUTUBE_URL = "https://www.youtube.com/embed/";
    private static final String BASIC_URL = "http://image.tmdb.org/t/p/";
    private static final String BASIC_IMG_SIZE = "w500";
    private static final String ORIGINAL_IMG_SIZE = "original";
    private static final String BASIC_SOURCE = "TMDB";

    private static final String JOB_TYPE_DIRECTOR = "Director";
    private static final String JOB_TYPE_SCENARIO = "Screenplay";

    private static final String DEFAULT_COVER_PATH = "../images/filmzone_default.png";
    private static final String DEFAULT_COVER_AUTHOR = "Filmzone";

    protected MovieDb movie;

    public MovieMapper(MovieDb movie){
        this.movie = movie;
    }

    protected ImageDTO getCover(){
        String coverPath = movie.getPosterPath();
        if(coverPath == null || coverPath.isEmpty()){
            return new ImageDTO(movie.getTitle(), DEFAULT_COVER_PATH, DEFAULT_COVER_AUTHOR) ;
        }else{
            return new ImageDTO(movie.getTitle(), BASIC_URL + BASIC_IMG_SIZE + coverPath, BASIC_SOURCE);
        }
    }

    protected List<String> getCategories(){
        return movie.getGenres().stream().map(NamedIdElement::getName).collect(Collectors.toList());
    }

    protected List<String> getCountries(){
        return movie.getProductionCountries().stream().map(ProductionCountry::getName).collect(Collectors.toList());
    }

    protected List<String> getDirectors(){
        return movie.getCrew().stream().filter(personCrew -> personCrew.getJob().equals(JOB_TYPE_DIRECTOR)).map(NamedIdElement::getName).collect(Collectors.toList());
    }

    protected List<String> getScenarios(){
        return movie.getCrew().stream().filter(personCrew -> personCrew.getJob().equals(JOB_TYPE_SCENARIO)).map(NamedIdElement::getName).collect(Collectors.toList());
    }

    protected List<ImageDTO> getPictures(){
        return movie.getImages(ArtworkType.BACKDROP).stream()
                .map(artwork -> new ImageDTO(movie.getTitle(), BASIC_URL + ORIGINAL_IMG_SIZE + artwork.getFilePath(), BASIC_SOURCE))
                .collect(Collectors.toList());
    }

    protected String getTrailerUrl(){
        List<Video> youtubeVideos = movie.getVideos().stream()
                .filter(video -> video.getSite().equals("YouTube")).collect(Collectors.toList());
        if(youtubeVideos.size() > 0) return BASIC_YOUTUBE_URL + youtubeVideos.get(0).getKey();
        else return "";
    }

    protected List<PersonCast> getCharacters(){
        return movie.getCast();
    }

}
