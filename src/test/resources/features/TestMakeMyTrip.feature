Feature: Launch MakeMyTrip Website

  Scenario: MakeMyTrip page is loaded
    Given The browser is launched with "https://www.makemytrip.com"
    When The Account Creation page is loaded
    Then Validate the header of the page

  Scenario: Book hotel at Bangalore
    Given The browser is launched with "https://www.makemytrip.com"
    And User has navigated to "Hotels" tab
    When The User enters "Bangalore" as City
    Then Click on Search Button

    @SmokeTest
  Scenario Outline: Book bus functionality for different locations
    Given The browser is launched with "https://www.makemytrip.com"
    And User has navigated to "Bus" tab
    When The user enters "<from>" location and "<to>" location
    And I click on Search
    Then User should be able to view the list of available buses
    Examples:
      | from  | to  |
      | Chennai | Bangalore |
      | Bangalore | Hyderabad |