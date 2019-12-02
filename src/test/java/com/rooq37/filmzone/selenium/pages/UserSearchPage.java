package com.rooq37.filmzone.selenium.pages;

import org.openqa.selenium.By;

public class UserSearchPage extends BasePage {

    private static final By selectorUsernameInput = By.xpath("//input[@name='username']");
    private static final By selectorSearchButton = By.xpath("//button[@class='btn btn-dark my-2 my-sm-0' and text()='Szukaj']");

    private static final String xpathGoToProfile = "//div[@class='card text-white bg-dark' and contains(.,'PROFILE_NAME')]//a";

    public void enterTextIntoUsernameInput(String text){
        getDriver().findElement(selectorUsernameInput).clear();
        getDriver().findElement(selectorUsernameInput).sendKeys(text);
    }

    public void clickSearchButton(){
        getDriver().findElement(selectorSearchButton).click();
    }

    public void goToProfileWithName(String username){
        String xpath = xpathGoToProfile.replaceAll("PROFILE_NAME", username);
        By selector = By.xpath(xpath);
        getDriver().findElement(selector).click();
    }

}
