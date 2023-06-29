package restAssuredTaskSix;

import POJO.Dates;
import POJO.RequestBody;
import io.qameta.allure.*;
import io.qameta.allure.restassured.AllureRestAssured;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

@Epic("Booking API task")
@Feature("Updating booking api, and adding new user")
@Story("Adding new information and doing some assertion")
public class BookingTest {

    Token token = new Token();

    @Test
    @Description("Creating new user, with its fields, then getting token for restful-booker and sending response on site" +
            "and lastly updating base with new information and asserting all that")
    public void UpdateBookingTest(){

        RequestBody requestBody = RequestBody.builder()
                .firstName("Rati")
                .lastName("Shatirishvili")
                .totalPrice(45)
                .depositPaid(true)
                .bookingDates(new Dates("06-29-2023", "06-30-2023"))
                .additionalNeeds("Berserk Manga")
                .build();

        Response response =  RestAssured.given()
                .filter(new AllureRestAssured())
                .contentType(ContentType.JSON)
                .auth()
                .oauth2(token.token)
                .header("Cookie", "token=" + token.token)
                .body(requestBody)
                .when()
                .put("https://restful-booker.herokuapp.com/booking/1");

        int statusCode = response.getStatusCode();
        Assert.assertEquals(statusCode, 200);

        String responseBody = response.getBody().asString();
        // Checking some truth about response body
        Assert.assertTrue(responseBody.contains("Rati"));
        Assert.assertTrue(responseBody.contains("Shatirishvili"));

    }

}
