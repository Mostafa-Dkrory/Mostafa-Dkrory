package TestModules.Base;
import Pages.LoginPage;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import org.testng.annotations.*;

import java.io.File;
import java.io.FileOutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class BaseTest implements ITestListener{
    protected WebDriver driver;

    protected LoginPage loginPage;
    private WebDriverWait wait;
    private static Logger logger = LogManager.getLogger();


    @BeforeClass
    @Parameters({"URL","BrowserType"})
    public void setUp(String url, String browserType, ITestContext context) {
        if (browserType.equalsIgnoreCase("Edge")) {
            driver = new EdgeDriver();
        }
        else if (browserType.equalsIgnoreCase("Firefox")) {
            driver = new FirefoxDriver();
        }
        else {
            driver = new ChromeDriver();
        }
        driver.manage().window().maximize();
        loginPage = new LoginPage(driver);
        wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        driver.get(url);
    }

    public void goToLoginPage(){
        driver.get("https://www.saucedemo.com/");
    }

    @BeforeMethod
    public void beforeMethod(){
        driver.get("https://www.saucedemo.com/");
    }

    @AfterMethod
    public void tearMethod(ITestResult result){
        if (result.getStatus() == ITestResult.FAILURE) {
            captureScreenshot(result.getMethod().getMethodName());
            logger.error("Error in " + result.getMethod().getMethodName() + "Test Method...");
        }
    }
    @AfterClass
    public void tearDown(){
        if (driver != null) {

            try {
                driver.quit();
            } catch (WebDriverException e) {
                System.out.println("***** CAUGHT EXCEPTION IN DRIVER TEARDOWN *****");
                throw e;
                //System.out.println(e);
            }
        }
    }

    private void captureScreenshot(String methodName) {
        //LocalDateTime currentDateTime = LocalDateTime.now();
        // Define a formatter for YYYY-MM-DD hh:mm format
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH-mm");
        // Format the current date and time to YYYY-MM-DD hh:mm format
        String formattedDateTime = LocalDateTime.now().format(formatter);

        try {
            File screenshotFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);

            // Create a directory if it doesn't exist
            // Update with your desired directory
            String screenshotDirectory = "C:\\Users\\DKRORY\\Desktop\\SauceDemo\\SauceDemo\\Screenshots";
            Path directoryPath = Paths.get(screenshotDirectory);
            Files.createDirectories(directoryPath);

            // Save the screenshot to the specified directory
            Path screenshotPath = directoryPath.resolve(methodName + formattedDateTime + ".png");

            // Use FileOutputStream to write the screenshot bytes
            try (FileOutputStream fos = new FileOutputStream(screenshotPath.toFile())) {
                fos.write(Files.readAllBytes(screenshotFile.toPath()));
            }

            System.out.println("Screenshot saved at: " + screenshotPath);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public WebDriver getDriver() {
        return driver;
    }

}

