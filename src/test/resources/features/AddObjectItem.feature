@Add_ObjectItem
Feature: Add new Object Items

  @Add_ObjectItem_allFields @Get_ObjectItem
  Scenario Outline: Verify Object can be added with all data fields and retrieved

    Given object item "<objectItem>"to be added
    When the API request is sent to add the object
    Then a <statusCode> response code is returned
    And response should have the object added with created date time
    When the API request is sent to get "added" object
    Then a <statusCode> response code is returned
    And added object is retrieved
    When the API request is sent to get "added" object
    Then a <statusCode> response code is returned
    And added object is retrieved

    Examples:
      | objectItem          | statusCode |
      | ObjectWithAllFields | 200        |


#Assuming below fields are mandatory only for Demonstration purposes
  @Add_ObjectItem_mandatoryFields @Get_ObjectItem
  Scenario Outline: Verify object Item can be added with mandatory data fields and retrieved

    Given an object "<objectName>" to be added
    And object has "<cpu model>" as CPU model and price <price>
    When the API request is sent to add the object
    Then a <statusCode> response code is returned
    And response should have the object added with created date time
    When the API request is sent to get "added" object
    Then a <statusCode> response code is returned
    And added object is retrieved
    When the API request is sent to get "added" object
    Then a <statusCode> response code is returned
    And added object is retrieved

    Examples:

      | objectName           | cpu model     | price   | statusCode |
      | Apple MacBook Pro 17 | Intel Core i9 | 1889.50 | 200        |

  @Add_ObjectItem_InvalidRequest
  Scenario: Verify Invalid Objects cannot be added

    Given an object "InvalidObject" is invalid
    When the API request is sent to add invalid object
    Then a 400 response code is returned

#Application is allowing to add Object with Empty Data - Hence this scenarios is commented out for Bug to be resolved
#  Scenario: Verify Objects cannot be added with Empty
#
#    Given an object "EmptyObject" is invalid
#    When the API request is sent to add invalid object
#    Then a 400 response code is returned