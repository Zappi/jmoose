Feature: As a user I can, save an item

  Scenario: Save item
    Given database is initialized
    When item is added
    Then item should be stored in database
