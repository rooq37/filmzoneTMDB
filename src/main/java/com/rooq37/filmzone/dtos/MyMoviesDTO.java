package com.rooq37.filmzone.dtos;

import java.util.List;

public class MyMoviesDTO {

    private List<String> listsContainMovie;
    private List<String> availableLists;

    public MyMoviesDTO(List<String> listsContainMovie, List<String> availableLists) {
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
