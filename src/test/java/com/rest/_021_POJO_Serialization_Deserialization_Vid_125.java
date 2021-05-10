package com.rest;

import com.rest.pojo.workspace.Workspace;
import com.rest.pojo.workspace.WorkspaceRoot;
import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.filter.log.LogDetail;
import io.restassured.http.ContentType;
import io.restassured.specification.ResponseSpecification;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

public class _021_POJO_Serialization_Deserialization_Vid_125 {

    ResponseSpecification rs;

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

       rs = rsb_2.build();
    }


    @Test
    public void validate_POJO_by_Serialization_Deserialization() {

        Workspace workspace = new Workspace("myWorkspace4", "personal", "description");
        WorkspaceRoot workspaceRoot = new WorkspaceRoot(workspace);

WorkspaceRoot deSerializedObject =  RestAssured.given()
                                                   .body(workspaceRoot)
                                               .when()
                                                   .post("/workspaces")
                                               .then().spec(rs)
                                                   .extract()
                                                   .response()
                                                   .as(WorkspaceRoot.class);

assertThat(deSerializedObject.getWorkspace().getName(), equalTo(workspaceRoot.getWorkspace().getName()));


    }

}
