package org.mln.utils;


import org.mln.constants.FrameworkConstants;
import org.testng.annotations.DataProvider;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;


/**
 * It reads the excel data and returns the data as a 2D object array
 */
public class ExcelDataProviderUtil {
    private static List<Map<String, String>> actualExcelData = new ArrayList<>();

    private ExcelDataProviderUtil() {
    }


    @DataProvider(parallel = false)
    // A method which is returning the data as a 2D object array.
    public static Object[][] getExcelData(Method method)  {
        String testName = method.getName();
        if (actualExcelData.isEmpty()) {
            actualExcelData = ExcelUtil.
                    getExcelRowDataAsMapList(
                            FrameworkConstants.getRUNMANAGERPATH()
                            ,FrameworkConstants.getTESTDATASHEET());
        }
        List<Map<String, String>> requiredExceldata = new ArrayList<>();

        for (Map<String, String> actualExcelDatum : actualExcelData) {
            if ((actualExcelDatum.get("TestName").equalsIgnoreCase(testName)) &&
                    (actualExcelDatum.get("Execute").equalsIgnoreCase("YES"))) {
                requiredExceldata.add(actualExcelDatum);
            }
        }

        Object[][] requiredExcelDataAsObjArray = new Object[requiredExceldata.size()][2];
        for (int index = 0; index < requiredExceldata.size(); index++) {
            requiredExcelDataAsObjArray[index][0] = index;
            requiredExcelDataAsObjArray[index][1] = requiredExceldata.get(index);

        }


        return requiredExcelDataAsObjArray;
    }
}
