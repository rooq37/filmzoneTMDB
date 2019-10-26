package com.rooq37.filmzone.entities;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "COUNTRY")
public class CountryEntity {

    @Id
    @GeneratedValue
    private Long id;

    @Column(name = "name")
    private String name;

    @ManyToMany
    @JoinTable(
            name = "MOVIE_COUNTRY",
            joinColumns = @JoinColumn(name = "id_country"),
            inverseJoinColumns = @JoinColumn(name = "id_movie"))
    private Set<MovieEntity> movies;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<MovieEntity> getMovies() {
        return movies;
    }

    public void setMovies(Set<MovieEntity> movies) {
        this.movies = movies;
    }

}
