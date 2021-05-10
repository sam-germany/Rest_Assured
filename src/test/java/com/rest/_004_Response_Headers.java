package com.rest;

import io.restassured.RestAssured;
import io.restassured.http.Headers;
import org.testng.annotations.Test;

import java.util.HashMap;
import java.util.List;

public class _004_Response_Headers {

    @Test
    public void assert_response_headers (){
        HashMap<String, String> mapHeaders = new HashMap<>();
        mapHeaders.put("header","value2");
        mapHeaders.put("x-mock-match-request-headers","header");

    RestAssured.given()
                     .baseUri("https://1455ed1a-6ad4-4966-bba6-e902b9171734.mock.pstmn.io")
                     .headers(mapHeaders)
               .when()
                     .get("/get")
               .then()
                     .assertThat()
                     .statusCode(200)
                     .header("responseHeader", "resValue2")
                     .header("X-RateLimit-Limit", "120");
    }


    @Test
    public void assert_response_headers_shortcut (){
        HashMap<String, String> mapHeaders = new HashMap<>();
        mapHeaders.put("header","value2");
        mapHeaders.put("x-mock-match-request-headers","header");

    RestAssured.given()
                     .baseUri("https://1455ed1a-6ad4-4966-bba6-e902b9171734.mock.pstmn.io")
                     .headers(mapHeaders)
               .when()
                     .get("/get")
               .then()
                     .assertThat()
                     .statusCode(200)
                     .headers("responseHeader", "resValue2",
                         "X-RateLimit-Limit", "120");
    }

    @Test
    public void extract_response_headers() {
        HashMap<String, String> mapHeaders = new HashMap<>();
        mapHeaders.put("header", "value2");
        mapHeaders.put("x-mock-match-request-headers", "header");

Headers extracted_Header22 = RestAssured.given()
                              .baseUri("https://1455ed1a-6ad4-4966-bba6-e902b9171734.mock.pstmn.io")
                              .headers(mapHeaders)
                        .when()
                              .get("/get")
                        .then()
                              .assertThat()
                              .statusCode(200)
                              .extract()
                              .headers();

System.out.println("Header Name" + extracted_Header22.get("responseHeader").getName());
System.out.println("Header Value" + extracted_Header22.get("responseHeader").getValue());

System.out.println("Header Value" + extracted_Header22.getValue("responseHeader"));
    }



    @Test
    public void extract_response_multi_Value_headers() {
        HashMap<String, String> mapHeaders = new HashMap<>();
        mapHeaders.put("header", "value2");
        mapHeaders.put("x-mock-match-request-headers", "header");

Headers extracted_Header22 = RestAssured.given()
                              .baseUri("https://1455ed1a-6ad4-4966-bba6-e902b9171734.mock.pstmn.io")
                              .headers(mapHeaders)
                        .when()
                              .get("/get")
                        .then()
                              .assertThat()
                              .statusCode(200)
                              .extract()
                              .headers();

   List<String> multiValues  = extracted_Header22.getValues("multiValueHeader");

   for(String x: multiValues) {
       System.out.println(x);
   }

    }
}
