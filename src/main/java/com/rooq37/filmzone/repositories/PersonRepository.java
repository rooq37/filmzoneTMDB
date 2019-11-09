package com.rooq37.filmzone.repositories;

import com.rooq37.filmzone.entities.PersonEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PersonRepository
        extends CrudRepository<PersonEntity, Long> {

    Optional<PersonEntity> findPersonEntityByNameAndSurname(String name, String surname);

}
