package com.rest;

import io.restassured.RestAssured;
import io.restassured.config.LogConfig;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

import static io.restassured.RestAssured.config;
import static io.restassured.RestAssured.given;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class _001_Get_Request {

    @Test
    public void validate_response_body(){
        given()
               .baseUri("https://api.postman.com")
               .header("X-Api-Key", "PMAK-60942815a6d1310048da6638-fcf89349e5d3af5ac4d1599a6c16367a2a")
        .when()
               .get("/workspaces")
        .then()
               .log().all()         // <-- log().all() must be used before the .assertThat() method, because if
               .assertThat()          // we get a error in Assertion then as we are getting  log before it
               .statusCode(200)      // then we can check the response easily and issue is cleared
               .body("workspaces.name", hasItems("Rest_Assured","My Workspace", "Team Workspace"),
                "workspaces.type", hasItem("team"),
                        "workspaces.type", hasItems( "team","personal"),
                        "workspaces[0].name", equalTo("Rest_Assured"),
                        "workspaces.size()", equalTo(3)


                     );
    }

    @Test
    public void extract_response(){

  Response res = RestAssured.given()
                                .baseUri("https://api.postman.com")
                                .header("X-Api-Key", "PMAK-60942815a6d1310048da6638-fcf89349e5d3af5ac4d1599a6c16367a2a")
                           .when()
                                .get("/workspaces")
                           .then()
                                .assertThat()
                                .statusCode(200)
                                .extract()
                                .response();
                                              // or  .response().asString()
                                              //  or   .response().path("workspaces[0].names");  here we are  directly
                                             //                               filtering the response as per requirement

        System.out.println("whole Response" + res.asString());  // to print the whole response
        System.out.println("whole Response" + res.path("workspaces[0].name"));  // to print the whole response

        JsonPath  jsonPath = new JsonPath(res.asString());  // to need to put the response as String type
        System.out.println("workspace as json type"+ jsonPath.getString("workspaces[0].name"));

        // their is a shortcut to print the JsonPath

        System.out.println( JsonPath.from(res.asString()).getString("workspaces[0].name"));
    }


    @Test
    public void  hamcrest_assert_on_extracted_response(){

    String res = given()
                        .baseUri("https://api.postman.com")
                        .header("X-Api-Key", "PMAK-60942815a6d1310048da6638-fcf89349e5d3af5ac4d1599a6c16367a2a")
                 .when()
                        .get("/workspaces")
                 .then()
                        .assertThat()
                        .statusCode(200)
                        .extract().path("workspaces[0].name");

        assertThat(res, equalTo("Rest_Assured"));    // using special Static method from Hamcrest

        Assert.assertEquals(res, "Rest_Assured");   // this is Testng method for assertion
    }


    @Test
    public void  hamcrest_assert_on_extracted_methods(){

        given()
                .baseUri("https://api.postman.com")
                .header("X-Api-Key", "PMAK-60942815a6d1310048da6638-fcf89349e5d3af5ac4d1599a6c16367a2a")
        .when()
                .get("/workspaces")
        .then()
                .assertThat()
                .statusCode(200)
                .body("workspaces.name", contains("Rest_Assured", "My Workspace", "Team Workspace"),
                 "workspaces.name", containsInAnyOrder("My Workspace", "Team Workspace","Rest_Assured"),
                  //     "workspaces.name", empty(),
                         "workspaces.name", is(not(empty())),
                         "workspaces.name", is(not(emptyArray())),
                         "workspaces.name",  hasSize(3),
                    //   "workspaces.name",  everyItem(startsWith("My"))
                         "workspaces[0]",  hasKey("id"),
                         "workspaces[0]",  hasValue("Team Workspace"),
                         "workspaces[0]",  hasEntry("id", "0a14d1b0-3ac5-4ad4-a397-083a83ddbf1c"),
                        "workspaces[0]", equalTo(Collections.EMPTY_MAP)
                //        "workspaces[0].name", allOf(startsWith("team"), containsString("Workspace"))
                );
    }


    @Test
    public void  request_response_logging(){

        given()
               .baseUri("https://api.postman.com")
               .header("X-Api-Key", "PMAK-60942815a6d1310048da6638-fcf89349e5d3af5ac4d1599a6c16367a2a")
               .log().all()         // here it log the whole request
               .log().parameters()
               .log().cookies()
               .log().ifValidationFails()
               .config(config.logConfig(LogConfig.logConfig().enableLoggingOfRequestAndResponseIfValidationFails()))
        .when()
               .get("/workspaces")
        .then()
                .log().all()           // here it log the whole response, as we are using before Assertion
                .log().ifError()
                .log().ifValidationFails()
                .assertThat()
                .statusCode(200);
    }

    @Test
    public void  request_blacklist_header(){      // video 67

        Set<String> manyHeaders = new HashSet<String>();
        manyHeaders.add("X-Api-Key");
        manyHeaders.add("Accept");

        given()
                .baseUri("https://api.postman.com")
                .header("X-Api-Key", "PMAK-60942815a6d1310048da6638-fcf89349e5d3af5ac4d1599a6c16367a2a")
                .config(config.logConfig(LogConfig.logConfig().blacklistHeader("X-Api-Key")))
                .config(config.logConfig(LogConfig.logConfig().blacklistHeaders(manyHeaders)))
                .log().all()
        .when()
                .get("/workspaces")
        .then()
                .assertThat()
                .statusCode(200);
    }


}
