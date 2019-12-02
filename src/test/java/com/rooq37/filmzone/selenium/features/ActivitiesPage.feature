Feature: Aktywności

  @Aktywnosci
  Scenario: Obserwowanie użytkowników i aktywności
    Given Otwórz stronę główną
    When Idź do opcji menu "Logowanie"
    And Wpisz w pole "Adres email" wartość "user@email.pl" na stronie Logowanie
    And Wpisz w pole "Hasło" wartość "pass" na stronie Logowanie
    And Kliknij przycisk "Zaloguj się" na stronie Logowanie
    Then Sprawdź czy użytkownik o nazwie "user@email.pl" jest zalogowany
    When Idź do opcji z menu użytkownika "Wyszukiwarka użytkowników"
    And Wyszukaj użytkownika o nazwie "admin"
    And Przejdź do profilu użytkownika o nazwie "admin"
    And Kliknij przycisk "Obserwuj" na stronie profilu użytkownika
    And Idź do opcji z menu użytkownika "Aktywności"
    Then Sprawdź czy lista aktywności zawiera aktywność "Ocena" filmu "Pianista" przez użytkownika "admin"
    And Sprawdź czy lista aktywności zawiera aktywność "Komentarz" filmu "Pianista" przez użytkownika "admin"