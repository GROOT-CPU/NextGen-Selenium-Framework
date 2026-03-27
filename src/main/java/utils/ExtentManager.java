package utils;

import com.aventstack.extentreports.*;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.text.SimpleDateFormat;
import java.util.Date;

public class ExtentManager {

    private static ExtentReports extent;

    
    private static final Logger log =
            LogManager.getLogger(ExtentManager.class);

    public static ExtentReports getInstance() {

        if (extent == null) {

            log.info("Initializing Extent Report");

            // Timestamp report
            String timeStamp =
                    new SimpleDateFormat("yyyyMMdd_HHmmss")
                            .format(new Date());

            String reportPath =
                    "reports/ExtentReport_" + timeStamp + ".html";

            ExtentSparkReporter reporter =
                    new ExtentSparkReporter(reportPath);

            reporter.config().setReportName("Automation Test Report");
            reporter.config().setDocumentTitle("Selenium Framework Report");

            extent = new ExtentReports();
            extent.attachReporter(reporter);

            
            extent.setSystemInfo("Tester", "Sriram");
            extent.setSystemInfo("Framework", "Selenium + TestNG");
            extent.setSystemInfo("Environment", "QA");

            log.info("Extent Report Created at: {}", reportPath);
        }

        return extent;
    }
}