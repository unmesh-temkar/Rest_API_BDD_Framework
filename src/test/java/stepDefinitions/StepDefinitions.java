package stepDefinitions;

import io.cucumber.java.en.*;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.junit.Assert;
import resources.testData.Payload;
import resources.utils.Utils;

import java.io.IOException;

import static io.restassured.RestAssured.given;

public class StepDefinitions extends Utils {
    private RequestSpecification request;
    private Response response;
    private final Payload payload = new Payload();

    @Given("We prepare add place API payload with {string}, {string} and {string}")
    public void addPlacePayload(String name, String language, String address) throws IOException {
        request =
                given()
                        .spec(buildRequestSpec())
                        .body(payload.addPlaceRequestBody(name, language, address));
    }

    @When("We call {string} with POST HTTP request")
    public void we_call_with_post_http_request(String string) {
        response =
                request
                        .when()
                        .post("/maps/api/place/add/json");
    }

    @Then("We verify that the status code is {int}")
    public void validateStatusCode(int expectedStatusCode) {
        Assert.assertEquals(expectedStatusCode, response.getStatusCode());
    }

    @Then("We verify that the key {string} in the response body has a value of {string}")
    public void validateResponseBody(String key, String value) {
        String responseStr =
                response
                        .then()
                        .extract()
                        .response().asPrettyString();

        JsonPath jsonPath = new JsonPath(responseStr);
        String valueFromResponse = jsonPath.get(key);
        Assert.assertEquals(value,valueFromResponse);
    }
}
