package com.rooq37.filmzone.services;

import com.rooq37.filmzone.dtos.MovieSimpleDTO;
import com.rooq37.filmzone.entities.ViewEntity;
import com.rooq37.filmzone.dtos.HomeDTO;
import com.rooq37.filmzone.repositories.RatingRepository;
import com.rooq37.filmzone.repositories.UserRepository;
import com.rooq37.filmzone.repositories.ViewRepository;
import info.movito.themoviedbapi.TmdbApi;
import info.movito.themoviedbapi.TmdbMovies;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class ViewService {

    private static final String API_KEY = "5a46d5b61d76c153823d4be68aed3798";

    private static final int NUMBER_OF_MOVIES_IN_CAROUSEL = 5;

    @Autowired
    private ViewRepository viewRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private RatingRepository ratingRepository;
    @Autowired
    private HelperService helperService;

    public void saveViewLog(int tmdbMovieId){
        ViewEntity viewEntity = new ViewEntity();
        viewEntity.setTmdbMovieId(tmdbMovieId);
        viewEntity.setDate(new Date());
        viewRepository.save(viewEntity);
    }

    private List<MovieSimpleDTO> getMostPopularMoviesLastWeek(){
        Date date = getDateOneWeekAgo();
        TmdbApi api = new TmdbApi(API_KEY);
        TmdbMovies movies = api.getMovies();
        List<MovieSimpleDTO> mostPopularMovies = new ArrayList<>();
        List<Integer> mostSearchedIds = viewRepository.findMostSearchedMovies(date);
        int sublistMaxSize = Math.min(NUMBER_OF_MOVIES_IN_CAROUSEL, mostSearchedIds.size());
        for(Integer id : mostSearchedIds.subList(0, sublistMaxSize)){
            MovieSimpleDTO movDTO = helperService.getMovieSimpleDTO(id);
            movDTO.setFzNumberOfSearches(viewRepository.countByTmdbMovieIdAndDateAfter(id, date));
            mostPopularMovies.add(movDTO);
        }

        return mostPopularMovies;
    }

    public HomeDTO getHome(){
        HomeDTO homeDTO = new HomeDTO();

        homeDTO.setMostPopularMovies(getMostPopularMoviesLastWeek());

        homeDTO.setNumberOfRegisteredAccounts(userRepository.count());
        homeDTO.setNumberOfRatings(ratingRepository.count());
        homeDTO.setNumberOfSearches(viewRepository.count());

        Date date = getDateOneWeekAgo();

        homeDTO.setNumberOfNewAccountsLastWeek(userRepository.countByRegisterDateAfter(date));
        homeDTO.setNumberOfRatingsLastWeek(ratingRepository.countByDateAfter(date));
        homeDTO.setNumberOfSearchesLastWeek(viewRepository.countByDateAfter(date));

        return homeDTO;
    }

    private Date getDateOneWeekAgo(){
        Calendar cal = Calendar.getInstance();
        cal.setTime(new Date());
        cal.add(Calendar.DATE, -7);
        return cal.getTime();
    }

}
