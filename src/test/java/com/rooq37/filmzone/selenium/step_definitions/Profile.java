package com.rooq37.filmzone.selenium.step_definitions;

import com.rooq37.filmzone.selenium.pages.ProfilePage;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

import static org.assertj.core.api.Assertions.assertThat;

public class Profile {

    private ProfilePage profilePage;

    public Profile(){
        this.profilePage = new ProfilePage();
    }

    @Then("^Sprawdź czy na liście ocenionych filmów znajduje się film \"([^\"]*)\"$")
    public void checkIfListOfRatedMoviesContainsMovie(String movieTitle) {
        assertThat(profilePage.checkIfListContains(movieTitle)).isTrue();
    }

    @Then("^Sprawdź czy na liście ocenionych filmów \"([^\"]*)\" otrzymał ocenę \"([^\"]*)\"$")
    public void checkIfMovieOnTheListHasRating(String movieName, String expectedRating) {
        assertThat(profilePage.getRatingOfMovie(movieName)).describedAs("Ocena filmu " + movieName).isEqualTo(expectedRating);
    }

    @When("^Kliknij przycisk \"([^\"]*)\" na stronie profilu użytkownika$")
    public void clickButton(String buttonName) {
        switch (buttonName){
            case "Obserwuj":
                profilePage.clickFollowButton();
                break;
        }
    }

}
