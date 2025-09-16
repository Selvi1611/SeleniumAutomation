Feature: Read data from Json and input in the OrangeHRM UI
  Background:
    Given User is logged in to OrangeHRM portal
    When The user has navigated to Admin tab
    Scenario: Add Users in Admin Portal
      Given The user clicked on Add Users button
      When The Add User page is loaded
      And User has to enter the details "<fname>" "<lname>" "<mname>" "<empid>"
      Then User has to click on Save button



