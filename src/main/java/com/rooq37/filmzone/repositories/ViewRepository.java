package com.rooq37.filmzone.repositories;

import com.rooq37.filmzone.entities.ViewEntity;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface ViewRepository
        extends CrudRepository<ViewEntity, Long> {

    Integer countByTmdbMovieIdAndDateAfter(int tmdbMovieId, Date date);
    Integer countByTmdbMovieId(int tmdbMovieId);
    Integer countByDateAfter(Date date);

    @Query(value = "select V.ID_TMDB_MOVIE from VIEW V where V.DATE > :param_date group by V.ID_TMDB_MOVIE order by COUNT(V.DATE) desc", nativeQuery = true)
    List<Integer> findMostSearchedMovies(@Param("param_date") Date date);

    @Query(value = "select V.ID_TMDB_MOVIE from VIEW V group by V.ID_TMDB_MOVIE order by COUNT(V.DATE) desc", nativeQuery = true)
    List<Integer> findMostSearchedMovies();

}
