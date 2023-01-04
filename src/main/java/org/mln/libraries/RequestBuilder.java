package org.mln.libraries;

import io.restassured.http.Headers;
import lombok.Getter;
import lombok.Setter;
import org.mln.enums.HTTPMethods;

import java.util.Objects;

public class RequestBuilder {
    @Getter
    @Setter
    private String url;
    @Getter
    @Setter
    private String auth;
    @Getter
    @Setter
    private String body;
    @Getter
    @Setter
    private HTTPMethods httpMethods;
    @Getter
    @Setter
    private String contentType;
    @Getter
    @Setter
    private Headers headers;


    private RequestBuilder() {
    }

    public RequestBuilder(HTTPMethods httpMethods, String url, String auth, String body) {

        this.setAuth(auth);
        this.setBody(body);
        this.setUrl(url);
        this.setContentType("application/json");
        this.setHttpMethods(httpMethods);
    }

    public RequestBuilder(HTTPMethods httpMethods, String url, String auth) {
        this.setAuth(auth);
        this.setUrl(url);
        this.setContentType("application/json");
        this.setHttpMethods(httpMethods);
    }

}
