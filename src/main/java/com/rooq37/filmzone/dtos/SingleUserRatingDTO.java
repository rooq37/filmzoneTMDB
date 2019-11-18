package com.rooq37.filmzone.dtos;

public class SingleUserRatingDTO {

    private static final String TEXT_RATED = "Twoja ocena filmu: ";
    private static final String TEXT_NOT_RATED = "Oceń film: ";
    private static final String TOOLTIP_RATED = "Zmień ocene filmu";
    private static final String TOOLTIP_NOT_RATED = "Oceń film";

    private boolean alreadyRated;
    private int rate = -1;

    public SingleUserRatingDTO(boolean alreadyRated) {
        this.alreadyRated = alreadyRated;
    }

    public SingleUserRatingDTO(boolean alreadyRated, int rate) {
        this.alreadyRated = alreadyRated;
        this.rate = rate;
    }

    public int getRate() {
        return rate;
    }

    public String getHeader(){
        if(alreadyRated) return TEXT_RATED;
        else return TEXT_NOT_RATED;
    }

    public String getTooltip(){
        if(alreadyRated) return TOOLTIP_RATED;
        else return TOOLTIP_NOT_RATED;
    }

}
