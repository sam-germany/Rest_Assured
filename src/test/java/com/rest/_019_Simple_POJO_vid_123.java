package com.rest;

import com.rest.pojo.simple.SimplePojo;
import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.filter.log.LogDetail;
import io.restassured.http.ContentType;
import io.restassured.specification.ResponseSpecification;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static org.hamcrest.Matchers.equalTo;

public class _019_Simple_POJO_vid_123 {

    ResponseSpecification rs;

    @BeforeClass                  // <-- this Annotated method will be executed before all the @Test methods
    public void before_Class(){

        RequestSpecBuilder rsb_1 = new RequestSpecBuilder()
                .setBaseUri("https://1455ed1a-6ad4-4966-bba6-e902b9171734.mock.pstmn.io")
                .setContentType(ContentType.JSON)    // in POST request we need to specify the Content-Type
                .log(LogDetail.ALL);

        RestAssured.requestSpecification = rsb_1.build();   // at this point we are creating a new object of  "requestSpecification"
        // and storing the response in it, when we need to use it we need directly
        // use the RestAssured.get()   method then we can call it

        ResponseSpecBuilder rsb_2 = new ResponseSpecBuilder()
                .expectStatusCode(200)
                .expectContentType(ContentType.JSON)
                .log(LogDetail.ALL);

        rs = rsb_2.build();
    }

    @Test
    public void simple_pojo_example() {

        SimplePojo simplePojo = new SimplePojo("value1", "value2");


        RestAssured.given()
                          .body(simplePojo)
                   .when()
                          .post("/postSimpleJson")
                   .then().spec(rs)
                          .assertThat()
                          .body("key1", equalTo(simplePojo.getKey1()),
                "key2", equalTo(simplePojo.getKey2()));
    }


}
