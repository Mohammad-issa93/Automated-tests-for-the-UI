import org.testng.annotations.Test;

public class TestCase extends BaseSetup {



    @Test
    public void runAllTests() {
        if (extent == null) {
            System.err.println("ExtentReports is not initialized!");
            startReport();
        }

        logger = extent.createTest(getClass().getSimpleName());

        try {
       /*     AddProduct addProductTest = new AddProduct();
            addProductTest.runTest();
            SearchProduct SearchProduct = new SearchProduct();
            SearchProduct.runTest();
            EditProduct editProductTest = new EditProduct();
            editProductTest.runTest();
            DeleteProduct DeleteProduct = new DeleteProduct();
            DeleteProduct.runTest();
            SearchProductAll SearchProductAll = new SearchProductAll();
            SearchProductAll.runTest();
            */
            Counter Counter = new Counter();
            Counter.runTest();

        } catch (Exception e) {
            if (logger != null) {
                logger.fail("Error while running tests: " + e.getMessage());
            }
        }
    }

}

