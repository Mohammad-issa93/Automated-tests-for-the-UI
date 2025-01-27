import com.aventstack.extentreports.Status;
import org.openqa.selenium.By;

public class EditProduct extends BaseSetup {

    public void runTest() {
        logger = extent.createTest("Add Product Test");
        try {
            driver.findElement(By.xpath("//*[@id='root']/div/header/div[2]/div/a")).click();
            driver.findElement(By.id("product-name")).sendKeys("Sample Product");
            driver.findElement(By.id("product-price")).sendKeys("100");
            driver.findElement(By.id("product-description")).sendKeys("Sample Description");
            driver.findElement(By.id("save-product")).click();

            boolean isAdded = driver.findElement(By.xpath("//td[contains(text(),'Sample Product')]")).isDisplayed();
            if (isAdded) {
                logger.log(Status.PASS, "Add Product Test Passed.");
            } else {
                logger.log(Status.FAIL, "Add Product Test Failed.");
            }
        } catch (Exception e) {
            logger.log(Status.FAIL, "An error occurred: " + e.getMessage());
        }
    }
}