package org.mln.utils;

import org.mln.customexceptions.FileIOException;
import org.mln.enums.HTTPMethods;
import org.mln.libraries.RequestBuilder;
import org.mln.libraries.RestResponse;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public final class JSONUtil {
    private static RequestBuilder requestBuilder;
    private static RestResponse response;

    private JSONUtil(){}
    public static String readAsJsonString(String path)  {
        try {
            return new String(Files.readAllBytes(Paths.get(path)));
        } catch (IOException e) {
            throw new FileIOException(path);
        }
    }
    public static String getActualResponse(HTTPMethods httpMethods,String url,String auth,String body){
        if(httpMethods.name().equalsIgnoreCase(HTTPMethods.GET.name())){
            requestBuilder = new RequestBuilder(HTTPMethods.GET,url,auth);
            response = RestResponse.getRestResponse(requestBuilder);

        }else if (httpMethods.name().equalsIgnoreCase(HTTPMethods.POST.name())){
            requestBuilder = new RequestBuilder(HTTPMethods.POST,url,auth,body);
            response = RestResponse.getRestResponse(requestBuilder);

        }

        return response.getResponse().asString();
    }

}
