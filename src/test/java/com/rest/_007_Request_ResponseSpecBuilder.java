package com.rest;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.filter.log.LogDetail;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

public class _007_Request_ResponseSpecBuilder {


    @BeforeClass                // this method will be executed before all any @Test method executes
    public void before_Class() {

         RequestSpecBuilder rsb_1 = new RequestSpecBuilder();
         rsb_1.setBaseUri("https://api.postman.com");
         rsb_1.addHeader("X-Api-Key","PMAK-60942815a6d1310048da6638-fcf89349e5d3af5ac4d1599a6c16367a2a");
         rsb_1.log(LogDetail.ALL);

          RestAssured.requestSpecification  = rsb_1.build();


        ResponseSpecBuilder rsb_2 = new ResponseSpecBuilder().expectStatusCode(200)
                                                           .expectContentType(ContentType.JSON)
                                                           .log(LogDetail.ALL);

        RestAssured.responseSpecification = rsb_2.build();
    }

   @Test
   public void validate_status_code() {         //  RestAssured.requestSpecification  = rsb_1.build();
                                                //
       RestAssured.get("/workspaces");     //   here   RestAssured  object is created and store the response
   }                                            // that come after this "rsb_1.build();"   method in it
                                                // this is the reason  we directly call the
                                               // RestAssured.get("/workspaces");    as we do not use any other
                                                 // refrence variable,

   @Test
   public void validate_response_body(){
       Response response = RestAssured.get("/workspaces")
                                               .then()
                                               .extract()
                                               .response();

       assertThat(response.path("workspaces[0].name").toString(), equalTo("Rest_Assured"));
   }
}
