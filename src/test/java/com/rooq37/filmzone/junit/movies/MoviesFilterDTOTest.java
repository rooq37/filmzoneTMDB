package com.rooq37.filmzone.junit.movies;

import com.rooq37.filmzone.dtos.MoviesFilterDTO;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;

public class MoviesFilterDTOTest {

    @Test
    public void checkIfCategoriesMatchFilter() {
        MoviesFilterDTO moviesFilterDTO = new MoviesFilterDTO();
        List<String> categories = new ArrayList<>(Arrays.asList("aaa", "bbb", "ccc"));

        moviesFilterDTO.setSelectedCategories("aaa");
        assertTrue(moviesFilterDTO.checkIfCategoriesMatchFilter(categories));

        moviesFilterDTO.setSelectedCategories("ddd");
        assertFalse(moviesFilterDTO.checkIfCategoriesMatchFilter(categories));

        moviesFilterDTO.setSelectedCategories("aaa, bbb");
        assertTrue(moviesFilterDTO.checkIfCategoriesMatchFilter(categories));

        moviesFilterDTO.setSelectedCategories("aaa, ddd");
        assertFalse(moviesFilterDTO.checkIfCategoriesMatchFilter(categories));

        moviesFilterDTO.setSelectedCategories("aaa, bbb, ccc");
        assertTrue(moviesFilterDTO.checkIfCategoriesMatchFilter(categories));

        moviesFilterDTO.setSelectedCategories("aaa, bbb, ccc, ddd");
        assertFalse(moviesFilterDTO.checkIfCategoriesMatchFilter(categories));
    }

    @Test
    public void checkIfCountriesMatchFilter() {
        MoviesFilterDTO moviesFilterDTO = new MoviesFilterDTO();
        List<String> countries = new ArrayList<>(Arrays.asList("aaa", "bbb", "ccc"));

        moviesFilterDTO.setSelectedCountries("aaa");
        assertTrue(moviesFilterDTO.checkIfCountriesMatchFilter(countries));

        moviesFilterDTO.setSelectedCountries("ddd");
        assertFalse(moviesFilterDTO.checkIfCountriesMatchFilter(countries));

        moviesFilterDTO.setSelectedCountries("aaa, bbb");
        assertTrue(moviesFilterDTO.checkIfCountriesMatchFilter(countries));

        moviesFilterDTO.setSelectedCountries("aaa, ddd");
        assertFalse(moviesFilterDTO.checkIfCountriesMatchFilter(countries));

        moviesFilterDTO.setSelectedCountries("aaa, bbb, ccc");
        assertTrue(moviesFilterDTO.checkIfCountriesMatchFilter(countries));

        moviesFilterDTO.setSelectedCountries("aaa, bbb, ccc, ddd");
        assertFalse(moviesFilterDTO.checkIfCountriesMatchFilter(countries));
    }

}