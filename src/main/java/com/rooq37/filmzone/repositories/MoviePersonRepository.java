package com.rooq37.filmzone.repositories;

import com.rooq37.filmzone.entities.MovieEntity;
import com.rooq37.filmzone.entities.MoviePersonEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MoviePersonRepository
        extends CrudRepository<MoviePersonEntity, Long> {

    List<MoviePersonEntity> findAllByMovieAndType(MovieEntity movieEntity, String type);
    void deleteAllByMovie_Id(Long movieId);

}
