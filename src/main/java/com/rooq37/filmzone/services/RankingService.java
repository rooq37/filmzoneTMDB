package com.rooq37.filmzone.services;

import com.rooq37.filmzone.commons.MovieListElement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RankingService {

    @Autowired
    private HelperService helperService;

    public Page<MovieListElement> getMostSearchedMovies(Pageable pageable, int numberOfMostSearchedMovies){
        List<MovieListElement> movieList = helperService.getAllMovieListElements();

        movieList.sort(MovieListElement.numberOfSearchesComparator.reversed());
        int listSize = numberOfMostSearchedMovies > movieList.size() ? movieList.size() : numberOfMostSearchedMovies;
        movieList = movieList.subList(0, listSize);

        return new PageImpl<>(movieList, pageable, movieList.size());
    }

    public Page<MovieListElement> getHighestRatedMovies(Pageable pageable, int numberOfHighestRatedMovies){
        List<MovieListElement> movieList = helperService.getAllMovieListElements();

        movieList.sort(MovieListElement.avgUsersRatingComparator.reversed());
        int listSize = numberOfHighestRatedMovies > movieList.size() ? movieList.size() : numberOfHighestRatedMovies;
        movieList = movieList.subList(0, listSize);

        return new PageImpl<>(movieList, pageable, movieList.size());
    }

    public Page<MovieListElement> getMostRatedMovies(Pageable pageable, int numberOfMostRatedMovies){
        List<MovieListElement> movieList = helperService.getAllMovieListElements();

        movieList.sort(MovieListElement.numberOfPeopleWhoWatchedComparator.reversed());
        int listSize = numberOfMostRatedMovies > movieList.size() ? movieList.size() : numberOfMostRatedMovies;
        movieList = movieList.subList(0, listSize);

        return new PageImpl<>(movieList, pageable, movieList.size());
    }

    public Page<MovieListElement> getMostExpectedMovies(Pageable pageable, int numberOfMostExpectedMovies){
        List<MovieListElement> movieList = helperService.getAllMovieListElements();

        movieList.sort(MovieListElement.numberOfPeopleWhoWantToWatchComparator.reversed());
        int listSize = numberOfMostExpectedMovies > movieList.size() ? movieList.size() : numberOfMostExpectedMovies;
        movieList = movieList.subList(0, listSize);

        return new PageImpl<>(movieList, pageable, movieList.size());
    }

}
