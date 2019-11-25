package com.rooq37.filmzone.entities;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name = "RATING")
public class RatingEntity{

    @EmbeddedId
    private RatingPk ratingPk;

    @Column(name = "id_tmdb_movie", insertable = false, updatable = false)
    private Integer tmdbMovieId;

    @ManyToOne
    @JoinColumn(name = "id_user", insertable = false, updatable = false)
    private UserEntity user;

    @Column(name = "value")
    private int value;

    @Column(name = "date")
    private Date date;

    @PrePersist
    private void prePersist() {
        if (getRatingPk() == null) {
            RatingPk pk = new RatingPk();
            pk.setUserId(getUser().getId());
            pk.setMovieId(tmdbMovieId);
            setRatingPk(pk);
        }
    }

    public RatingPk getRatingPk() {
        return ratingPk;
    }

    public void setRatingPk(RatingPk ratingPk) {
        this.ratingPk = ratingPk;
    }

    public Integer getTmdbMovieId() {
        return tmdbMovieId;
    }

    public void setTmdbMovieId(Integer tmdbMovieId) {
        this.tmdbMovieId = tmdbMovieId;
    }

    public UserEntity getUser() {
        return user;
    }

    public void setUser(UserEntity user) {
        this.user = user;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

}
