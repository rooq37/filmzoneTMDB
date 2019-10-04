package com.rooq37.filmzone.home;

import com.rooq37.filmzone.commons.MovieListElement;

import java.util.Map;

public class HomeForm {

    private Map<MovieListElement, Integer> mostPopularMovies;

    private long numberOfMoviesInDatabase;
    private long numberOfRegisteredAccounts;
    private long numberOfActiveUsers;
    private long numberOfRatings;
    private long numberOfSearches;

    private long numberOfNewAccountsLastWeek;
    private long numberOfRatingsLastWeek;
    private long numberOfSearchesLastWeek;

    public Map<MovieListElement, Integer> getMostPopularMovies() {
        return mostPopularMovies;
    }

    public void setMostPopularMovies(Map<MovieListElement, Integer> mostPopularMovies) {
        this.mostPopularMovies = mostPopularMovies;
    }

    public long getNumberOfMoviesInDatabase() {
        return numberOfMoviesInDatabase;
    }

    public void setNumberOfMoviesInDatabase(long numberOfMoviesInDatabase) {
        this.numberOfMoviesInDatabase = numberOfMoviesInDatabase;
    }

    public long getNumberOfRegisteredAccounts() {
        return numberOfRegisteredAccounts;
    }

    public void setNumberOfRegisteredAccounts(long numberOfRegisteredAccounts) {
        this.numberOfRegisteredAccounts = numberOfRegisteredAccounts;
    }

    public long getNumberOfActiveUsers() {
        return numberOfActiveUsers;
    }

    public void setNumberOfActiveUsers(long numberOfActiveUsers) {
        this.numberOfActiveUsers = numberOfActiveUsers;
    }

    public long getNumberOfRatings() {
        return numberOfRatings;
    }

    public void setNumberOfRatings(long numberOfRatings) {
        this.numberOfRatings = numberOfRatings;
    }

    public long getNumberOfSearches() {
        return numberOfSearches;
    }

    public void setNumberOfSearches(long numberOfSearches) {
        this.numberOfSearches = numberOfSearches;
    }

    public long getNumberOfNewAccountsLastWeek() {
        return numberOfNewAccountsLastWeek;
    }

    public void setNumberOfNewAccountsLastWeek(long numberOfNewAccountsLastWeek) {
        this.numberOfNewAccountsLastWeek = numberOfNewAccountsLastWeek;
    }

    public long getNumberOfRatingsLastWeek() {
        return numberOfRatingsLastWeek;
    }

    public void setNumberOfRatingsLastWeek(long numberOfRatingsLastWeek) {
        this.numberOfRatingsLastWeek = numberOfRatingsLastWeek;
    }

    public long getNumberOfSearchesLastWeek() {
        return numberOfSearchesLastWeek;
    }

    public void setNumberOfSearchesLastWeek(long numberOfSearchesLastWeek) {
        this.numberOfSearchesLastWeek = numberOfSearchesLastWeek;
    }

}
