# Author
# Date
# Description

@SmokeFeature
Feature: login functionality test

  @smoketest
  Scenario: Check login with valid credentials
    Given user is on login page
    When user enters valid username and password
    And clicks on login button
    Then user is navigated to the home page