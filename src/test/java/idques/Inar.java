package idques;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.specification.RequestSpecification;
import idques.Product;
import java.io.IOException;

import static io.restassured.RestAssured.given;

public class Inar {
    public static void main(String[] args) throws IOException {

        RequestSpecification req = new RequestSpecBuilder().setBaseUri("https://simple-grocery-store-api.glitch.me").build();
        RequestSpecification reqGet = given().log().all().spec(req);
        Product[] product = reqGet.when().get("/products").then().log().all().extract().response().as(Product[].class);
        System.out.println(product[1].getCategory());

//        ObjectMapper objectMapper = new ObjectMapper();
//        RestAssured.baseURI = "https://simple-grocery-store-api.glitch.me/products";
//        RequestSpecification request = RestAssured.given();
//        String response = request.get().asString();
//        objectMapper.configure(DeserializationFeature.USE_JAVA_ARRAY_FOR_JSON_ARRAY, true);
//        Product[] product = objectMapper.readValue(response, Product[].class);
//        System.out.println(product[1].getCategory());


    }
}
