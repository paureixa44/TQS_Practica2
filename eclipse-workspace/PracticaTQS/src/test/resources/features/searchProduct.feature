Feature: SearchProduct

Scenario Outline: Search for a product using the search bar

  Given the user is in the index page
  When the user enters "<article>" in the search bar
  And the user clicks the search button
  Then the "<article>" list appears

Examples:
  | article     |
  | proteina    |
  | creatina    |
  | vitamins    |
