package com.rooq37.filmzone.selenium.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
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

    private static final By selectorNewCommentArea = By.xpath("//textarea[@name='comment_content']");
    private static final By selectorAddCommentButton = By.xpath("//button[text()='Dodaj komentarz']");

    private static final String xpathComment = "//div[@class='card border-dark mb-2' and contains(.,'USERNAME') and contains(.,'CONTENT')]";

    private static final String xpathRating = "//button[@name='rating' and @value='RATING']";

    private static final By selectorNewListInput = By.xpath("//input[@name='newListName']");
    private static final By selectorCreateNewListButon = By.xpath("//button[@value='create' and text()='Utwórz']");
    private static final By selectorLists = By.xpath("//div[@id='mymovies']//ul");

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

    public void enterInNewCommentArea(String text){
        getDriver().findElement(selectorNewCommentArea).clear();
        getDriver().findElement(selectorNewCommentArea).sendKeys(text);
    }

    public void clickAddCommentButton(){
        JavascriptExecutor js = (JavascriptExecutor) getDriver();
        js.executeScript("arguments[0].scrollIntoView();", getDriver().findElement(selectorAddCommentButton));
        getDriver().findElement(selectorAddCommentButton).click();
    }

    public boolean checkIfCommentExists(String username, String content){
        String xpath = xpathComment.replaceAll("USERNAME", username);
        xpath = xpath.replaceAll("CONTENT", content);
        return getDriver().findElements(By.xpath(xpath)).size() > 0;
    }

    public void clickRating(String rating){
        String xpath = xpathRating.replaceAll("RATING", rating);
        JavascriptExecutor js = (JavascriptExecutor) getDriver();
        js.executeScript("window.scrollBy(0,500)");
        By selector = By.xpath(xpath);
        getDriver().findElement(selector).click();
    }

    public void enterNameOfNewList(String name){
        getDriver().findElement(selectorNewListInput).clear();
        getDriver().findElement(selectorNewListInput).sendKeys(name);
    }

    public void clickCreateNewListButton(){
        JavascriptExecutor js = (JavascriptExecutor) getDriver();
        js.executeScript("window.scrollBy(0,500)");
        getDriver().findElement(selectorCreateNewListButon).click();
    }

    public String getListNames(){
        return getDriver().findElement(selectorLists).getText();
    }


}
