package org.mln.constants;

import lombok.Getter;
import org.mln.enums.ConfigProperties;
import org.mln.utils.PropertyUtil;


public class FrameworkConstants {


    @Getter
    private static final String TESTRESOURCEPATH = System.getProperty("user.dir") + "/src/test/resources/";
    @Getter
    private static final String CONFIGPATH = TESTRESOURCEPATH + "config/config.properties";
    @Getter
    private static final String EXTENTREPORTFOLDERPATH = System.getProperty("user.dir") + "/extent-reports/";

    @Getter
    private static final String EXCELPATH = TESTRESOURCEPATH+"excel/";
    @Getter
    private static final String RUNMANAGERPATH =EXCELPATH +"TestInput.xlsx";
    @Getter
    private static final String RUNMANAGERSHEET = "RunManager";
    @Getter
    private static final String TESTDATASHEET = "TestData";

    @Getter
    private static final String TESTDATAINPUT = TESTRESOURCEPATH +"data/input/";
    @Getter
    private static final String TESTDATAOUTPUT = TESTRESOURCEPATH +"data/output/";
    private static String extenReportFilePath = "";

    private FrameworkConstants() {
    }
    public static String getExtenReportFilePath() {
        if (PropertyUtil.getValue(ConfigProperties.OVERRIDEDYNAMICREPORT).equalsIgnoreCase("no")) {
            if (extenReportFilePath.isEmpty()) {
                extenReportFilePath = EXTENTREPORTFOLDERPATH + "index" + System.currentTimeMillis() + ".html";
            }

        } else {
            extenReportFilePath = EXTENTREPORTFOLDERPATH + "index.html";
        }
        return extenReportFilePath;
    }


}
