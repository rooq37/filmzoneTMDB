package com.rooq37.filmzone.repositories;

import com.rooq37.filmzone.entities.RatingEntity;
import com.rooq37.filmzone.entities.UserEntity;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface RatingRepository
        extends CrudRepository<RatingEntity, Long> {

    int countByUserAndValueGreaterThan(UserEntity user, int value);
    int countByDateAfter(Date date);
    RatingEntity findByUser_EmailAndTmdbMovieId(String userEmail, int tmdbMovieId);

    @Query(value = "select R.ID_TMDB_MOVIE from RATING R group by R.ID_TMDB_MOVIE order by AVG(R.VALUE) desc", nativeQuery = true)
    List<Integer> findHighestRatedMovies();

    Integer countByTmdbMovieId(int tmdbMovieId);

    @Query(value = "select AVG(R.VALUE) from RATING R where R.ID_TMDB_MOVIE = :param_movieid group by R.ID_TMDB_MOVIE", nativeQuery = true)
    Double averageByTmdbMovieId(@Param("param_movieid")int tmdbMovieId);

    @Query(value = "select R.ID_TMDB_MOVIE from RATING R group by R.ID_TMDB_MOVIE order by COUNT(R.VALUE) desc", nativeQuery = true)
    List<Integer> findMostRatedMovies();

}
