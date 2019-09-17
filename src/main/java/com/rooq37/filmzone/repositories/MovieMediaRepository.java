package com.rooq37.filmzone.repositories;

import com.rooq37.filmzone.entities.MovieEntity;
import com.rooq37.filmzone.entities.MovieMediaEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MovieMediaRepository
        extends CrudRepository<MovieMediaEntity, Long> {

    List<MovieMediaEntity> findAllByMovieAndMedia_Type(MovieEntity movieEntity, String type);

}
