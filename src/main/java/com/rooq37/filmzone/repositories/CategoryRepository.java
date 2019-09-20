package com.rooq37.filmzone.repositories;

import com.rooq37.filmzone.entities.CategoryEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryRepository
        extends CrudRepository<CategoryEntity, Long> {
}
