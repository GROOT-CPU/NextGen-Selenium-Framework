package utils;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import org.apache.commons.io.FileUtils;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class ScreenshotUtil {

    private static final Logger log =
            LogManager.getLogger(ScreenshotUtil.class);

    public static String captureScreenshot(WebDriver driver,
                                           String testName) {

        String path = null;

        try {

            // Safety Check
            if (driver == null) {
                log.warn("Driver is NULL - Screenshot skipped");
                return null;
            }

            // Timestamp
            String timeStamp =
                    new SimpleDateFormat("yyyyMMdd_HHmmss")
                            .format(new Date());

            File src =
                    ((TakesScreenshot) driver)
                            .getScreenshotAs(OutputType.FILE);

            path = System.getProperty("user.dir")
                    + "/Screenshots/"
                    + testName + "_"
                    + timeStamp + ".png";

            FileUtils.copyFile(src, new File(path));

            log.info("Screenshot Saved: {}", path);

        } catch (Exception e) {

            log.error("Screenshot capture FAILED", e);
        }

        return path;
    }
}