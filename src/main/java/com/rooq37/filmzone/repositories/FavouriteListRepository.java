package com.rooq37.filmzone.repositories;

import com.rooq37.filmzone.entities.FavouriteListEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface FavouriteListRepository extends JpaRepository<FavouriteListEntity, Long> {

    FavouriteListEntity findFavouriteListEntityByNameAndUser_Email(String name, String userEmail);
    List<FavouriteListEntity> findFavouriteListEntitiesByUser_Email(String userEmail);

}
