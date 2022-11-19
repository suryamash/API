@ChangeProfilepic
Feature: ChangeProfilepic Module API automation

  Scenario Outline: Change profile pic from the application through api
   Given User add header and bearer authorization for accessing  endpoint
    When user add form data for changeProfilePic
    And User send "POST" request for changeProfilePic endpoint
    Then User verify the status code is 200
    Then User verify the changeProfilePic response message matches "Profile updated Successfully"
