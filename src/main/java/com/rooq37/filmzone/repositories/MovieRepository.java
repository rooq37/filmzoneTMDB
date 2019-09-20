package com.rooq37.filmzone.repositories;

import com.rooq37.filmzone.entities.MovieEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MovieRepository
        extends CrudRepository<MovieEntity, Long> {
}
