package com.rooq37.filmzone.repositories;

import com.rooq37.filmzone.entities.MediaEntity;
import com.rooq37.filmzone.entities.MovieEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MediaRepository
        extends CrudRepository<MediaEntity, Long> {

    List<MediaEntity> findAllByMovieEqualsAndType(MovieEntity movie, String type);
    MediaEntity findByMovieAndType(MovieEntity movie, String type);
    void deleteAllByMovieAndType(MovieEntity movie, String type);
    void deleteByValue(String value);

}
