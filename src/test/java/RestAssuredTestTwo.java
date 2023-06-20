import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.json.simple.JSONObject;

import java.time.Instant;
import java.util.List;

import static io.restassured.RestAssured.given;


public class RestAssuredTestTwo {

    @Test
    public void TestOne() {

        JSONObject requestParams = new JSONObject();
        requestParams.put("firstname", "test");
        requestParams.put("lastname", "testing");
        requestParams.get("firstname");

        RequestSpecification request = given().header("Content-Type", "application/json")
                .body(requestParams);


        Response response = given().spec(request).put("https://restful-booker.herokuapp.com/booking/:id");

        int responseCode =
                given()
                        .header("Content-type", "application/json")
                        .when()
                        .get("https://restful-booker.herokuapp.com/booking/:id")
                        .then()
                        .extract().statusCode();

        response.then().log().ifStatusCodeIsEqualTo(201);
        Assert.assertEquals(responseCode, 201);


    }

    @Test
    public void TestTwo() {

        RestAssured.baseURI = "https://bookstore.toolsqa.com/";

        Response booksResponse = RestAssured.given()
                .contentType(ContentType.JSON)
                .get("/BookStore/v1/Books");
        booksResponse.then().log().all(); // არვიცი ლოგი უნდა იყოს თუარა, მარა მაინც დავტოვებ

        List<String> isbnList = booksResponse.jsonPath().getList("books.isbn");
        String lastRecordIsbn = isbnList.get(isbnList.size() - 1);

        Assert.assertEquals("9781593277574", lastRecordIsbn);

        long currentTime = System.currentTimeMillis();
        List<String> publishDates = booksResponse.jsonPath().getList("books.publish_date");

        for (String date : publishDates) {
            long publishDate = Instant.parse(date).toEpochMilli();
            assert publishDate < currentTime;
        }
    }
}
