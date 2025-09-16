Feature: Validate Offers section
  Background:
    Given The browser is launched with "https://www.makemytrip.com"
    And The Account Creation page is loaded
    Scenario: Verify Offers Section
      Given The user has navigated to Offers section
      And The user has clicked on Trains tab
      When The user clicks on an Book Now in Offer
      Then The Offer has to load in a new tab