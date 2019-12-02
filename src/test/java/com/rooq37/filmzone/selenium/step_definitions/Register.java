package com.rooq37.filmzone.selenium.step_definitions;

import com.rooq37.filmzone.selenium.pages.RegisterPage;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

import static org.assertj.core.api.Assertions.assertThat;

public class Register {

    private RegisterPage registerPage;

    public Register(){
        this.registerPage = new RegisterPage();
    }

    @When("^Wpisz w pole \"([^\"]*)\" wartość \"([^\"]*)\" na stronie Rejestracja$")
    public void enterValueIntoField(String fieldName, String value) {
        registerPage.enterInField(fieldName, value);
    }

    @When("^Zaznacz checkbox \"([^\"]*)\" na stronie Rejestracja$")
    public void selectCheckbox(String checkboxName) {
        switch (checkboxName){
            case "Zapoznałem się z regulaminem":
                registerPage.clickTermsOfUseCheckbox();
                break;
        }
    }

    @When("^Kliknij przycisk \"([^\"]*)\" na stronie Rejestracja$")
    public void clickButtonOnTheRegisterPage(String buttonName) {
        switch (buttonName){
            case "Zarejestruj się":
                registerPage.clickRegisterButton();
                break;
        }
    }

}
