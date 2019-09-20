package com.rooq37.filmzone.entities;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "MOVIE_CATEGORY")
@IdClass(MovieCategoryEntity.class)
public class MovieCategoryEntity implements Serializable {

    @Id
    @MapsId
    @ManyToOne
    @JoinColumn(name = "id_movie")
    private MovieEntity movie;

    @Id
    @MapsId
    @ManyToOne
    @JoinColumn(name = "id_category")
    private CategoryEntity category;

    public MovieEntity getMovie() {
        return movie;
    }

    public void setMovie(MovieEntity movie) {
        this.movie = movie;
    }

    public CategoryEntity getCategory() {
        return category;
    }

    public void setCategory(CategoryEntity category) {
        this.category = category;
    }

}

