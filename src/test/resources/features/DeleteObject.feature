@Delete_ObjectItem
Feature: Objects - Add, Retrieve and Delete


  @ObjectItem_delete @ObjectItem_get_Not_Found
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