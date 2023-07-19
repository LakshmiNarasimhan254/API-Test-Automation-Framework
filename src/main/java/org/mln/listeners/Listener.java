package org.mln.listeners;


import org.mln.annotations.TestInfo;
import org.mln.reports.ExtentLogger;
import org.mln.reports.ExtentReport;
import org.testng.*;

import java.util.Arrays;

/**
 * This class implements the ITestListener and ISuiteListener interfaces. The onStart() method of the ISuiteListener
 * interface is used to initialize the ExtentReports object. The onFinish() method of the ISuiteListener interface is used
 * to flush the ExtentReports object. The onTestStart() method of the ITestListener interface is used to create a test in
 * the ExtentReports object. The onTestSuccess() method of the ITestListener interface is used to log a passed test in the
 * ExtentReports object. The onTestFailure() method of the ITestListener interface is used to log a failed test in the
 * ExtentReports object. The onTestSkipped() method of the ITestListener interface is used to log a skipped test in the
 * ExtentReports object
 */
public class Listener implements ITestListener, ISuiteListener {

    @Override
    // This method is called before the Suite start
    public void onStart(ISuite suite) {
        ExtentReport.initReports();
    }


    @Override
    // This method is called after the Suite finish
    public void onFinish(ISuite suite) {
        ExtentReport.flushReports();
    }


    @Override
    // This method is called before the test start
    public void onTestStart(ITestResult result) {

        ExtentReport.createTest(result.getMethod().getMethodName());
        ExtentReport.addAuthor(result
                                .getMethod()
                                .getConstructorOrMethod()
                                .getMethod()
                                .getAnnotation(TestInfo.class)
                                .author());

        ExtentReport.addCategory(result
                .getMethod()
                .getConstructorOrMethod()
                .getMethod()
                .getAnnotation(TestInfo.class)
                .categories());
    }


    @Override
    // This method is called when a test is passed.
    public void onTestSuccess(ITestResult result) {
        ExtentLogger.pass(result.getMethod().getMethodName() + " is  Passed");



    }

    @Override
    // This method is called when a test is failed.
    public void onTestFailure(ITestResult result) {
        ExtentLogger.fail(result.getMethod().getMethodName() + " is  Failed. Reason: "+ result.getThrowable().getMessage());
    }


    @Override
    // This method is called when a test is skipped.
    public void onTestSkipped(ITestResult result) {
        ExtentLogger.skip(result.getMethod().getMethodName() + " is  Skipped");
    }

    @Override
    // This method is called when a test is failed but within success percentage.
    public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
        ITestListener.super.onTestFailedButWithinSuccessPercentage(result);
    }

    @Override
    // This method is called when a test is failed with timeout.
    public void onTestFailedWithTimeout(ITestResult result) {
        //No operation for now
    }

    @Override
    // This method is called when a test is failed with timeout.
    public void onStart(ITestContext context) {
        //No operation for now
    }

    @Override
    // This method is called when a test is failed with timeout.
    public void onFinish(ITestContext context) {
        //No operation for now

    }
}
