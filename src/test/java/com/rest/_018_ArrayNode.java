package com.rest;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.filter.log.LogDetail;
import io.restassured.http.ContentType;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.text.MatchesPattern.matchesPattern;

public class _018_ArrayNode {

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
    public void validate_post_request_with_ArrayNode() throws JsonProcessingException {

        ObjectMapper objectMapper = new ObjectMapper();
        ArrayNode  arrayNodeList = objectMapper.createArrayNode();

        ObjectNode objNode_1  = objectMapper.createObjectNode();
        objNode_1.put("id", "5001");
        objNode_1.put("type", "None");

        ObjectNode objNode_2  = objectMapper.createObjectNode();
        objNode_1.put("id", "5002");
        objNode_1.put("type", "Glazed");

        arrayNodeList.add(objNode_1);
        arrayNodeList.add(objNode_2);

        String jsonListStr = objectMapper.writeValueAsString(arrayNodeList);

        RestAssured.given()
                         .body(jsonListStr)
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
