package com.rooq37.filmzone.selenium.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public abstract class BasePage {

    private static final String PROPERTY_KEY = "webdriver.gecko.driver";
    private static final String PROPERTY_VALUE = "C:/Projekty/Java/filmzone/lib/geckodriver-v0.24.0-win64/geckodriver.exe";

    private static WebDriver driver;

    public static WebDriver getDriver(){
        if(driver == null || driver.toString().contains("(null)")){
            System.setProperty(PROPERTY_KEY, PROPERTY_VALUE);
            driver = new FirefoxDriver();
        }
        return driver;
    }

}
