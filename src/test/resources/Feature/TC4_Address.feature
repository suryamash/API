@Address
Feature: Address Module API Automation

  Scenario Outline: verify add user address to the application through api
    Given User add header and bearer authorization for accessing adduserAddress endpoint
    When user add request body for add new address "<first_name>","<last_name>","<mobile>","<apartment>","<state>","<city>","<country>","<zipcode>","<address>" and "<address_type>"
    And User send "POST" request for adduserAddress endpoint
    Then User verify the status code is 200
    Then user verify the adduserAddress response message matches "Address added successfully" and get the addressid

    Examples: 
      | first_name | last_name | mobile     | apartment | state   | city   | country | zipcode | address | address_type |
      | surya      | p         | 6369402173 | JH        | stateId | cityid |     103 |  600097 | chennai | Home         |

  Scenario Outline: verify update user address to the application through api
    Given User add header and bearer authorization for accessing updateUserAddress endpoint
    When user add request body for add update address "<address_id>","<first_name>","<last_name>","<mobile>","<apartment>","<state>","<city>","<country>","<zipcode>","<address>" and "<address_type>"
    And User send "PUT" request for updateUserAddress endpoint
    Then User verify the status code is 200
    Then user verify the updateUserAddress response message matches "Address updated successfully"

    Examples: 
      | address_id | first_name | last_name | mobile     | apartment | state   | city   | country | zipcode | address | address_type |
      | address_id | surya      | p         | 6369402173 | JH        | stateId | cityid |     103 |  600097 | chennai | Home         |

  Scenario Outline: verify Get user address to the application through api
    Given User add header and bearer authorization for accessing getUserAddress endpoint
    When User send "GET" request for getUserAddress endpoint
    Then User verify the status code is 200
    Then user verify the getUserAddress response message matches "OK"

  Scenario Outline: verify Delete user address to the application through api
    Given User add header and bearer authorization for accessing deleteAddress endpoint
    When User add request body for delete address "<address_id>"
    And User send "DELETE" request for deleteAddress endpoint
    Then User verify the status code is 200
    Then user verify the deleteAddress response message matches "Address deleted successfully"

    Examples: 
      | address_id |
      | address_id |
