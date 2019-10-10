package com.rooq37.filmzone.junit.services;

import com.rooq37.filmzone.FilmzoneApplication;
import com.rooq37.filmzone.commons.MovieListElement;
import com.rooq37.filmzone.services.RankingService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = FilmzoneApplication.class)
public class RankingServiceTest {

    @Autowired
    private RankingService rankingService;

    @Test
    public void getMostSearchedMovies() {
        List<MovieListElement> movies;

        movies = rankingService.getMostSearchedMovies(PageRequest.of(0, Integer.MAX_VALUE), 2).getContent();
        assertThat(movies.size()).isEqualTo(2);
        assertThat(movies.get(0).getTitle()).isEqualTo("Ojciec chrzestny");
        assertThat(movies.get(1).getTitle()).isEqualTo("Władca Pierścieni: Drużyna Pierścienia");

        movies = rankingService.getMostSearchedMovies(PageRequest.of(0, Integer.MAX_VALUE), 50).getContent();
        assertThat(movies.size()).isEqualTo(5);

        movies = rankingService.getMostSearchedMovies(PageRequest.of(0, Integer.MAX_VALUE), 3).getContent();
        assertThat(movies.size()).isEqualTo(3);
    }

    @Test
    public void getHighestRatedMovies() {
        List<MovieListElement> movies;

        movies = rankingService.getHighestRatedMovies(PageRequest.of(0, Integer.MAX_VALUE), 2).getContent();
        assertThat(movies.size()).isEqualTo(2);
        assertThat(movies.get(0).getTitle()).isEqualTo("Ojciec chrzestny");
        assertThat(movies.get(1).getTitle()).isEqualTo("Władca Pierścieni: Drużyna Pierścienia");

        movies = rankingService.getHighestRatedMovies(PageRequest.of(0, Integer.MAX_VALUE), 50).getContent();
        assertThat(movies.size()).isEqualTo(5);

        movies = rankingService.getHighestRatedMovies(PageRequest.of(0, Integer.MAX_VALUE), 3).getContent();
        assertThat(movies.size()).isEqualTo(3);
    }

    @Test
    public void getMostRatedMovies() {
        List<MovieListElement> movies;

        movies = rankingService.getMostRatedMovies(PageRequest.of(0, Integer.MAX_VALUE), 2).getContent();
        assertThat(movies.size()).isEqualTo(2);
        assertThat(movies.get(0).getTitle()).isEqualTo("Ojciec chrzestny");
        assertThat(movies.get(1).getTitle()).isEqualTo("Władca Pierścieni: Drużyna Pierścienia");

        movies = rankingService.getMostRatedMovies(PageRequest.of(0, Integer.MAX_VALUE), 50).getContent();
        assertThat(movies.size()).isEqualTo(5);

        movies = rankingService.getMostRatedMovies(PageRequest.of(0, Integer.MAX_VALUE), 3).getContent();
        assertThat(movies.size()).isEqualTo(3);
    }

    @Test
    public void getMostExpectedMovies() {
        List<MovieListElement> movies;

        movies = rankingService.getMostExpectedMovies(PageRequest.of(0, Integer.MAX_VALUE), 2).getContent();
        assertThat(movies.size()).isEqualTo(2);
        assertThat(movies.get(0).getTitle()).isEqualTo("Ojciec chrzestny");
        assertThat(movies.get(1).getTitle()).isEqualTo("Władca Pierścieni: Drużyna Pierścienia");

        movies = rankingService.getMostExpectedMovies(PageRequest.of(0, Integer.MAX_VALUE), 50).getContent();
        assertThat(movies.size()).isEqualTo(5);

        movies = rankingService.getMostExpectedMovies(PageRequest.of(0, Integer.MAX_VALUE), 3).getContent();
        assertThat(movies.size()).isEqualTo(3);
    }
}