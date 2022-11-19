@StateId
Feature: StateId Module API automation

  Scenario: Get stateId from the application through api
    Given User add header for stateList endpoint 
    And User send "GET" request for stateList endpoint
    Then User verify the status code is 200
    Then User verify the stateList response body name present as "Tamil Nadu" and get the stateId of Tamil Nadu
