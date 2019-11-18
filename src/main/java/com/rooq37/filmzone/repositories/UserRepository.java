package com.rooq37.filmzone.repositories;

import com.rooq37.filmzone.entities.UserEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository
        extends CrudRepository<UserEntity, Long> {

    UserEntity findByEmail(String email);
    List<UserEntity> findAllByNicknameContains(String nickname);
    Optional<UserEntity> findById(Long id);
    int countByRegisterDateAfter(Date date);

}
