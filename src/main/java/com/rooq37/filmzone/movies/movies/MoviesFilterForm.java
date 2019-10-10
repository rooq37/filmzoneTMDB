package com.rooq37.filmzone.movies.movies;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MoviesFilterForm {

    private static final String DELIMITER = ", ";

    private String name;
    private int minYear = 1900;
    private int maxYear = 2020;
    private String selectedCategories;
    private List<String> possibleCategories;
    private String selectedCountries;
    private List<String> possibleCountries;
    private int minRate = 1;
    private int maxRate = 10;

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

    public List<String> getSelectedCategoriesAsList(){
        if(selectedCategories == null || selectedCategories.isEmpty()) return new ArrayList<>();
        return new ArrayList<>(Arrays.asList(selectedCategories.split(DELIMITER)));
    }

    public void setSelectedCategories(String selectedCategories) {
        this.selectedCategories = selectedCategories;
    }

    public List<String> getPossibleCategories() {
        return possibleCategories;
    }

    public void setPossibleCategories(List<String> possibleCategories) {
        this.possibleCategories = possibleCategories;
    }

    public String getSelectedCountries() {
        return selectedCountries;
    }

    public List<String> getSelectedCountriesAsList(){
        if(selectedCountries == null || selectedCountries.isEmpty()) return new ArrayList<>();
        return new ArrayList<>(Arrays.asList(selectedCountries.split(DELIMITER)));
    }

    public void setSelectedCountries(String selectedCountries) {
        this.selectedCountries = selectedCountries;
    }

    public List<String> getPossibleCountries() {
        return possibleCountries;
    }

    public void setPossibleCountries(List<String> possibleCountries) {
        this.possibleCountries = possibleCountries;
    }

    public int getMinRate() {
        return minRate;
    }

    public void setMinRate(int minRate) {
        this.minRate = minRate;
    }

    public int getMaxRate() {
        return maxRate;
    }

    public void setMaxRate(int maxRate) {
        this.maxRate = maxRate;
    }

    public boolean checkIfCategoriesMatchFilter(List<String> movieCategories){
        for(String selectedCategory : getSelectedCategoriesAsList()){
            if(!movieCategories.contains(selectedCategory)) return false;
        }
        return true;
    }

    public boolean checkIfCountriesMatchFilter(List<String> movieCountries){
        for(String selectedCountry : getSelectedCountriesAsList()){
            if(!movieCountries.contains(selectedCountry)) return false;
        }
        return true;
    }

}
