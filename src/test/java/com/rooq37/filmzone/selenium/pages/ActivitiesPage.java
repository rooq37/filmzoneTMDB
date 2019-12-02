package com.rooq37.filmzone.selenium.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.List;
import java.util.stream.Collectors;

public class ActivitiesPage extends BasePage {

    private static final By selectorCard = By.xpath("//div[@class='card-body']");

    public List<String> getTextFromCards(){
        return getDriver().findElements(selectorCard).stream().map(WebElement::getText).collect(Collectors.toList());
    }

}
