package com.rooq37.filmzone.selenium.pages;

import org.openqa.selenium.By;

public class RegisterPage extends BasePage {

    private static final String FIELD_XPATH = "//input[@placeholder='PLACEHOLDER']";
    private static final By selectorTermsOfUseCheckbox = By.id("terms_of_use_check");
    private static final By selectorRegisterButton = By.xpath("//button[text()='Zarejestruj się']");

    public void enterInField(String fieldName, String value){
        By selector = By.xpath(FIELD_XPATH.replaceAll("PLACEHOLDER", fieldName));
        getDriver().findElement(selector).clear();
        getDriver().findElement(selector).sendKeys(value);
    }

    public void clickTermsOfUseCheckbox(){
        getDriver().findElement(selectorTermsOfUseCheckbox).click();
    }

    public void clickRegisterButton(){
        getDriver().findElement(selectorRegisterButton).click();
    }

}
