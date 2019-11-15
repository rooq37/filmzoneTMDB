package com.rooq37.filmzone.mappers;

import com.rooq37.filmzone.dtos.ImageDTO;
import com.rooq37.filmzone.dtos.MovieSimpleDTO;
import com.rooq37.filmzone.entities.*;

import java.util.List;
import java.util.stream.Collectors;

public class MovieSimpleMapper {

    protected static final String PICTURES_PATH = "../images/";

    private static final String MEDIA_TYPE_COVER = "COVER";

    private static final String DEFAULT_COVER_PATH = "../images/filmzone_default.png";
    private static final String DEFAULT_COVER_AUTHOR = "Filmzone";

    protected MovieSimpleDTO movieDTO;
    protected MovieEntity movieEntity;

    public MovieSimpleMapper(MovieEntity movieEntity){
        this.movieEntity = movieEntity;
        movieDTO = new MovieSimpleDTO();
    }

    public MovieSimpleDTO getMovieSimpleDTO(){
        movieDTO.setMovieId(movieEntity.getId());
        movieDTO.setTitle(movieEntity.getTitle());
        movieDTO.setCover(getCover());
        movieDTO.setCategories(getCategories());
        movieDTO.setDescription(movieEntity.getDescription());
        movieDTO.setYear(movieEntity.getYear());
        movieDTO.setCountry(getCountry());
        movieDTO.setAvgUsersRating(getAvgUsersRating());
        movieDTO.setNumberOfPeopleWhoWatched(getNumberOfPeopleWhoWatched());
        movieDTO.setNumberOfPeopleWhoWantToWatch(getNumberOfPeopleWhoWantToWatch());
        movieDTO.setNumberOfSearches(getNumberOfSearches());

        return movieDTO;
    }

    protected ImageDTO getCover(){
        List<MediaEntity> covers = movieEntity.getMedia().stream()
                .filter(mediaEntity -> mediaEntity.getType().equals(MEDIA_TYPE_COVER)).collect(Collectors.toList());
        if(covers.isEmpty()){
            return new ImageDTO(movieEntity.getTitle(), DEFAULT_COVER_PATH, DEFAULT_COVER_AUTHOR) ;
        }else{
            MediaEntity cover = covers.get(0);
            return new ImageDTO(movieEntity.getTitle(), PICTURES_PATH + cover.getValue(), cover.getAuthor());
        }
    }

    protected List<String> getCategories(){
        return movieEntity.getCategories().stream().map(CategoryEntity::getName).collect(Collectors.toList());
    }

    protected String getCountry(){
        return movieEntity.getCountries().stream().map(CountryEntity::getName).collect(Collectors.joining(", "));
    }

    protected String getAvgUsersRating(){
        double avgUsersRating;
        List<Integer> ratings = movieEntity.getRatings().stream().map(RatingEntity::getValue).collect(Collectors.toList());
        if(ratings.isEmpty()){
            avgUsersRating = 0;
        }else{
            avgUsersRating = ratings.stream().mapToInt(Integer::intValue).average().getAsDouble();
        }
        return String.format("%.2f", avgUsersRating);
    }

    protected String getNumberOfPeopleWhoWatched(){
        long numberOfPeopleWhoWatched = movieEntity.getRatings()
                .stream().filter(ratingEntity -> ratingEntity.getValue() > 0).count();
        return String.format("%,d", numberOfPeopleWhoWatched);
    }

    protected String getNumberOfPeopleWhoWantToWatch(){
        long numberOfPeopleWhoWantToWatch = movieEntity.getRatings()
                .stream().filter(ratingEntity -> ratingEntity.getValue() == 0).count();
        return String.format("%,d", numberOfPeopleWhoWantToWatch);
    }

    private int getNumberOfSearches(){
        return movieEntity.getViews().size();
    }

}
