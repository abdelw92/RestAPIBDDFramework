package com.apple.stepDef;

import com.apple.Utilities.APIResources;
import com.apple.Utilities.APIUtils;
import com.apple.Utilities.TestDataBuilder;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import org.junit.Assert;

import java.io.FileNotFoundException;

import static io.restassured.RestAssured.given;

public class StepDefinitions extends APIUtils {

    RequestSpecification res;
    ResponseSpecification responseSpec;
    Response response;
    static String place_id; // static : to keep same value ,
    // from scenario to scenario, without static place_id will become null
    TestDataBuilder data = new TestDataBuilder();

    @Given("Add Place Payload with {string} {string} {string}")
    public void add_Place_Payload_with(String name, String language, String address) throws FileNotFoundException {

        res = given().spec(requestSpecification())
                .body(data.addPlacePayload(name, language, address));

    }

    @When("user calls {string} with {string} http request")
    public void user_Calls_With_HttpRequest(String resource, String httpRequest) {

        // constructor will be called with value of resource which you pass
        APIResources resourceAPI = APIResources.valueOf(resource);
        String requestType = resourceAPI.getResource();

        // responseSpec = new ResponseSpecBuilder().expectStatusCode(200)
        //       .expectContentType(ContentType.JSON).build();

        if (httpRequest.equalsIgnoreCase("POST")) {
            response = res.when().post(requestType);
            // .then().spec(responseSpec).extract().response();
        } else if (httpRequest.equalsIgnoreCase("GET")) {
            response = res.when().get(requestType);
        }
    }

    @Then("the API call got success with status code {int}")
    public void the_api_call_got_success_with_status_code(Integer int1) {
        Assert.assertEquals(response.getStatusCode(), int1.intValue()); // int1 is wrapper class
    }

    @Then("{string} in response body is {string}")
    public void in_response_body_is(String keyValue, String expectedValue) {

        Assert.assertEquals(getJsonPath(response, keyValue), expectedValue);
    }


    @And("verify place_Id created maps to {string} using {string}")
    public void verify_Place_Id_Created_Maps_To_Using(String expectedName, String resource) throws FileNotFoundException {
        // requestSpec
        place_id = getJsonPath(response, "place_id");
        res = given().spec(requestSpecification()).queryParam("place_id", place_id);

        user_Calls_With_HttpRequest(resource,"GET");
        String actualName = getJsonPath(response, "name");

   Assert.assertEquals(actualName,expectedName);



    }
    @Given("DeletePlace Payload")
    public void delete_place_payload() throws FileNotFoundException {

        res = given().spec(requestSpecification()).body(data.deletePLacePayload(place_id));

    }
}
