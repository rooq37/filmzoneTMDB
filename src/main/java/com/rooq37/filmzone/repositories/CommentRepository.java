package com.rooq37.filmzone.repositories;

import com.rooq37.filmzone.entities.CommentEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CommentRepository
        extends PagingAndSortingRepository<CommentEntity, Long> {

    Page<CommentEntity> findAllByTmdbMovieId(int tmdbMovieId, Pageable pageable);

}


