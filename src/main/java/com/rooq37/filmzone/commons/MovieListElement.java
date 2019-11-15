package com.rooq37.filmzone.commons;

import com.rooq37.filmzone.dtos.ImageDTO;

import java.util.Comparator;
import java.util.List;

public class MovieListElement {

    private Long id;
    private String title;
    private ImageDTO cover;
    private List<String> categories;
    private List<String> countries;
    private String description;

    private int year;
    private double avgUsersRating;
    private int numberOfPeopleWhoWatched;
    private int numberOfPeopleWhoWantToWatch;
    private int numberOfSearches;

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

    public int getNumberOfSearches() {
        return numberOfSearches;
    }

    public void setNumberOfSearches(int numberOfSearches) {
        this.numberOfSearches = numberOfSearches;
    }

    public static Comparator<MovieListElement> idComparator = new Comparator<MovieListElement>() {
        @Override
        public int compare(MovieListElement o1, MovieListElement o2) {
            return Long.compare(o1.getId(), o2.getId());
        }
    };

    public static Comparator<MovieListElement> avgUsersRatingComparator = new Comparator<MovieListElement>() {
        @Override
        public int compare(MovieListElement o1, MovieListElement o2) {
            return Double.compare(o1.getAvgUsersRating(), o2.getAvgUsersRating());
        }
    };

    public static Comparator<MovieListElement> numberOfPeopleWhoWatchedComparator = new Comparator<MovieListElement>() {
        @Override
        public int compare(MovieListElement o1, MovieListElement o2) {
            return Integer.compare(o1.getNumberOfPeopleWhoWatched(), o2.getNumberOfPeopleWhoWatched());
        }
    };

    public static Comparator<MovieListElement> numberOfPeopleWhoWantToWatchComparator = new Comparator<MovieListElement>() {
        @Override
        public int compare(MovieListElement o1, MovieListElement o2) {
            return Integer.compare(o1.getNumberOfPeopleWhoWantToWatch(), o2.getNumberOfPeopleWhoWantToWatch());
        }
    };

    public static Comparator<MovieListElement> numberOfSearchesComparator = new Comparator<MovieListElement>() {
        @Override
        public int compare(MovieListElement o1, MovieListElement o2) {
            return Integer.compare(o1.getNumberOfSearches(), o2.getNumberOfSearches());
        }
    };

}
