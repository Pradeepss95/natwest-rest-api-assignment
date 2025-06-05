Feature: Objects - Add, Retrieve and Delete

  Scenario Outline: Verify object Item can be added
    Given an object "<objectName>" to be added
    And object has "<cpu model>" as CPU model and price <price>
    When the API request is sent to add the object
    Then a <statusCode> response code is returned
    And response should have the object added with created date time

    Examples:

      | objectName           | cpu model     | price    | statusCode |
      | Apple MacBook Pro 17 | Intel Core i9 | 1889.50  | 200        |

    Scenario: Verify All the Objects can be retrieved
      When the API request is sent to get all the objects
      Then a 200 response code is returned
      And response should retrieve all object items
