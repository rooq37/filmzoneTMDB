package com.rooq37.filmzone.repositories;

import com.rooq37.filmzone.entities.FavouriteListEntity;
import com.rooq37.filmzone.entities.MovieEntity;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MovieRepository
        extends PagingAndSortingRepository<MovieEntity, Long> {

    List<MovieEntity> findAll();
    List<MovieEntity> findAll(Sort sort);
    List<MovieEntity> findMovieEntitiesByYearBetween(int start, int end, Sort sort);
    List<MovieEntity> findMovieEntitiesByYearBetween(int start, int end);
    MovieEntity findMovieEntityById(Long id);

}
