package com.rooq37.filmzone.entities;

import javax.persistence.*;

@Entity
@Table(name = "MEDIA")
public class MediaEntity {

    @Id
    @GeneratedValue
    private Long id;

    @Column(name = "value")
    private String value;

    @Column(name = "type")
    private String type;

    @Column(name = "author")
    private String author;

    @Column(name = "date")
    private String date;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

}
