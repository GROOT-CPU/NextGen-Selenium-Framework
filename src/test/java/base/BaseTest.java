package base;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.AfterMethod;
import utils.ConfigReader;


import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class BaseTest {

    private static WebDriver driver;


    protected static final Logger log =
            LogManager.getLogger(BaseTest.class);

    public static WebDriver getDriver() {
        return driver;
    }

    @BeforeMethod
    public void setup() {

        log.info(" Test Execution Started");

        driver = new ChromeDriver();
        log.info("Browser Launched");

        driver.manage().window().maximize();
        driver.get(ConfigReader.get("url"));

        log.info("Navigated to URL");
    }

    @AfterMethod
    public void tearDown() {

        if (driver != null) {
            driver.quit();
            log.info("Browser Closed");
        }
    }
}