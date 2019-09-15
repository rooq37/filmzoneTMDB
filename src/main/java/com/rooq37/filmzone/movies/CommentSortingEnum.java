package com.rooq37.filmzone.movies;

public enum CommentSortingEnum {

    DATE_DESCENDING("date", false),
    DATE_ASCENDING("date", true),
    RATE_DESCENDING("rating", false),
    RATE_ASCENDING("rating", true);

    private String property;
    private boolean ascending;

    private CommentSortingEnum(String property, boolean ascending){
        this.property = property;
        this.ascending = ascending;
    }

}
