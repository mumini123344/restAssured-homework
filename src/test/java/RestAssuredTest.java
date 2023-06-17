import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

public class RestAssuredTest {

    @Test(dataProvider = "CircuitIndex", dataProviderClass = Circuits.class)
    public void circuitTest(Integer index, String countryTwo) {

        Response response = RestAssured.get("https://ergast.com/api/f1/2017/circuits.json");
        JsonPath jsonPath = response.jsonPath();

        String circuitId = jsonPath.getString(String.format("MRData.CircuitTable.Circuits[%s].circuitId", index));

        Response responseOne = RestAssured.get("https://ergast.com/api/f1/2017/circuits/" + circuitId + ".json");
        responseOne.then().log().all();

        JsonPath jsonPathCountry = response.jsonPath();
        String countryOne = jsonPathCountry.getString(String.format("MRData.CircuitTable.Circuits[%s].Location.country", index));

        Assert.assertEquals(countryOne, countryTwo);

    }


}