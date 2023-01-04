package org.mln.testcases;

import org.assertj.core.api.Assertions;
import org.json.JSONException;
import org.mln.annotations.TestInfo;
import org.mln.enums.Categories;
import org.mln.enums.HTTPMethods;
import org.mln.libraries.JSONCustomAssert;
import org.mln.libraries.RequestBuilder;
import org.mln.libraries.RestResponse;
import org.testng.annotations.Test;
import java.io.IOException;
import java.util.HashMap;

public class ProductsTest extends BaseTest {

private ProductsTest(){}
    @TestInfo(author = {"Lakshmi Mohan"},categories = {Categories.FUNCTIONAL,Categories.SMOKE})
    @Test
    public void getAllProducts(Integer runCount , HashMap<String,String> testdata) throws IOException, JSONException {
        String body = "";
        RequestBuilder requestBuilder = new RequestBuilder(HTTPMethods.GET,url,auth);
        RestResponse response = RestResponse.getRestResponse(requestBuilder);
        JSONCustomAssert.jsonCustomAssertEqualsWithPath(response.getResponse().asString(),expectedresponse,"$.products[4]");
    }
    @TestInfo(author = {"Lakshmi Narasimhan"},categories = {Categories.SMOKE})
    @Test
    public void getSingleProduct(Integer runCount , HashMap<String,String> testdata) throws IOException, JSONException {
        String body = "";
        RequestBuilder requestBuilder = new RequestBuilder(HTTPMethods.GET,url,auth);
        RestResponse response = RestResponse.getRestResponse(requestBuilder);
        JSONCustomAssert.jsonCustomAssertEqualsIgnoreNodes(response.getResponse().asString(),expectedresponse,ignoreNodeList);
    }
    @TestInfo(author = {"Lakshmi Narasimhan"},categories = {Categories.FUNCTIONAL})
    @Test
    public void addAProduct(Integer runCount , HashMap<String,String> testdata) throws IOException, JSONException {

        RequestBuilder requestBuilder = new RequestBuilder(HTTPMethods.POST,url,auth,body);
        RestResponse response = RestResponse.getRestResponse(requestBuilder);
        JSONCustomAssert.jsonCustomAssertEquals(response.getResponse().asString(),expectedresponse);
    }


}
