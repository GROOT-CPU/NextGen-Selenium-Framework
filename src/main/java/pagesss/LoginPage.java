package pagesss;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import utils.WaitUtil;

public class LoginPage {

    WebDriver driver;

    public LoginPage(WebDriver driver) {
        this.driver = driver;
    }

    // Locators
    By username = By.id("username");
    By password = By.id("password");
    By loginBtn = By.cssSelector("button[type='submit']");
    By message = By.id("flash");

    // Actions
    public void enterUsername(String user) {
        driver.findElement(username).sendKeys(user);
    }

    public void enterPassword(String pass) {
        driver.findElement(password).sendKeys(pass);
    }

    public void clickLogin() {
        driver.findElement(loginBtn).click();
    }

    // ✅ UPDATED METHOD
    public String getMessage() {

        String msg =
                WaitUtil
                        .waitForElementVisible(driver, message)
                        .getText();

        // remove close icon + extra spaces
        msg = msg.replace("×", "").trim();

        return msg;
    }
}