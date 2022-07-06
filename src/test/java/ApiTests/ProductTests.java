package ApiTests;

import APIs.Endpoints;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.filter.log.LogDetail;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import models.ProductPojo;
import org.testng.annotations.Test;

import java.io.File;
import java.io.IOException;

import static org.hamcrest.CoreMatchers.containsString;

public class ProductTests extends BaseTest{

    @Test
    public void createProductTest() throws IOException {

        String location = request.auth()
                .basic("maria", "maria123")
                .body(getProductDetails())
                .when()
                .post(Endpoints.CREATE_PRODUCT)
                .then()
                .assertThat().statusCode(201)
                .header("Location",containsString("/api/v1/products"))
                .log()
                .all()
                .extract()
                .header("Location");

        System.out.println("location returned by server is " + location);

    }

    @Test
    public void createProductWIthDifferentPrice() throws IOException {
            ProductPojo productPojo = getProductDetails();
            productPojo.setPrice(4);
        String location = request.auth()
                .basic("maria", "maria123")
                .body(productPojo)
                .when()
                .post(Endpoints.CREATE_PRODUCT)
                .then()
                .assertThat().statusCode(201)
                .header("Location",containsString("/api/v1/products"))
                .log()
                .all()
                .extract()
                .header("Location");

        System.out.println("location returned by server is " + location);

    }

    private ProductPojo getProductDetails() throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        ProductPojo productDetails =mapper.readValue(new File("src/main/resources/data/Product.json"), ProductPojo.class);
        return productDetails;
    }

    @Test
    public void getSpecificProduct(){
        String id = "06feb98f-f3df-4e74-a118-50df7e7cc59c";

        request.auth()
                .basic("maria", "maria123")
                .pathParam("id", id)
                .when()
                .get(Endpoints.GET_PRODUCT)
                .then()
                .assertThat()
                .statusCode(200)
                .body("name", containsString("Hamilton"))
                .log()
                .all();
    }
}
