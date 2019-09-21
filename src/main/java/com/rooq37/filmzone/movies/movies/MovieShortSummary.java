package com.rooq37.filmzone.movies.movies;

import com.rooq37.filmzone.commons.Image;

import java.util.Comparator;
import java.util.List;

public class MovieShortSummary {

    private Long id;
    private String title;
    private Image cover;
    private List<String> categories;
    private List<String> countries;
    private String description;

    private int year;
    private double avgUsersRating;
    private int numberOfPeopleWhoWatched;
    private int numberOfPeopleWhoWantToWatch;

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

    public List<String> getCountries() {
        return countries;
    }

    public void setCountries(List<String> countries) {
        this.countries = countries;
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

    public double getAvgUsersRating() {
        return avgUsersRating;
    }

    public String getAvgUsersRatingAsString() {
        return String.format("%.2f", avgUsersRating);
    }

    public void setAvgUsersRating(double avgUsersRating) {
        this.avgUsersRating = avgUsersRating;
    }

    public int getNumberOfPeopleWhoWatched() {
        return numberOfPeopleWhoWatched;
    }

    public void setNumberOfPeopleWhoWatched(int numberOfPeopleWhoWatched) {
        this.numberOfPeopleWhoWatched = numberOfPeopleWhoWatched;
    }

    public int getNumberOfPeopleWhoWantToWatch() {
        return numberOfPeopleWhoWantToWatch;
    }

    public void setNumberOfPeopleWhoWantToWatch(int numberOfPeopleWhoWantToWatch) {
        this.numberOfPeopleWhoWantToWatch = numberOfPeopleWhoWantToWatch;
    }

    public static Comparator<MovieShortSummary> avgUsersRatingComparator = new Comparator<MovieShortSummary>() {
        @Override
        public int compare(MovieShortSummary o1, MovieShortSummary o2) {
            return Double.compare(o1.getAvgUsersRating(), o2.getAvgUsersRating());
        }
    };

    public static Comparator<MovieShortSummary> numberOfPeopleWhoWatchedComparator = new Comparator<MovieShortSummary>() {
        @Override
        public int compare(MovieShortSummary o1, MovieShortSummary o2) {
            return Integer.compare(o1.getNumberOfPeopleWhoWatched(), o2.getNumberOfPeopleWhoWatched());
        }
    };

}
