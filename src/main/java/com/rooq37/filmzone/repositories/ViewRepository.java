package com.rooq37.filmzone.repositories;

import com.rooq37.filmzone.entities.MovieEntity;
import com.rooq37.filmzone.entities.ViewEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;

@Repository
public interface ViewRepository
        extends CrudRepository<ViewEntity, Long> {

    int countByMovieAndDateAfter(MovieEntity movieEntity, Date date);
    int countByMovie(MovieEntity movieEntity);
    int countByDateAfter(Date date);

}
