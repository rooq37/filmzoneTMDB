package com.rooq37.filmzone.repositories;

import com.rooq37.filmzone.commons.Comment;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CommentRepository
        extends PagingAndSortingRepository<Comment, Long> {
}
