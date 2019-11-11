package com.rooq37.filmzone.movies.editMovieForm;

import com.rooq37.filmzone.commons.Image;

import java.util.ArrayList;
import java.util.List;

public class EditMovieForm {

    private Long id;
    private String title = "";
    private int productionYear = 1900;
    private Image cover;
    private List<String> categories = new ArrayList<>();
    private String description;
    private int duration;
    private List<Person> directors = new ArrayList<>();
    private List<Person> scenario = new ArrayList<>();
    private List<String> countries = new ArrayList<>();
    private List<Image> pictures = new ArrayList<>();
    private String trailerUrl;
    private List<Character> characters = new ArrayList<>();

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

    public Image getCover() {
        return cover;
    }

    public void setCover(Image cover) {
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

    public List<Person> getDirectors() {
        return directors;
    }

    public void setDirectors(List<Person> directors) {
        this.directors = directors;
    }

    public List<Person> getScenario() {
        return scenario;
    }

    public void setScenario(List<Person> scenario) {
        this.scenario = scenario;
    }

    public List<String> getCountries() {
        return countries;
    }

    public void setCountries(List<String> countries) {
        this.countries = countries;
    }

    public List<Image> getPictures() {
        return pictures;
    }

    public void setPictures(List<Image> pictures) {
        this.pictures = pictures;
    }

    public String getTrailerUrl() {
        return trailerUrl;
    }

    public void setTrailerUrl(String trailerUrl) {
        this.trailerUrl = trailerUrl;
    }

    public List<Character> getCharacters() {
        return characters;
    }

    public void setCharacters(List<Character> characters) {
        this.characters = characters;
    }

}
