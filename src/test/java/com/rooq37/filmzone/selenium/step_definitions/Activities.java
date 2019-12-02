package com.rooq37.filmzone.selenium.step_definitions;

import com.rooq37.filmzone.selenium.pages.ActivitiesPage;
import cucumber.api.java.en.Then;

import static org.assertj.core.api.Assertions.assertThat;

public class Activities {

    private ActivitiesPage activitiesPage;

    public Activities(){
        this.activitiesPage = new ActivitiesPage();
    }

    @Then("^Sprawdź czy lista aktywności zawiera aktywność \"([^\"]*)\" filmu \"([^\"]*)\" przez użytkownika \"([^\"]*)\"$")
    public void checkIfListContainsActivities(String type, String movieTitle, String username) {
        boolean correct = false;
        for(String activity : activitiesPage.getTextFromCards()){
            switch (type){
                case "Ocena":{
                    if(activity.contains("ocenił") && activity.contains(username) && activity.contains(movieTitle)) correct = true;
                }
                break;
                case "Komentarz":{
                    if(activity.contains("dodał komentarz") && activity.contains(username) && activity.contains(movieTitle)) correct = true;
                }
                break;
            }
        }
        assertThat(correct).isTrue();
    }

}
