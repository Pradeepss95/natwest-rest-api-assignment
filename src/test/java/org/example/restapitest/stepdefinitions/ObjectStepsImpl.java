package org.example.restapitest.stepdefinitions;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import org.example.restapitest.utilities.RestBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.testng.Assert;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ObjectStepsImpl {

    private final Logger logger = Logger.getLogger(ObjectStepsImpl.class.getName());


    @Autowired
    RestBuilder restBuilder;

    @Given("^an object \"(.*)\" to be added$")
    public void createObject(String item) {
        logger.log(Level.INFO, "Item to be added: {0} ", item);
        logger.info("Item is created for adding");
    }

    @When("^the API request is sent to get all the objects$")
    public void requestToAdd() {
        restBuilder.withBaseUri("https://api.restful-api.dev/objects").invokeGETMethod();
        logger.info("Request is sent to Add Object");
    }

    @When("^a (\\d+) response code is returned$")
    public void responseStatus(int expectedStatusCode) {
        final int responseStatusCode = restBuilder.getLastResponse().statusCode();
        Assert.assertEquals(responseStatusCode, expectedStatusCode);
        logger.info("Status is 200:OK");
    }

}
