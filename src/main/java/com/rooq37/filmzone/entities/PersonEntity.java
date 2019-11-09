package com.rooq37.filmzone.entities;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "PERSON")
public class PersonEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "surname")
    private String surname;

    @OneToMany(mappedBy = "person")
    private Set<MoviePersonEntity> movies;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getFullName(){
        return name + " " + surname;
    }

    public Set<MoviePersonEntity> getMovies() {
        return movies;
    }

    public void setMovies(Set<MoviePersonEntity> movies) {
        this.movies = movies;
    }

}
