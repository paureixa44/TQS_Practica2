
Feature: CreateAccount

Scenario Outline: Create Account

  Given the user is in the index page
  When the user clics the Cuenta button
  And the user clicks the Registro button
  And the user fills the required camps
  And the user clics Continuar
  Then goes to the my account page
