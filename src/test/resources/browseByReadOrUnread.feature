Feature: As a user I can browse the collection by read/unread items
  Scenario: Browsing by unread items
    Given Database is initialized
    When I filter by unread
    Then I get items that are unread