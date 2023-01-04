package org.mln.libraries;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.Header;
import io.restassured.http.Headers;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import lombok.Getter;
import org.codehaus.groovy.transform.SourceURIASTTransformation;

import java.util.Objects;

import static io.restassured.RestAssured.given;


public class RestResponse {

    @Getter
    private Response response;
    @Getter
    private Headers headers;
    @Getter
    private int statusCode;
    @Getter
    private long responseTime;

    public static RestResponse getRestResponse(RequestBuilder requestBuilder) {
       RestResponse restResponse = new RestResponse();
        Headers requestHeaders = requestBuilder.getHeaders();
        RequestSpecBuilder requestSpecBuilder = new RequestSpecBuilder();

        if (Objects.nonNull(requestBuilder.getContentType())) {
            requestSpecBuilder.setContentType(requestBuilder.getContentType());
        }

        RequestSpecification requestSpecification = requestSpecBuilder.build();

        if (requestBuilder.getHttpMethods().name().equals("POST")) {
            Response restResponseObject;
            if (Objects.nonNull(requestBuilder.getBody())) {
                restResponseObject = given()
                        .spec(requestSpecification)
                        .body(requestBuilder.getBody())
                        .when()
                        .post(requestBuilder.getUrl())
                        .then()
                        .extract()
                        .response();
                restResponse.response = restResponseObject;
                restResponse.headers = restResponseObject.headers();
                restResponse.statusCode = restResponseObject.getStatusCode();
                restResponse.responseTime = restResponseObject.getTime();
            }
        } else if (requestBuilder.getHttpMethods().name().equals("GET")) {
            Response restResponseObject;
            restResponseObject = given()
                    .spec(requestSpecification)
                    .when()
                    .get(requestBuilder.getUrl())
                    .then()
                    .extract()
                    .response();
            restResponse.response = restResponseObject;
            restResponse.headers = restResponseObject.headers();
            restResponse.statusCode = restResponseObject.getStatusCode();
            restResponse.responseTime = restResponseObject.getTime();


        }

        return restResponse;
    }

}
