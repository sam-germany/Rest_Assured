package com.rest;

import io.restassured.RestAssured;
import org.testng.annotations.Test;

import java.util.HashMap;

public class _013_Request_Path_Parameters {

    @Test
    public void path_parameters() {
                    //https://regres.in/api/users/2     <-- this request will create at end this query

     RestAssured.given()
                      .baseUri("https://regres.in")
                      .pathParam("userId","2")
                      .log().all()
                .when()
                      .get("/api/users/{userId}")
                .then()
                      .log().all()
                      .assertThat()
                      .statusCode(200);
    }

    @Test                  //https://regres.in/api/users/2/11     <-- this request will create at end this query
    public void multiple_path_parameters() {


        RestAssured.given()
                          .baseUri("https://regres.in")
                          .pathParam("userId","2")
                          .pathParam("bookingId","11")
                          .log().all()
                   .when()
                         .get("/api/users/{userId}/{bookingId}")
                   .then()
                         .log().all()
                         .assertThat()
                         .statusCode(200);
    }


    @Test                         //https://regres.in/api/users/2/11     <-- this request will create at end this query
    public void multiple_path_parameters_with_HashMap() {
        HashMap<String, String> allPathParams = new HashMap<>();
        allPathParams.put("userId","2");
        allPathParams.put("bookingId","11");

        RestAssured.given()
                          .baseUri("https://regres.in")
                          .pathParams(allPathParams)
                          .log().all()
                   .when()
                          .get("/api/users/{userId}/{bookingId}")
                   .then()
                          .log().all()
                          .assertThat()
                          .statusCode(200);
    }
}
