package com.rooq37.filmzone.repositories;

import com.rooq37.filmzone.entities.MediaEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MediaRepository
        extends CrudRepository<MediaEntity, Long> {
}
