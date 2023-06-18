Feature: Validating Place API's

  @AddPlace @Regression
  Scenario Outline: Verify if Place is being Successfully added using AddPlaceAPI
    Given Add Place Payload with "<name>" "<language>" "<address>"
    When user calls "addPlaceAPI" with "post" http request
    Then the API call got success with status code 200
    And "status" in response body is "OK"
    And "scope" in response body is "APP"
    And verify placeId created maps to "<name>" using "getPlaceAPI"
    Examples:
      | name            | language  | address                   |
      | Frontline house | French-IN | 28, side layout, cohen 09 |
      | BB house        | Spanish   | Sea cross, line 65        |


  @DeletePlace @Regression
  Scenario: Verify if delete Place functionality is working
    Given Delete Place Payload
    When user calls "deletePlaceAPI" with "POST" http request
    Then the API call got success with status code 200
    And "status" in response body is "OK"