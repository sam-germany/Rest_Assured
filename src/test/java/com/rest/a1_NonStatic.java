package com.rest;

import io.restassured.RestAssured;
import org.hamcrest.Matchers;
import org.testng.annotations.Test;

public class a1_NonStatic {

    @Test
    public void testCase(){
        RestAssured.given()
                           .baseUri("https://api.postman.comk")
                           .header("x-api-key", "PMAK-60942815a6d1310048da6638-fcf89349e5d3af5ac4d1599a6c16367a2a")
                   .when()
                           .get("/workspaces")
                   .then()
                           .statusCode(300)
                           .body("name", Matchers.is(Matchers.equalTo("sunny")),
                                         "email", Matchers.is(Matchers.equalTo("s")));

    }
}
