package com.rooq37.filmzone.dtos;

import org.springframework.beans.support.PagedListHolder;

import java.util.AbstractMap;
import java.util.Date;
import java.util.List;

public class ProfileDTO {

    private Long id;
    private String nickname;
    private String email;
    private Date registerDate;
    private int numberOfRatedMovies;
    private int numberOfComments;
    private List<AbstractMap.SimpleEntry<MovieSimpleDTO, Integer>> ratedMovies;
    private PagedListHolder<ActivityDTO> activities;
    private List<ProfileDTO> followed;
    private List<ProfileDTO> followers;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
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

    public List<AbstractMap.SimpleEntry<MovieSimpleDTO, Integer>> getRatedMovies() {
        return ratedMovies;
    }

    public void setRatedMovies(List<AbstractMap.SimpleEntry<MovieSimpleDTO, Integer>> ratedMovies) {
        this.ratedMovies = ratedMovies;
    }

    public PagedListHolder<ActivityDTO> getActivities() {
        return activities;
    }

    public void setActivities(PagedListHolder<ActivityDTO> activities) {
        this.activities = activities;
    }

    public List<ProfileDTO> getFollowed() {
        return followed;
    }

    public void setFollowed(List<ProfileDTO> followed) {
        this.followed = followed;
    }

    public List<ProfileDTO> getFollowers() {
        return followers;
    }

    public void setFollowers(List<ProfileDTO> followers) {
        this.followers = followers;
    }
}
