package org.mln.reports;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
import org.mln.constants.FrameworkConstants;
import org.mln.enums.Categories;
import org.mln.enums.ConfigProperties;
import org.mln.utils.PropertyUtil;

import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.util.Objects;

/**
 * This class is used to initialize the ExtentReports object and also to create a test in the report
 */
public final class ExtentReport {
    private ExtentReport(){
    }
    private static ExtentReports extentReports;

    /**
     * This function is used to initialize the ExtentReports object
     */
    public static void initReports()  {
        if(Objects.isNull(extentReports)) {
            extentReports = new ExtentReports();
            ExtentSparkReporter extentSparkReporter = new ExtentSparkReporter(FrameworkConstants.getExtenReportFilePath());
            extentReports.attachReporter(extentSparkReporter);
            extentSparkReporter.config().setTheme(Theme.STANDARD);
            extentSparkReporter.config().setReportName(PropertyUtil.getValue(ConfigProperties.APPNAME));
            extentSparkReporter.config().setDocumentTitle("Automation-Report");
        }
    }

    /**
     * It flushes the report, unloads the extent report manager and opens the report in the default browser
     */
    public static void flushReports() {
        if(Objects.nonNull(extentReports)) {
            extentReports.flush();
        }
           ExtentReportManager.unLoad();

        try {
            Desktop.getDesktop().browse(new File(FrameworkConstants.getExtenReportFilePath()).toURI());
        } catch (IOException e) {
            // Did not handle using try catch because , don't want to stop the program because it is just opening the report
            e.printStackTrace();
        }


    }
    /**
     * This function creates a test case in the extent report
     *
     * @param testcaseName The name of the test case.
     */
    public static void createTest(String testcaseName){
          ExtentReportManager.setExtentTest(extentReports.createTest(testcaseName));

    }

    /**
     * It assigns the author name to the test case.
     *
     * @param authors String array of authors
     */
    public static void addAuthor(String[] authors){
        for(String author:authors){
            ExtentReportManager.getExtentTest().assignAuthor(author);
        }
    }
    public static void addCategory(Categories[] categories){
        for(Enum<Categories> category:categories){
            ExtentReportManager.getExtentTest().assignCategory(String.valueOf(category));
        }
    }
}
