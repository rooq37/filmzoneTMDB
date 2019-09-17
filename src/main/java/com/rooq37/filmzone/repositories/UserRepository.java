package com.rooq37.filmzone.repositories;

import com.rooq37.filmzone.entities.UserEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository
        extends CrudRepository<UserEntity, Long> {
}
