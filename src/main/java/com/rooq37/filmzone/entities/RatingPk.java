package com.rooq37.filmzone.entities;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;

@Embeddable
public class RatingPk implements Serializable {

    @Column(name = "id_tmdb_movie")
    private int movieId;
    @Column(name = "id_user")
    private Long userId;

    public RatingPk() { }

    public int getMovieId() {
        return movieId;
    }

    public void setMovieId(int movieId) {
        this.movieId = movieId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

}
