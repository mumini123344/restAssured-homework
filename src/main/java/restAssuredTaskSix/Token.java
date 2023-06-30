package restAssuredTaskSix;

import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

import static io.restassured.RestAssured.given;

public class Token {
    RequestSpecification request = given()
            .header("Content-Type", "application/json")
            .body("{ \"username\": \"admin\", \"password\": \"password123\" }");
    Response tokenResponse = given()
            .spec(request)
            .post("https://restful-booker.herokuapp.com/auth");
    String token = tokenResponse.jsonPath().get("token");
}
