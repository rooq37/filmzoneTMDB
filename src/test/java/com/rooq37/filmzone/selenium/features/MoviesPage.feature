Feature: Filmy

  Scenario Outline: Wyszukaj film po latach produkcji i ocenie
    Given Otwórz stronę główną
    When Idź do opcji menu "Filmy"
    Then Sprawdź czy strona Filmy została wyświetlona
    When Wpisz w przedział "Lata produkcji" od "<Lata_produkcji_od>" do "<Lata_produkcji_do>"
    And Wpisz w przedział "Ocena" od "<Ocena_od>" do "<Ocena_do>"
    And Kliknij przycisk "Szukaj"
    Then Sprawdź czy liczba wyników wynosi "<Liczba_wyników>"
    And Sprawdź czy lista wyników zawiera film "<Tytul>"

    Examples:
      | Lata_produkcji_od | Lata_produkcji_do | Ocena_od | Ocena_do | Liczba_wyników | Tytul                                         |
      | 2000              | 2001              | 9        | 10       | 1              | Władca Pierścieni: Drużyna Pierścienia (2001) |
      | 1900              | 2019              | 5        | 10       | 11             | Władca Pierścieni: Drużyna Pierścienia (2001) |
      | 2000              | 2002              | 9        | 9        | 1              | Władca Pierścieni: Drużyna Pierścienia (2001) |
      | 1972              | 1972              | 1        | 10       | 1              | Ojciec chrzestny (1972)                       |
      | 2001              | 2001              | 9        | 9        | 1              | Władca Pierścieni: Drużyna Pierścienia (2001) |


  Scenario: Wyszukaj film po kategoriach
    Given Otwórz stronę główną
    When Idź do opcji menu "Filmy"
    And Zaznacz w filtrze "Kategorie" opcję "dramat"
    And Zaznacz w filtrze "Kategorie" opcję "gangsterski"
    And Kliknij przycisk "Szukaj"
    Then Sprawdź czy liczba wyników wynosi "2"
    And Sprawdź czy lista wyników zawiera film "Ojciec chrzestny (1972)"
    And Sprawdź czy lista wyników zawiera film "Blow (2001)"

    When Idź do opcji menu "Filmy"
    And Zaznacz w filtrze "Kategorie" opcję "przygodowy"
    And Kliknij przycisk "Szukaj"
    Then Sprawdź czy liczba wyników wynosi "2"
    And Sprawdź czy lista wyników zawiera film "Władca Pierścieni: Drużyna Pierścienia (2001)"
    And Sprawdź czy lista wyników zawiera film "Harry Potter i Kamień Filozoficzny (2001)"


  Scenario: Wyszukaj film po nazwie
    Given Otwórz stronę główną
    When Wpisz w wyszukiwarkę na pasku tytuł "Ojciec chrzestny" i kliknij Szukaj
    Then Sprawdź czy liczba wyników wynosi "1"
    And Sprawdź czy lista wyników zawiera film "Ojciec chrzestny (1972)"