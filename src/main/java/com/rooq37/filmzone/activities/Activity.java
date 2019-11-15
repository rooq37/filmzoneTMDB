package com.rooq37.filmzone.activities;

import com.rooq37.filmzone.dtos.ImageDTO;

import java.util.Comparator;
import java.util.Date;

public class Activity {

    private Date date;
    private ImageDTO cover;
    private String movieTitle;
    private Long movieId;
    private String content;

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public ImageDTO getCover() {
        return cover;
    }

    public void setCover(ImageDTO cover) {
        this.cover = cover;
    }

    public String getMovieTitle() {
        return movieTitle;
    }

    public void setMovieTitle(String movieTitle) {
        this.movieTitle = movieTitle;
    }

    public Long getMovieId() {
        return movieId;
    }

    public void setMovieId(Long movieId) {
        this.movieId = movieId;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public static Comparator<Activity> dateComparator = new Comparator<Activity>() {
        @Override
        public int compare(Activity o1, Activity o2) {
            return o1.getDate().compareTo(o2.getDate());
        }
    };

}
