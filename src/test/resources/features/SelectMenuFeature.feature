Feature: Validate Select Menu functionality
  Scenario Outline: Verify user is able to select the values from dropdown
    Given The user has navigated to "https://demoqa.com/select-menu"
    When The user is able to see the "Select Menu" title
    Then User should be able to select the values from dropdown "<dropdown1>" "<dropdown2>" "<dropdown3>"
    Examples:
    | dropdown1 | dropdown2 | dropdown3 |
    | Group 2, option 2 | Ms. | Green   |
    | Group 1, option 2 | Mr. | Blue   |
    | A root option | Dr. | Aqua   |

