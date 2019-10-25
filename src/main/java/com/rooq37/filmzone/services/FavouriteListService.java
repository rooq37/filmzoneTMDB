package com.rooq37.filmzone.services;

import com.rooq37.filmzone.commons.MovieListElement;
import com.rooq37.filmzone.entities.CommentEntity;
import com.rooq37.filmzone.entities.FavouriteListEntity;
import com.rooq37.filmzone.entities.MovieEntity;
import com.rooq37.filmzone.movies.movieDetails.MyMovies;
import com.rooq37.filmzone.repositories.FavouriteListRepository;
import com.rooq37.filmzone.repositories.MovieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.support.PagedListHolder;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
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
    private HelperService helperService;

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

    public MyMovies getMyMovies(Long movieId, String userEmail){
        MovieEntity movieEntity = movieRepository.findMovieEntityById(movieId);
        List<String> listsContainMovie = movieEntity.getFavouriteLists()
                .stream().map(FavouriteListEntity::getName).collect(Collectors.toList());
        List<String> availableLists = getUserAllLists(userEmail);
        availableLists.removeAll(listsContainMovie);
        return new MyMovies(listsContainMovie, availableLists);
    }

    public PagedListHolder<MovieListElement> getFavouriteList(int currentPage, int pageSize, String userEmail, String listName) {
        if(listName.equals("-1")) return new PagedListHolder<>(Collections.emptyList());

        FavouriteListEntity fle = favouriteListRepository.findFavouriteListEntityByNameAndUser_Email(listName, userEmail);
        List<MovieListElement> list = fle.getMovies().stream().map(movieEntity ->
                helperService.getMovieListElement(movieEntity)).collect(Collectors.toList());
        list.sort(MovieListElement.idComparator);

        PagedListHolder<MovieListElement> pagedListHolder = new PagedListHolder<>(list);
        pagedListHolder.setPageSize(pageSize);
        pagedListHolder.setPage(currentPage);

        return pagedListHolder;
    }

    public String removeMovieFromList(String userEmail, String listName, Long movieId){
        FavouriteListEntity fle = favouriteListRepository.findFavouriteListEntityByNameAndUser_Email(listName, userEmail);
        MovieEntity movie = movieRepository.findMovieEntityById(movieId);
        fle.getMovies().remove(movie);
        favouriteListRepository.save(fle);
        return "Pomyślnie usunięto film o nazwie " + movie.getTitle() + " z listy o nazwie " + listName + ".";
    }

    public String removeList(String userEmail, String listName){
        FavouriteListEntity fle = favouriteListRepository.findFavouriteListEntityByNameAndUser_Email(listName, userEmail);
        favouriteListRepository.delete(fle);
        return "Pomyślnie usunięto listę o nazwie " + listName + ".";
    }

}
