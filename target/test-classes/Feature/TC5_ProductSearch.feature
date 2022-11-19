@ProductSearch
Feature: ProductSearch Module API automation

  Scenario Outline: Product Search from the application through api
    Given User add header for searchProduct endpoint
    When User should add request body for searchProduct "<text>"
    And User send "POST" request for searchProduct endpoint
    Then User verify the status code is 200
    Then User verify the searchProduct response message matches "OK"

    Examples: 
      | text |
      | nuts |
