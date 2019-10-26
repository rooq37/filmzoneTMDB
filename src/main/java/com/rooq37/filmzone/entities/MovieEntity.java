package com.rooq37.filmzone.entities;

import javax.persistence.*;
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
    private Set<CategoryEntity> categories;

    @ManyToMany(mappedBy = "movies")
    private Set<FavouriteListEntity> favouriteLists;

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

    public Set<FavouriteListEntity> getFavouriteLists() {
        return favouriteLists;
    }

    public void setFavouriteLists(Set<FavouriteListEntity> favouriteLists) {
        this.favouriteLists = favouriteLists;
    }

}
