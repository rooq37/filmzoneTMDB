package com.rooq37.filmzone.services;

import com.rooq37.filmzone.dtos.*;
import com.rooq37.filmzone.entities.*;
import com.rooq37.filmzone.mappers.MovieDetailsMapper;
import com.rooq37.filmzone.dtos.MoviesFilterDTO;
import com.rooq37.filmzone.mappers.MovieSimpleMapper;
import com.rooq37.filmzone.repositories.*;
import info.movito.themoviedbapi.*;
import info.movito.themoviedbapi.model.*;
import info.movito.themoviedbapi.model.core.MovieResultsPage;
import info.movito.themoviedbapi.model.core.ResultsPage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class MovieService {

    private static final String API_KEY = "5a46d5b61d76c153823d4be68aed3798";

    @Autowired
    private CommentRepository commentRepository;
    @Autowired
    private RatingRepository ratingRepository;
    @Autowired
    private UserService userService;
    @Autowired
    private HelperService helperService;

    public Page<CommentEntity> getMovieComments(int tmdbMovieId, Pageable pageable) {
        return commentRepository.findAllByTmdbMovieId(tmdbMovieId, pageable);
    }

    public String getBasicImageUrl(){
        TmdbApi api = new TmdbApi(API_KEY);
        return api.getConfiguration().getBaseUrl() + "w500/";
    }

    public List<Genre> getAllPossibleCategories(){
        TmdbApi api = new TmdbApi(API_KEY);
        return api.getGenre().getGenreList("pl");
    }

    private ResultsPage<MovieSimpleDTO> mapMovieResultsPageToDTOPage(MovieResultsPage movieResultsPage){
        TmdbApi api = new TmdbApi(API_KEY);
        ResultsPage<MovieSimpleDTO> movieSimplePage = new ResultsPage<>();
        movieSimplePage.setResults(new ArrayList<>());
        movieSimplePage.setPage(movieResultsPage.getPage());
        movieSimplePage.setTotalPages(movieResultsPage.getTotalPages());
        movieSimplePage.setTotalResults(movieResultsPage.getTotalResults());
        for(MovieDb movie : movieResultsPage){
            movie.setGenres(api.getMovies().getMovie(movie.getId(), "pl").getGenres());
            MovieSimpleMapper mapper = new MovieSimpleMapper(movie);
            movieSimplePage.getResults().add(mapper.getMovieSimpleDTO());
        }
        return movieSimplePage;
    }

    public ResultsPage<MovieSimpleDTO> getMovieResultsPageByName(String name, int pageNumber){
        TmdbApi api = new TmdbApi(API_KEY);
        TmdbSearch search = api.getSearch();
        MovieResultsPage movieResultsPage = search.searchMovie(name, null, "pl", false, pageNumber);
        return mapMovieResultsPageToDTOPage(movieResultsPage);
    }

    public ResultsPage<MovieSimpleDTO> getMovieResultsPage(MoviesFilterDTO moviesFilterDTO, int pageNumber){
        TmdbApi api = new TmdbApi(API_KEY);
        TmdbDiscover discover = api.getDiscover();
        MovieResultsPage movieResultsPage = discover.getDiscover(moviesFilterDTO.getDiscover().page(pageNumber));
        return mapMovieResultsPageToDTOPage(movieResultsPage);
    }

    public MovieDetailsDTO getMovieDetails(int tmdbId){
        TmdbApi api = new TmdbApi(API_KEY);
        TmdbMovies movies = api.getMovies();
        MovieDb moviePL = movies.getMovie(tmdbId, "pl", TmdbMovies.MovieMethod.credits, TmdbMovies.MovieMethod.images, TmdbMovies.MovieMethod.videos);
        MovieDb movieUNIVERSAL = movies.getMovie(tmdbId, "null", TmdbMovies.MovieMethod.images);
        MovieDb movieEN = movies.getMovie(tmdbId, "en", TmdbMovies.MovieMethod.videos);
        MovieImages backdrops = new MovieImages();
        backdrops.setBackdrops(movieUNIVERSAL.getImages(ArtworkType.BACKDROP));
        moviePL.setImages(backdrops);
        moviePL.getVideos().addAll(movieEN.getVideos());
        MovieDetailsMapper mdp = new MovieDetailsMapper(moviePL);
        MovieDetailsDTO dto = mdp.getMovieDetailsDTO();
        dto.setFzVoteAvg(helperService.getFzVoteAvg(tmdbId));
        dto.setFzVoteCount(helperService.getFzVoteCount(tmdbId));
        return dto;
    }

    public SingleUserRatingDTO getMovieRatingByUser(int movieId, String userEmail){
        RatingEntity userRating = ratingRepository.findByUser_EmailAndTmdbMovieId(userEmail, movieId);
        return (userRating != null) ?
                new SingleUserRatingDTO(true, userRating.getValue()) : new SingleUserRatingDTO(false);
    }

    public void rateMovie(int movieId, String userEmail, int newRating){
        RatingEntity userRating = ratingRepository.findByUser_EmailAndTmdbMovieId(userEmail, movieId);
        if(userRating == null){
            userRating = new RatingEntity();
            userRating.setTmdbMovieId(movieId);
            userRating.setUser(userService.getUserByEmail(userEmail));
        }
        userRating.setDate(new Date());
        userRating.setValue(newRating);
        ratingRepository.save(userRating);
    }

    public void addCommentToMovie(int movieId, String userEmail, String content){
        CommentEntity commentEntity = new CommentEntity();
        commentEntity.setTmdbMovieId(movieId);
        commentEntity.setUser(userService.getUserByEmail(userEmail));
        commentEntity.setContent(content);
        commentEntity.setDate(new Date());
        commentRepository.save(commentEntity);
    }

    @Transactional
    public void removeComment(Long commentId){
        commentRepository.deleteById(commentId);
    }

}
