package com.rooq37.filmzone.commons;

public class CastPair {

    private String actorName;
    private String characterName;

    public CastPair(String actorName, String characterName) {
        this.actorName = actorName;
        this.characterName = characterName;
    }

    public String getActorName() {
        return actorName;
    }

    public void setActorName(String actorName) {
        this.actorName = actorName;
    }

    public String getCharacterName() {
        return characterName;
    }

    public void setCharacterName(String characterName) {
        this.characterName = characterName;
    }
}
