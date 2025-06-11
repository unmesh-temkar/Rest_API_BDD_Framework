package stepDefinitions;

import POJO.AddPlaceResponseBody;
import io.cucumber.java.en.*;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.junit.Assert;
import resources.APIResources;
import resources.testData.Payload;
import resources.utils.Utils;

import java.io.IOException;

import static io.restassured.RestAssured.given;

public class StepDefinitions extends Utils {
    private RequestSpecification request;
    private Response response;
    private final Payload payload = new Payload();
    private static String placeId;

    public static String getPlaceId() {
        return placeId;
    }

    public static void setPlaceId(String placeId) {
        StepDefinitions.placeId = placeId;
    }

    @Given("We prepare add place API payload with {string}, {string} and {string}")
    public void addPlacePayload(String name, String language, String address) throws IOException {
        request =
                given()
                        .spec(buildRequestSpec())
                        .body(payload.addPlaceRequestBody(name, language, address));
    }

    @When("We call resource {string} with {string} HTTP request")
    public void executeHttpRequest(String resource, String httpMethod) {
        String apiResource = APIResources.valueOf(resource).getResource();

        if (httpMethod.equalsIgnoreCase("POST")) {
            response = request
                    .when()
                    .post(apiResource);
        } else if (httpMethod.equalsIgnoreCase("GET")) {
            response = request
                    .when()
                    .get(apiResource);
        }
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
        Assert.assertEquals(value, valueFromResponse);
    }

    @And("We verify that the place_id generated maps to expected {string} using api resource {string}")
    public void validatePlaceId(String expectedName, String resource) throws IOException {
        placeId = response.as(AddPlaceResponseBody.class).getPlace_id();

        request =
                given()
                        .spec(buildRequestSpec())
                        .queryParam("place_id", placeId);
        executeHttpRequest(resource, "GET");
        String actualName = Utils.extractJsonValue(response, "name");
        Assert.assertEquals(actualName, expectedName);
    }

    @Given("We prepare delete place API payload")
    public void prepareDeletePlaceAPIPayload() throws IOException {
        request =
                given()
                        .spec(buildRequestSpec())
                        .body(Payload.deletePlaceRequestBody(placeId));
    }
}
