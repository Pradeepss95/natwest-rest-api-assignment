Feature: Objects - Add, Retrieve and Delete

  Scenario Outline: Verify object can be added
    Given an object "<objectName>" to be added
    When the API request is sent to get all the objects
    Then a <statusCode> response code is returned

    Examples:

      | objectName           | statusCode |
      | Apple MacBook Pro 16 | 200        |
