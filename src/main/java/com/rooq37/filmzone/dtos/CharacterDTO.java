package com.rooq37.filmzone.dtos;

public class CharacterDTO {

    private PersonDTO actor;
    private String role;

    public CharacterDTO(PersonDTO actor, String role) {
        this.actor = actor;
        this.role = role;
    }

    public PersonDTO getActor() {
        return actor;
    }

    public void setActor(PersonDTO actor) {
        this.actor = actor;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

}
