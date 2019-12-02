package com.rooq37.filmzone.selenium.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class NavPage extends BasePage{

    private static final By selectorMenuOption = By.xpath("//nav//*[@class='nav-item' or @class='nav-link dropdown-toggle']");
    private static final String xpathMenuOption = "//nav//a[@class='nav-link' and text()='MENU_OPTION']";
    private static final By selectorSearchInput = By.xpath("//input[@placeholder='Szukaj filmu']");
    private static final By selectorSearchButton = By.xpath("//button[@class='btn btn-outline-light my-2 my-sm-0' and text()='Szukaj']");
    private static final By selectorRegisterButton = By.xpath("//button[@class='btn btn-outline-warning ml-2 my-2 my-sm-0' and text()='Rejestracja']");
    private static final By selectorLoginButton = By.xpath("//button[@class='btn btn-outline-warning ml-2 my-2 my-sm-0' and text()='Logowanie']");
    private static final By selectorNotificationSuccess = By.xpath("//div[contains(@class,'alert alert-success alert-dismissible')]");
    private static final By selectorUserOptions = By.id("userOptions");
    private static final String xpathUserMenuOption = "//div//a[@class='dropdown-item' and text()='MENU_OPTION']";


    public List<String> getMenuOptions(){
        List<WebElement> navLinks = getDriver().findElements(selectorMenuOption);
        Set<String> menuOptions = new HashSet<>();
        for(WebElement navLink : navLinks)
            menuOptions.add(navLink.getText().trim());
        return new ArrayList<>(menuOptions);
    }

    public void goToMenuOption(String menuOption){
        String xpath = xpathMenuOption.replaceAll("MENU_OPTION", menuOption);
        getDriver().findElement(By.xpath(xpath)).click();
    }

    public void goToRegisterPage(){
        getDriver().findElement(selectorRegisterButton).click();
    }

    public void goToLoginPage(){
        getDriver().findElement(selectorLoginButton).click();
    }

    public void searchMovieByName(String movieName){
        getDriver().findElement(selectorSearchInput).clear();
        getDriver().findElement(selectorSearchInput).sendKeys(movieName);
        getDriver().findElement(selectorSearchButton).click();
    }

    public String getTextFromSuccessNotificationAlert(){
        return getDriver().findElement(selectorNotificationSuccess).getText();
    }

    public String getLoggedUserName(){
        return getDriver().findElement(selectorUserOptions).getText();
    }

    public void expandUserOptionsMenu(){
        getDriver().findElement(selectorUserOptions).click();
    }

    public void selectOptionFromUserOptionsMenu(String option){
        String xpath = xpathUserMenuOption.replaceAll("MENU_OPTION", option);
        getDriver().findElement(By.xpath(xpath)).click();
    }

}
