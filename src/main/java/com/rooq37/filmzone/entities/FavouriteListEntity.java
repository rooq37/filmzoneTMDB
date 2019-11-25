package com.rooq37.filmzone.entities;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

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

    @ElementCollection(fetch = FetchType.EAGER)
    private Set<Integer> tmdbMovieIds = new HashSet<>();

    @ElementCollection(fetch = FetchType.EAGER)
    private Set<Integer> recommendationIds = new HashSet<>();

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

    public Set<Integer> getTmdbMovieIds() {
        return tmdbMovieIds;
    }

    public void setTmdbMovieIds(Set<Integer> tmdbMovieIds) {
        this.tmdbMovieIds = tmdbMovieIds;
    }

    public Set<Integer> getRecommendationIds() {
        return recommendationIds;
    }

    public void setRecommendationIds(Set<Integer> recommendationIds) {
        this.recommendationIds = recommendationIds;
    }

}
