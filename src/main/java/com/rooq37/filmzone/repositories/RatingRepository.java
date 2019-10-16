package com.rooq37.filmzone.repositories;

import com.rooq37.filmzone.entities.MovieEntity;
import com.rooq37.filmzone.entities.RatingEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface RatingRepository
        extends CrudRepository<RatingEntity, Long> {

    List<RatingEntity> findAllByMovie(MovieEntity movieEntity);
    int countByMovieAndValueGreaterThan(MovieEntity movieEntity, int value);
    int countByMovieAndValueIs(MovieEntity movieEntity, int value);
    int countByDateAfter(Date date);
    RatingEntity findByUser_EmailAndMovie_Id(String userEmail, Long movieId);

}
