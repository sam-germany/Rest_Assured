package com.rest;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.filter.log.LogDetail;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.io.File;
import java.util.HashMap;

import static io.restassured.RestAssured.with;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.text.MatchesPattern.matchesPattern;

public class _008_Automate_POST {

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
        String payload22 = "{\n" +
                              "    \"workspace\":{\n" +
                              "        \"name\": \"mySecondWorkspace\",\n" +
                              "        \"type\": \"personal\",\n" +
                              "        \"description\": \"Rest Assured Created this\"\n" +
                              "    }\n" +
                              "}";


        RestAssured.given()                     // main point is in the @BeforeClass   method the   we have
                         .body(payload22)          // defined this static method  "RestAssured.responseSpecification"
                   .when()                         // so here no need to define it separately
                         .post("/workspaces")
                   .then()
                         .log().all()
                         .assertThat()
                         .body( "workspace.name", equalTo("mySecondWorkspace"),
                                  "workspace.id", matchesPattern("^[a-z0-9-]{36}")
                         );
    }


    @Test
    public void validate_post_request_non_bdd_style(){
        String payload22 = "{\n" +
                "    \"workspace\":{\n" +
                "        \"name\": \"myThirdWorkspace\",\n" +
                "        \"type\": \"personal\",\n" +
                "        \"description\": \"Rest Assured Created this\"\n" +
                "    }\n" +
                "}";

      Response response22  = with()
                                .body(payload22)
                            .post("/workspaces");

       assertThat(response22.<String>path("workspace.name"), equalTo("myThirdWorkspace"));
       assertThat(response22.<String>path("workspace.id"), matchesPattern("^[a-z0-9-]{36}$"));
    }

    @Test
    public void validate_post_request_payload_from_file() {  // here we are using Paylode from a File
        File file = new File("src/main/Create_WorkSpace_payload.json");

        RestAssured.given()
                          .body(file)
                   .when()
                          .post("/workspaces")
                   .then()
                          .log().all()
                          .assertThat()
                          .body( "workspace.name", equalTo("mySecondWorkspace"),
                              "workspace.id", matchesPattern("^[a-z0-9-]{36}")
                );
    }


    @Test
    public void validate_post_request_payload_from_Map() {  // here we are using Payload from a Map to convert
                                                                  //from "Map-to-Json Object" we need
        HashMap<String, Object> parentObject   = new HashMap<>(); // Json binding maven dependency

        HashMap<String, String> childObject = new HashMap<>();
        childObject.put("name", "myFistWorkspace");
        childObject.put("type", "personal");
        childObject.put("description", "Rest Assured created this");

        parentObject.put("workspace", childObject);



        RestAssured.given()
                          .body(parentObject)
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
