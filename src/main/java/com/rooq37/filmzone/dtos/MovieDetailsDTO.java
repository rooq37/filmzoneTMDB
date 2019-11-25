package com.rooq37.filmzone.dtos;

import info.movito.themoviedbapi.model.people.PersonCast;

import java.util.List;

public class MovieDetailsDTO extends MovieSimpleDTO {

    private int duration;
    private String director;
    private String scenario;

    private List<ImageDTO> pictures;
    private String trailerLink;

    private List<PersonCast> characters;

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public String getDirector() {
        return director;
    }

    public void setDirector(String director) {
        this.director = director;
    }

    public String getScenario() {
        return scenario;
    }

    public void setScenario(String scenario) {
        this.scenario = scenario;
    }

    public List<ImageDTO> getPictures() {
        return pictures;
    }

    public void setPictures(List<ImageDTO> pictures) {
        this.pictures = pictures;
    }

    public String getTrailerLink() {
        return trailerLink;
    }

    public void setTrailerLink(String trailerLink) {
        this.trailerLink = trailerLink;
    }

    public List<PersonCast> getCharacters() {
        return characters;
    }

    public void setCharacters(List<PersonCast> characters) {
        this.characters = characters;
    }

    @Override
    public String getDescription() {
        return description;
    }
}
