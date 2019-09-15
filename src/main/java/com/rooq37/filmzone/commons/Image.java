package com.rooq37.filmzone.commons;

public class Image {

    private String name;
    private String source;
    private String author;

    public Image(String name, String source, String author) {
        this.name = name;
        this.source = source;
        this.author = author;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

}
