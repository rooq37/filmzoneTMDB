package com.rooq37.filmzone.repositories;

import com.rooq37.filmzone.entities.CountryEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CountryRepository
        extends CrudRepository<CountryEntity, Long> {
}
