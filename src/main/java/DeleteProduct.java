import com.aventstack.extentreports.Status;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class DeleteProduct extends BaseSetup{
    public void runTest() {
        logger = extent.createTest("Delete Product Test");
        try {
            driver.wait(10);
            driver.findElement(By.xpath("//*[@id=\"root\"]/div/main/div/div/div/div[2]/div/div[4]/div[2]/button[2]")).click();

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
