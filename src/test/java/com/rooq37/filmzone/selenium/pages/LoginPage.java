package com.rooq37.filmzone.selenium.pages;

import org.openqa.selenium.By;

public class LoginPage extends BasePage {

    private static final String FIELD_XPATH = "//input[@placeholder='PLACEHOLDER']";
    private static final By selectorLoginButton = By.xpath("//button[text()='Zaloguj się']");

    public void enterInField(String fieldName, String value){
        By selector = By.xpath(FIELD_XPATH.replaceAll("PLACEHOLDER", fieldName));
        getDriver().findElement(selector).clear();
        getDriver().findElement(selector).sendKeys(value);
    }

    public void clickLoginButton(){
        getDriver().findElement(selectorLoginButton).click();
    }

}
