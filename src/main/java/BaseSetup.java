import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.aventstack.extentreports.reporter.configuration.ChartLocation;
import com.aventstack.extentreports.reporter.configuration.Theme;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.openqa.selenium.Capabilities;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;


import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class BaseSetup {
    protected static WebDriver driver;  // Make it static for access in static methods
    protected static WebDriverWait wait;
    protected static ExtentHtmlReporter htmlReporter;
    protected static ExtentReports extent;
    protected static ExtentTest logger;

    protected static String browserName;
    private static final String username = "Mohammad Issa";
    private static final String os = "Windows 10";
    private static final String javaVersion = "Java 17";
    private static final String hostname = "missa";

    private static final DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd_HH-mm-ss");
    private static final String reportLocation = "./Report/";
    private static final String reportName = "Automation_" + dateFormat.format(new Date()) + ".html";
    private static final String reportPath = reportLocation + reportName;

    @BeforeClass
    public static void startReport() {
        System.out.println(reportPath);
        htmlReporter = new ExtentHtmlReporter(reportPath);
        extent = new ExtentReports();
        extent.attachReporter(htmlReporter);

        // إضافة معلومات النظام
        extent.setSystemInfo("Username", username);
        extent.setSystemInfo("OS", os);
        extent.setSystemInfo("Java Version", javaVersion);
        extent.setSystemInfo("Hostname", hostname);
        extent.setSystemInfo("Browser", browserName);

        htmlReporter.config().setDocumentTitle("Automation Test Results");
        htmlReporter.config().setReportName("Automation Report");
        htmlReporter.config().setTestViewChartLocation(ChartLocation.TOP);
        htmlReporter.config().setChartVisibilityOnOpen(true);
        htmlReporter.config().setTheme(Theme.STANDARD);
    }



    @BeforeTest
    public void setup() {
        driver = Drivers.driverType(driver, "chrome");
        wait = new WebDriverWait(driver, 80);

        try {
            Capabilities caps = ((RemoteWebDriver) driver).getCapabilities();
            browserName = caps.getBrowserName();
        } catch (WebDriverException e) {
            System.err.println("Error retrieving browser capabilities: " + e.getMessage());
        }

        //driver.get("https://e-commerce-kib.netlify.app/");
        driver.get("https://flutter-angular.web.app/");
        driver.manage().window().maximize();
    }

    @AfterTest
    public void endReport() {
        if (extent != null) {
            extent.flush();  // This will flush the report at the end of the test
        }
    }

    @AfterMethod
    public void getResult(ITestResult result) {
        if (logger != null) {
            if (result.getStatus() == ITestResult.FAILURE) {
                logger.log(Status.FAIL, MarkupHelper.createLabel(result.getName() + " - Test Case Failed", ExtentColor.RED));
                logger.log(Status.FAIL, result.getThrowable());
            } else if (result.getStatus() == ITestResult.SUCCESS) {
                logger.log(Status.PASS, MarkupHelper.createLabel(result.getName() + " - Test Case Passed", ExtentColor.GREEN));
            } else if (result.getStatus() == ITestResult.SKIP) {
                logger.log(Status.SKIP, MarkupHelper.createLabel(result.getName() + " - Test Case Skipped", ExtentColor.ORANGE));
            }
        }
    }


    @AfterClass
    public static void teardown() {
        if (driver != null) {
            driver.quit();  // Clean up the WebDriver after tests
        }
    }
}
