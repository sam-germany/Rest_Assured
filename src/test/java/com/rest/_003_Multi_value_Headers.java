package com.rest;

import io.restassured.RestAssured;
import io.restassured.http.Header;
import io.restassured.http.Headers;
import org.testng.annotations.Test;

public class _003_Multi_value_Headers {

    @Test
    public void multi_Value_Headers (){

   RestAssured.given()
                 .baseUri("https://1455ed1a-6ad4-4966-bba6-e902b9171734.mock.pstmn.io")
                 .header("anyKey", "value1", "value2")   // normally if we see in the postman
              .when()                                             // if we do the same request then we
                 .get("/get")                          // have to put two entries in the Request Header
              .then()                                     //Header_1    -->   "anyKey", "value1",
                 .log().all()                             //Header_2    -->   "anyKey", "value2",
                 .assertThat()
                 .statusCode(200);
    }


    @Test
    public void multi_Value_Headers_2(){

        Header header_1 = new Header("anyKey", "value1");
        Header header_2 = new Header("anyKey", "value2");

        Headers  allHeaders = new Headers(header_1, header_2);

   RestAssured.given()
                   .baseUri("https://1455ed1a-6ad4-4966-bba6-e902b9171734.mock.pstmn.io")
                   .headers(allHeaders)
              .when()
                   .get("/get")
              .then()
                   .log().all()
                   .assertThat()
                   .statusCode(200);
    }
}
