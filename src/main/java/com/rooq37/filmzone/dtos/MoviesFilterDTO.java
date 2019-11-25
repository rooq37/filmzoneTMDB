package com.rooq37.filmzone.dtos;

import info.movito.themoviedbapi.model.Discover;
import info.movito.themoviedbapi.model.Genre;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class MoviesFilterDTO {

    private static final String DELIMITER = ", ";

    private String name = "";
    private int minYear = 1900;
    private int maxYear = 2020;
    private String selectedCategories;
    private List<Genre> possibleCategories;
    private int minVoteCount = 500;
    private int minRate = 0;
    private String sortBy = "vote_average.desc";

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getMinYear() {
        return minYear;
    }

    public void setMinYear(int minYear) {
        this.minYear = minYear;
    }

    public int getMaxYear() {
        return maxYear;
    }

    public void setMaxYear(int maxYear) {
        this.maxYear = maxYear;
    }

    public String getSelectedCategories() {
        return selectedCategories;
    }

    private String getSelectedCategoriesIds(){
        if(selectedCategories == null || selectedCategories.isEmpty()) return "";
        List<String> ids = new ArrayList<>();
        for(Genre genre : possibleCategories){
            if(selectedCategories.contains(genre.getName()))
                ids.add(String.valueOf(genre.getId()));
        }
        return ids.stream().collect(Collectors.joining(DELIMITER));
    }

    public void setSelectedCategories(String selectedCategories) {
        this.selectedCategories = selectedCategories;
    }

    public List<Genre> getPossibleCategories() {
        return possibleCategories;
    }

    public void setPossibleCategories(List<Genre> possibleCategories) {
        this.possibleCategories = possibleCategories;
    }

    public int getMinVoteCount() {
        return minVoteCount;
    }

    public void setMinVoteCount(int minVoteCount) {
        this.minVoteCount = minVoteCount;
    }

    public int getMinRate() {
        return minRate;
    }

    public void setMinRate(int minRate) {
        this.minRate = minRate;
    }

    public String getSortBy() {
        return sortBy;
    }

    public void setSortBy(String sortBy) {
        this.sortBy = sortBy;
    }

    public Discover getDiscover(){
        Discover discover = new Discover()
                .language("pl")
                .includeAdult(false)
                .withGenres(getSelectedCategoriesIds())
                .sortBy(sortBy)
                .voteAverageGte(minRate)
                .voteCountGte(minVoteCount);
        discover.getParams().put("primary_release_date.gte", minYear + "-01-01");
        discover.getParams().put("primary_release_date.lte", maxYear + "-12-31");
        return discover;
    }

}
