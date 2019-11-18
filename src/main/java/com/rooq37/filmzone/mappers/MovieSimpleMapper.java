package com.rooq37.filmzone.mappers;

import com.rooq37.filmzone.dtos.MovieSimpleDTO;
import com.rooq37.filmzone.entities.*;

import java.util.List;
import java.util.stream.Collectors;

public class MovieSimpleMapper extends MovieMapper {

    protected MovieSimpleDTO movieDTO;

    public MovieSimpleMapper(MovieEntity movieEntity){
        super(movieEntity);
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
