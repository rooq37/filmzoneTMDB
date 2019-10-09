package com.rooq37.filmzone.junit.services;

import com.rooq37.filmzone.entities.MovieEntity;
import com.rooq37.filmzone.entities.ViewEntity;
import com.rooq37.filmzone.home.HomeForm;
import com.rooq37.filmzone.repositories.MovieRepository;
import com.rooq37.filmzone.repositories.RatingRepository;
import com.rooq37.filmzone.repositories.UserRepository;
import com.rooq37.filmzone.repositories.ViewRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class ViewService {

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

    public Map<MovieEntity, Integer> getMostPopularMoviesLastWeek(int numberOfMostPopularMovies){
        Map<MovieEntity, Integer> movies = new HashMap<>();

        Date date = getDateOneWeekAgo();

        for(MovieEntity movie : movieRepository.findAll())
            movies.put(movie, viewRepository.countByMovieAndDateAfter(movie, date));

        return getMoviesMapSortedByValue(movies, numberOfMostPopularMovies);
    }

    private Map<MovieEntity, Integer> getMoviesMapSortedByValue(Map<MovieEntity, Integer> moviesMap, int maxSize){
        List<Map.Entry<MovieEntity, Integer>> list = new ArrayList<>(moviesMap.entrySet());
        list.sort(Map.Entry.comparingByValue());

        Map<MovieEntity, Integer> resultMap = new HashMap<>();
        for(int i = 0; i < maxSize && i < list.size(); i++)
            resultMap.put(list.get(i).getKey(), list.get(i).getValue());

        return resultMap;
    }

    public HomeForm getHome(){
        HomeForm homeForm = new HomeForm();

        homeForm.setNumberOfMoviesInDatabase(movieRepository.count());
        homeForm.setNumberOfRegisteredAccounts(userRepository.count());
        homeForm.setNumberOfActiveUsers(userRepository.count());
        homeForm.setNumberOfRatings(ratingRepository.count());
        homeForm.setNumberOfSearches(viewRepository.count());

        Date date = getDateOneWeekAgo();

        homeForm.setNumberOfNewAccountsLastWeek(userRepository.count());
        homeForm.setNumberOfRatingsLastWeek(ratingRepository.countByDateAfter(date));
        homeForm.setNumberOfSearchesLastWeek(viewRepository.countByDateAfter(date));

        return homeForm;
    }

    private Date getDateOneWeekAgo(){
        Calendar cal = Calendar.getInstance();
        cal.setTime(new Date());
        cal.add(Calendar.DATE, -7);
        return cal.getTime();
    }

}
