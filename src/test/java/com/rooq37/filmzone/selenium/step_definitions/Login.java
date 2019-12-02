package com.rooq37.filmzone.selenium.step_definitions;

import com.rooq37.filmzone.selenium.pages.LoginPage;
import cucumber.api.java.en.When;

public class Login {

    private LoginPage loginPage;

    public Login(){
        this.loginPage = new LoginPage();
    }

    @When("^Wpisz w pole \"([^\"]*)\" wartość \"([^\"]*)\" na stronie Logowanie$")
    public void enterValueIntoField(String fieldName, String value) {
        loginPage.enterInField(fieldName, value);
    }

    @When("^Kliknij przycisk \"([^\"]*)\" na stronie Logowanie$")
    public void clickButtonOnTheLoginPage(String buttonName) {
        switch (buttonName){
            case "Zaloguj się":
                loginPage.clickLoginButton();
                break;
        }
    }


}
