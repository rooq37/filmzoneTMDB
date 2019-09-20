package com.rooq37.filmzone.repositories;

import com.rooq37.filmzone.entities.CommentEntity;
import com.rooq37.filmzone.entities.MovieEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CommentRepository
        extends PagingAndSortingRepository<CommentEntity, Long> {

    Page<CommentEntity> findAllByMovie(MovieEntity movieEntity, Pageable pageable);

}
