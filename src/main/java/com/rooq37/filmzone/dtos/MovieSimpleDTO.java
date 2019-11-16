package com.rooq37.filmzone.dtos;


import java.util.List;

public class MovieSimpleDTO {

    private Long movieId;
    private String title;
    private ImageDTO cover;
    private List<String> categories;
    private String country;
    private String description;
    private int year;

    private String avgUsersRating;
    private String numberOfPeopleWhoWatched;
    private String numberOfPeopleWhoWantToWatch;
    private long numberOfSearches;

    public Long getMovieId() {
        return movieId;
    }

    public void setMovieId(Long movieId) {
        this.movieId = movieId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
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

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
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

    public String getAvgUsersRating() {
        return avgUsersRating;
    }

    public void setAvgUsersRating(String avgUsersRating) {
        this.avgUsersRating = avgUsersRating;
    }

    public String getNumberOfPeopleWhoWatched() {
        return numberOfPeopleWhoWatched;
    }

    public void setNumberOfPeopleWhoWatched(String numberOfPeopleWhoWatched) {
        this.numberOfPeopleWhoWatched = numberOfPeopleWhoWatched;
    }

    public String getNumberOfPeopleWhoWantToWatch() {
        return numberOfPeopleWhoWantToWatch;
    }

    public void setNumberOfPeopleWhoWantToWatch(String numberOfPeopleWhoWantToWatch) {
        this.numberOfPeopleWhoWantToWatch = numberOfPeopleWhoWantToWatch;
    }

    public long getNumberOfSearches() {
        return numberOfSearches;
    }

    public void setNumberOfSearches(long numberOfSearches) {
        this.numberOfSearches = numberOfSearches;
    }

}
