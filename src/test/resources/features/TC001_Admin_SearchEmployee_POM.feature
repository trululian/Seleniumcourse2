Feature: TC001_Admin_SearchEmployee_POM
  Scenario: Search an user by typing the username in the searchbox, then validate that user is displayed in result box.
    Given that I opened the browser at automationpractice page
    When I look for 'dress' and select any displayed result to go to the shopping cart
    Then should the shopping cart show the product is not 'null'