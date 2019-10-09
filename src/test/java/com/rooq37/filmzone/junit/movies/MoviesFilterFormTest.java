package com.rooq37.filmzone.junit.movies;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;

public class MoviesFilterFormTest {

    @Test
    public void checkIfCategoriesMatchFilter() {
        MoviesFilterForm moviesFilterForm = new MoviesFilterForm();
        List<String> categories = new ArrayList<>(Arrays.asList("aaa", "bbb", "ccc"));

        moviesFilterForm.setSelectedCategories("aaa");
        assertTrue(moviesFilterForm.checkIfCategoriesMatchFilter(categories));

        moviesFilterForm.setSelectedCategories("ddd");
        assertFalse(moviesFilterForm.checkIfCategoriesMatchFilter(categories));

        moviesFilterForm.setSelectedCategories("aaa, bbb");
        assertTrue(moviesFilterForm.checkIfCategoriesMatchFilter(categories));

        moviesFilterForm.setSelectedCategories("aaa, ddd");
        assertFalse(moviesFilterForm.checkIfCategoriesMatchFilter(categories));

        moviesFilterForm.setSelectedCategories("aaa, bbb, ccc");
        assertTrue(moviesFilterForm.checkIfCategoriesMatchFilter(categories));

        moviesFilterForm.setSelectedCategories("aaa, bbb, ccc, ddd");
        assertFalse(moviesFilterForm.checkIfCategoriesMatchFilter(categories));
    }

    @Test
    public void checkIfCountriesMatchFilter() {
        MoviesFilterForm moviesFilterForm = new MoviesFilterForm();
        List<String> countries = new ArrayList<>(Arrays.asList("aaa", "bbb", "ccc"));

        moviesFilterForm.setSelectedCountries("aaa");
        assertTrue(moviesFilterForm.checkIfCountriesMatchFilter(countries));

        moviesFilterForm.setSelectedCountries("ddd");
        assertFalse(moviesFilterForm.checkIfCountriesMatchFilter(countries));

        moviesFilterForm.setSelectedCountries("aaa, bbb");
        assertTrue(moviesFilterForm.checkIfCountriesMatchFilter(countries));

        moviesFilterForm.setSelectedCountries("aaa, ddd");
        assertFalse(moviesFilterForm.checkIfCountriesMatchFilter(countries));

        moviesFilterForm.setSelectedCountries("aaa, bbb, ccc");
        assertTrue(moviesFilterForm.checkIfCountriesMatchFilter(countries));

        moviesFilterForm.setSelectedCountries("aaa, bbb, ccc, ddd");
        assertFalse(moviesFilterForm.checkIfCountriesMatchFilter(countries));
    }

}