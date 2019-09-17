package com.rooq37.filmzone.entities;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "MOVIE_COUNTRY")
@IdClass(MovieCountryEntity.class)
public class MovieCountryEntity implements Serializable {

    @Id
    @MapsId
    @ManyToOne
    @JoinColumn(name = "id_movie")
    private MovieEntity movie;

    @Id
    @MapsId
    @ManyToOne
    @JoinColumn(name = "id_country")
    private CountryEntity country;

    public MovieEntity getMovie() {
        return movie;
    }

    public void setMovie(MovieEntity movie) {
        this.movie = movie;
    }

    public CountryEntity getCountry() {
        return country;
    }

    public void setCountry(CountryEntity country) {
        this.country = country;
    }

}
