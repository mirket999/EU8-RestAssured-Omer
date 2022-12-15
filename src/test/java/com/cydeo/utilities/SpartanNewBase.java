package com.cydeo.utilities;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;

import static io.restassured.RestAssured.*;

public class SpartanNewBase {

   public static RequestSpecification requestSpec ;

    public static ResponseSpecification responseSpec;

    @BeforeAll
    public static void init(){
        //save baseurI inside this variable so that we dont need to type each http method.
        baseURI = "http://54.152.222.159";
        port= 7000;
        basePath = "/api";

        requestSpec = given().accept(ContentType.JSON)
                .auth().basic("admin", "admin");

        responseSpec = expect().then().statusCode(200)
                .contentType(ContentType.JSON);

    }

    @AfterAll
    public static void destroy(){
        reset();
    }

}
