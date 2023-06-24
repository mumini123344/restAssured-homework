package restAssuredTaskFour;

import com.google.gson.Gson;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.annotations.Test;
import restAssuredTask4.ErrorResponse;
import restAssuredTask4.User;
import restAssuredTask4.UserResponse;

import static org.hamcrest.Matchers.*;
import static org.hamcrest.Matchers.notNullValue;

public class MainTest {

    String baseUrl = "https://reqres.in/api/register";
    User user = new User();
    Serialization serialization = new Serialization();


    @Test
    public void TaskFourTest() {
        Response response = RestAssured
                .given()
                .when()
                .get(baseUrl);


        if (response.statusCode() == 200) {
            user.setEmail("eve.holt@reqres.in");
            user.setPassword("pistol");

            Response success = serialization.request(user, baseUrl);

            success.then().assertThat().body("id", notNullValue());
            success.then().assertThat().body("token", notNullValue());

        } else if (response.statusCode() == 400) {
            user.setEmail("sydney@fife");
            Response fail = serialization.request(user, baseUrl);
            fail.then().assertThat().body("error", equalTo("Missing password"));
        }
    }

    @Test
    public void SecondTest() {
        String baseUrl = "https://reqres.in/api/users";

        String requestBody = "{\"name\": \"morpheus\", \"job\": \"leader\"}";
        Response response = RestAssured.given()
                .baseUri(baseUrl)
                .contentType(ContentType.JSON)
                .body(requestBody)
                .post();

        int statusCode = response
                .getStatusCode();

        if (statusCode == 201) {
            UserResponse userResponse = new Gson().fromJson(response.getBody().asString(), UserResponse.class);

            String id = userResponse.getId();
            String createdAt = userResponse.getCreatedAt();
//            System.out.println("User created,id: " + id + ", Created At: " + createdAt);

            response.then().assertThat().body("id", equalTo(id));
            response.then().assertThat().body("createdAt", equalTo(createdAt));

        } else {
            ErrorResponse errorResponse = new Gson().fromJson(response.getBody().asString(), ErrorResponse.class);
            String error = errorResponse.getError();

            System.out.println("failed. Error: " + error);

        }

    }
}

