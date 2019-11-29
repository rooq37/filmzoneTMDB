Feature: Filmy

  @Filmy
  Scenario Outline: Wyszukaj film po latach produkcji i kategorii
    Given Otwórz stronę główną
    When Idź do opcji menu "Filmy"
    Then Sprawdź czy strona Filmy została wyświetlona
    When Wpisz w przedział "Lata produkcji" od "<Lata_produkcji_od>" do "<Lata_produkcji_do>"
    And Zaznacz w filtrze "Kategorie" opcję "<Kategoria>"
    And Kliknij przycisk "Szukaj"
    Then Sprawdź czy liczba wyników wynosi "<Liczba_wyników>"
    And Sprawdź czy lista wyników zawiera film "<Tytul>"

    Examples:
      | Lata_produkcji_od | Lata_produkcji_do | Kategoria   | Liczba_wyników | Tytul                  |
      | 2001              | 2003              | Historyczny | 10             | Hero (2002)            |
      | 1900              | 1930              | Dramat      | 7              | Brzdąc (1921)          |
      | 2000              | 2000              | Kryminał    | 14             | Requiem dla snu (2000) |

  @Filmy
  Scenario: Wyszukaj film po nazwie
    Given Otwórz stronę główną
    When Wpisz w wyszukiwarkę na pasku tytuł "Ojciec Chrzestny" i kliknij Szukaj
    Then Sprawdź czy liczba wyników wynosi "4"
    And Sprawdź czy lista wyników zawiera film "Ojciec Chrzestny (1972)"