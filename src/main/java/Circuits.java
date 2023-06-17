import org.testng.annotations.DataProvider;

public class Circuits {
    @DataProvider(name = "CircuitIndex")
    public Object[][] CircuitIndex() {
        Object[][] data = {{1, "USA"}, {5, "Hungary"}};
        return data;
    }
}


