package com.rooq37.filmzone.selenium.pages;

public class HomePage extends BasePage{

    private static final String URL = "http://localhost:22140";

    public void openHomePage(){
        getDriver().get(URL);
    }

}
