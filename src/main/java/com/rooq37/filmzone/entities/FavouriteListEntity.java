package com.rooq37.filmzone.entities;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "FAVOURITE_LIST")
public class FavouriteListEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    private String name;

    @ManyToOne
    @JoinColumn(name = "id_author")
    private UserEntity user;

    @ElementCollection
    private List<Integer> tmdbMovieIds = new ArrayList<>();

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

    public UserEntity getUser() {
        return user;
    }

    public void setUser(UserEntity user) {
        this.user = user;
    }

    public List<Integer> getTmdbMovieIds() {
        return tmdbMovieIds;
    }

    public void setTmdbMovieIds(List<Integer> tmdbMovieIds) {
        this.tmdbMovieIds = tmdbMovieIds;
    }

}
