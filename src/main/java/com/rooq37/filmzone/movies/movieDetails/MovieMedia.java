package com.rooq37.filmzone.movies.movieDetails;

import com.rooq37.filmzone.commons.Image;

import java.util.List;

public class MovieMedia {

    private List<Image> photos;
    private String trailerLink;

    public List<Image> getPhotos() {
        return photos;
    }

    public void setPhotos(List<Image> photos) {
        this.photos = photos;
    }

    public String getTrailerLink() {
        return trailerLink;
    }

    public void setTrailerLink(String trailerLink) {
        this.trailerLink = trailerLink;
    }

}
