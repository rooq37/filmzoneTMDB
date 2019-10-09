package com.rooq37.filmzone.selenium.step_definitions;

import com.rooq37.filmzone.selenium.IntegrationTest;
import com.rooq37.filmzone.selenium.pages.MoviesPage;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

import static org.assertj.core.api.Assertions.assertThat;

public class Movies extends IntegrationTest {

    private MoviesPage moviesPage;

    public Movies(){
        this.moviesPage = new MoviesPage();
    }

    @Then("^Sprawdź czy strona Filmy została wyświetlona$")
    public void checkIfMoviesPageIsDisplayed() {
        assertThat(moviesPage.checkIfPageIsDisplayed()).describedAs("Czy strona Filmy jest wyświetlona").isTrue();
    }

    @When("^Wpisz w przedział \"([^\"]*)\" od \"([^\"]*)\" do \"([^\"]*)\"$")
    public void enterInRange(String rangeName, String min, String max) {
        switch (rangeName){
            case "Lata produkcji":
                moviesPage.enterInYearsRange(min, max);
                break;
            case "Ocena":
                moviesPage.enterInRatingRange(min, max);
                break;

            default:
                throw new IllegalArgumentException("Nie ma takiego filtra jak " + rangeName + " na stronie Filmy");
        }
    }

    @When("^Kliknij przycisk \"([^\"]*)\"$")
    public void clickButton(String buttonName) {
        switch (buttonName){
            case "Szukaj":
                moviesPage.clickSearchButton();
                break;

            default:
                throw new IllegalArgumentException("Nie ma takiego przycisku jak " + buttonName + " na stronie Filmy");
        }
    }

    @Then("^Sprawdź czy liczba wyników wynosi \"([^\"]*)\"$")
    public void checkIfNumberOfResultsIsCorrect(String expectedNumOfResults) {
        assertThat(moviesPage.getNumberOfResults()).describedAs("Liczba wyników").isEqualTo(expectedNumOfResults);
    }

    @Then("^Sprawdź czy lista wyników zawiera film \"([^\"]*)\"$")
    public void checkIfResultsListContainsMovie(String movieName) {
        assertThat(moviesPage.getMovieTitlesResultList()).describedAs("Tytułu filmów z listy wyników").contains(movieName);
    }

    @When("^Zaznacz w filtrze \"([^\"]*)\" opcję \"([^\"]*)\"$")
    public void chooseInFilterOption(String filterName, String optionName) {
        switch (filterName){
            case "Kategorie":
                moviesPage.selectCategory(optionName);
                break;
        }
    }

    @When("^Otwórz widok szczegółowy filmu o tytule \"([^\"]*)\"$")
    public void openDetailViewOfMovieWithTitle(String movieTitle) {
        moviesPage.openDetailViewOfMovie(movieTitle);
    }

}
