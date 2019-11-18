package com.rooq37.filmzone.dtos;

import java.util.ArrayList;
import java.util.List;

public class EditMovieDTO {

    private Long id;
    private String title = "";
    private int productionYear = 1900;
    private ImageDTO cover;
    private List<String> categories = new ArrayList<>();
    private String description;
    private int duration;
    private List<PersonDTO> directors = new ArrayList<>();
    private List<PersonDTO> scenario = new ArrayList<>();
    private List<String> countries = new ArrayList<>();
    private List<ImageDTO> pictures = new ArrayList<>();
    private String trailerUrl;
    private List<CharacterDTO> characters = new ArrayList<>();

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getProductionYear() {
        return productionYear;
    }

    public void setProductionYear(int productionYear) {
        this.productionYear = productionYear;
    }

    public ImageDTO getCover() {
        return cover;
    }

    public void setCover(ImageDTO cover) {
        this.cover = cover;
    }

    public List<String> getCategories() {
        return categories;
    }

    public void setCategories(List<String> categories) {
        this.categories = categories;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public List<PersonDTO> getDirectors() {
        return directors;
    }

    public void setDirectors(List<PersonDTO> directors) {
        this.directors = directors;
    }

    public List<PersonDTO> getScenario() {
        return scenario;
    }

    public void setScenario(List<PersonDTO> scenario) {
        this.scenario = scenario;
    }

    public List<String> getCountries() {
        return countries;
    }

    public void setCountries(List<String> countries) {
        this.countries = countries;
    }

    public List<ImageDTO> getPictures() {
        return pictures;
    }

    public void setPictures(List<ImageDTO> pictures) {
        this.pictures = pictures;
    }

    public String getTrailerUrl() {
        return trailerUrl;
    }

    public void setTrailerUrl(String trailerUrl) {
        this.trailerUrl = trailerUrl;
    }

    public List<CharacterDTO> getCharacters() {
        return characters;
    }

    public void setCharacters(List<CharacterDTO> characters) {
        this.characters = characters;
    }

}
