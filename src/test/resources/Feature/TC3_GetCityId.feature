@CityId
Feature: CityId Module API automation

  Scenario Outline: Get cityId from the application through api
    Given User add header for cityList endpoint
    When User add stateId to get cityList "<stateId>"
    And User send "POST" request for cityList endpoint
    Then User verify the status code is 200
    Then User verify the cityList response body name present as "Dharmapuri" and get the cityId of Dharmapuri

    Examples: 
      | state   |
      | stateId |
