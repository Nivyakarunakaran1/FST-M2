package example;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class RequestSpecExample {

    RequestSpecification requestSpec;

    @BeforeClass
    public void setUp() {
        // Create request specification
        requestSpec = new RequestSpecBuilder()
                .setContentType(ContentType.JSON)
                .setBaseUri("https://petstore.swagger.io/v2/pet")
                .build();
    }

    @Test
    public void testPet1() {
        Response response =
                given().spec(requestSpec) // Use requestSpec
                        .pathParam("petId", "77232") // Set path parameter
                        .get("/{petId}"); // Send GET request

        // Print response
        String body = response.getBody().asPrettyString();
        System.out.println(body);

        // Assertion
        response.then().body("name", equalTo("Riley"));
    }

    @Test
    public void testPet2() {
        Response response =
                given().spec(requestSpec) // Use requestSpec
                        .pathParam("petId", "77233") // Set path parameter
                        .get("/{petId}"); // Send GET request

        // Print response
        String body = response.getBody().asPrettyString();
        System.out.println(body);

        // Assertion
        response.then().body("name", equalTo("Hansel"));
    }
}
