Feature: As a user I can browse the collection of saved items

  Scenario: Browse items
    Given Database is initialized
    When I request a listing of saved items
    Then items should be displayed
    And item with text "Da Vinci Code" should be displayed
    And item with text "The Lord Of The Rings: One Volume1" should be displayed
    And item with text "Before We Were Yours" should be displayed
    And item with text "A Man Called Ove" should be displayed
    And item with text "Alice Isn't Dead" should be displayed
    And item with text "You Must Remember This" should be displayed
    And item with text "California's Little-Known Genocide" should be displayed
    And item with text "When Killers Leave Ciphers" should be displayed
