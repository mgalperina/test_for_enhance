Feature: Testing Trade Me Motors UI

  Scenario: Verify Used Cars category has more than one search result

    Given Open Firefox browser

    When I go to Motors in the Trade Me website

    And I open "Used cars" category

    Then I see at least one search result



  Scenario: Check the make "Kia" exists in a Used Cars category

    Given Open Firefox browser

    When I go to Motors in the Trade Me website

    And I open "Used cars" category

    Then There is Kia make displayed


  @multiple
  Scenario Outline: Verify attributes exist in the listing

    Given Open Firefox browser

    When I go to Motors in the Trade Me website

    And I open "Motorbikes" category

    And I open "Other" category

    And I open a listing

    Then I can see the following "<attribute>" on the page

    Examples:
    | attribute  |
    | Kilometres  |
    | Make        |
    | Model       |
    | Engine size |
    | Year        |


