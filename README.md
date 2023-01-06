
## API Test Automation Framework   
ATAF is the acronym for API Test Automation Framework# API Test Automation Framework   
ATAF is the acronym for API Test Automation Framework

## Description
This is a API Automation framework using RestAssured-Java and TestNG Framework.
Please read the document for its features, capability and usage 


## Libraries Used:
    1. RestAssured - API Automation
    2. TestNG -   Testing Library
    3. Extent Reports - Reporting Library to generate beautiful html reports
    4. jsonAssert - Assertion Library in Java for JSON
    5. jacksondatabind  - JSON Serailisation and deserialisation
    6. Lombok - To reduce boilerplate codes
    7. Apache poi -To control the text execution run and test data in excel
    

## Capabilities:
    1. Configuration can be changed from config. properties inside src/test/resources
    2. Ability perform various json validation like 
            a) validate Request and Response that includes 
               ignoring Nodes, values, Regex ,with jsonpaths
            b) validate JSON Schema validation
            
    3. Report creation that includes that actual and expected values 

## Adding more tests:
        Prerequisites: Java 8+, Maven 3.6.3 Installed and path set
    1. Tests should be added as per the convention followed. New testng tests should be created inside the src/test/java folder.
    2. All the tests should extend BaseTest.java
    3. Each test created should define the author and category in the @TestInfo annotation as mandatory parameters. This will be helpful while creating the extent reports.
    4. All the tests should have an Assertion. Have maximum of one to two assertions per test.
    5. All assertions should be done through JSONCustomAssert in the framework libararies.
    6. Request body should be placed in src/test/resources/data/input with .json extention
    7. Expected Response should be placed in src/test/resources/data/output with .json extention
       and the name of .json file should be in appropriate column in the TestInput.xlsx under
       src/test/resources/excel

## Including a Test 
    1. Update the @Test Method name in RunManager &  TestData sheet in src/test/resources/excel/
       TestInput.xlsx under TestName column 
    2. Update "Yes" Or "No" under the column Execute in both the sheet.
    3. Invocation count determines , how many runs the test should run

## Running Tests:
        Prerequisites: Java 8+, Maven 3.6.3 Installed and path set
    1. There are multiple ways to run the tests. During development phase you can use the testng.xml present in the root folder to run. Right click and choose run.
    2. Each of the testng xml file should contain the test classes to pick for the run.
    3. User can also choose the thread-count parameter to run the tests in parallel.
    4. User can also choose to run via maven commands which is highly recommended.
    5. Maven profiles are configured in the pom.xml. You can run these from IDE Terminal or any OS Terminal inside the project folder.
        mvn clean test - To run all the tests available in the testng.xml

### Note: User should not try to run the test from test class - Might end up in NPE. Because the listeners are configured only in testng.xml


## Report Interpretation:
    1. Dashboard view clearly indicates the start time, end time of the run, number of tests passed and failed. It also shows the time took for the individual tests in form of timeline. Hover the mouse over the timeline to get the exact time details.
    2. Tags tab in the Dashboard view clearly indicates the pass and fail according to the functionality.
    3. Click on each test tab to view the corresponding test log events.
    4. After each test run, reports can be found under root directory and Extent Reports folder.
    5. Press "l" on the keyboard after opening the report to switch the theme to Standard.


## Final Notes:
    1.I have not written any unit tests because all the methods are relatively small and static
    2.I have performed sonar lint analysis to check the code quality.
    3.I have used apis from dummyJson.com for sample testcases 

### Questions:
For any further clarification - please reach out to Lakshminarasimhan254@gmail.com

    
