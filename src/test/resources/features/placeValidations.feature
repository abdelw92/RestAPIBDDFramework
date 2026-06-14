Feature: Validating Place API's
  # Feature = test suite
# Scenario = test case

@AddPlace
  Scenario Outline: Verify if place is being successfully added using AddPlaceAPI

    Given Add Place Payload with "<name>" "<language>" "<address>"
    When user calls "AddPlaceAPI" with "Post" http request
    Then the API call got success with status code 200
    And "status" in response body is "OK"
    And "scope" in response body is "APP"
    And verify place_Id created maps to "<name>" using "getPlaceAPI"


    Examples:
    | name   | language | address       |
    | Omar   | Spanish  | 109 summer st |
    | Rachid | Italy    | 108 summer st |


  @DeletePlace
    Scenario: Verify if Delete Place functionality is working
      Given DeletePlace Payload
      When user calls "deletePlaceAPI" with "Post" http request
      Then the API call got success with status code 200
      And "status" in response body is "OK"
