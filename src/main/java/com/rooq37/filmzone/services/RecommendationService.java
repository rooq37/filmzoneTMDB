package com.rooq37.filmzone.services;

import com.rooq37.filmzone.entities.FavouriteListEntity;
import com.rooq37.filmzone.recommendations.RecommendationEngine;
import com.rooq37.filmzone.repositories.FavouriteListRepository;
import info.movito.themoviedbapi.TmdbApi;
import info.movito.themoviedbapi.TmdbMovies;
import info.movito.themoviedbapi.model.MovieDb;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class RecommendationService {

    private static final String API_KEY = "5a46d5b61d76c153823d4be68aed3798";

    @Autowired
    private FavouriteListRepository favouriteListRepository;

    @Async("AsyncTaskExecutor")
    public void findSimilarMovies(String userEmail, String listName) {
        FavouriteListEntity fle = favouriteListRepository.findFavouriteListEntityByNameAndUser_Email(listName, userEmail);
        if(fle != null){
            TmdbApi api = new TmdbApi(API_KEY);
            TmdbMovies movies = api.getMovies();
            List<MovieDb> movieDbList = new ArrayList<>();
            List<MovieDb> similarMovies = new ArrayList<>();
            for (Integer id : fle.getTmdbMovieIds()) {
                movieDbList.add(movies.getMovie(id, "pl", TmdbMovies.MovieMethod.similar, TmdbMovies.MovieMethod.keywords));
            }
            for (MovieDb movie : movieDbList) {
                for (MovieDb similar : movie.getSimilarMovies())
                    similarMovies.add(movies.getMovie(similar.getId(), "pl", TmdbMovies.MovieMethod.keywords));
            }
            RecommendationEngine re = new RecommendationEngine(movieDbList, similarMovies, 8);
            fle.setRecommendationIds(re.getRecommendations());
            favouriteListRepository.save(fle);
        }
    }

}
