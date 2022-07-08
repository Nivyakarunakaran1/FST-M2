package example;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.http.Headers;
import io.restassured.response.Response;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.baseURI;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

public class TestPetAPI {

    @Test
    public void GetPetDetails() {
        // Specify the base URL to the RESTful web service
        baseURI = "https://petstore.swagger.io/v2/pet";
       // Make a request to the server by specifying the method Type and
        // resource to send the request to.
        // Store the response received from the server for later use.
        Response response =
                given().contentType(ContentType.JSON) // Set headers
                        .when().get(baseURI + "/findByStatus?status=sold"); // Run GET request

        // Now let us print the body of the message to see what response
        // we have received from the server
        String responseBody = response.getBody().asString();
        System.out.println("Response Body is =>  " + responseBody);
        Headers responseHeaders = response.then().extract().headers();
        System.out.println("Headers are =>  " + responseHeaders);
        // Extract status from response
        String responseJSON_status = response.then().extract().path("[0].status");
        System.out.println("Status is =>  " + responseJSON_status);
// Extract name from response
        String responseJSON_name = response.then().extract().path("[0].name");
        System.out.println("Name is =>  " + responseJSON_name);


        // Assertions
        response.then().statusCode(200);
        response.then().body("[0].status", equalTo("sold"));
        response.then().extract().headers().hasHeaderWithName("Date");
        response.then().extract().path("[0].status").equals("sold");
        response.then().statusCode(200);

        // To assert multiple status codes
        response.then().statusCode(anyOf(is(200), is(201), is(202), is(203), is(204)));

    }


    @Test
    public void AddNewPet() {
        String ROOT_URI = "https://petstore.swagger.io/v2/pet";


        // Write the request body
        String reqBody = "{\n" +
                "        \"id\": 77233, \n" +
                "        \"name\": \"Riley\", \n" +
                "        \"status\": \"alive\"\n" +
                "\t\t}";

    Response response =
            given().contentType(ContentType.JSON) // Set headers
                    .body(reqBody).when().post(ROOT_URI); // Send POST request

    // Print response of POST request
    String body = response.getBody().asPrettyString();
    System.out.println(body);
}

    @Test(dataProvider = "petIdProvider")
    public void simple_get_test(String id) {
        String ROOT_URI = "https://petstore.swagger.io/v2/pet/";

        Response response =
                given().contentType(ContentType.JSON) // Set headers
                        .when().get(ROOT_URI + id); // Send GET request

        // Print response
        System.out.println(response.asPrettyString());
        // Assertions
        response.then().body("status", equalTo("alive"));
    }

    @DataProvider
    public Object[][] petIdProvider() {
        // Setting parameters to pass to test case
        Object[][] testData = new Object[][] { { "77232" }, { "77233" } };
        return testData;
    }

    @Test
    public void AddNewPetHansel() {
        String ROOT_URI = "https://petstore.swagger.io/v2/pet";

        // Write the request body
         String reqBody = "{\n" +
            "        \"id\": 77232, \n" +
            "        \"name\": \"Hansel\", \n" +
            "        \"status\": \"alive\"\n" +
            "\t\t}";
    Response response =
            given().contentType(ContentType.JSON) // Set headers
                    .body(reqBody).when().put(ROOT_URI); // Send PUT request

    // Print the response
    String resBody = response.getBody().asPrettyString();
    System.out.println(resBody);

    // Assert the updates
    response.then().body("name", equalTo("Hansel"));
}

    @Test
    public void deletePet() {
        String ROOT_URI = "https://petstore.swagger.io/v2/pet";

        Response response =
                given().contentType(ContentType.JSON) // Set headers
                        .when().delete(ROOT_URI + "/77232"); // Send DELETE request

        // Assert DELETE operation
        /* However, this will only work the first time
         * the DELETE request is run, because
         * the pet with id 77232 has already been deleted.
         */
        response.then().body("code", equalTo(200));
        response.then().body("message", equalTo("77232"));
    }
}
