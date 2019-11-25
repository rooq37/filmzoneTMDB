package com.rooq37.filmzone.entities;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "VIEW")
public class ViewEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "id_tmdb_movie")
    private int tmdbMovieId;

    @Column(name = "date")
    private Date date;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getTmdbMovieId() {
        return tmdbMovieId;
    }

    public void setTmdbMovieId(int tmdbMovieId) {
        this.tmdbMovieId = tmdbMovieId;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

}
