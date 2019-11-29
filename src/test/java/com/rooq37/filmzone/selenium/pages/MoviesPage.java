package com.rooq37.filmzone.selenium.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;
import java.util.stream.Collectors;

public class MoviesPage extends BasePage {

    private static final By selectorMoviesForm = By.xpath("//span[@class='badge bg-dark text-white py-2']");
    private static final By selectorYearsFilterMin = By.xpath("//div[@id='years_filter']//input[@name='minYear']");
    private static final By selectorYearsFilterMax = By.xpath("//div[@id='years_filter']//input[@name='maxYear']");
    private static final By selectorSearchButton = By.xpath("//button[@class='btn btn-dark btn-lg btn-block' and text()='Szukaj']");
    private static final By selectorNumberOfElements = By.id("movies:numberOfElements");
    private static final By selectorMovieTitle = By.xpath("//h3[@class='card-title']//a");
    private static final By selectorCategoriesDropdown = By.xpath("//div[@id='categories_filter']//button");
    private static final String xpathCategory = "//div[@id='categories_filter']//label[@class='form-check-label' and text()='CATEGORY_NAME']";
    private static final String xpathMovieTitle = "//h3[@class='card-title']//a[text()='MOVIE_TITLE']";
    private static final By selectorNumberOfRatingsDropdown = By.id("minVoteCount");
    private static final String xpathNumberOfRatings = "//select[@id='minVoteCount']//option[text()='NUMBER_OF_RATINGS']";

    public boolean checkIfPageIsDisplayed(){
        new WebDriverWait(getDriver(), 20).until(ExpectedConditions.visibilityOfElementLocated(selectorMoviesForm));
        return getDriver().findElement(selectorMoviesForm).isDisplayed();
    }

    public void enterInYearsRange(String min, String max){
        getDriver().findElement(selectorYearsFilterMin).clear();
        getDriver().findElement(selectorYearsFilterMax).clear();
        getDriver().findElement(selectorYearsFilterMin).sendKeys(min);
        getDriver().findElement(selectorYearsFilterMax).sendKeys(max);
    }

    public void clickSearchButton(){
        getDriver().findElement(selectorSearchButton).click();
    }

    public String getNumberOfResults(){
        return getDriver().findElement(selectorNumberOfElements).getText();
    }

    public List<String> getMovieTitlesResultList(){
        List<WebElement> elementList = getDriver().findElements(selectorMovieTitle);
        return elementList.stream().map(WebElement::getText).collect(Collectors.toList());
    }

    public void selectCategory(String categoryName){
        getDriver().findElement(selectorCategoriesDropdown).click();
        String xpath = xpathCategory.replaceAll("CATEGORY_NAME", categoryName);
        getDriver().findElement(By.xpath(xpath)).click();
    }

    public void selectNumberOfRatings(String numberOfRatings){
        getDriver().findElement(selectorNumberOfRatingsDropdown).click();
        String xpath = xpathNumberOfRatings.replaceAll("NUMBER_OF_RATINGS", numberOfRatings);
        getDriver().findElement(By.xpath(xpath)).click();
    }

    public void openDetailViewOfMovie(String movieTitle){
        String xpath = xpathMovieTitle.replaceAll("MOVIE_TITLE", movieTitle);
        getDriver().findElement(By.xpath(xpath)).click();
    }

}
