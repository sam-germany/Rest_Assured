package com.rest;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

public class _006_ResponseSpecification {

 RequestSpecification requestSpecification;    // every Method of RestAssured comes under RequestSpecification Interface
                                               // so we can remove so  much of duplicate code with this see this example below

 ResponseSpecification responseSpecification;



    @BeforeClass                // this method will be executed before all any @Test method executes
    public void before_Class() {
    requestSpecification = RestAssured.given()
                .baseUri("https://api.postman.com")
                .header("X-Api-Key","PMAK-60942815a6d1310048da6638-fcf89349e5d3af5ac4d1599a6c16367a2a")
                .log().all();

    responseSpecification = RestAssured.expect()
                                       .statusCode(200)
                                       .contentType(ContentType.JSON);

    }
                                               //
   @Test
   public void validate_status_code() {

       requestSpecification.get("/workspaces")
                           .then().spec(responseSpecification)
                           .log().all();
   }                                     //.then().spec(responseSpecification)    <-- with this main point is
                                         //                                    the validation condition is written
                                         // in the above  responseSpecification  in the  @BeforeClass
   @Test
   public void validate_response_body(){
       Response response = requestSpecification.get("/workspaces")
                                               .then().spec(responseSpecification)
                                               .log().all()
                                               .extract()
                                               .response();

       assertThat(response.path("workspaces[0].name").toString(), equalTo("Rest_Assured"));
   }
}
