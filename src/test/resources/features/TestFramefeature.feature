Feature: Validate Offers section
  Background:
    Given The browser is launched with "https://www.makemytrip.com"
    And User has navigated to "Flights" tab


Scenario: Validate user is able to switch to frames
    Given The user is able to see Myra icon
    And The user has clicked on it
    When The Chat window is opened
    Then user should be able to type in any questions