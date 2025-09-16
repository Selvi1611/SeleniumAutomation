Feature: Validate Upload/Download Functionality
  Scenario: User has to upload file from the local desktop
    Given The user has navigated to "https://demoqa.com/upload-download"
    When The user has clicked on Choose File button
    And The path of the file has been entered
    Then Uploaded file path should be displayed on the UI