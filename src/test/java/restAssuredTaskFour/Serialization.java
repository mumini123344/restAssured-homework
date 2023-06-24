package restAssuredTaskFour;

import io.qameta.allure.Step;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class Serialization {
    @Step
    public Response request(Object user, String URL) {
        Response response = RestAssured.given()
                .baseUri(URL)
                .contentType(ContentType.JSON)
                .body(user)
                .when()
                .post();
        return response;
    }

}
