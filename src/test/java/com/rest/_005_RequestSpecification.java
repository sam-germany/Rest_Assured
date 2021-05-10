package com.rest;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.QueryableRequestSpecification;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.SpecificationQuerier;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;

public class _005_RequestSpecification {



 RequestSpecification requestSpecification;    // every Method of RestAssured comes under RequestSpecification Interface
                                               // so we can remove so  much of duplicate code with this see this example below

    @BeforeClass                // this method will be executed before all any @Test method executes
    public void before_Class() {
         requestSpecification = RestAssured.given()
                                       .baseUri("https://api.postman.com")
                                       .header("X-Api-Key","PMAK-60942815a6d1310048da6638-fcf89349e5d3af5ac4d1599a6c16367a2a")
                                       .log().all();
   }

   @Test
   public void validate_status_code() {

        Response response = requestSpecification.get("/workspaces");
        assertThat(response.statusCode(), is(equalTo(200)));
   }

   @Test
   public void validate_response_body(){
       Response response = requestSpecification.get("/workspaces");
       assertThat(response.statusCode(), is(equalTo(200)));
       assertThat(response.path("workspaces[0].name").toString(), equalTo("Rest_Assured"));
   }

   @Test
    public void queryTest() {   // to find the base uri that comes with request
       QueryableRequestSpecification qRs = SpecificationQuerier.query(requestSpecification);

       System.out.println(qRs.getBaseUri());
   }






}
