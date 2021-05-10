package com.rest;

import io.restassured.RestAssured;
import io.restassured.http.Header;
import io.restassured.http.Headers;
import org.testng.annotations.Test;

import java.util.HashMap;

public class _002_Headers {

    @Test
    public void multiple_headers_1() {

  RestAssured.given()
                    .baseUri("https://1455ed1a-6ad4-4966-bba6-e902b9171734.mock.pstmn.io")
                    .header("header", "value1")
                    .header("x-mock-match-request-headers","header")
             .when()
                    .get("/get")
             .then()
                    .log().all()
                    .assertThat()
                    .statusCode(200);
    }

    @Test
    public void multiple_headers_shortcut_2() {
        Header header_1 = new Header("header","value2");
        Header header_2 = new Header("x-mock-match-request-headers","header");

    RestAssured.given()
                     .baseUri("https://1455ed1a-6ad4-4966-bba6-e902b9171734.mock.pstmn.io")
                     .header(header_1)
                     .header(header_2)
               .when()
                     .get("/get")
               .then()
                     .log().all()
                     .assertThat()
                     .statusCode(200);
    }


    @Test
    public void multiple_headers_shortcut_3() {
        Header header_1 = new Header("header","value2");
        Header header_2 = new Header("x-mock-match-request-headers","header");

        Headers allHeaders = new Headers(header_1, header_2);

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


    @Test
    public void multiple_headers_shortcut_4() {
        HashMap<String, String> mapHeaders = new HashMap<>();
        mapHeaders.put("header","value2");
        mapHeaders.put("x-mock-match-request-headers","header");

    RestAssured.given()
                     .baseUri("https://1455ed1a-6ad4-4966-bba6-e902b9171734.mock.pstmn.io")
                     .headers(mapHeaders)
               .when()
                     .get("/get")
               .then()
                     .log().all()
                     .assertThat()
                     .statusCode(200);
    }

}
