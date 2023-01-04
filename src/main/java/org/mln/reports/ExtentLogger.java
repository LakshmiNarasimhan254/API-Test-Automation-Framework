package org.mln.reports;

import com.aventstack.extentreports.MediaEntityBuilder;
import org.mln.enums.ConfigProperties;
import org.mln.utils.PropertyUtil;

/**
 * This class is used to log the test steps in the extent report
 */
public class ExtentLogger {

    private ExtentLogger() {
    }

    /**
     * If the value of the property "PASSEDSTEPSSCREENSHOT" is "YES", then the screenshot will be attached to the report
     *
     * @param loggerText
     *         The text that you want to log in the report.
     */
    public static void pass(String loggerText) {
        ExtentReportManager.getExtentTest()
                .pass(loggerText);
    }

    public static void fail(String loggerText) {
        ExtentReportManager.getExtentTest()
                .fail(loggerText);

    }

    public static void skip(String loggerText) {

        ExtentReportManager.getExtentTest()
                .skip(loggerText);

    }
    public static void info(String loggerText) {

        ExtentReportManager.getExtentTest()
                .info(loggerText);

    }


}
