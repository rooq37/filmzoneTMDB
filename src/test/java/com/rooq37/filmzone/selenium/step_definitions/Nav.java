package com.rooq37.filmzone.selenium.step_definitions;

import com.rooq37.filmzone.selenium.IntegrationTest;
import com.rooq37.filmzone.selenium.pages.NavPage;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

import static org.assertj.core.api.Assertions.assertThat;

public class Nav extends IntegrationTest {

    private NavPage navPage;

    public Nav(){
        this.navPage = new NavPage();
    }

    @Then("^sprawdź czy menu zawiera opcję \"([^\"]*)\"$")
    public void checkIfMenuContainsOption(String expectedOption) {
        assertThat(navPage.getMenuOptions()).describedAs("Opcje w menu").contains(expectedOption);
    }

    @When("^Idź do opcji menu \"([^\"]*)\"$")
    public void goToMenuOption(String menuOptionName) {
        navPage.goToMenuOption(menuOptionName);
    }

    @When("^Wpisz w wyszukiwarkę na pasku tytuł \"([^\"]*)\" i kliknij Szukaj$")
    public void searechMovieByName(String movieName) {
        navPage.searchMovieByName(movieName);
    }

}
