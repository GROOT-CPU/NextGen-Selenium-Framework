package utils;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.*;

import java.time.Duration;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class WaitUtil {

    private static final Logger log =
            LogManager.getLogger(WaitUtil.class);

    private static final int DEFAULT_TIMEOUT = 10;

    
    public static WebElement waitForElementVisible(
            WebDriver driver, By locator) {

        log.info("Waiting for element visibility: {}", locator);

        WebDriverWait wait =
                new WebDriverWait(driver,
                        Duration.ofSeconds(DEFAULT_TIMEOUT));

        return wait.until(
                ExpectedConditions.visibilityOfElementLocated(locator));
    }

    
    public static WebElement waitForElementClickable(
            WebDriver driver, By locator) {

        log.info("Waiting for element clickable: {}", locator);

        WebDriverWait wait =
                new WebDriverWait(driver,
                        Duration.ofSeconds(DEFAULT_TIMEOUT));

        return wait.until(
                ExpectedConditions.elementToBeClickable(locator));
    }
// Wait for presence
    public static void waitForPresence(
            WebDriver driver, By locator) {

        log.info("Waiting for element presence: {}", locator);

        WebDriverWait wait =
                new WebDriverWait(driver,
                        Duration.ofSeconds(DEFAULT_TIMEOUT));

        wait.until(
                ExpectedConditions.presenceOfElementLocated(locator));
    }

    
    public static void waitForTitle(
            WebDriver driver, String title) {

        log.info("Waiting for title: {}", title);

        WebDriverWait wait =
                new WebDriverWait(driver,
                        Duration.ofSeconds(DEFAULT_TIMEOUT));

        wait.until(ExpectedConditions.titleContains(title));
    }
}