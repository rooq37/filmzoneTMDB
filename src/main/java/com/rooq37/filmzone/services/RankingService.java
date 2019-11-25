package com.rooq37.filmzone.services;

import com.rooq37.filmzone.dtos.MovieSimpleDTO;
import com.rooq37.filmzone.mappers.MovieSimpleMapper;
import com.rooq37.filmzone.repositories.RatingRepository;
import com.rooq37.filmzone.repositories.ViewRepository;
import info.movito.themoviedbapi.model.MovieDb;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class RankingService {

    @Autowired
    private HelperService helperService;
    @Autowired
    private ViewRepository viewRepository;
    @Autowired
    private RatingRepository ratingRepository;

    public List<MovieSimpleDTO> getMostSearchedMovies(int size){
        List<MovieSimpleDTO> mostSearchedMovies = new ArrayList<>();
        List<Integer> mostSearchedIds = viewRepository.findMostSearchedMovies();
        int sublistMaxSize = Math.min(size, mostSearchedIds.size());
        for(Integer id : mostSearchedIds.subList(0, sublistMaxSize)){
            mostSearchedMovies.add(helperService.getMovieSimpleDTO(id));
        }

        return mostSearchedMovies;
    }

    public List<MovieSimpleDTO> getHighestRatedMovies(int size) {
        List<MovieSimpleDTO> highestRatedMovies = new ArrayList<>();
        List<Integer> highestRatedIds = ratingRepository.findHighestRatedMovies();
        int sublistMaxSize = Math.min(size, highestRatedIds.size());
        for(Integer id : highestRatedIds.subList(0, sublistMaxSize)){
            highestRatedMovies.add(helperService.getMovieSimpleDTO(id));
        }
        return highestRatedMovies;
    }

    public List<MovieSimpleDTO> getMostRatedMovies(int size) {
        List<MovieSimpleDTO> mostRatedMovies = new ArrayList<>();
        List<Integer> mostRatedIds = ratingRepository.findMostRatedMovies();
        int sublistMaxSize = Math.min(size, mostRatedIds.size());
        for(Integer id : mostRatedIds.subList(0, sublistMaxSize)){
            mostRatedMovies.add(helperService.getMovieSimpleDTO(id));
        }
        return mostRatedMovies;
    }
}

