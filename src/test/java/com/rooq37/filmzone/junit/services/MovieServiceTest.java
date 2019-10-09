package com.rooq37.filmzone.junit.services;

import com.rooq37.filmzone.FilmzoneApplication;
import com.rooq37.filmzone.commons.MovieListElement;
import com.rooq37.filmzone.entities.CommentEntity;
import com.rooq37.filmzone.movies.movieDetails.MovieCast;
import com.rooq37.filmzone.movies.movieDetails.MovieMedia;
import com.rooq37.filmzone.movies.movieDetails.MovieRating;
import com.rooq37.filmzone.movies.movieDetails.MovieSummary;
import com.rooq37.filmzone.junit.movies.MoviesFilterForm;
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
public class MovieServiceTest {

    @Autowired
    private MovieService movieService;

    @Test
    public void getMovieSummary() {
        MovieSummary movieSummary = movieService.getMovieSummary(1L);
        assertThat(movieSummary.getYear()).isEqualTo(1972);
        assertThat(movieSummary.getTitle()).isEqualTo("Ojciec chrzestny");
        assertThat(movieSummary.getScenario()).isEqualTo("Francis Ford Coppola, Mario Puzo");
        assertThat(movieSummary.getDuration()).isEqualTo(175);
        assertThat(movieSummary.getDirector()).isEqualTo("Francis Ford Coppola");
        assertThat(movieSummary.getDescription()).isEqualTo("Starzejący się patriarcha dynastii przestępczości zorganizowanej przekazuje kontrolę nad swoim tajnym imperium niechętnemu synowi.");
        assertThat(movieSummary.getCover().getName()).isEqualTo("Ojciec chrzestny");
        assertThat(movieSummary.getCountry()).isEqualTo("USA");
        assertThat(movieSummary.getCategories().size()).isEqualTo(2);
        assertThat(movieSummary.getCategories()).contains("dramat");
        assertThat(movieSummary.getCategories()).contains("gangsterski");
        assertThat(movieSummary.getAvgUsersRating()).isEqualTo("10,0");
    }

    @Test
    public void getMovieRating() {
        MovieRating movieRating = movieService.getMovieRating(1L);
        assertThat(movieRating.getAvg()).isEqualTo("10,00");
        assertThat(movieRating.getNumberOfPeopleWhoWantToWatch()).isEqualTo("0");
        assertThat(movieRating.getNumberOfPeopleWhoWatched()).isEqualTo("1");
    }

    @Test
    public void getMovieMedia() {
        MovieMedia movieMedia = movieService.getMovieMedia(1L);
        assertThat(movieMedia.getPhotos().size()).isEqualTo(5);
        assertThat(movieMedia.getPhotos().get(0).getAuthor()).isEqualTo("Paramount Pictures");
        assertThat(movieMedia.getPhotos().get(0).getName()).isEqualTo("Ojciec chrzestny");
        assertThat(movieMedia.getTrailerLink()).isEqualTo("https://www.youtube.com/embed/sY1S34973zA");
    }

    @Test
    public void getMovieCast() {
        MovieCast movieCast = movieService.getMovieCast(1L);
        assertThat(movieCast.getCast().size()).isEqualTo(5);
        assertThat(movieCast.getCast().get(0).getCharacterName()).isEqualTo("Don Vito Corleone");
        assertThat(movieCast.getCast().get(0).getActorName()).isEqualTo("Marlon Brando");
        assertThat(movieCast.getCast().get(1).getCharacterName()).isEqualTo("Michael Corleone");
        assertThat(movieCast.getCast().get(1).getActorName()).isEqualTo("Al Pacino");
    }

    @Test
    public void getMovieComments() {
        List<CommentEntity> comments = movieService.getMovieComments(1L, PageRequest.of(0, Integer.MAX_VALUE)).getContent();
        assertThat(comments.size()).isEqualTo(11);

        comments = movieService.getMovieComments(1L, PageRequest.of(0, 5)).getContent();
        assertThat(comments.size()).isEqualTo(5);
    }

    @Test
    public void getMovieListElementsSortByRatingDescending() {
        List<MovieListElement> movies = movieService.getMovieListElements(PageRequest.of(0, Integer.MAX_VALUE), 1, new MoviesFilterForm()).getContent();
        assertThat(movies.get(0).getTitle()).isEqualTo("Ojciec chrzestny");
        assertThat(movies.get(1).getTitle()).isEqualTo("Władca Pierścieni: Drużyna Pierścienia");
        assertThat(movies.get(2).getTitle()).isEqualTo("Ali");
    }

    @Test
    public void getMovieListElementsSortByRatingAscending() {
        List<MovieListElement> movies = movieService.getMovieListElements(PageRequest.of(0, Integer.MAX_VALUE), 2, new MoviesFilterForm()).getContent();
        assertThat(movies.get(0).getTitle()).isEqualTo("Ali");
        assertThat(movies.get(1).getTitle()).isEqualTo("Blow");
        assertThat(movies.get(2).getTitle()).isEqualTo("Harry Potter i Kamień Filozoficzny");
    }

    @Test
    public void getMovieListElementsSortByNameDescending() {
        List<MovieListElement> movies = movieService.getMovieListElements(PageRequest.of(0, Integer.MAX_VALUE), 3, new MoviesFilterForm()).getContent();
        assertThat(movies.get(0).getTitle()).isEqualTo("Władca Pierścieni: Drużyna Pierścienia");
        assertThat(movies.get(1).getTitle()).isEqualTo("Ojciec chrzestny");
        assertThat(movies.get(2).getTitle()).isEqualTo("Harry Potter i Kamień Filozoficzny");
    }

    @Test
    public void getMovieListElementsSortByNameAscending() {
        List<MovieListElement> movies = movieService.getMovieListElements(PageRequest.of(0, Integer.MAX_VALUE), 4, new MoviesFilterForm()).getContent();
        assertThat(movies.get(0).getTitle()).isEqualTo("Ali");
        assertThat(movies.get(1).getTitle()).isEqualTo("Blow");
        assertThat(movies.get(2).getTitle()).isEqualTo("Harry Potter i Kamień Filozoficzny");
    }

    @Test
    public void getMovieListElementsSortByYearDescending() {
        List<MovieListElement> movies = movieService.getMovieListElements(PageRequest.of(0, Integer.MAX_VALUE), 5, new MoviesFilterForm()).getContent();
        assertThat(movies.get(0).getTitle()).isEqualTo("Władca Pierścieni: Drużyna Pierścienia");
        assertThat(movies.get(1).getTitle()).isEqualTo("Ali");
        assertThat(movies.get(2).getTitle()).isEqualTo("Blow");
    }

    @Test
    public void getMovieListElementsSortByYearAscending() {
        List<MovieListElement> movies = movieService.getMovieListElements(PageRequest.of(0, Integer.MAX_VALUE), 6, new MoviesFilterForm()).getContent();
        assertThat(movies.get(0).getTitle()).isEqualTo("Ojciec chrzestny");
        assertThat(movies.get(1).getTitle()).isEqualTo("Władca Pierścieni: Drużyna Pierścienia");
        assertThat(movies.get(2).getTitle()).isEqualTo("Ali");
    }

    @Test
    public void getMovieListElementsSortByNumberOfRatingsDescending() {
        List<MovieListElement> movies = movieService.getMovieListElements(PageRequest.of(0, Integer.MAX_VALUE), 7, new MoviesFilterForm()).getContent();
        assertThat(movies.get(0).getTitle()).isEqualTo("Ojciec chrzestny");
        assertThat(movies.get(1).getTitle()).isEqualTo("Władca Pierścieni: Drużyna Pierścienia");
        assertThat(movies.get(2).getTitle()).isEqualTo("Ali");
    }

    @Test
    public void getMovieListElementsSortByNumberOfRatingsAscending() {
        List<MovieListElement> movies = movieService.getMovieListElements(PageRequest.of(0, Integer.MAX_VALUE), 8, new MoviesFilterForm()).getContent();
        assertThat(movies.get(0).getTitle()).isEqualTo("Ojciec chrzestny");
        assertThat(movies.get(1).getTitle()).isEqualTo("Władca Pierścieni: Drużyna Pierścienia");
        assertThat(movies.get(2).getTitle()).isEqualTo("Ali");
    }

    @Test
    public void getMovieListElementsFilterByName() {
        MoviesFilterForm moviesFilterForm = new MoviesFilterForm();
        List<MovieListElement> movies;

        moviesFilterForm.setName("O");
        movies = movieService.getMovieListElements(PageRequest.of(0, Integer.MAX_VALUE), 4, moviesFilterForm).getContent();
        assertThat(movies.size()).isEqualTo(3);
        assertThat(movies.get(0).getTitle()).isEqualTo("Blow");

        moviesFilterForm.setName("blabla");
        movies = movieService.getMovieListElements(PageRequest.of(0, Integer.MAX_VALUE), 4, moviesFilterForm).getContent();
        assertThat(movies.size()).isEqualTo(0);

        moviesFilterForm.setName("drużyna");
        movies = movieService.getMovieListElements(PageRequest.of(0, Integer.MAX_VALUE), 4, moviesFilterForm).getContent();
        assertThat(movies.size()).isEqualTo(1);
        assertThat(movies.get(0).getTitle()).isEqualTo("Władca Pierścieni: Drużyna Pierścienia");

        moviesFilterForm.setName("ojciec");
        movies = movieService.getMovieListElements(PageRequest.of(0, Integer.MAX_VALUE), 4, moviesFilterForm).getContent();
        assertThat(movies.size()).isEqualTo(1);
        assertThat(movies.get(0).getTitle()).isEqualTo("Ojciec chrzestny");

        moviesFilterForm.setName("A");
        movies = movieService.getMovieListElements(PageRequest.of(0, Integer.MAX_VALUE), 4, moviesFilterForm).getContent();
        assertThat(movies.size()).isEqualTo(3);
        assertThat(movies.get(0).getTitle()).isEqualTo("Ali");
        assertThat(movies.get(1).getTitle()).isEqualTo("Harry Potter i Kamień Filozoficzny");
    }

    @Test
    public void getMovieListElementsFilterByYear() {
        MoviesFilterForm moviesFilterForm = new MoviesFilterForm();
        List<MovieListElement> movies;

        moviesFilterForm.setMinYear(1972);
        moviesFilterForm.setMaxYear(1972);
        movies = movieService.getMovieListElements(PageRequest.of(0, Integer.MAX_VALUE), 4, moviesFilterForm).getContent();
        assertThat(movies.size()).isEqualTo(1);
        assertThat(movies.get(0).getTitle()).isEqualTo("Ojciec chrzestny");

        moviesFilterForm.setMinYear(1950);
        moviesFilterForm.setMaxYear(1960);
        movies = movieService.getMovieListElements(PageRequest.of(0, Integer.MAX_VALUE), 4, moviesFilterForm).getContent();
        assertThat(movies.size()).isEqualTo(0);

        moviesFilterForm.setMinYear(2001);
        moviesFilterForm.setMaxYear(2002);
        movies = movieService.getMovieListElements(PageRequest.of(0, Integer.MAX_VALUE), 4, moviesFilterForm).getContent();
        assertThat(movies.size()).isEqualTo(4);
        assertThat(movies.get(0).getTitle()).isEqualTo("Ali");
        assertThat(movies.get(1).getTitle()).isEqualTo("Blow");


        moviesFilterForm.setMinYear(2000);
        moviesFilterForm.setMaxYear(2001);
        movies = movieService.getMovieListElements(PageRequest.of(0, Integer.MAX_VALUE), 4, moviesFilterForm).getContent();
        assertThat(movies.size()).isEqualTo(4);
        assertThat(movies.get(0).getTitle()).isEqualTo("Ali");
        assertThat(movies.get(1).getTitle()).isEqualTo("Blow");
    }

    @Test
    public void getMovieListElementsFilterByCategories() {
        MoviesFilterForm moviesFilterForm = new MoviesFilterForm();
        List<MovieListElement> movies;

        moviesFilterForm.setSelectedCategories("dramat");
        movies = movieService.getMovieListElements(PageRequest.of(0, Integer.MAX_VALUE), 4, moviesFilterForm).getContent();
        assertThat(movies.size()).isEqualTo(2);
        assertThat(movies.get(0).getTitle()).isEqualTo("Blow");

        moviesFilterForm.setSelectedCategories("dramat, gangsterski, historyczny");
        movies = movieService.getMovieListElements(PageRequest.of(0, Integer.MAX_VALUE), 4, moviesFilterForm).getContent();
        assertThat(movies.size()).isEqualTo(0);

        moviesFilterForm.setSelectedCategories("brak kategorii");
        movies = movieService.getMovieListElements(PageRequest.of(0, Integer.MAX_VALUE), 4, moviesFilterForm).getContent();
        assertThat(movies.size()).isEqualTo(0);

        moviesFilterForm.setSelectedCategories("");
        movies = movieService.getMovieListElements(PageRequest.of(0, Integer.MAX_VALUE), 4, moviesFilterForm).getContent();
        assertThat(movies.size()).isEqualTo(5);
        assertThat(movies.get(0).getTitle()).isEqualTo("Ali");
        assertThat(movies.get(1).getTitle()).isEqualTo("Blow");
        assertThat(movies.get(2).getTitle()).isEqualTo("Harry Potter i Kamień Filozoficzny");


        moviesFilterForm.setSelectedCategories("dramat, gangsterski");
        movies = movieService.getMovieListElements(PageRequest.of(0, Integer.MAX_VALUE), 4, moviesFilterForm).getContent();
        assertThat(movies.size()).isEqualTo(2);
        assertThat(movies.get(0).getTitle()).isEqualTo("Blow");
    }

    @Test
    public void getMovieListElementsFilterByCountries() {
        MoviesFilterForm moviesFilterForm = new MoviesFilterForm();
        List<MovieListElement> movies;

        moviesFilterForm.setSelectedCountries("USA");
        movies = movieService.getMovieListElements(PageRequest.of(0, Integer.MAX_VALUE), 4, moviesFilterForm).getContent();
        assertThat(movies.size()).isEqualTo(5);
        assertThat(movies.get(0).getTitle()).isEqualTo("Ali");
        assertThat(movies.get(1).getTitle()).isEqualTo("Blow");
        assertThat(movies.get(2).getTitle()).isEqualTo("Harry Potter i Kamień Filozoficzny");

        moviesFilterForm.setSelectedCountries("");
        movies = movieService.getMovieListElements(PageRequest.of(0, Integer.MAX_VALUE), 4, moviesFilterForm).getContent();
        assertThat(movies.size()).isEqualTo(5);
        assertThat(movies.get(0).getTitle()).isEqualTo("Ali");
        assertThat(movies.get(1).getTitle()).isEqualTo("Blow");
        assertThat(movies.get(2).getTitle()).isEqualTo("Harry Potter i Kamień Filozoficzny");


        moviesFilterForm.setSelectedCategories("BRAK");
        movies = movieService.getMovieListElements(PageRequest.of(0, Integer.MAX_VALUE), 4, moviesFilterForm).getContent();
        assertThat(movies.size()).isEqualTo(0);

        moviesFilterForm.setSelectedCategories("USA, Kanada");
        movies = movieService.getMovieListElements(PageRequest.of(0, Integer.MAX_VALUE), 4, moviesFilterForm).getContent();
        assertThat(movies.size()).isEqualTo(0);
    }

    @Test
    public void getMovieListElementsFilterByRating() {
        MoviesFilterForm moviesFilterForm = new MoviesFilterForm();
        List<MovieListElement> movies;

        moviesFilterForm.setMinRate(10);
        moviesFilterForm.setMaxRate(10);
        movies = movieService.getMovieListElements(PageRequest.of(0, Integer.MAX_VALUE), 4, moviesFilterForm).getContent();
        assertThat(movies.size()).isEqualTo(1);
        assertThat(movies.get(0).getTitle()).isEqualTo("Ojciec chrzestny");

        moviesFilterForm.setMinRate(1);
        moviesFilterForm.setMaxRate(7);
        movies = movieService.getMovieListElements(PageRequest.of(0, Integer.MAX_VALUE), 4, moviesFilterForm).getContent();
        assertThat(movies.size()).isEqualTo(0);

        moviesFilterForm.setMinRate(8);
        moviesFilterForm.setMaxRate(9);
        movies = movieService.getMovieListElements(PageRequest.of(0, Integer.MAX_VALUE), 4, moviesFilterForm).getContent();
        assertThat(movies.size()).isEqualTo(4);
        assertThat(movies.get(0).getTitle()).isEqualTo("Ali");
        assertThat(movies.get(1).getTitle()).isEqualTo("Blow");


        moviesFilterForm.setMinRate(7);
        moviesFilterForm.setMaxRate(8);
        movies = movieService.getMovieListElements(PageRequest.of(0, Integer.MAX_VALUE), 4, moviesFilterForm).getContent();
        assertThat(movies.size()).isEqualTo(3);
        assertThat(movies.get(0).getTitle()).isEqualTo("Ali");
    }

}