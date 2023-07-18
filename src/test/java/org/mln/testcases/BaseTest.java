package org.mln.testcases;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import org.mln.constants.FrameworkConstants;
import org.mln.enums.ConfigProperties;
import org.mln.enums.HTTPMethods;
import org.mln.libraries.RequestBuilder;
import org.mln.libraries.RestResponse;
import org.mln.utils.CommonUtil;
import org.mln.utils.JSONUtil;
import org.mln.utils.PropertyUtil;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;

import java.lang.reflect.Array;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Map;

public class BaseTest {
   protected String url;
   protected String auth;
   protected String body;

   protected ArrayList<String> ignoreNodeList;
   protected ArrayList<String>ignoreValueList;

   protected String expectedresponse;
   protected String actualResponse;
    protected BaseTest() {}

    @BeforeMethod
    public void setUp(Object[] data, Method method) {
        Map<String, String> dataMap = (Map<String, String>) data[1];
        url = PropertyUtil.getValue(ConfigProperties.BASEURL) + dataMap.get("Path");
        auth = PropertyUtil.getValue(ConfigProperties.AUTHORISATION);
        if (dataMap.get("Request Body").contains(".json")) {
            body = JSONUtil.readAsJsonString(FrameworkConstants.getTESTDATAINPUT() + dataMap.get("Request Body"));
        }
        if (dataMap.get("Expected Response").contains(".json")) {
            expectedresponse = JSONUtil.readAsJsonString(FrameworkConstants.getTESTDATAOUTPUT() + dataMap.get("Expected Response"));
        }
        if(!dataMap.get("Ignore Node List").equals("")){
            ignoreNodeList= CommonUtil.getIgnoreList(dataMap.get("Ignore Node List"));
        }
        if(!dataMap.get("Ignore Value List").equals("")){
            ignoreValueList= CommonUtil.getIgnoreList(dataMap.get("Ignore Value List"));
        }
        String body = "";
        RequestBuilder requestBuilder = new RequestBuilder(HTTPMethods.GET,url,auth);
        RestResponse response = RestResponse.getRestResponse(requestBuilder);

    }
}
