package com.rooq37.filmzone.selenium.step_definitions;

import com.rooq37.filmzone.selenium.IntegrationTest;
import com.rooq37.filmzone.selenium.pages.HomePage;
import cucumber.api.java.en.Given;

import static org.assertj.core.api.Assertions.assertThat;

public class Home extends IntegrationTest {

    private HomePage homePage;

    public Home(){
        homePage = new HomePage();
    }

    @Given("^Otwórz stronę główną$")
    public void openHomePage() {
        homePage.openHomePage();
    }

}
