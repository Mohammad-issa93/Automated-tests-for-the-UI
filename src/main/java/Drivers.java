import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.edge.EdgeDriver;

public class Drivers {
    public Drivers() {
    }

    public static WebDriver driverType(WebDriver driver, String browser) {
        browser = browser.toLowerCase();
        String driverPath = "./drivers/";
        byte var4 = -1;
        switch(browser.hashCode()) {
            case -1361128838:
                if (browser.equals("chrome")) {
                    var4 = 0;
                }
            default:
                switch(var4) {
                    case 0:
                        WebDriverManager.edgedriver().setup();
                        driver = new EdgeDriver();
                        ((WebDriver)driver).manage().window().maximize();
                    default:
                        return (WebDriver)driver;
                }
        }
    }

    public static void close(WebDriver driver) {
        driver.close();
    }

    public static void quit(WebDriver driver) {
        driver.quit();
        System.out.println("Closing the browser.");
    }
}