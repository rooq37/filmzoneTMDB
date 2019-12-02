package com.rooq37.filmzone.selenium.step_definitions;

import com.rooq37.filmzone.selenium.pages.UserSearchPage;
import cucumber.api.java.en.When;

public class UserSearch {

    private UserSearchPage userSearchPage;

    public UserSearch(){
        this.userSearchPage = new UserSearchPage();
    }

    @When("^Wyszukaj użytkownika o nazwie \"([^\"]*)\"$")
    public void searchForUser(String username) {
        userSearchPage.enterTextIntoUsernameInput(username);
        userSearchPage.clickSearchButton();
    }

    @When("^Przejdź do profilu użytkownika o nazwie \"([^\"]*)\"$")
    public void goToProfileWithName(String username) {
        userSearchPage.goToProfileWithName(username);
    }

}
