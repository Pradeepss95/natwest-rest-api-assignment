@ObjectItem
Feature: Objects - Add, Retrieve and Delete

  @ObjectItem_Add @ObjectItem_get
  Scenario Outline: Verify object Item can be added and retrieved
    Given an object "<objectName>" to be added
    And object has "<cpu model>" as CPU model and price <price>
    When the API request is sent to add the object
    Then a <statusCode> response code is returned
    And response should have the object added with created date time
    When the API request is sent to get "added" object
    Then a <statusCode> response code is returned
    And added object is retrieved

    Examples:

      | objectName           | cpu model     | price   | statusCode |
      | Apple MacBook Pro 17 | Intel Core i9 | 1889.50 | 200        |

  @ObjectItem_getAll
  Scenario: Verify All the Objects can be retrieved
    When the API request is sent to get all the objects
    Then a 200 response code is returned
    And response should retrieve all object items

  @ObjectItem_Add @ObjectItem_delete @ObjectItem_get_Not_Found
  Scenario: Verify object Item can be deleted
    Given an object "HP EliteBook - Intel" to be added
    And object has "Intel vPRO i7" as CPU model and price 899.00
    When the API request is sent to add the object
    Then a 200 response code is returned
    And response should have the object added with created date time
    When the API request is sent to delete the added Object Item
    Then a 200 response code is returned
    When the API request is sent to get "an" object
    Then a 404 response code is returned