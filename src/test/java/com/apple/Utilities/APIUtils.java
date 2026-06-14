package com.apple.Utilities;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintStream;

public class APIUtils {
   public static RequestSpecification requestSpec;

    public  RequestSpecification requestSpecification() throws FileNotFoundException {

        if (requestSpec == null) {
            PrintStream log = new PrintStream(new FileOutputStream("logging.txt"));
            requestSpec = new RequestSpecBuilder().setBaseUri(ConfigurationReader.getProperty("baseUrl"))
                    .addQueryParam("key", "qaclick123")
                    .addFilter(RequestLoggingFilter.logRequestTo(log))
                    .addFilter(ResponseLoggingFilter.logResponseTo(log))
                    .setContentType(ContentType.JSON).build();
            return requestSpec;
        }
        return requestSpec;
    }

    public String getJsonPath (Response response , String key){

        String resp = response.asString();
       JsonPath json = new JsonPath(resp);
       return json.get(key).toString();
    }
}
