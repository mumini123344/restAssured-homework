package restAssuredTaskSix;

import POJO.RequestBody;
import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.restassured.AllureRestAssured;
import io.restassured.http.ContentType;
import jdk.jfr.Frequency;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

@Epic("Booking API task with DataProvider class")
@Feature("Updating booking api with data provider")
public class BookingTestWithDataProvider {
    Token token = new Token();
    @Test(dataProvider = "dataProvider", dataProviderClass = DataProviders.class)
    @Description("Sending response to restful-booker then using token and accessing site, after that updating api inserting" +
            "new information and asserting that")
    public void bookingTest(RequestBody requestBody){
        given().filter(new AllureRestAssured())
                .contentType(ContentType.JSON)
                .auth()
                .oauth2(token.token)
                .header("Cookie", "token=" + token.token)
                .body(requestBody)
                .when()
                .put("https://restful-booker.herokuapp.com/booking/2")
                .then().assertThat()
                .statusCode(200)
                .body("firstname", equalTo(requestBody.getFirstName()));
    }
}
