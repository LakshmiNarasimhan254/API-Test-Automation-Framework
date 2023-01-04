package org.mln.utils;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.mln.customexceptions.CustomException;
import org.mln.customexceptions.FileIOException;
import org.mln.customexceptions.InvalidPathForFileException;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.*;

public class ExcelUtil {

    private ExcelUtil() {
    }


    /**
     * It reads the excel file and returns a list of maps, where each map represents a row in the excel sheet
     *
     * @param excelFilePath The path of the excel file.
     * @param excelSheetName The name of the sheet in the excel file.
     * @return A list of maps.
     */
    public static List<Map<String, String>> getExcelRowDataAsMapList(String excelFilePath, String excelSheetName) {
        List<Map<String, String>> excelData = new ArrayList<>();
        try (FileInputStream fileInputStream = new FileInputStream(excelFilePath)) {
            XSSFWorkbook wb = new XSSFWorkbook(fileInputStream);
            XSSFSheet sheet = wb.getSheet(excelSheetName);
            int iRowCount = getExcelRowCount(sheet);

            for (int iRow = 1; iRow <= iRowCount; iRow++) {
                excelData.add(getExcelRowAsMap(sheet, iRow));
            }
        } catch (FileNotFoundException e) {
            throw new InvalidPathForFileException(excelFilePath+"/"+excelSheetName);
        } catch (IOException e) {
            throw new FileIOException(excelFilePath+"/"+excelSheetName);
        } catch (Exception e) {
            throw new CustomException(e.getMessage());
        }
        return excelData;
    }


    public String[][] getAnyExcelDataAs2DString(String excelFilePath, String excelSheetName) {
        String[][] excelData = new String[0][];
        try (FileInputStream fileInputStream = new FileInputStream(excelFilePath)) {
            XSSFWorkbook wb = new XSSFWorkbook(fileInputStream);
            XSSFSheet sheet = wb.getSheet(excelSheetName);
            int iRowCount = getExcelRowCount(sheet);
            int iColCount = getExcelColCount(sheet);
            Iterator<Row> rowIterator = sheet.rowIterator();
            excelData = new String[iRowCount][iColCount];
            int iRowNo = 0;
            while (rowIterator.hasNext()) {
                Row rowValue = rowIterator.next();
                Iterator<Cell> cellIterator = rowValue.iterator();

                int iColNo = 0;
                if (iRowNo > 0) {
                    while (cellIterator.hasNext()) {
                        excelData[iRowNo - 1][iColNo] = cellIterator.next().toString();
                        iColNo++;
                    }
                }
                iRowNo++;
            }
        } catch (FileNotFoundException e) {
            throw new InvalidPathForFileException(excelFilePath+"/"+excelSheetName);
        } catch (IOException e) {
            throw new FileIOException(excelFilePath+"/"+excelSheetName);
        } catch (Exception e) {
            throw new CustomException(e.getMessage());
        }
        return excelData;

    }

    public static String[][] getExcelDataMethod2(String excelFilePath, String excelSheetName) throws IOException {

        String[][] excelData = new String[0][];
        try (FileInputStream fileInputStream = new FileInputStream(excelFilePath)) {
            XSSFWorkbook wb = new XSSFWorkbook(fileInputStream);
            XSSFSheet sheet = wb.getSheet(excelSheetName);
            int iRowCount = getExcelRowCount(sheet);
            int iColCount = getExcelColCount(sheet);
            excelData = new String[iRowCount][iColCount];
            for (int iRow = 1; iRow <= iRowCount; iRow++) {
                for (int iCol = 0; iCol < iColCount; iCol++) {
                    excelData[iRow - 1][iCol] = sheet.getRow(iRow).getCell(iCol).getStringCellValue();

                }
            }
        } catch (FileNotFoundException e) {
            throw new InvalidPathForFileException(excelFilePath+"/"+excelSheetName);
        } catch (IOException e) {
            throw new FileIOException(excelFilePath+"/"+excelSheetName);
        } catch (Exception e) {
            throw new CustomException(e.getMessage());
        }
        return excelData;

    }

    public static Object[][] getExcelData(String excelFilePath, String excelSheetName) throws IOException {
        Object[][] excelData = new Object[0][];
        try (FileInputStream fileInputStream = new FileInputStream(excelFilePath)) {
            XSSFWorkbook wb = new XSSFWorkbook(fileInputStream);
            XSSFSheet sheet = wb.getSheet(excelSheetName);
            int iRowCount = getExcelRowCount(sheet);
            excelData = new Object[iRowCount][iRowCount];
            for (int iRow = 1; iRow <= iRowCount; iRow++) {

                excelData[iRow - 1][0] = iRow;
                excelData[iRow - 1][1] = getExcelRowAsMap(sheet, iRow);
            }
        } catch (FileNotFoundException e) {
            throw new InvalidPathForFileException(excelFilePath+"/"+excelSheetName, " File is not found in");
        } catch (IOException e) {
            throw new FileIOException(excelFilePath+"/"+excelSheetName, " File IO Exception occurred");
        } catch (Exception e) {
            throw new CustomException(e.getMessage());
        }

        return excelData;


    }


    private static int getExcelRowCount(XSSFSheet sheet) {
        return sheet.getLastRowNum();
    }

    private static int getExcelColCount(XSSFSheet sheet) {
        return sheet.getRow(getExcelRowCount(sheet)).getLastCellNum();
    }

    private static Map<String, String> getExcelRowAsMap(XSSFSheet sheet, int excelRow) {
        Map<String, String> excelRowMap = new HashMap<>();
        int iColCount = getExcelColCount(sheet);
        for (int iCol = 0; iCol < iColCount; iCol++) {
            String key = sheet.getRow(0).getCell(iCol).getStringCellValue();
            String value = sheet.getRow(excelRow).getCell(iCol).getStringCellValue();
            excelRowMap.put(key, value);
        }

        return excelRowMap;

    }


}




