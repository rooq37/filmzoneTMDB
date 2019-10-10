package com.rooq37.filmzone.selenium.hooks;

import com.rooq37.filmzone.selenium.pages.BasePage;
import cucumber.api.java.After;
import cucumber.api.java.Before;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;

public class Hooks {

    @Before
    public void setUp() {
        System.out.println("=====FILMZONE INTEGRATION TEST=====");
    }

    @After(order = 2)
    public void makeScreenshot() throws IOException {
        File screenshotFile = ((TakesScreenshot) BasePage.getDriver()).getScreenshotAs(OutputType.FILE);
        File destinationFile = new File("screenshot.jpg");
        FileUtils.copyFile(screenshotFile, destinationFile);
    }

    @After(order = 1)
    public void tearDown(){
        BasePage.getDriver().manage().deleteAllCookies();
        BasePage.getDriver().quit();
    }

}
