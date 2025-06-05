package org.example.restapitest.stepdefinitions;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import io.restassured.http.ContentType;
import org.example.restapitest.builder.RestBuilder;
import org.example.restapitest.domain.object.DataInObject;
import org.example.restapitest.domain.object.ObjectItem;
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

    String baseURI = "https://api.restful-api.dev/objects";


    @Given("^an object \"(.*)\" to be added$")
    public void createObject(String itemName){
        objectItem.setName(itemName);
        logger.log(Level.INFO, "Item to be added: {0} ", itemName);
    }

    @And("^object has \"(.*)\" as CPU model and price ([0-9]+\\.[0-9]+)$")
    public void addCPUModel(String cpuModel, double price) {
        DataInObject dataInObject = new DataInObject();
        dataInObject.setCpuModel(cpuModel);
        dataInObject.setPrice(price);
        objectItem.setDataInObject(dataInObject);
        logger.log(Level.INFO, "Data added to item");
    }


    @When("^the API request is sent to add the object$")
    public void requestToAdd() throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        final String apiBody = objectMapper.writeValueAsString(objectItem);
        restBuilder.withBaseUri(baseURI).withHeader(createHeader()).withBody(apiBody).invokePOSTMethod();
        logger.info("Request is sent to Add Object");
    }
    
    
    @When("^the API request is sent to get all the objects$")
    public void requestToGetAllObjects() {
        restBuilder.withBaseUri("https://api.restful-api.dev/objects").invokeGETMethod();
        logger.info("Request is sent to Get All Object");
    }

    @When("^a (\\d+) response code is returned$")
    public void responseStatus(int expectedStatusCode) {
        final int responseStatusCode = restBuilder.getLastResponse().statusCode();
        Assert.assertEquals(responseStatusCode, expectedStatusCode);
        logger.info("Status is 200:OK");
        logger.info("Response Body is: {0}" + restBuilder.getLastResponseBody());
    }

    public Map<String, Object> createHeader(){
        HashMap<String, Object> header = new HashMap<>();
        header.put("Content-Type", ContentType.JSON);
        return header;
    }

}
