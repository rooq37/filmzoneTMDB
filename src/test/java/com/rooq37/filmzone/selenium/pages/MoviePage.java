package com.rooq37.filmzone.selenium.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class MoviePage extends BasePage {

    private static final By selectorTitle = By.id("moviePage:movieTitle");
    private static final By selectorRating = By.id("moviePage:movieRating");
    private static final By selectorCategories = By.xpath("//a[@class='badge badge-pill badge-dark' and contains(text(),'#')]");
    private static final By selectorDescription = By.id("moviePage:movieDescription");
    private static final String xpathFeatures = "//span[contains(text(),'FEATURE_NAME')]";

    private static final By selectorNumberOfRatings = By.id("moviePage:numberOfRatings");
    private static final By selectorNumberOfPeopleWhoWantToWatch = By.id("moviePage:numberOfPeopleWhoWantToWatch");

    private static final By selectorActors = By.xpath("//h4[text()='Obsada']/ancestor::div//div[@class='col'][1]/p");
    private static final By selectorCharacters = By.xpath("//h4[text()='Obsada']/ancestor::div//div[@class='col'][2]/p");

    public String getTitle(){
        return getDriver().findElement(selectorTitle).getText();
    }

    public String getRating(){
        return getDriver().findElement(selectorRating).getText();
    }

    public List<String> getCategories(){
        List<WebElement> elements = getDriver().findElements(selectorCategories);
        return elements.stream().map(WebElement::getText).collect(Collectors.toList());
    }

    public String getDescription(){
        return getDriver().findElement(selectorDescription).getText();
    }

    public String getFeature(String featureName){
        String xpath = xpathFeatures.replaceAll("FEATURE_NAME", featureName);
        return getDriver().findElement(By.xpath(xpath)).getText();
    }

    public String getNumberOfRatings(){
        return getDriver().findElement(selectorNumberOfRatings).getText();
    }

    public String getNumberOfPeopleWhoWantToWatch(){
        return getDriver().findElement(selectorNumberOfPeopleWhoWantToWatch).getText();
    }

    public List<String> getCastPairs(){
        List<String> actors = getDriver().findElements(selectorActors).stream().map(WebElement::getText).collect(Collectors.toList());
        List<String> characters = getDriver().findElements(selectorCharacters).stream().map(WebElement::getText).collect(Collectors.toList());
        List<String> resultList = new ArrayList<>();
        for(int i = 0; i < actors.size(); i++)
            resultList.add(actors.get(i) + " - " + characters.get(i));
        return resultList;
    }

}
