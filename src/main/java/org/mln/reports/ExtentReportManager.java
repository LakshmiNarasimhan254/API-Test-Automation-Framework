package org.mln.reports;


import com.aventstack.extentreports.ExtentTest;

import java.util.Objects;


/**
 * This class is used to store the ExtentTest object in a ThreadLocal variable
 */
public class ExtentReportManager {
    private ExtentReportManager(){}
    //
    private static ThreadLocal<ExtentTest>tl = new ThreadLocal<>();

    /**
     * It returns the ExtentTest object that is stored in the ThreadLocal object
     *
     * @return The ExtentTest object is being returned.
     */
    static ExtentTest getExtentTest(){//Made it to Defaul access specifier so that it is private to package
        return tl.get();
    }
    /**
     * It sets the ExtentTest object in the ThreadLocal variable
     *
     * @param extentTest ExtentTest object
     */
    static void setExtentTest(ExtentTest extentTest){
        if(Objects.nonNull(extentTest)) {
            tl.set(extentTest);
        }
    }
    /**
     * > The function `unLoad()` removes the current thread from the `ThreadLocal` object `tl`
     */
    static void unLoad(){
        tl.remove();
    }
}
