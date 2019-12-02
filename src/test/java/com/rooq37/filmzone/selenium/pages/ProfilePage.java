package com.rooq37.filmzone.selenium.pages;

import org.openqa.selenium.By;

public class ProfilePage extends BasePage {

    private static final String xpathMovieOnList = "//div[@class='card text-white bg-dark' and contains(.,'MOVIE_TITLE')]";
    private static final String xpathRatingOfMovie = "//div[@class='card text-white bg-dark' and contains(.,'MOVIE_TITLE')]//a/span[2]";
    private static final By selectorFollowButton = By.xpath("//button[text()='Obserwuj']");

    public boolean checkIfListContains(String movieName){
        String xpath = xpathMovieOnList.replaceAll("MOVIE_TITLE", movieName);
        return getDriver().findElements(By.xpath(xpath)).size() > 0;
    }

    public String getRatingOfMovie(String movieName){
        String xpath = xpathRatingOfMovie.replaceAll("MOVIE_TITLE", movieName);
        return getDriver().findElement(By.xpath(xpath)).getText();
    }

    public void clickFollowButton(){
        getDriver().findElement(selectorFollowButton).click();
    }

}
