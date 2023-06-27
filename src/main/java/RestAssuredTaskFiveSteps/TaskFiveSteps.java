package RestAssuredTaskFiveSteps;

import io.qameta.allure.Step;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.testng.Assert;

import static io.restassured.RestAssured.get;

public class TaskFiveSteps {
    Response response;
    Integer size;
    //Main Test Class is TestRunner test/java/TestRunner
    @Step
    public TaskFiveSteps getResp()
    {
        response = RestAssured.given().when().get("http://webservices.oorsprong.org/websamples.countryinfo/CountryInfoService.wso/ListOfContinentsByName");
        return this;
    }

    @Step
    public TaskFiveSteps validatesName()
    {
        String nameCount = response
                .then()
                .extract()
                .path("ArrayOftContinent.tContinent.sName.size()")
                .toString();
        size = Integer.valueOf(nameCount);
        Assert.assertEquals("6", nameCount);
        return this;
    }

    @Step
    public TaskFiveSteps validateNodeValues()
    {
        for (int i = 0; i<size; i++)
        {
            String path = String.format("ArrayOftContinent.tContinent.sName[%s]", i);
            System.out.println(response.then().extract().path(path).toString());
        }
        return this;
    }

    @Step
    public TaskFiveSteps validatesNameNodeWithsCode() {
        String sNameWithAN = response.then().extract().path("ListOfContinentsByNameResult.tContinent.findAll{ it.sCode == 'AN' }.sName").toString();
        System.out.println(sNameWithAN);
        return this;
    }
    @Step
    public TaskFiveSteps validateLastTContinentsName() {
        String lastSName = response.then().extract().path("ListOfContinentsByNameResult.tContinent[-1].sName").toString();
        System.out.println(lastSName);
        return this;
    }
}