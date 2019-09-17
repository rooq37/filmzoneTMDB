package com.rooq37.filmzone.entities;

import javax.persistence.*;

@Entity
@Table(name = "CATEGORY")
public class CategoryEntity {

    @Id
    @GeneratedValue
    private Long id;

    @Column(name = "name")
    private String name;

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

}
