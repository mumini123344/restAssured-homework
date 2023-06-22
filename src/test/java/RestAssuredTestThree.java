import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.testng.Assert;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.get;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

public class RestAssuredTestThree {


    @Test
    public void TokenAndBooking() {
        RequestSpecification request = given()
                .header("Content-Type", "application/json")
                .body("{ \"username\": \"admin\", \"password\": \"password123\" }");

        Response response = given()
                .spec(request)
                .post("https://restful-booker.herokuapp.com/auth");
        response.then().log().all();
        String token = response.jsonPath().get("token");

        Response deleteResponse = RestAssured.given()
                .contentType(ContentType.JSON)
                .auth()
                .oauth2(token)
                .header("Cookie", "token=" + token)// ჰედერს დავტოვებ იმიტორო რო ვშლი აღარ მუშაობს კოდი
                .when()
                .delete("https://restful-booker.herokuapp.com/booking/4");//id-ს რო ვცვლი ბოლოში მუშუაობს ჩვეულებრივ

        int statusCode = deleteResponse
                .getStatusCode();
        Assert.assertEquals(statusCode, 201);

    }

    // არ მიბრუნებს აქ ტოკენს
//    @Test
//    public void getData() {
//        RequestSpecification request = RestAssured.given().header("Content-Type", "application/json")
//                .auth().basic("admin", "password123");
//        Response response = given().spec(request).post("https://restful-booker.herokuapp.com/auth");
//        response.then().log().all();
//        token = response.jsonPath().get("token");
//        System.out.println(token);
//    }
//
//}

    @Test
    public void CircuitTest() {
        Response response = get("http://ergast.com/api/f1/2017/circuits.json");
        response.then().log().body();
        response.then().assertThat().body("MRData.CircuitTable.Circuits.circuitId", hasItem("marina_bay"))
                .body("MRData.CircuitTable.Circuits[-1].Location.long.toDouble()",
                        anyOf(greaterThan(1.0), (equalTo(10.0))))
                .body("MRData.CircuitTable.Circuits[1].Location.country", equalTo("USA"))
                .body("MRData.CircuitTable.Circuits[-1].Location.country", equalTo("UAE"));

    }
}
