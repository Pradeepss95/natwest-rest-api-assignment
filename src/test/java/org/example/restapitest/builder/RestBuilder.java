package org.example.restapitest.builder;


import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
@Scope("cucumber-glue")
public class RestBuilder {

    private RequestSpecification requestSpecification;

    private Response response;

    public RestBuilder() {
        resetRequest(); //to initialize the request spec
    }

    private void resetRequest() {
        this.requestSpecification = RestAssured.given();
    }

    /**
     * This adds Base Uri to the Request
     *
     * @param uri The Base URI of Request
     * @return this {@code RestBuilder} instance for method chaining
     */
    public RestBuilder withBaseUri(String uri) {
        requestSpecification.baseUri(uri);
        return this;
    }

    /**
     * This adds Body in String format to the Request
     *
     * @param body Body to be used in Request
     * @return this {@code RestBuilder} instance for method chaining
     */
    public RestBuilder withBody(String body) {
        requestSpecification.body(body);
        return this;
    }

    /**
     * This adds Header to the Request
     *
     * @param header Header to be used in Request
     * @return this {@code RestBuilder} instance for method chaining
     */
    public RestBuilder withHeader(Map<String, Object> header) {
        requestSpecification.headers(header);
        return this;
    }

    /**
     * Invokes the Get Method with request specification
     *
     * @return instance of RestBuilder with Response
     */
    public RestBuilder invokeGETMethod() {
        this.response = requestSpecification.when().get();
        resetRequest();
        return this;
    }

    /**
     * Invokes the POST Method with request specification
     *
     * @return instance of RestBuilder with Response
     */
    public RestBuilder invokePOSTMethod() {
        this.response = requestSpecification.when().post();
        resetRequest();
        return this;
    }

    /**
     * Invokes the PUT Method with request specification
     *
     * @return instance of RestBuilder with Response
     */
    public RestBuilder invokePUTMethod() {
        this.response = requestSpecification.when().put();
        resetRequest();
        return this;
    }

    /**
     * Invokes the Delete Method with request specification
     *
     * @return instance of RestBuilder with Response
     */
    public RestBuilder invokeDeleteMethod() {
        this.response = requestSpecification.when().delete();
        resetRequest();
        return this;
    }

    /**
     * Gets the last Response of the request
     *
     * @return Response of API
     */
    public Response getLastResponse() {
        return this.response;
    }

    /**
     * Gets the last Response Body of the request
     *
     * @return Response Body of API
     */
    public String getLastResponseBody() {
        return this.response.body().asString();
    }
}
