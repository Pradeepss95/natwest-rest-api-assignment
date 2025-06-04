Feature: Objects - Add, Retrieve and Delete

  Scenario Outline: Verify object can be added
    Given an object "<objectName>" to be added
    When the API request is sent to add the object
    Then a 200 response code is returned

    Examples:

    | objectName           |
    | Apple MacBook Pro 16 |
