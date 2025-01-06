
Feature: LogIn

Scenario Outline: Log In

  Given the user is in the index page
  When the user clics the Cuenta button
  And the user fills the Log In camps
  And the user clics Log In
  Then goes to the my account page
