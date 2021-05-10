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

import static org.hamcrest.Matchers.equalTo;

public class _011_POST_complex_JSON_Array_vid_95 {
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
    public void validate_post_request_Payload_complex_JSON(){

        HashMap<String, Object>  betterHashMap1 = new HashMap<>();
        betterHashMap1.put("id", "1001" );
        betterHashMap1.put("type","Regular");

        List<Integer> idArrayList = new ArrayList<>();
        idArrayList.add(5);
        idArrayList.add(9);
        HashMap<String, Object>  betterHashMap2 = new HashMap<>();
        betterHashMap2.put("id", idArrayList );
        betterHashMap2.put("type","Chocolate");

        List<HashMap<String, Object>> betterArrayList = new ArrayList<>();
        betterArrayList.add(betterHashMap1);
        betterArrayList.add(betterHashMap2);

        HashMap<String, List<HashMap<String, Object>>> betterHashMap = new HashMap<>();
        betterHashMap.put("better", betterArrayList);


        HashMap<String, Object> toppingHashMap1 = new HashMap<>();
        toppingHashMap1.put("id", "5001");
        toppingHashMap1.put("type", "None");

        List<String> typeArrayList = new ArrayList<>();
        typeArrayList.add("test1");
        typeArrayList.add("test2");
        HashMap<String, Object> toppingHashMap2 = new HashMap<>();
        toppingHashMap2.put("id", "5002");
        toppingHashMap2.put("type", typeArrayList);


        List<HashMap<String,Object>> toppingArrayList = new ArrayList<>();
        toppingArrayList.add(toppingHashMap1);
        toppingArrayList.add(toppingHashMap2);

        HashMap<String, Object> mainHashMap = new HashMap<>();
        mainHashMap.put("id", "0001");
        mainHashMap.put("type", "donut");
        mainHashMap.put("name", "Cake");
        mainHashMap.put("ppu", 0.55);
        mainHashMap.put("betters", betterHashMap);
        mainHashMap.put("topping", toppingArrayList);



        RestAssured.given()
                          .body(mainHashMap)
                   .when()
                          .post("/POST_complex_JSON")
                   .then().spec(customRS)
                          .log().all()
                          .assertThat()
                          .body("msg", equalTo("Success"));

    }
}
