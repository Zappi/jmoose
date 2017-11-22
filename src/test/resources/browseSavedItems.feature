Feature: As a user I can browse the collection of saved items

  Scenario: Browse items
    Given items are stored in database
    When I request a listing of saved items
    Then items should be displayed

