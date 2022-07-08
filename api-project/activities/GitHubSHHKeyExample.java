package activities;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import org.testng.Reporter;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

public class GitHubSHHKeyExample {

    // Declare request specification
    RequestSpecification requestSpec1;
    RequestSpecification requestSpec2;
    // Declare response specification
    ResponseSpecification responseSpec1;
    ResponseSpecification responseSpec2;
    ResponseSpecification responseSpec3;
    String shhkey;
    private int keyiD;

    String tokenGenerated = "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";

    @BeforeClass
    public void setUp() {
        // Create request specification
        requestSpec1 = new RequestSpecBuilder()
                // Set content type
                .setContentType(ContentType.JSON)
                // Set base URL
                .setBaseUri("https://api.github.com/").setBasePath("user/keys")
                // Build request specification
                .build().log().all();

        responseSpec1 = new ResponseSpecBuilder()
                // Check status code in response
                .expectStatusCode(201)
                // Check response content type
                .expectContentType("application/json")
                // Check if response contains name property

                .build();
        responseSpec2 = new ResponseSpecBuilder()
                // Check status code in response
                .expectStatusCode(200)
                // Check response content type
                .expectContentType("application/json")
                // Check if response contains name property
                .build();

        responseSpec3 = new ResponseSpecBuilder()
                // Check status code in response
                .expectStatusCode(204)
                // Check if response contains name property
                .build();
    }


   @Test(priority=1)
    public void addSHHKeyToAccount() {
        String reqBody = "\n" +
                "{\n" +
                "    \"title\": \"TestAPIKey\",\n" +
                "    \"key\": \"ssh-ed25519 AAAAC3NzaC1lZDI1NTE5AAAAIMJQFquAxfgQnvzKx+/++FgEaH/1PYoLsjS5mf5hQK9L\"\n" +
                "}\n" +
                "\n";
        Response response = given().spec(requestSpec1).auth().oauth2(tokenGenerated) // Use requestSpec
                .body(reqBody) // Send request body
                .when().post(); // Send POST request


        response.then().spec(responseSpec1).log().all(); // Use responseSpec
        keyiD = response.jsonPath().get("id");
        shhkey = response.jsonPath().get("key");

        System.out.println("Id is.............."+keyiD);
        System.out.println("Shhhkey is.............."+shhkey);
    }


    @Test( priority=2)
    public void getSHHKeyAttachedToAccount() {
        Response response = given().spec(requestSpec1).auth().oauth2(tokenGenerated)// Use requestSpe
                .when().get(); // Send GET request

        // Print response
        System.out.println("SHHKeyAttachedToAccount are^^^^^ "+response.asPrettyString());
        // Assertions
        response.then()
                .spec(responseSpec2); // Use responseSpec
        Reporter.log("SHHKeyAttachedToAccount are^^^^^ "+response.toString());
    }

    @Test(priority=3)
    public void deleteSSHKey() throws InterruptedException {
        Response response = given().spec(requestSpec1).
                auth().oauth2(tokenGenerated).pathParam("keyID",keyiD) // Use requestSpec
                .when().delete("/{keyID}"); // Send Delete Request

        // Assertions
        response.then().spec(responseSpec3);
        Reporter.log("SSH Key deleted from Account ");

    }
}
