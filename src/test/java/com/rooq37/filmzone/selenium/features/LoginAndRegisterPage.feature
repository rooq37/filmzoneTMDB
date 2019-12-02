Feature: Rejestracja i logowanie

  @Rejestracja
  Scenario: Rejestracja
    Given Otwórz stronę główną
    When Idź do opcji menu "Rejestracja"
    And Wpisz w pole "Nick" wartość "test" na stronie Rejestracja
    And Wpisz w pole "Adres email" wartość "test@testing.pl" na stronie Rejestracja
    And Wpisz w pole "Hasło" wartość "password" na stronie Rejestracja
    And Wpisz w pole "Powtórz hasło" wartość "password" na stronie Rejestracja
    And Zaznacz checkbox "Zapoznałem się z regulaminem" na stronie Rejestracja
    And Kliknij przycisk "Zarejestruj się" na stronie Rejestracja
    Then Sprawdź czy pojawił się komunikat o treści "Konto zostało pomyślnie utworzone!"

  @Logowanie
  Scenario: Logowanie
    Given Otwórz stronę główną
    When Idź do opcji menu "Logowanie"
    And Wpisz w pole "Adres email" wartość "user@email.pl" na stronie Logowanie
    And Wpisz w pole "Hasło" wartość "pass" na stronie Logowanie
    And Kliknij przycisk "Zaloguj się" na stronie Logowanie
    Then Sprawdź czy użytkownik o nazwie "user@email.pl" jest zalogowany