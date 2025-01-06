
Feature: HelpCenter

Scenario Outline: Go to the help center

  Given the user is in the index page
  When the user clics Ayuda
  Then goes to the help center
