Feature: Film

  @Film
  Scenario: Sprawdź widok szczegółowy filmu
    Given Otwórz stronę główną
    When Wpisz w wyszukiwarkę na pasku tytuł "Ojciec chrzestny" i kliknij Szukaj
    Then Sprawdź czy liczba wyników wynosi "4"
    And Sprawdź czy lista wyników zawiera film "Ojciec Chrzestny (1972)"
    When Otwórz widok szczegółowy filmu o tytule "Ojciec Chrzestny (1972)"
    Then Sprawdź czy strona Filmy została wyświetlona
    And Sprawdź czy pole "Tytuł" zawiera tekst "Ojciec Chrzestny"
    And Sprawdź czy pole "Kategorie" zawiera tekst "#Dramat"
    And Sprawdź czy pole "Kategorie" zawiera tekst "#Kryminał"
    And Sprawdź czy pole "Opis" zawiera tekst "Akcja filmu toczy się pod koniec II wojny światowej w Ameryce. Don Vito Corleone (Marlon Brando) jest głową jednej z pięciu mafijnych rodzin w Nowym Jorku."
    And Sprawdź czy pole "Rok produkcji" zawiera tekst "1972"
    And Sprawdź czy pole "Czas trwania" zawiera tekst "175 min"
    And Sprawdź czy pole "Reżyseria" zawiera tekst "Francis Ford Coppola"
    And Sprawdź czy pole "Scenariusz" zawiera tekst "Francis Ford Coppola"
    And Sprawdź czy pole "Scenariusz" zawiera tekst "Mario Puzo"
    And Sprawdź czy pole "Produkcja" zawiera tekst "United States of America"
    And Sprawdź czy pole "Obsada" zawiera tekst "Marlon Brando - Don Vito Corleone"
    And Sprawdź czy pole "Obsada" zawiera tekst "Al Pacino - Michael Corleone"
    And Sprawdź czy pole "Obsada" zawiera tekst "James Caan - Santino 'Sonny' Corleone"
    And Sprawdź czy pole "Obsada" zawiera tekst "Richard S. Castellano - Pete Clemenza"
    And Sprawdź czy pole "Obsada" zawiera tekst "Robert Duvall - Tom Hagen"

  @Komentarze
  Scenario: Dodaj nowy komentarz
    Given Otwórz stronę główną
    When Idź do opcji menu "Logowanie"
    And Wpisz w pole "Adres email" wartość "user@email.pl" na stronie Logowanie
    And Wpisz w pole "Hasło" wartość "pass" na stronie Logowanie
    And Kliknij przycisk "Zaloguj się" na stronie Logowanie
    Then Sprawdź czy użytkownik o nazwie "user@email.pl" jest zalogowany
    When Wpisz w wyszukiwarkę na pasku tytuł "Ojciec chrzestny" i kliknij Szukaj
    Then Sprawdź czy lista wyników zawiera film "Ojciec Chrzestny (1972)"
    When Otwórz widok szczegółowy filmu o tytule "Ojciec Chrzestny (1972)"
    Then Sprawdź czy strona Filmy została wyświetlona
    When Dodaj nowy komentarz o treści "Wspaniały film"
    Then Sprawdź czy lista komentarzy zawiera komentarz użytkownika "user" o treści "Wspaniały film"

  @Oceny
  Scenario: Oceń film
    Given Otwórz stronę główną
    When Idź do opcji menu "Logowanie"
    And Wpisz w pole "Adres email" wartość "user@email.pl" na stronie Logowanie
    And Wpisz w pole "Hasło" wartość "pass" na stronie Logowanie
    And Kliknij przycisk "Zaloguj się" na stronie Logowanie
    Then Sprawdź czy użytkownik o nazwie "user@email.pl" jest zalogowany
    When Wpisz w wyszukiwarkę na pasku tytuł "Ojciec chrzestny" i kliknij Szukaj
    Then Sprawdź czy lista wyników zawiera film "Ojciec Chrzestny (1972)"
    When Otwórz widok szczegółowy filmu o tytule "Ojciec Chrzestny (1972)"
    Then Sprawdź czy strona Filmy została wyświetlona
    When Oceń film na ocenę "8"
    And Idź do opcji z menu użytkownika "Mój profil"
    Then Sprawdź czy na liście ocenionych filmów znajduje się film "Ojciec Chrzestny"
    And Sprawdź czy na liście ocenionych filmów "Ojciec Chrzestny" otrzymał ocenę "8"

  @Listy
  Scenario: Dodaj film do listy
    Given Otwórz stronę główną
    When Idź do opcji menu "Logowanie"
    And Wpisz w pole "Adres email" wartość "user@email.pl" na stronie Logowanie
    And Wpisz w pole "Hasło" wartość "pass" na stronie Logowanie
    And Kliknij przycisk "Zaloguj się" na stronie Logowanie
    Then Sprawdź czy użytkownik o nazwie "user@email.pl" jest zalogowany
    When Wpisz w wyszukiwarkę na pasku tytuł "Ojciec chrzestny" i kliknij Szukaj
    Then Sprawdź czy lista wyników zawiera film "Ojciec Chrzestny (1972)"
    When Otwórz widok szczegółowy filmu o tytule "Ojciec Chrzestny (1972)"
    Then Sprawdź czy strona Filmy została wyświetlona
    When Dodaj film do nowej listy o nazwie "Klasyka"
    Then Sprawdź czy film znajduje się na liście "Klasyka"