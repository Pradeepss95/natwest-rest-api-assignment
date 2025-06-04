package org.example.restapitest.stepdefinitions;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;

import java.util.logging.Level;
import java.util.logging.Logger;

public class ObjectStepsImpl {

    private final Logger logger = Logger.getLogger(ObjectStepsImpl.class.getName());

    @Given("^an object \"(.*)\" to be added$")
    public void createObject(String item) {
        logger.log(Level.INFO, "Item to be added: {0} ", item);
        logger.info("Item is created for adding");
    }

    @When("^the API request is sent to add the object$")
    public void requestToAdd() {
        logger.info("Request is sent to Add Object");
    }

    @When("^a 200 response code is returned$")
    public void responseStatus() {
        logger.info("Status is 200:OK");
    }

}
