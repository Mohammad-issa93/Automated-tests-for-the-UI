import com.aventstack.extentreports.Status;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class Counter extends BaseSetup{

    public void runTest() {
        logger = extent.createTest("Counter is Increased Test");
        try {
            WebElement plusButton = driver.findElement(By.xpath("//button[@id='increment-button']"));
            plusButton.click();
            Thread.sleep(2000);

            WebElement counterElement = driver.findElement(By.xpath("//span[@id='counter-value']"));
            int counterValue = Integer.parseInt(counterElement.getText());

            // التحقق من أن العداد أصبح يساوي 2
            if (counterValue == 2) {
                logger.log(Status.PASS, "Counter is increased to 2 as expected.");
            } else {
                logger.log(Status.FAIL, "Counter value is incorrect. Expected: 2, Found: " + counterValue);
            }
        } catch (Exception e) {
            logger.log(Status.FAIL, "An error occurred: " + e.getMessage());
        }
    }
}
