Feature: Test user is able to confirm alerts
  Scenario Outline: User has to click on Launch Alert
    Given The user has navigated to URL "https://bonigarcia.dev/selenium-webdriver-java/dialog-boxes.html"
    When The user has clicked on "<AlertType>"
    Then User has to click on the "<Action>" button
    Examples:
    | AlertType | Action |
    | Launch alert | accept |
    | Launch confirm | dismiss |
    | Launch prompt | accept   |
    | Launch modal | Save changes |