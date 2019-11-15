package com.rooq37.filmzone.repositories;

import com.rooq37.filmzone.entities.MovieEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MovieRepository
        extends PagingAndSortingRepository<MovieEntity, Long> {

    List<MovieEntity> findAll();
    List<MovieEntity> findAll(Sort sort);
    MovieEntity findMovieEntityById(Long id);

    Page<MovieEntity> findAll(Specification<MovieEntity> specification, Pageable pageable);

}
