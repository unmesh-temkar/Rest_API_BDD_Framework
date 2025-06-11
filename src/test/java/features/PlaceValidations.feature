Feature: Validating place API's

  @AddPlaceTest
  Scenario Outline: Verify if place is being successfully added using AddPlaceAPI

    Given We prepare add place API payload with "<name>", "<language>" and "<address>"
    When We call resource "addPlaceAPIResource" with "POST" HTTP request
    Then We verify that the status code is 200
    And We verify that the key 'status' in the response body has a value of 'OK'
    And We verify that the key 'scope' in the response body has a value of 'APP'
    Examples:
      | name           | language | address           |
      | Robert Dicosta | Marathi  | Khatra Mahal      |
      | Babu Bisleri   | Hindi    | Shamshan ke samne |

