package com.rest;

import io.restassured.RestAssured;
import org.testng.annotations.Test;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

public class _014_File_Upload_Download_formdata_Vid_014 {

    @Test
    public void upload_file_multipart_form_data() {

        String attribute22 = "{\"name\":\"temp.txt\",\"parent\":{\"id\":\"123456\"}}";


        RestAssured.given()
                        .baseUri("https://postman-echo.com")
                        .multiPart("file", new File("src/resources/multipart_file.txt"))
                        .multiPart("attributes", attribute22, "application/json")  //<--we can send also any
                        .log().all()                                              // extra key-Value with the multipart
                   .when()
                        .post("/post")
                   .then()
                        .log().all()
                        .assertThat()
                        .statusCode(200);
    }

    @Test
    public void download_file_multipart_form_data() throws IOException {

    byte[] byteArray= RestAssured.given()
                                     .baseUri("https://raw.githubusercontent.com")
                                     .log().all()
                                 .when()
                                     .get("/appium/appium/master/sample-code/apps/ApiDemos-debug.apk")
                                 .then()
                                     .log().all()
                                     .extract()
                                     .response().asByteArray();

        OutputStream os = new FileOutputStream(new File("ApiDemos-debug.apk"));
        os.write(byteArray);
        os.close();

    }

}
