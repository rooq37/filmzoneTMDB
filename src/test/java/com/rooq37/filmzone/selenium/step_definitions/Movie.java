package com.rooq37.filmzone.selenium.step_definitions;

import com.rooq37.filmzone.selenium.pages.MoviePage;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

import static org.assertj.core.api.Assertions.assertThat;

public class Movie {

    private MoviePage moviePage;

    public Movie(){
        this.moviePage = new MoviePage();
    }

    @Then("^Sprawdź czy pole \"([^\"]*)\" zawiera tekst \"([^\"]*)\"$")
    public void checkIfFieldContainsText(String fieldName, String expectedText) {
        switch (fieldName){
            case "Tytuł":
                assertThat(moviePage.getTitle()).describedAs("Tytuł").isEqualTo(expectedText);
                break;
            case "Ocena":
                assertThat(moviePage.getRating()).describedAs("Ocena").isEqualTo(expectedText);
                break;
            case "Kategorie":
                assertThat(moviePage.getCategories()).describedAs("Kategorie").contains(expectedText);
                break;
            case "Opis":
                assertThat(moviePage.getDescription()).describedAs("Opis").contains(expectedText);
                break;
            case "Rok produkcji":
            case "Czas trwania":
            case "Reżyseria":
            case "Scenariusz":
            case "Produkcja":
                assertThat(moviePage.getFeature(fieldName)).describedAs(fieldName).contains(expectedText);
                break;
            case "Liczba ocen":
                assertThat(moviePage.getNumberOfRatings()).describedAs("Liczba ocen").contains(expectedText);
                break;
            case "Liczba osób, które chcą obejrzeć":
                assertThat(moviePage.getNumberOfPeopleWhoWantToWatch()).describedAs("Liczba osób, które chcą obejrzeć").contains(expectedText);
                break;
            case "Obsada":
                assertThat(moviePage.getCastPairs()).describedAs("Obsada").contains(expectedText);
                break;

            default:
                throw new IllegalArgumentException("Nie ma takiego pola jak " + fieldName + "na stronie Film.");
        }
    }

    @When("^Dodaj nowy komentarz o treści \"([^\"]*)\"$")
    public void addNewComment(String content) {
        moviePage.enterInNewCommentArea(content);
        moviePage.clickAddCommentButton();
    }

    @Then("^Sprawdź czy lista komentarzy zawiera komentarz użytkownika \"([^\"]*)\" o treści \"([^\"]*)\"$")
    public void checkIfListOfCommentsContainsComment(String username, String content) {
        assertThat(moviePage.checkIfCommentExists(username, content)).isTrue();
    }

    @When("^Oceń film na ocenę \"([^\"]*)\"$")
    public void rateMovie(String rating) {
        moviePage.clickRating(rating);
    }

    @When("^Dodaj film do nowej listy o nazwie \"([^\"]*)\"$")
    public void addMovieToNewList(String listName) {
        moviePage.enterNameOfNewList(listName);
        moviePage.clickCreateNewListButton();
    }

    @Then("^Sprawdź czy film znajduje się na liście \"([^\"]*)\"$")
    public void checkIfMovieIsOnTheList(String listName) {
        assertThat(moviePage.getListNames()).describedAs("Listy na których znajduje się film").contains(listName);
    }

}
