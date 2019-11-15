package com.rooq37.filmzone.junit.services;

import com.rooq37.filmzone.FilmzoneApplication;
import com.rooq37.filmzone.dtos.ImageDTO;
import com.rooq37.filmzone.commons.MovieListElement;
import com.rooq37.filmzone.entities.MovieEntity;
import com.rooq37.filmzone.repositories.CategoryRepository;
import com.rooq37.filmzone.repositories.MovieRepository;
import com.rooq37.filmzone.services.HelperService;
import org.hibernate.Hibernate;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = FilmzoneApplication.class)
public class MovieEditHelperServiceTest {

    @Autowired
    private HelperService helperService;

    @Autowired
    private MovieRepository movieRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    @Test
    public void getAllMovieListElements() {
        List<MovieListElement> movieListElements = helperService.getAllMovieListElements();
        assertThat(movieListElements.size()).isEqualTo(5);
    }

    @Test
    public void getMovieListElement() {
        MovieEntity movieEntity = movieRepository.findMovieEntityById((long) 1);
        MovieListElement movieListElement = helperService.getMovieListElement(movieEntity);
        assertThat(movieListElement.getYear()).isEqualTo(movieEntity.getYear());
        assertThat(movieListElement.getId()).isEqualTo(movieEntity.getId());
        assertThat(movieListElement.getDescription()).isEqualTo(movieEntity.getDescription());
        assertThat(movieListElement.getCover()).isEqualToComparingFieldByField(helperService.getCover(movieEntity));
        assertThat(movieListElement.getAvgUsersRatingAsString()).isEqualTo("10,00");
        assertThat(movieListElement.getTitle()).isEqualTo(movieEntity.getTitle());
        assertThat(movieListElement.getCountries()).contains("USA");
        assertThat(movieListElement.getCategories()).contains("dramat");
        assertThat(movieListElement.getCategories()).contains("gangsterski");
        assertThat(movieListElement.getNumberOfSearches()).isEqualTo(0);
        assertThat(movieListElement.getNumberOfPeopleWhoWatched()).isEqualTo(1);
        assertThat(movieListElement.getNumberOfPeopleWhoWantToWatch()).isEqualTo(0);
    }

    @Test
    public void countAverageRating() {
        MovieEntity movieEntity = movieRepository.findMovieEntityById((long) 1);
        double avgRating = helperService.countAverageRating(movieEntity);
        assertThat(avgRating).isEqualTo(10.0);
    }

    @Test
    public void getCover() {
        MovieEntity movieEntity = movieRepository.findMovieEntityById((long) 1);
        ImageDTO cover = helperService.getCover(movieEntity);
        assertThat(cover.getAuthor()).isEqualTo("Paramount Pictures");
        assertThat(cover.getName()).isEqualTo("Ojciec chrzestny");
        assertThat(cover.getSource()).contains("../images/godfather/");
        assertThat(cover.getSource()).endsWith(".jpg");
    }

    @Test
    public void getPictures() {
        MovieEntity movieEntity = movieRepository.findMovieEntityById((long) 1);
        List<ImageDTO> pictures = helperService.getPictures(movieEntity);
        assertThat(pictures.size()).isEqualTo(5);
        for(ImageDTO imageDTO : pictures){
            assertThat(imageDTO.getAuthor()).isEqualTo("Paramount Pictures");
            assertThat(imageDTO.getName()).isEqualTo("Ojciec chrzestny");
            assertThat(imageDTO.getSource()).contains("../images/godfather/");
            assertThat(imageDTO.getSource()).endsWith(".jpg");
        }
    }

    @Test
    public void getCategories() {
        MovieEntity movieEntity = movieRepository.findMovieEntityById((long) 1);
        Hibernate.initialize(movieEntity.getCategories());
        List<String> categories = helperService.getCategories(movieEntity);
        assertThat(categories.size()).isEqualTo(2);
        assertThat(categories).contains("dramat");
        assertThat(categories).contains("gangsterski");
    }

    @Test
    public void getCountries() {
        MovieEntity movieEntity = movieRepository.findMovieEntityById((long) 1);
        List<String> countries = helperService.getCountries(movieEntity);
        assertThat(countries.size()).isEqualTo(1);
        assertThat(countries).contains("USA");
    }

    @Test
    public void getAllCategories() {
        List<String> categories = helperService.getAllCategories();
        assertThat(categories.size()).isEqualTo(6);
        assertThat(categories).contains("dramat");
        assertThat(categories).contains("gangsterski");
        assertThat(categories).contains("dramat");
        assertThat(categories).contains("gangsterski");
        assertThat(categories).contains("dramat");
        assertThat(categories).contains("gangsterski");
    }

    @Test
    public void getAllCountries() {
        List<String> countries = helperService.getAllCountries();
        assertThat(countries.size()).isEqualTo(1);
        assertThat(countries).contains("USA");
    }

}