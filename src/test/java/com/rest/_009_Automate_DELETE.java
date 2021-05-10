package com.rest;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.filter.log.LogDetail;
import io.restassured.http.ContentType;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.text.MatchesPattern.matchesPattern;

public class _009_Automate_DELETE {

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
    public void validate_post_request_bdd_style(){
        String workspaceId_1 = "0a14d1b0-3ac5-4ad4-a397-083a83ddbf1c";


           RestAssured.given()
                            .pathParam("workspaceId_2", workspaceId_1)   // watch video  88 to understand this
                      .when()
                            .delete("/workspaces/{workspaceId_2}")
                      .then()
                            .log().all()
                            .assertThat()
                            .body("workspace.id", matchesPattern("^[a-z0-9-]{36}"),
                             "workspace.id", equalTo(workspaceId_1));
    }
}
