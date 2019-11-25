Feature: Film

  Scenario: Sprawdź widok szczegółowy filmu
    Given Otwórz stronę główną
    When Wpisz w wyszukiwarkę na pasku tytuł "Ojciec chrzestny" i kliknij Szukaj
    Then Sprawdź czy liczba wyników wynosi "1"
    And Sprawdź czy lista wyników zawiera film "Ojciec chrzestny (1972)"
    When Otwórz widok szczegółowy filmu o tytule "Ojciec chrzestny (1972)"
    Then Sprawdź czy strona Filmy została wyświetlona
    And Sprawdź czy pole "Tytuł" zawiera tekst "Ojciec chrzestny"
    And Sprawdź czy pole "Ocena" zawiera tekst "10,00"
    And Sprawdź czy pole "Kategorie" zawiera tekst "#dramat"
    And Sprawdź czy pole "Kategorie" zawiera tekst "#gangsterski"
    And Sprawdź czy pole "Opis" zawiera tekst "Starzejący się patriarcha dynastii przestępczości zorganizowanej przekazuje kontrolę nad swoim tajnym imperium niechętnemu synowi."
    And Sprawdź czy pole "Rok produkcji" zawiera tekst "1972"
    And Sprawdź czy pole "Czas trwania" zawiera tekst "175 min"
    And Sprawdź czy pole "Reżyseria" zawiera tekst "Francis Ford Coppola"
    And Sprawdź czy pole "Scenariusz" zawiera tekst "Francis Ford Coppola"
    And Sprawdź czy pole "Scenariusz" zawiera tekst "Mario Puzo"
    And Sprawdź czy pole "Produkcja" zawiera tekst "USA"
    And Sprawdź czy pole "Liczba ocen" zawiera tekst "1"
    And Sprawdź czy pole "Liczba osób, które chcą obejrzeć" zawiera tekst "0"
    And Sprawdź czy pole "Obsada" zawiera tekst "Marlon Brando - Don Vito Corleone"
    And Sprawdź czy pole "Obsada" zawiera tekst "Al Pacino - Michael Corleone"
    And Sprawdź czy pole "Obsada" zawiera tekst "James Caan - Sonny Corleone"
    And Sprawdź czy pole "Obsada" zawiera tekst "Richard S. Castellano - Peter Clemenza"
    And Sprawdź czy pole "Obsada" zawiera tekst "Robert Duvall - Tom Hagen"
