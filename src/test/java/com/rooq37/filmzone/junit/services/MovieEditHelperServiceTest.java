package com.rooq37.filmzone.junit.services;

import com.rooq37.filmzone.FilmzoneApplication;
import com.rooq37.filmzone.dtos.ImageDTO;
import com.rooq37.filmzone.entities.MovieEntity;
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