package com.rooq37.filmzone.services;

import com.rooq37.filmzone.dtos.*;
import com.rooq37.filmzone.entities.*;
import com.rooq37.filmzone.mappers.MovieDetailsMapper;
import com.rooq37.filmzone.mappers.MovieEditMapper;
import com.rooq37.filmzone.mappers.MovieSimpleMapper;
import com.rooq37.filmzone.dtos.EditMovieDTO;
import com.rooq37.filmzone.dtos.ImageFileDTO;
import com.rooq37.filmzone.dtos.MoviesFilterDTO;
import com.rooq37.filmzone.repositories.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.util.Date;
import java.util.List;

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

    public Page<MovieSimpleDTO> getMovieSimplePage(Pageable pageable){
        Page<MovieEntity> movieEntityPage = movieRepository.findAll(pageable);

        return movieEntityPage.map(movieEntity -> {
            MovieSimpleMapper mapper = new MovieSimpleMapper(movieEntity);
            return mapper.getMovieSimpleDTO();
        });
    }

    public Page<MovieSimpleDTO> getMovieSimplePage(Pageable pageable, MoviesFilterDTO moviesFilter){
        Page<MovieEntity> movieEntityPage = movieRepository.findAll(moviesFilter.movieMatchesFilter(), pageable);

        return movieEntityPage.map(movieEntity -> {
            MovieSimpleMapper mapper = new MovieSimpleMapper(movieEntity);
            return mapper.getMovieSimpleDTO();
        });
    }

    public SingleUserRatingDTO getMovieRatingByUser(Long movieId, String userEmail){
        RatingEntity userRating = ratingRepository.findByUser_EmailAndMovie_Id(userEmail, movieId);
        return (userRating != null) ?
                new SingleUserRatingDTO(true, userRating.getValue()) : new SingleUserRatingDTO(false);
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

    public EditMovieDTO getEditMovieForm(Long movieId){
        MovieEntity movie = movieRepository.findById(movieId).get();
        MovieEditMapper mapper = new MovieEditMapper(movie);
        return mapper.getEditMovieDTO();
    }

    @Transactional
    void changeCover(MovieEntity movie, ImageFileDTO newCover){
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

    public MovieEntity saveMovie(EditMovieDTO movieForm, ImageFileDTO cover, List<ImageFileDTO> pictures, List<String> oldPictures){
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
            for(ImageFileDTO image : pictures) fileSaverService.saveMediaEntity(image, movie, "PICTURE");
        } catch (IOException e) {
            e.printStackTrace();
        }

        return movie;
    }

}
