package com.rooq37.filmzone.selenium;

import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(features = "src/test/java/com/rooq37/filmzone/selenium/features")
public class CucumberRunner extends IntegrationTest{

}
