Feature: As a user I can select an item and look at it's detailed view

  Scenario: Look at a detailed view
    Given Database is initialized
    When I request a listing of saved items
    And I choose a single item
    Then I can view it