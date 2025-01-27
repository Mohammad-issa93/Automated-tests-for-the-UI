import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class AddProduct extends BaseSetup {
    public void runTest() {
        logger = extent.createTest("Add Product Test");
        try {
            driver.wait(10);
            driver.findElement(By.xpath("/html/body/div/div/header/div[2]/div/a/svg")).click();

            // الخطوة 2: العثور على عنصر رفع الصورة
            WebElement uploadElement = driver.findElement(By.xpath("/html/body/div/div/main/div/form/div[1]/div/label/div"));

            // الخطوة 3: تحديد مسار الصورة
            String imagePath = "./Support/pic.png";
            uploadElement.sendKeys(imagePath);

            driver.findElement(By.xpath("/html/body/div/div/main/div/form/div[2]/div[1]/input")).sendKeys("Tilte Tilte");
            driver.findElement(By.xpath("/html/body/div/div/main/div/form/div[2]/div[2]/input")).sendKeys("Description Description Description Description");
            driver.findElement(By.xpath("/html/body/div/div/main/div/form/div[3]/div/input")).sendKeys("10");
            driver.findElement(By.xpath("/html/body/div/div/main/div/form/button")).click();

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


