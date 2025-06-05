package org.example.restapitest.stepdefinitions;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import io.restassured.http.ContentType;
import org.example.restapitest.builder.RestBuilder;
import org.example.restapitest.domain.object.DataInObject;
import org.example.restapitest.domain.object.ObjectItem;
import org.example.restapitest.utilities.ObjectMapperBean;
import org.json.JSONArray;
import org.json.JSONException;
import org.skyscreamer.jsonassert.JSONAssert;
import org.skyscreamer.jsonassert.JSONCompareMode;
import org.springframework.beans.factory.annotation.Autowired;
import org.testng.Assert;

import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ObjectStepsImpl {

    private final Logger logger = Logger.getLogger(ObjectStepsImpl.class.getName());


    @Autowired
    RestBuilder restBuilder;


    @Autowired
    ObjectItem objectItem;

    @Autowired
    ObjectMapperBean objectMapperBean;

    private final String baseURI = "https://api.restful-api.dev/objects";


    @Given("^an object \"(.*)\" to be added$")
    public void createObject(String itemName) {
        objectItem.setName(itemName);
        logger.log(Level.INFO, "Item to be added: {0} ", itemName);
    }

    @And("^object has \"(.*)\" as CPU model and price ([0-9]+\\.[0-9]+)$")
    public void addCPUModelPrice(String cpuModel, double price) {
        DataInObject dataInObject = new DataInObject();
        dataInObject.setCpuModel(cpuModel);
        dataInObject.setPrice(price);
        objectItem.setDataInObject(dataInObject);
        logger.log(Level.INFO, "Data added to item");
    }

    @When("^the API request is sent to add the object$")
    public void requestToAdd() {
        final String apiBody = objectMapperBean.convertToString(objectItem);
        logger.log(Level.INFO, "Request Body: {0}", apiBody);
        restBuilder.withBaseUri(baseURI).withHeader(createHeader()).withBody(apiBody).invokePOSTMethod();
        logger.log(Level.INFO, "Request is sent to Add Object");
    }


    @When("^the API request is sent to get all the objects$")
    public void requestToGetAllObjects() {
        restBuilder.withBaseUri(baseURI).invokeGETMethod();
        logger.log(Level.INFO, "Request is sent to Get All Object");
    }

    @When("^a (\\d+) response code is returned$")
    public void responseStatus(int expectedStatusCode) {
        final int responseStatusCode = restBuilder.getLastResponse().statusCode();
        Assert.assertEquals(responseStatusCode, expectedStatusCode);
        logger.log(Level.INFO, "Status is 200:OK");
        logger.log(Level.INFO, "Response Body is: {0}" + restBuilder.getLastResponseBody());
    }

    @And("^response should have the object added with created date time$")
    public void responseHadAddedObject() {
        final String lastResponseBody = restBuilder.getLastResponseBody();
        Assert.assertNotNull(lastResponseBody, "Response Body is Null");
        ObjectItem requestResponseObject = objectMapperBean.convertStringToObjectItem(lastResponseBody);
        //Validate Name is correct
        String nameInResponse = requestResponseObject.getName();
        String nameInRequest = objectItem.getName();
        Assert.assertEquals(nameInRequest, nameInResponse);
        logger.log(Level.INFO, "Item name in the Response matches with Request");
        //Validate Data is correct
        DataInObject dataInResponse = requestResponseObject.getDataInObject();
        Assert.assertEquals(dataInResponse, objectItem.getDataInObject());
        logger.log(Level.INFO, "Data in Item in Response matches with Request");
        //Validate Created Date is not null in Response
        Assert.assertNotNull(requestResponseObject.getCreatedAt());
        logger.log(Level.INFO, "CreatedAt is not NULL");
    }

    @And("^response should retrieve all object items$")
    public void responseHasAllObjects() throws JSONException {
        final String lastResponseBody = restBuilder.getLastResponseBody();
        Assert.assertNotNull(lastResponseBody, "Response Body is Null");
        //Use JsonPath to get all object rather than Using Pojo. It is for demonstration purpose
        JSONArray responseItems = new JSONArray(lastResponseBody);
        logger.log(Level.INFO, "Number of Objects Returned is: " + responseItems.length());
        Assert.assertTrue(responseItems.length() > 1, "Response got less than or equals to 1 Object Item");
    }

    @When("^the API request is sent to get \"(.*)\" object$")
    public void getObjectByID(String flagValue) {
        String idCreated = null;
        if ("added".equalsIgnoreCase(flagValue)){
            final String lastResponseBody = restBuilder.getLastResponseBody();
            Assert.assertNotNull(lastResponseBody, "Response Body is Null");
            ObjectItem requestResponseObject = objectMapperBean.convertStringToObjectItem(lastResponseBody);
            idCreated = requestResponseObject.getId();
            objectItem.setId(idCreated);
        }else {
            idCreated = objectItem.getId();
        }
        Assert.assertNotNull(idCreated,"Id Fetched is null");
        String newBaseURI = baseURI + "/" + idCreated;
        restBuilder.withBaseUri(newBaseURI).invokeGETMethod();
        logger.log(Level.INFO, "Request sent to get Object Item by ID: {0}", idCreated);
    }

    @And("^added object is retrieved$")
    public void verifyAddedObjectById() throws JSONException {
        final String lastResponseBody = restBuilder.getLastResponseBody();
        Assert.assertNotNull(lastResponseBody, "Response Body is Null");
        //This is to demonstrate how Json Asser Can be used to compare two JSONs
        final String expectedObjectItem = objectMapperBean.convertToString(objectItem);
        JSONAssert.assertEquals(expectedObjectItem, lastResponseBody, JSONCompareMode.STRICT);
        logger.log(Level.INFO, "ObjectItem retrieved matches with objectItem added");
    }

    @When("^the API request is sent to delete the added Object Item$")
    public void deleteObject() {
        String id = getIDFromResponse();
        Assert.assertNotNull(id,"ID from Response is NULL");
        objectItem.setId(id);
        String newBaseURI = baseURI + "/" + id;
        restBuilder.withBaseUri(newBaseURI).invokeDeleteMethod();
        logger.log(Level.INFO, "Request sent to Delete Item with ID : {0}", id);
    }


    /**
     * Sets up the header for the request
     * @return header
     */
    private Map<String, Object> createHeader() {
        HashMap<String, Object> header = new HashMap<>();
        header.put("Content-Type", ContentType.JSON);
        return header;
    }

    /**
     * Gets the id from Response and set it to Object Item
     * @return id created
     */
    private String getIDFromResponse(){
        final String lastResponseBody = restBuilder.getLastResponseBody();
        Assert.assertNotNull(lastResponseBody, "Response Body is Null");
        ObjectItem requestResponseObject = objectMapperBean.convertStringToObjectItem(lastResponseBody);
        final String idCreated = requestResponseObject.getId();
        logger.log(Level.INFO, "Id from the Response is: {0}", idCreated);
        return idCreated;
    }

}
