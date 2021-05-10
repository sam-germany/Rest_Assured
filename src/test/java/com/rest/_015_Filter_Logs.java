package com.rest;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.filter.log.LogDetail;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.http.ContentType;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class _015_Filter_Logs {

    @BeforeClass                  // <-- this Annotated method will be executed before all the @Test methods
    public void before_Class(){

        RequestSpecBuilder rsb_1 = new RequestSpecBuilder()
                .setBaseUri("https://api.postman.com")
                .addHeader("X-Api-Key","PMAK-60942815a6d1310048da6638-fcf89349e5d3af5ac4d1599a6c16367a2a")
                .setContentType(ContentType.JSON)    // in POST request we need to specify the Content-Type
                .log(LogDetail.ALL);

        RestAssured.requestSpecification = rsb_1.build();   // at this point we are creating a new object of  "requestSpecification"
        // and storing the response in it, when we need to use it we need directly
        // use the RestAssured.get()   method then we can call it

        ResponseSpecBuilder rsb_2 = new ResponseSpecBuilder()
                .expectStatusCode(200)
                .expectContentType(ContentType.JSON)
                .log(LogDetail.ALL);

        RestAssured.responseSpecification = rsb_2.build();
    }



    @Test
    public void loggingFilter() {

    RestAssured.given()
                     .baseUri("https://postman-echo.com")
                     .filter(new RequestLoggingFilter())       //<-- it is same as   .log().all()
                     .filter(new ResponseLoggingFilter())       //<-- it is same as   .log().all()
               .when()
                     .get("/get")
               .then()
                     .assertThat()
                     .statusCode(200);
    }


}
