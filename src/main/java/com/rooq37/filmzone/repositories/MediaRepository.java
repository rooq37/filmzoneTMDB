package com.rooq37.filmzone.repositories;

import com.rooq37.filmzone.entities.MediaEntity;
import com.rooq37.filmzone.entities.MovieEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MediaRepository
        extends CrudRepository<MediaEntity, Long> {

    List<MediaEntity> findAllByMoviesEqualsAndType(MovieEntity movie, String type);

}
