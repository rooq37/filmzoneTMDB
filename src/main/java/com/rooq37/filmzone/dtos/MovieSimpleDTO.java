package com.rooq37.filmzone.dtos;


import java.util.List;

public class MovieSimpleDTO {

    private int movieId;
    private String title;
    private ImageDTO cover;
    private List<String> categories;
    private String country;
    protected String description;
    private String releaseDate;

    private double tmdbVoteAvg;
    private long tmdbVoteCount;
    private long fzNumberOfSearches;
    private double fzVoteAvg;
    private long fzVoteCount;

    public int getMovieId() {
        return movieId;
    }

    public void setMovieId(int movieId) {
        this.movieId = movieId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public ImageDTO getCover() {
        return cover;
    }

    public void setCover(ImageDTO cover) {
        this.cover = cover;
    }

    public List<String> getCategories() {
        return categories;
    }

    public void setCategories(List<String> categories) {
        this.categories = categories;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getDescription() {
        if(description.length() > 200) return description.substring(0, 200) + "...";
        else return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getYear(){
        return releaseDate.substring(0,4);
    }

    public String getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(String releaseDate) {
        this.releaseDate = releaseDate;
    }

    public String getTmdbVoteAvg() {
        return String.format("%.2f", tmdbVoteAvg);
    }

    public void setTmdbVoteAvg(double tmdbVoteAvg) {
        this.tmdbVoteAvg = tmdbVoteAvg;
    }

    public String getTmdbVoteCount() {
        return String.format("%,d", tmdbVoteCount);
    }

    public void setTmdbVoteCount(long tmdbVoteCount) {
        this.tmdbVoteCount = tmdbVoteCount;
    }

    public String getFzNumberOfSearches() {
        return String.format("%,d", fzNumberOfSearches);
    }

    public void setFzNumberOfSearches(long fzNumberOfSearches) {
        this.fzNumberOfSearches = fzNumberOfSearches;
    }

    public String getFzVoteAvg() {
        return String.format("%.2f", fzVoteAvg);
    }

    public void setFzVoteAvg(double fzVoteAvg) {
        this.fzVoteAvg = fzVoteAvg;
    }

    public String getFzVoteCount() {
        return String.format("%,d", fzVoteCount);
    }

    public void setFzVoteCount(long fzVoteCount) {
        this.fzVoteCount = fzVoteCount;
    }
}
