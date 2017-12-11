Feature: As a user I can browse the collection by read/unread items
  Scenario: Browsing by read items
    Given Database is initialized
    When I filter by read
    Then I get items that are read