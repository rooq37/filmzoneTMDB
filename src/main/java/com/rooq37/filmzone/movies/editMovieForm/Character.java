package com.rooq37.filmzone.movies.editMovieForm;

public class Character {

    private Person actor;
    private String role;

    public Character(Person actor, String role) {
        this.actor = actor;
        this.role = role;
    }

    public Person getActor() {
        return actor;
    }

    public void setActor(Person actor) {
        this.actor = actor;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

}
