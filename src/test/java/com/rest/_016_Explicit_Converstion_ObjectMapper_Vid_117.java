package com.rest;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.filter.log.LogDetail;
import io.restassured.http.ContentType;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.HashMap;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.text.MatchesPattern.matchesPattern;

public class _016_Explicit_Converstion_ObjectMapper_Vid_117 {

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
    public void validate_post_request_payload_as_Map() throws JsonProcessingException {
        HashMap<String, Object> parentObject   = new HashMap<>(); // Json binding maven dependency

        HashMap<String, String> childObject = new HashMap<>();
        childObject.put("name", "myFistWorkspace");
        childObject.put("type", "personal");
        childObject.put("description", "Rest Assured created this");

        parentObject.put("workspace", childObject);

        ObjectMapper objectMapper = new ObjectMapper();
        String parentObjectAs_String = objectMapper.writeValueAsString(parentObject);

        RestAssured.given()
                         .body(parentObjectAs_String)
                   .when()
                         .post("/workspaces")
                   .then()
                         .log().all()
                         .assertThat()
                         .body( "workspace.name", equalTo("mySecondWorkspace"),
                           "workspace.id", matchesPattern("^[a-z0-9-]{36}")
                );
    }
}
/*
 ObjectMapper objectMapper = new ObjectMapper();    <-- here we are doing a explicit conversion from  a
                                   List-Object, Map-Object  into String , but normally it is done automatically
                                                 by the Rest-Assured for us as we are using Jackson Dependency

 */
