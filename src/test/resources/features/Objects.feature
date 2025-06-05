Feature: Objects - Add, Retrieve and Delete

  Scenario Outline: Verify object can be added
    Given an object "<objectName>" to be added
    And object has "<cpu model>" as CPU model and price <price>
    When the API request is sent to add the object
    Then a <statusCode> response code is returned

    Examples:

      | objectName           | cpu model     | price    | statusCode |
      | Apple MacBook Pro 16 | Intel Core i9 | 1889.50  | 200        |
