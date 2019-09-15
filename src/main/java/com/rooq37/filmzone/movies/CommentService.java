package com.rooq37.filmzone.movies;

import com.rooq37.filmzone.commons.Comment;
import com.rooq37.filmzone.repositories.CommentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class CommentService {

    @Autowired
    CommentRepository commentRepository;

    public Page<Comment> findPaginated(Pageable pageable) {
        Pageable paging = PageRequest.of(pageable.getPageNumber(), pageable.getPageSize(), pageable.getSort());
        Page<Comment> pagedResult = commentRepository.findAll(paging);

        return pagedResult;
    }

}
