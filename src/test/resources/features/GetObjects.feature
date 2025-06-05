@Get_All_ObjectItem
Feature: Retrieve all the object Item

  Scenario: Verify All the Objects can be retrieved
    When the API request is sent to get all the objects
    Then a 200 response code is returned
    And response should retrieve all object items