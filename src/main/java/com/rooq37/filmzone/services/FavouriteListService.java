package com.rooq37.filmzone.services;

import com.rooq37.filmzone.dtos.MovieSimpleDTO;
import com.rooq37.filmzone.entities.FavouriteListEntity;
import com.rooq37.filmzone.dtos.MyMoviesDTO;
import com.rooq37.filmzone.mappers.MovieSimpleMapper;
import com.rooq37.filmzone.repositories.FavouriteListRepository;
import info.movito.themoviedbapi.TmdbApi;
import info.movito.themoviedbapi.TmdbMovies;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.support.PagedListHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class FavouriteListService {

    private static final String API_KEY = "5a46d5b61d76c153823d4be68aed3798";

    @Autowired
    private FavouriteListRepository favouriteListRepository;
    @Autowired
    private UserService userService;

    public List<String> getUserAllLists(String userEmail){
        return favouriteListRepository.findFavouriteListEntitiesByUser_Email(userEmail)
                .stream().map(FavouriteListEntity::getName).collect(Collectors.toList());
    }

    public String createFavouriteMovieList(String userEmail, String listName){
        if(listName.isEmpty()) return "Nazwa listy nie może być pusta!";

        List<String> availableLists = getUserAllLists(userEmail);
        if(availableLists.contains(listName)) return "Lista o podanej nazwie już istnieje!";

        FavouriteListEntity fle = new FavouriteListEntity();
        fle.setUser(userService.getUserByEmail(userEmail));
        fle.setName(listName);
        favouriteListRepository.save(fle);
        return null;
    }

    public void addMovieToFavouriteList(int tmdbMovieId, String userEmail, String listName){
        FavouriteListEntity fle = favouriteListRepository.findFavouriteListEntityByNameAndUser_Email(listName, userEmail);
        fle.getTmdbMovieIds().add(tmdbMovieId);
        favouriteListRepository.save(fle);
    }

    public MyMoviesDTO getMyMovies(int movieId, String userEmail){
        List<String> listsContainMovie = favouriteListRepository.findFavouriteListEntitiesByUser_Email(userEmail)
                .stream().filter(favouriteListEntity -> favouriteListEntity.getTmdbMovieIds().contains(movieId))
                .map(FavouriteListEntity::getName).collect(Collectors.toList());

        List<String> availableLists = getUserAllLists(userEmail);
        availableLists.removeAll(listsContainMovie);
        return new MyMoviesDTO(listsContainMovie, availableLists);
    }

    public PagedListHolder<MovieSimpleDTO> getFavouriteList(int currentPage, int pageSize, String userEmail, String listName) {
        if(listName.equals("-1")) return new PagedListHolder<>(Collections.emptyList());

        TmdbApi api = new TmdbApi(API_KEY);
        TmdbMovies movies = api.getMovies();

        FavouriteListEntity fle = favouriteListRepository.findFavouriteListEntityByNameAndUser_Email(listName, userEmail);
        List<MovieSimpleDTO> list = (fle != null) ? fle.getTmdbMovieIds().stream().map(movieId ->
                new MovieSimpleMapper(movies.getMovie(movieId, "pl")).getMovieSimpleDTO()).collect(Collectors.toList()) : Collections.emptyList();

        PagedListHolder<MovieSimpleDTO> pagedListHolder = new PagedListHolder<>(list);
        pagedListHolder.setPageSize(pageSize);
        pagedListHolder.setPage(currentPage);

        return pagedListHolder;
    }

    @Transactional
    public String removeMovieFromList(String userEmail, String listName, int movieId){
        FavouriteListEntity fle = favouriteListRepository.findFavouriteListEntityByNameAndUser_Email(listName, userEmail);
        if(fle != null){
            fle.getTmdbMovieIds().remove(new Integer(movieId));
            favouriteListRepository.save(fle);
            TmdbApi api = new TmdbApi(API_KEY);
            TmdbMovies movies = api.getMovies();
            return "Pomyślnie usunięto film o nazwie " + movies.getMovie(movieId, "pl").getTitle() + " z listy o nazwie " + listName + ".";
        }else{
            return "Lista o nazwie " + listName + " nie istnieje.";
        }
    }

    public String removeList(String userEmail, String listName){
        FavouriteListEntity fle = favouriteListRepository.findFavouriteListEntityByNameAndUser_Email(listName, userEmail);
        favouriteListRepository.delete(fle);
        return "Pomyślnie usunięto listę o nazwie " + listName + ".";
    }

}
