Feature: As a user I can add one or many comments to items
  Scenario: Adding a comment to an item
    Given Database is initialized
    When I add a comment to an item
    Then Comment is added