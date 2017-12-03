Feature: As a user I can, save an item

  Scenario: Save item
    Given Database is initialized
    When Item is added
    Then Item should be stored in database
