package com.rooq37.filmzone.services;

import com.rooq37.filmzone.controllers.movies.MovieDetailController;
import com.rooq37.filmzone.dtos.MovieSimpleDTO;
import com.rooq37.filmzone.entities.FavouriteListEntity;
import com.rooq37.filmzone.entities.MovieEntity;
import com.rooq37.filmzone.entities.RatingEntity;
import com.rooq37.filmzone.mappers.MovieSimpleMapper;
import com.rooq37.filmzone.dtos.MyMoviesDTO;
import com.rooq37.filmzone.repositories.FavouriteListRepository;
import com.rooq37.filmzone.repositories.MovieRepository;
import com.rooq37.filmzone.repositories.RatingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.support.PagedListHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class FavouriteListService {

    @Autowired
    private FavouriteListRepository favouriteListRepository;
    @Autowired
    private UserService userService;
    @Autowired
    private MovieRepository movieRepository;
    @Autowired
    private RatingRepository ratingRepository;

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
        fle.setMovies(new HashSet<>());
        favouriteListRepository.save(fle);
        return null;
    }

    public void addMovieToFavouriteList(Long movieId, String userEmail, String listName){
        FavouriteListEntity fle = favouriteListRepository.findFavouriteListEntityByNameAndUser_Email(listName, userEmail);
        fle.getMovies().add(movieRepository.findMovieEntityById(movieId));
        favouriteListRepository.save(fle);
    }

    public MyMoviesDTO getMyMovies(Long movieId, String userEmail){
        MovieEntity movieEntity = movieRepository.findMovieEntityById(movieId);
        List<String> listsContainMovie = movieEntity.getFavouriteLists()
                .stream().map(FavouriteListEntity::getName).collect(Collectors.toList());
        List<String> availableLists = getUserAllLists(userEmail);
        availableLists.removeAll(listsContainMovie);
        return new MyMoviesDTO(listsContainMovie, availableLists);
    }

    public PagedListHolder<MovieSimpleDTO> getFavouriteList(int currentPage, int pageSize, String userEmail, String listName) {
        if(listName.equals("-1")) return new PagedListHolder<>(Collections.emptyList());

        FavouriteListEntity fle = favouriteListRepository.findFavouriteListEntityByNameAndUser_Email(listName, userEmail);
        List<MovieSimpleDTO> list = (fle != null) ? fle.getMovies().stream().map(movieEntity ->
                new MovieSimpleMapper(movieEntity).getMovieSimpleDTO()).collect(Collectors.toList()) : Collections.emptyList();
        list.sort(Comparator.comparingLong(MovieSimpleDTO::getMovieId));

        PagedListHolder<MovieSimpleDTO> pagedListHolder = new PagedListHolder<>(list);
        pagedListHolder.setPageSize(pageSize);
        pagedListHolder.setPage(currentPage);

        return pagedListHolder;
    }

    @Transactional
    public String removeMovieFromList(String userEmail, String listName, Long movieId){
        FavouriteListEntity fle = favouriteListRepository.findFavouriteListEntityByNameAndUser_Email(listName, userEmail);
        if(fle != null){
            MovieEntity movie = movieRepository.findMovieEntityById(movieId);
            fle.getMovies().remove(movie);
            favouriteListRepository.save(fle);
            if(listName.equals(MovieDetailController.I_WANT_TO_WATCH_LIST)){
                RatingEntity rating = ratingRepository.findByUser_EmailAndMovie_Id(userEmail, movieId);
                if(rating != null) ratingRepository.delete(rating);
            }
            return "Pomyślnie usunięto film o nazwie " + movie.getTitle() + " z listy o nazwie " + listName + ".";
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
