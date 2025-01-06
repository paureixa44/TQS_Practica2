Feature: Add Product to Cart

Scenario: Add a product to the cart

  Given the user is in the index page
  And the user searches for "proteina"
  When the user selects a product from the results
  And the user clicks the Comprar button
  Then the product is added to the cart
  

