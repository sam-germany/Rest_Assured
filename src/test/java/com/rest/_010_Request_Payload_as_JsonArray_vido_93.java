package com.rest;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.filter.log.LogDetail;
import io.restassured.http.ContentType;
import io.restassured.specification.ResponseSpecification;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.hamcrest.Matchers.equalTo;

public class _010_Request_Payload_as_JsonArray_vido_93 {
    ResponseSpecification customRS;

    @BeforeClass                  // <-- this Annotated method will be executed before all the @Test methods
    public void before_Class(){

    RequestSpecBuilder rsb_1 = new RequestSpecBuilder()
                                     .setBaseUri("https://api.postman.com")
                                     .addHeader("X-Api-Key","PMAK-60942815a6d1310048da6638-fcf89349e5d3af5ac4d1599a6c16367a2a")
                                     .addHeader("x-mock-match-request-body", "true")
                                     .setContentType("application/json;charset=utf-8")    // in POST request we need to specify the Content-Type
                                     .log(LogDetail.ALL);

        RestAssured.requestSpecification = rsb_1.build();   // at this point we are creating a new object of  "requestSpecification"
                                                            // and storing the response in it, when we need to use it we need directly
                                                            // use the RestAssured.get()   method then we can call it

    ResponseSpecBuilder rsb_2 = new ResponseSpecBuilder()
                                     .expectStatusCode(200)
                                     .expectContentType(ContentType.JSON)
                                     .log(LogDetail.ALL);

        customRS = rsb_2.build();
    }

    @Test
    public void validate_post_request_Payload_Array_as_List(){
        HashMap<String, String> obj1 = new HashMap<>();
        obj1.put("id", "5001");
        obj1.put("type","None");

        HashMap<String, String> obj2 = new HashMap<>();
        obj1.put("id", "5002");
        obj1.put("type","aaaa");

        List<Map<String,String>> jsonList = new ArrayList<>();
        jsonList.add(obj1);
        jsonList.add(obj2);

        RestAssured.given()
                          .body(jsonList)
                   .when()
                          .post()
                   .then().spec(customRS)
                          .log().all()
                          .assertThat()
                          .body("msg", equalTo("Success"));

    }
}
