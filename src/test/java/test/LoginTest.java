package test;

import base.BaseTest;
import pagesss.LoginPage;
import utils.WaitUtil;
import utils.ExcelUtil;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import org.openqa.selenium.By;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class LoginTest extends BaseTest {

    private static final Logger log =
            LogManager.getLogger(LoginTest.class);

    

    @DataProvider(name = "loginData")
    public Object[][] getLoginData() {

        String path = System.getProperty("user.dir")
                + "/src/test/resources/testdata/LoginData.xlsx";

        log.info("Reading Login Data from Excel");

        return ExcelUtil.getData(path, "LoginData");
    }

    

    @Test(dataProvider = "loginData")
    public void loginTest(String username,
                          String password,
                          String expected) {

        log.info("Starting Login Test");
        log.info("Username Used: {}", username);

        LoginPage login = new LoginPage(getDriver());

        login.enterUsername(username);
        log.info("Entered Username");

        login.enterPassword(password);
        log.info("Entered Password");

        login.clickLogin();
        log.info("Clicked Login Button");

        
        WaitUtil.waitForElementVisible(getDriver(), By.id("flash"));
        log.info("Waiting for Login Message");

        String msg = login.getMessage();

        log.info("Application Message: {}", msg);

        if (expected.equalsIgnoreCase("success")) {

            Assert.assertTrue(
                    msg.contains("You logged into a secure area"),
                    "Valid login failed!"
            );

            log.info("Valid Login Passed");

        } else {

            Assert.assertTrue(
                    msg.contains("Your username is invalid"),
                    "Invalid login validation failed!"
            );

            log.info("Invalid Login Tested");
        }

        log.info("Login Test Completed");
    }
}