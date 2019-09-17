package com.rooq37.filmzone.movies.movieDetail;

import com.rooq37.filmzone.commons.Image;

import java.util.List;

public class MovieSummary {

    private String title;
    private Image cover;
    private List<String> categories;
    private String description;

    private int year;
    private int duration;
    private String director;
    private String scenario;
    private String country;

    private String avgUsersRating;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
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

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

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

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getAvgUsersRating() {
        return avgUsersRating;
    }

    public void setAvgUsersRating(String avgUsersRating) {
        this.avgUsersRating = avgUsersRating;
    }

}
