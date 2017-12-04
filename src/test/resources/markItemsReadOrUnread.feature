Feature: As a user I want to mark items read or unread

  Scenario: Marking an item read
    Given Database is initialized
    When I request a listing of saved items
    And I choose a single item
    And I mark it read
    Then Chosen item is marked read