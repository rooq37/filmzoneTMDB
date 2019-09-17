package com.rooq37.filmzone.repositories;

import com.rooq37.filmzone.entities.MovieCategoryEntity;
import com.rooq37.filmzone.entities.MovieEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MovieCategoryRepository
        extends CrudRepository<MovieCategoryEntity, Long> {

    List<MovieCategoryEntity> findAllByMovie(MovieEntity movieEntity);

}
