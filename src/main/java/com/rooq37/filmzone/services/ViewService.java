package com.rooq37.filmzone.services;

import com.rooq37.filmzone.dtos.MovieSimpleDTO;
import com.rooq37.filmzone.entities.MovieEntity;
import com.rooq37.filmzone.entities.ViewEntity;
import com.rooq37.filmzone.dtos.HomeDTO;
import com.rooq37.filmzone.mappers.MovieSimpleMapper;
import com.rooq37.filmzone.repositories.MovieRepository;
import com.rooq37.filmzone.repositories.RatingRepository;
import com.rooq37.filmzone.repositories.UserRepository;
import com.rooq37.filmzone.repositories.ViewRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class ViewService {

    private static final int NUMBER_OF_MOVIES_IN_CAROUSEL = 5;

    @Autowired
    private ViewRepository viewRepository;
    @Autowired
    private MovieRepository movieRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private RatingRepository ratingRepository;

    public void saveViewLog(Long movieId){
        MovieEntity movieEntity = movieRepository.findMovieEntityById(movieId);
        ViewEntity viewEntity = new ViewEntity();
        viewEntity.setMovie(movieEntity);
        viewEntity.setDate(new Date());
        viewRepository.save(viewEntity);
    }

    private List<MovieSimpleDTO> getMostPopularMoviesLastWeek(){
        Date date = getDateOneWeekAgo();
        List<MovieSimpleDTO> mostPopularMovies = new ArrayList<>();
        for(MovieEntity movie : movieRepository.findAll()){
            MovieSimpleMapper mapper = new MovieSimpleMapper(movie);
            MovieSimpleDTO movDTO = mapper.getMovieSimpleDTO();
            movDTO.setNumberOfSearches(viewRepository.countByMovieAndDateAfter(movie, date));
            mostPopularMovies.add(movDTO);
        }

        mostPopularMovies.sort(Comparator.comparingLong(MovieSimpleDTO::getNumberOfSearches).reversed());
        return mostPopularMovies.subList(0, NUMBER_OF_MOVIES_IN_CAROUSEL);
    }

    public HomeDTO getHome(){
        HomeDTO homeDTO = new HomeDTO();

        homeDTO.setMostPopularMovies(getMostPopularMoviesLastWeek());
        homeDTO.setNumberOfMoviesInDatabase(movieRepository.count());
        homeDTO.setNumberOfRegisteredAccounts(userRepository.count());
        homeDTO.setNumberOfActiveUsers(userRepository.count());
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
