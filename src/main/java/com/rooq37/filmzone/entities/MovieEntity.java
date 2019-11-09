package com.rooq37.filmzone.entities;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "MOVIE")
public class MovieEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "title")
    private String title;

    @Column(name = "description")
    private String description;

    @Column(name = "year")
    private int year;

    @Column(name = "duration")
    private int duration;

    @ManyToMany(fetch = FetchType.EAGER, mappedBy = "movies")
    private Set<CategoryEntity> categories = new HashSet<>();

    @ManyToMany(fetch = FetchType.EAGER, mappedBy = "movies")
    private Set<CountryEntity> countries = new HashSet<>();

    @ManyToMany(fetch = FetchType.EAGER, mappedBy = "movies")
    private Set<MediaEntity> media = new HashSet<>();

    @ManyToMany(mappedBy = "movies")
    private Set<FavouriteListEntity> favouriteLists = new HashSet<>();

    @OneToMany(mappedBy = "movie")
    private Set<CommentEntity> comments = new HashSet<>();

    @OneToMany(mappedBy = "movie")
    private Set<MoviePersonEntity> people = new HashSet<>();

    @OneToMany(mappedBy = "movie")
    private Set<RatingEntity> ratings = new HashSet<>();

    @OneToMany(mappedBy = "movie")
    private Set<ViewEntity> views = new HashSet<>();

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public Set<CategoryEntity> getCategories() {
        return categories;
    }

    public void setCategories(Set<CategoryEntity> categories) {
        this.categories = categories;
    }

    public Set<CountryEntity> getCountries() {
        return countries;
    }

    public void setCountries(Set<CountryEntity> countries) {
        this.countries = countries;
    }

    public Set<MediaEntity> getMedia() {
        return media;
    }

    public void setMedia(Set<MediaEntity> media) {
        this.media = media;
    }

    public Set<FavouriteListEntity> getFavouriteLists() {
        return favouriteLists;
    }

    public void setFavouriteLists(Set<FavouriteListEntity> favouriteLists) {
        this.favouriteLists = favouriteLists;
    }

    public Set<CommentEntity> getComments() {
        return comments;
    }

    public void setComments(Set<CommentEntity> comments) {
        this.comments = comments;
    }

    public Set<MoviePersonEntity> getPeople() {
        return people;
    }

    public void setPeople(Set<MoviePersonEntity> people) {
        this.people = people;
    }

    public Set<RatingEntity> getRatings() {
        return ratings;
    }

    public void setRatings(Set<RatingEntity> ratings) {
        this.ratings = ratings;
    }

    public Set<ViewEntity> getViews() {
        return views;
    }

    public void setViews(Set<ViewEntity> views) {
        this.views = views;
    }

}
