import com.aventstack.extentreports.Status;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class SearchProduct extends BaseSetup {
    public void runTest() {
        logger = extent.createTest("Search Product Test");
        try {

            driver.findElement(By.xpath("/html/body/div/div/header/div[2]/input")).sendKeys("Apple iphone 10");
            driver.wait(10);

            boolean isAdded = driver.findElement(By.xpath("/html/body/div/div/main/div/div/div/div[2]/div")).isDisplayed();
            if (isAdded) {
                logger.log(Status.PASS, "Search Test Passed.");
            } else {
                logger.log(Status.FAIL, "Search Product Test Failed.");
            }
        } catch (Exception e) {
            logger.log(Status.FAIL, "An error occurred: " + e.getMessage());
        }
    }
}
