package stepDefinitions;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import utilities.APIResources;
import utilities.TestDataBuild;
import utilities.Utils;

import java.io.IOException;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.*;

public class PlaceValidationsSteps extends Utils {

    RequestSpecification res;
    ResponseSpecification resspec;
    Response response;
    TestDataBuild data = new TestDataBuild();
    static String placeId;


    @Given("Add Place Payload with {string} {string} {string}")
    public void add_place_payload(String name, String language, String address) throws IOException {
        res = given().spec(requestSpecification()).body(data.addPlacePayload(name, language, address));

    }

    @When("user calls {string} with {string} http request")
    public void user_calls_with_http_request(String resource, String method) {
        APIResources resourceAPI = APIResources.valueOf(resource);
        resspec = new ResponseSpecBuilder().expectStatusCode(200).expectContentType(ContentType.JSON).build();
        if (method.equalsIgnoreCase("POST")) {
            response = res.when().post(resourceAPI.getResource());
        } else if (method.equalsIgnoreCase("GET")) {
            response = res.when().get(resourceAPI.getResource());
        }
    }

    @Then("the API call got success with status code {int}")
    public void the_api_call_got_success_with_status_code(int code) {
        assertEquals(response.getStatusCode(), code);

    }

    @Then("{string} in response body is {string}")
    public void in_response_body_is(String keyValue, String expectedValue) {
        assertEquals(getJsonPath(response, keyValue), expectedValue);
    }

    @Then("verify placeId created maps to {string} using {string}")
    public void verify_placeId_with_name(String expectedName, String resource) throws IOException {

        placeId = getJsonPath(response, "place_id");
        res = given().spec(requestSpecification()).queryParam("place_id", placeId);
        user_calls_with_http_request(resource, "get");
        String actualName = getJsonPath(response, "name");
        assertEquals(actualName, expectedName);

    }

    @Given("Delete Place Payload")
    public void delete_place_payload() throws IOException {

        res = given().spec(requestSpecification()).body(data.deletePlacePayload(placeId));
    }
}
