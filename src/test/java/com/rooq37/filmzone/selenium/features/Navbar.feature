Feature: Pasek nawigacyjny

  Scenario: Test menu
    Given Otwórz stronę główną
    Then sprawdź czy menu zawiera opcję "Strona główna"
    And sprawdź czy menu zawiera opcję "Filmy"
    And sprawdź czy menu zawiera opcję "Rankingi"
    And sprawdź czy menu zawiera opcję "Regulamin"