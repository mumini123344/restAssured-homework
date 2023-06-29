package restAssuredTaskSix;

import POJO.Dates;
import POJO.RequestBody;
import org.testng.annotations.DataProvider;


public class DataProviders {

    @DataProvider(name = "dataProvider")
    public Object[][] bookingData() {
        return new Object[][]{
                {RequestBody.builder()
                        .firstName("TestOne")
                        .lastName("TestOneSur")
                        .totalPrice(23)
                        .depositPaid(true)
                        .bookingDates(new Dates("2023-07-01", "2023-07-03"))
                        .additionalNeeds("One Piece Manga")
                        .passportNo(null)
                        .build()},

                {RequestBody.builder()
                        .firstName("TestTwo")
                        .lastName("TestTwoSur")
                        .totalPrice(40)
                        .depositPaid(false)
                        .bookingDates(new Dates("2023-07-05", "2023-07-08"))
                        .additionalNeeds("Jujutsu Kaisen Manga")
                        .passportNo(null)
                        .build()}
        };

    }
}

