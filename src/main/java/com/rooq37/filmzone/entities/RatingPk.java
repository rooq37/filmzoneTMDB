package com.rooq37.filmzone.entities;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;

@Embeddable
public class RatingPk implements Serializable {

    @Column(name = "id_movie")
    private Long movieId;
    @Column(name = "id_user")
    private Long userId;

    public RatingPk() { }

    public Long getMovieId() {
        return movieId;
    }

    public void setMovieId(Long movieId) {
        this.movieId = movieId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

}
