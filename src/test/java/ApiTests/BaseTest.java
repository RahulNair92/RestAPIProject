package ApiTests;

import APIs.Endpoints;
import io.restassured.RestAssured;
import io.restassured.specification.RequestSpecification;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;


public class BaseTest {

    RequestSpecification request;

    @BeforeClass
    public void setup() {
        RestAssured.baseURI = Endpoints.BASE_URL;
        request = RestAssured.given();
        request.header("Content-Type", "application/json");
    }

    @AfterClass
    public void teardown() {

    }

 
}
