Feature: Verify if the balance in the header is the same as the one from the response to request getMemberBalance

  Background:
    Given the user navigates to the login page
  Scenario: Check member balance from header equals the balance from getMemberBalance request
    Given the user logs in with valid username and password
    When the user closes any modals
    Then the balance in the header should match the balance from the API response of getMemberBalance
