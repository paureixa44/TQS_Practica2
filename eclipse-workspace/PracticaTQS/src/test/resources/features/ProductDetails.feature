
Feature: ProductDetails

Scenario Outline:  
  Given the user is in the index page
  When the user enters "<article>" in the search bar
  And the user clicks the search button
  And clicks one of the products
  Then the info of the product appears

Examples:
  | article     |
  | proteina    |
  | creatina    |
  | vitamins    |
