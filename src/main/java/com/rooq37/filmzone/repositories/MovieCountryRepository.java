package com.rooq37.filmzone.repositories;

import com.rooq37.filmzone.entities.MovieCountryEntity;
import com.rooq37.filmzone.entities.MovieEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MovieCountryRepository
        extends CrudRepository<MovieCountryEntity, Long> {

    List<MovieCountryEntity> findAllByMovie(MovieEntity movieEntity);

}
