import au.com.dius.pact.consumer.dsl.DslPart;
import au.com.dius.pact.consumer.dsl.PactDslJsonArray;
import au.com.dius.pact.consumer.dsl.PactDslJsonBody;
import au.com.dius.pact.consumer.dsl.PactDslWithProvider;
import au.com.dius.pact.consumer.junit5.PactConsumerTestExt;
import au.com.dius.pact.consumer.junit5.PactTestFor;
import au.com.dius.pact.core.model.RequestResponsePact;
import au.com.dius.pact.core.model.annotations.Pact;
import au.com.dius.pact.provider.junit.Provider;
import au.com.dius.pact.provider.junit.State;
import au.com.dius.pact.provider.junit.loader.PactFolder;
import au.com.dius.pact.provider.junit5.HttpsTestTarget;
import au.com.dius.pact.provider.junit5.PactVerificationContext;
import au.com.dius.pact.provider.junit5.PactVerificationInvocationContextProvider;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestTemplate;
import org.junit.jupiter.api.extension.ExtendWith;

import java.text.ParseException;
import java.util.HashMap;
import java.util.Map;

@ExtendWith(PactConsumerTestExt.class)
public class PactTestConsumerExample {

    Map<String , String>  headers = new HashMap<>();
    String createTut =  "/api/tutorials";

    @Pact(provider = "My_Provider", consumer = "My_Consumer")
    public RequestResponsePact createPact(PactDslWithProvider builder) throws ParseException {

    headers.put("Content-Type","application/json");
    headers.put("Accept","application/json");

        DslPart requestBody = new PactDslJsonBody()
                .stringType("title")
                .stringType("description")
                .stringType("published");


        DslPart responseBody = new PactDslJsonBody()
                .numberType("id")
                .stringType("title")
                .stringType("description")
                .stringType("published");

        return builder.given("A request to create a tutorial")
                .uponReceiving("A request to create a tutorial")
                .path(createTut)
                .headers(headers)
                .body(requestBody)
                .willRespondWith()
                .status(201)
                .body(responseBody)
                .toPact();
    }

    @Test
    @PactTestFor(providerName = "My_Provider" ,port = "8080")

    public void runTest()
    {
        RestAssured.baseURI = "http://localhost:8080";


        RequestSpecification request = RestAssured.given().headers(headers).when();

        Map<String , Object>  map = new HashMap<>();

        map.put("title","Tutorial example");
        map.put("description","Sample");
        map.put("published",true);

        Response res =request.body(map).post(createTut);
        res.then().log().all();
        assert(res.statusCode()==201);

    }
}

