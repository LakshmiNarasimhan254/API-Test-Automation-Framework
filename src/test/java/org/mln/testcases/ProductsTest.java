package org.mln.testcases;


import org.json.JSONException;
import org.mln.annotations.TestInfo;
import org.mln.enums.Categories;
import org.mln.enums.HTTPMethods;
import org.mln.libraries.JSONCustomAssert;
import org.mln.utils.JSONUtil;
import org.testng.annotations.Test;

import java.util.HashMap;

public class ProductsTest extends BaseTest {

private ProductsTest(){}
    @TestInfo(author = {"Lakshmi Mohan"},categories = {Categories.FUNCTIONAL,Categories.SMOKE})
    @Test
    public void getAllProducts(Integer runCount , HashMap<String,String> testdata) {
        actualResponse= JSONUtil.getActualResponse(HTTPMethods.GET,url,auth,body);
        JSONCustomAssert.jsonCustomAssertEqualsWithPath(actualResponse,expectedresponse,"$.products[4]");
    }
    @TestInfo(author = {"Lakshmi Narasimhan"},categories = {Categories.SMOKE})
    @Test
    public void getSingleProduct(Integer runCount , HashMap<String,String> testdata) {
        actualResponse= JSONUtil.getActualResponse(HTTPMethods.GET,url,auth,body);
        JSONCustomAssert.jsonCustomAssertEqualsIgnoreNodes(actualResponse,expectedresponse,ignoreNodeList);
    }
    @TestInfo(author = {"Lakshmi Narasimhan"},categories = {Categories.FUNCTIONAL})
    @Test
    public void addAProduct(Integer runCount , HashMap<String,String> testdata) {

        actualResponse= JSONUtil.getActualResponse(HTTPMethods.POST,url,auth,body);
        JSONCustomAssert.jsonCustomAssertEquals(actualResponse,expectedresponse);
        JSONCustomAssert.jsonCustomAssert(actualResponse)
                .inPath(testdata.get("JsonPath")).isEqualTo(testdata.get("Expected Value"));

    }


}
