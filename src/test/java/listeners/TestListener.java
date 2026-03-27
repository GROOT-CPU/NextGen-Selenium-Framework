package listeners;

import org.testng.ITestListener;
import org.testng.ITestResult;
import org.openqa.selenium.WebDriver;

import base.BaseTest;
import utils.ScreenshotUtil;


import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class TestListener implements ITestListener {

    
    private static final Logger log =
            LogManager.getLogger(TestListener.class);

    @Override
    public void onTestStart(ITestResult result) {

        log.info(" Test Started: {}", result.getName());
    }
    
   

    @Override
    public void onTestSuccess(ITestResult result) {

        log.info(" Test Passed: {}", result.getName());
    }

    @Override
    public void onTestFailure(ITestResult result) {

        String testName = result.getName();

        log.error(" Test Failed: {}", testName);
        log.error("Failure Reason: ", result.getThrowable());

        WebDriver driver = BaseTest.getDriver();

        if (driver != null) {
            ScreenshotUtil.captureScreenshot(driver, testName);
            log.info(" Screenshot captured for failed test: {}", testName);
        } else {
            log.warn("Driver is NULL — Screenshot not captured");
        }
    }

    @Override
    public void onTestSkipped(ITestResult result) {

        log.warn(" Test Skipped: {}", result.getName());
    }
}