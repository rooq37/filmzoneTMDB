package com.rooq37.filmzone.profiles;

import com.rooq37.filmzone.commons.MovieListElement;
import javafx.util.Pair;

import java.util.Date;
import java.util.List;

public class ProfileForm {

    private String nickname;
    private Date registerDate;
    private int numberOfRatedMovies;
    private int numberOfComments;
    private List<Pair<MovieListElement, Integer>> ratedMovies;

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public Date getRegisterDate() {
        return registerDate;
    }

    public void setRegisterDate(Date registerDate) {
        this.registerDate = registerDate;
    }

    public int getNumberOfRatedMovies() {
        return numberOfRatedMovies;
    }

    public void setNumberOfRatedMovies(int numberOfRatedMovies) {
        this.numberOfRatedMovies = numberOfRatedMovies;
    }

    public int getNumberOfComments() {
        return numberOfComments;
    }

    public void setNumberOfComments(int numberOfComments) {
        this.numberOfComments = numberOfComments;
    }

    public List<Pair<MovieListElement, Integer>> getRatedMovies() {
        return ratedMovies;
    }

    public void setRatedMovies(List<Pair<MovieListElement, Integer>> ratedMovies) {
        this.ratedMovies = ratedMovies;
    }

}
