Feature: Validate the Menu options available in the Home Page
  Background:
    Given The browser is launched with "https://www.makemytrip.com"
    And The Account Creation page is loaded

  Scenario Outline: User is able to change Country/Currency/Language
    Given The user has clicked on the Country selection Menu
    And The selection window has been opened with "Country,Currency,Language"
    When The user has selected the options "<Country>" "<Currency>" "<Language>"
    And The user has clicked on Apply
    Then The page should be changed with the selections accordingly "<currencyShort>"

    Examples:
      |Country  |Currency |Language |currencyShort |
      |Global   |US Dollar|English  |INR           |
      |United Arab Emirates |United Arab Emirates Dirham  |Arabic  |AED |
      |India                | Indian Rupee                |Tamil   |INR |
      |India                | Indian Rupee                |English |INR |

    Scenario: User selects MyBiz Menu
      Given The user hovers on the myBiz Menu
      When The Popup has been displayed
      Then User validating the title of the popup

