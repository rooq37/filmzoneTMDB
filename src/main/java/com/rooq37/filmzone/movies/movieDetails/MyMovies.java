package com.rooq37.filmzone.movies.movieDetails;

import java.util.List;

public class MyMovies {

    private List<String> listsContainMovie;
    private List<String> availableLists;

    public MyMovies(List<String> listsContainMovie, List<String> availableLists) {
        this.listsContainMovie = listsContainMovie;
        this.availableLists = availableLists;
    }

    public List<String> getListsContainMovie() {
        return listsContainMovie;
    }

    public void setListsContainMovie(List<String> listsContainMovie) {
        this.listsContainMovie = listsContainMovie;
    }

    public List<String> getAvailableLists() {
        return availableLists;
    }

    public void setAvailableLists(List<String> availableLists) {
        this.availableLists = availableLists;
    }

}
