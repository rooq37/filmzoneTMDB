package com.rooq37.filmzone.entities;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "MOVIE_MEDIA")
@IdClass(MovieMediaEntity.class)
public class MovieMediaEntity implements Serializable {

    @Id
    @MapsId
    @ManyToOne
    @JoinColumn(name = "id_movie")
    private MovieEntity movie;

    @Id
    @MapsId
    @ManyToOne
    @JoinColumn(name = "id_media")
    private MediaEntity media;

    public MovieEntity getMovie() {
        return movie;
    }

    public void setMovie(MovieEntity movie) {
        this.movie = movie;
    }

    public MediaEntity getMedia() {
        return media;
    }

    public void setMedia(MediaEntity media) {
        this.media = media;
    }

}
