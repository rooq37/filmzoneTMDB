package com.rooq37.filmzone.entities;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name = "RATING")
@IdClass(RatingEntity.class)
public class RatingEntity implements Serializable {

    @Id
    @MapsId
    @ManyToOne
    @JoinColumn(name = "id_movie")
    private MovieEntity movie;

    @Id
    @MapsId
    @ManyToOne
    @JoinColumn(name = "id_user")
    private UserEntity user;

    @Column(name = "value")
    private int value;

    @Column(name = "date")
    private Date date;

    public MovieEntity getMovie() {
        return movie;
    }

    public void setMovie(MovieEntity movie) {
        this.movie = movie;
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
