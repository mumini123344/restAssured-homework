package restAssuredTaskFive;

import RestAssuredTaskFiveSteps.FindPersonApi;
import RestAssuredTaskFiveSteps.TaskFiveSteps;
import org.testng.annotations.Test;

public class TaskFiveRunner {
    TaskFiveSteps taskFiveSteps = new TaskFiveSteps();
    FindPersonApi findPersonApi = new FindPersonApi();

    @Test
    public void test()
    {
        taskFiveSteps.getResp()
                .validatesName()
                .validateNodeValues()
                .validatesNameNodeWithsCode()
                .validateLastTContinentsName();
    }
    @Test
    public void testFindPerson()
    {
        findPersonApi.request()
                .validateZip();
    }

}