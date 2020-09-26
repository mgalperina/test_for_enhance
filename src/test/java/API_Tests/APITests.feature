Feature: Testing Trade Me Motors API

  Scenario: Verify Used Cars category has more than one search result

    Given I perform GET operation for "/get"

    When I query Used cars category

    Then At least one search result is returned


  Scenario: Verify Used cars category has Kia make

    When I query Used cars category search

    Then I can see Kia make is returned


  Scenario: Verify attributes for a listing are returned

      When I query a listing in Used cars category

      Then The attributes are returned