package com.rest;

import io.restassured.RestAssured;
import org.testng.annotations.Test;

import java.util.HashMap;

public class _012_Request_Query_Parameters_Vid_98 {


    @Test
    public void single_query_parameter(){

        RestAssured.given()
                          .baseUri("https://postman-echo.com")
                          .param("foo1", "bar1")          //<-- both methods are same
                          .queryParam("foo1", "bar1")     //<--  .param()  and  .queryParam()
                   .when()                                           //.param()  is generic we can use anywhere
                          .post("/get")                  // .queryParam()  it is specific and used only to
                   .then()                                 // insert  query parameters
                          .log().all()
                          .assertThat()
                          .statusCode(200);
    }


    @Test
    public void multiple_query_parameters(){

        RestAssured.given()
                         .baseUri("https://postman-echo.com")
                         .queryParam("foo1", "bar1")
                         .queryParam("foo2", "bar2")
                   .when()
                         .post("/get")
                   .then()
                         .log().all()
                         .assertThat()
                         .statusCode(200);
    }


    @Test
    public void multiple_query_parameters_with_HashMap(){
        HashMap<String, String> allQueryParams = new HashMap<>();
        allQueryParams.put("foo1", "bar1");
        allQueryParams.put("foo2", "bar2");

        RestAssured.given()
                          .baseUri("https://postman-echo.com")
                          .queryParams(allQueryParams)
                   .when()
                          .post("/get")
                   .then()
                          .log().all()
                          .assertThat()
                          .statusCode(200);
    }

    @Test                                        // "key", "abc-1,abc-2,abc-3"      <-- one key has multiple values
    public void multi_value_query_parameters(){

        RestAssured.given()
                         .baseUri("https://postman-echo.com")
                         .queryParams("key", "abc-1, abc-2, abc-3")   // <-- we can use (,) comma for separation
                         .queryParams("key", "abc-1; abc-2; abc-3")   // <-- we can use (;) semicolon for separation
                   .when()
                         .post("/get")
                   .then()
                         .log().all()
                         .assertThat()
                         .statusCode(200);
    }




}
