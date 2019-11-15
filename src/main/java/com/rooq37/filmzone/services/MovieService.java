package com.rooq37.filmzone.services;

import com.rooq37.filmzone.dtos.*;
import com.rooq37.filmzone.entities.*;
import com.rooq37.filmzone.mappers.MovieDetailsMapper;
import com.rooq37.filmzone.mappers.MovieSimpleMapper;
import com.rooq37.filmzone.movies.editMovieForm.EditMovieForm;
import com.rooq37.filmzone.movies.editMovieForm.ImageFile;
import com.rooq37.filmzone.movies.movieDetails.*;
import com.rooq37.filmzone.movies.movies.MoviesFilterForm;
import com.rooq37.filmzone.repositories.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class MovieService {

    @Autowired
    private HelperService helperService;
    @Autowired
    private CommentRepository commentRepository;
    @Autowired
    private MoviePersonRepository moviePersonRepository;
    @Autowired
    private MovieRepository movieRepository;
    @Autowired
    private RatingRepository ratingRepository;
    @Autowired
    private UserService userService;
    @Autowired
    private MediaRepository mediaRepository;
    @Autowired
    private FileSaverService fileSaverService;

    public MovieDetailsDTO getMovieDetailsDTO(Long movieId){
        MovieEntity movieEntity = movieRepository.findMovieEntityById(movieId);
        MovieDetailsMapper movieDetailsMapper = new MovieDetailsMapper(movieEntity);
        return movieDetailsMapper.getMovieDetailsDTO();
    }

    private List<MoviePersonEntity> getDirectors(MovieEntity movieEntity){
        return moviePersonRepository.findAllByMovieAndType(movieEntity, "DIRECTOR");
    }

    private List<MoviePersonEntity> getScenarioAuthors(MovieEntity movieEntity){
        return  moviePersonRepository.findAllByMovieAndType(movieEntity, "SCENARIO");
    }

    private List<MoviePersonEntity> getCharacters(MovieEntity movieEntity){
        return  moviePersonRepository.findAllByMovieAndType(movieEntity, "ACTOR");
    }

    private String getTrailerLink(MovieEntity movie){
        List<MediaEntity> trailers = mediaRepository.findAllByMovieEqualsAndType(movie, "TRAILER");
        return !trailers.isEmpty() ? trailers.get(0).getValue() : "";
    }

    public Page<CommentEntity> getMovieComments(Long id, Pageable pageable) {
        MovieEntity movie = movieRepository.findById(id).get();
        Pageable paging = PageRequest.of(pageable.getPageNumber(), pageable.getPageSize(), pageable.getSort());

        return commentRepository.findAllByMovie(movie, paging);
    }

    public Page<MovieSimpleDTO> getMovieSimplePage(Pageable pageable, MoviesFilterForm moviesFilter){
        Page<MovieEntity> movieEntityPage = movieRepository.findAll(moviesFilter.movieMatchesFilter(), pageable);

        return movieEntityPage.map(movieEntity -> {
            MovieSimpleMapper mapper = new MovieSimpleMapper(movieEntity);
            return mapper.getMovieSimpleDTO();
        });
    }

    public SingleUserRating getMovieRatingByUser(Long movieId, String userEmail){
        RatingEntity userRating = ratingRepository.findByUser_EmailAndMovie_Id(userEmail, movieId);
        return (userRating != null) ?
                new SingleUserRating(true, userRating.getValue()) : new SingleUserRating(false);
    }

    public void rateMovie(Long movieId, String userEmail, int newRating){
        RatingEntity userRating = ratingRepository.findByUser_EmailAndMovie_Id(userEmail, movieId);
        if(userRating == null){
            userRating = new RatingEntity();
            userRating.setMovie(movieRepository.findMovieEntityById(movieId));
            userRating.setUser(userService.getUserByEmail(userEmail));
        }
        userRating.setDate(new Date());
        userRating.setValue(newRating);
        ratingRepository.save(userRating);
    }

    public void addCommentToMovie(Long movieId, String userEmail, String content){
        CommentEntity commentEntity = new CommentEntity();
        commentEntity.setMovie(movieRepository.findMovieEntityById(movieId));
        commentEntity.setUser(userService.getUserByEmail(userEmail));
        commentEntity.setContent(content);
        commentEntity.setDate(new Date());
        commentRepository.save(commentEntity);
    }

    @Transactional
    public void removeComment(Long commentId){
        commentRepository.deleteById(commentId);
    }

    public EditMovieForm getEditMovieForm(Long movieId){
        EditMovieForm editMovieForm = new EditMovieForm();
        MovieEntity movie = movieRepository.findById(movieId).get();

        editMovieForm.setId(movieId);
        editMovieForm.setTitle(movie.getTitle());
        editMovieForm.setProductionYear(movie.getYear());
        editMovieForm.setDescription(movie.getDescription());
        editMovieForm.setDuration(movie.getDuration());
        editMovieForm.setCategories(helperService.getCategories(movie));
        editMovieForm.setCover(helperService.getCover(movie));
        editMovieForm.setDirectors(getDirectors(movie).stream().map(mpe -> new PersonDTO(mpe.getPerson().getName(), mpe.getPerson().getSurname()))
                .collect(Collectors.toList()));
        editMovieForm.setScenario(getScenarioAuthors(movie).stream().map(mpe -> new PersonDTO(mpe.getPerson().getName(), mpe.getPerson().getSurname()))
                .collect(Collectors.toList()));
        editMovieForm.setCountries(helperService.getCountries(movie));
        editMovieForm.setPictures(helperService.getPictures(movie));
        editMovieForm.setTrailerUrl(getTrailerLink(movie));
        editMovieForm.setCharacters(getCharacters(movie).stream().
                map(mpe -> new CharacterDTO(new PersonDTO(mpe.getPerson().getName(), mpe.getPerson().getSurname()), mpe.getRole()))
                .collect(Collectors.toList()));

        return editMovieForm;
    }

    @Transactional
    void changeCover(MovieEntity movie, ImageFile newCover){
        if(!newCover.isEmpty()){
            MediaEntity oldCover = mediaRepository.findByMovieAndType(movie, "COVER");
            try {
                fileSaverService.saveMediaEntity(newCover, movie, "COVER");
            } catch (IOException e) {
                e.printStackTrace();
            }
            if(oldCover != null) fileSaverService.removeMediaEntity(oldCover.getValue());
        }
    }

    @Transactional
    void changeTrailer(MovieEntity movie, String trailerLink){
        if(!trailerLink.isEmpty()){
            MediaEntity trailer = mediaRepository.findByMovieAndType(movie, "TRAILER");
            if(trailer == null) trailer = new MediaEntity();
            trailer.setMovie(movie);
            trailer.setValue(trailerLink);
            trailer.setType("TRAILER");
            trailer.setAuthor("YouTube");
            trailer.setDate(new Date());
            mediaRepository.save(trailer);
        }
    }

    @Transactional
    void removeOldPictures(List<ImageDTO> currentImageDTOS, List<String> oldPictures){
        for(ImageDTO currImg : currentImageDTOS){
            if(!oldPictures.contains(currImg.getSource())){
                fileSaverService.removeMediaEntity(currImg.getSource());
            }
        }
    }

    public MovieEntity saveMovie(EditMovieForm movieForm, ImageFile cover, List<ImageFile> pictures, List<String> oldPictures){
        MovieEntity movie = (movieForm.getId() == null) ? new MovieEntity() : movieRepository.findById(movieForm.getId()).get();
        movie.setTitle(movieForm.getTitle());
        movie.setDescription(movieForm.getDescription());
        movie.setDuration(movieForm.getDuration());
        movie.setYear(movieForm.getProductionYear());
        movie = movieRepository.save(movie);
        helperService.saveMoviePersonEntities(movie, movieForm.getDirectors(), movieForm.getScenario(), movieForm.getCharacters());
        helperService.saveCategoryEntities(movie, movieForm.getCategories());
        helperService.saveCountryEntities(movie, movieForm.getCountries());
        changeCover(movie, cover);
        changeTrailer(movie, movieForm.getTrailerUrl());
        try {
            removeOldPictures(helperService.getPictures(movie), oldPictures);
            for(ImageFile image : pictures) fileSaverService.saveMediaEntity(image, movie, "PICTURE");
        } catch (IOException e) {
            e.printStackTrace();
        }

        return movie;
    }

}
