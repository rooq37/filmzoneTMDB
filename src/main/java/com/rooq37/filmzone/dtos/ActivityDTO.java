package com.rooq37.filmzone.dtos;

import java.util.Comparator;
import java.util.Date;

public class ActivityDTO {

    private Date date;
    private ImageDTO cover;
    private String movieTitle;
    private Integer movieId;
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

    public Integer getMovieId() {
        return movieId;
    }

    public void setMovieId(Integer movieId) {
        this.movieId = movieId;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public static Comparator<ActivityDTO> dateComparator = new Comparator<ActivityDTO>() {
        @Override
        public int compare(ActivityDTO o1, ActivityDTO o2) {
            return o1.getDate().compareTo(o2.getDate());
        }
    };

}
