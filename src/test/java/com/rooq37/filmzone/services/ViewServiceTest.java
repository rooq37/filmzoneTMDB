package com.rooq37.filmzone.services;

import com.rooq37.filmzone.FilmzoneApplication;
import com.rooq37.filmzone.entities.MovieEntity;
import com.rooq37.filmzone.home.HomeForm;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.transaction.Transactional;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = FilmzoneApplication.class)
@Transactional
public class ViewServiceTest {

    @Autowired
    private ViewService viewService;

    @Test
    public void saveViewLogAndGetMostPopularMoviesLastWeek() {
        for(int i = 0; i < 50; i++)
            viewService.saveViewLog(1L);

        for(int i = 0; i < 26; i++)
            viewService.saveViewLog(2L);

        Map<MovieEntity, Integer> movies;

        movies = viewService.getMostPopularMoviesLastWeek(2);
        assertThat(movies.size()).isEqualTo(2);

        movies = viewService.getMostPopularMoviesLastWeek(5);
        assertThat(movies.size()).isEqualTo(3);

        movies = viewService.getMostPopularMoviesLastWeek(3);
        assertThat(movies.size()).isEqualTo(3);

        for(Map.Entry<MovieEntity, Integer> movie : movies.entrySet()){
            if(movie.getKey().getTitle().equals("Ojciec chrzestny")) assertThat(movie.getValue()).isEqualTo(50);
            if(movie.getKey().getTitle().equals("Władca Pierścieni: Drużyna Pierścienia")) assertThat(movie.getValue()).isEqualTo(26);
            if(movie.getKey().getTitle().equals("Ali")) assertThat(movie.getValue()).isEqualTo(0);
        }
    }

    @Test
    public void getHome() {
        HomeForm homeForm = viewService.getHome();
        assertThat(homeForm.getNumberOfActiveUsers()).isEqualTo(1L);
        assertThat(homeForm.getNumberOfMoviesInDatabase()).isEqualTo(3L);
        assertThat(homeForm.getNumberOfNewAccountsLastWeek()).isEqualTo(1L);
        assertThat(homeForm.getNumberOfRatings()).isEqualTo(3L);
        assertThat(homeForm.getNumberOfRatingsLastWeek()).isEqualTo(1L);
        assertThat(homeForm.getNumberOfRegisteredAccounts()).isEqualTo(1L);
        assertThat(homeForm.getNumberOfSearches()).isEqualTo(0L);
        assertThat(homeForm.getNumberOfSearchesLastWeek()).isEqualTo(0L);
    }
}