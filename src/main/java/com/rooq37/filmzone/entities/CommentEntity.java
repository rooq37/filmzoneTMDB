package com.rooq37.filmzone.entities;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "COMMENT")
public class CommentEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "id_author")
    private UserEntity user;

    @Column(name = "content")
    private String content;

    @Column(name = "date")
    private Date date;

    @Column(name = "id_tmdb_movie")
    private Integer tmdbMovieId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public UserEntity getUser() {
        return user;
    }

    public void setUser(UserEntity user) {
        this.user = user;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Integer getTmdbMovieId() {
        return tmdbMovieId;
    }

    public void setTmdbMovieId(Integer tmdbMovieId) {
        this.tmdbMovieId = tmdbMovieId;
    }

}
