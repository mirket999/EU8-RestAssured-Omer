package com.cydeo.day3;

import io.restassured.http.ContentType;
import io.restassured.internal.http.HttpResponseException;
import io.restassured.response.Response;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.*;

public class SpartanTestsWithParameters {
    @BeforeAll
    public static void init() {
        baseURI = "http://54.152.222.159:8000/";
    }

    @Test
    public void test1() {
        Response response = given().accept(ContentType.JSON).and().
                when().get("api/spartans/5");
//        Given accept type is Json
//        And Id parameter value is 5

//        When user sends GET request to /api/spartans/{id}
//        Then response status code should be 200
//        And response content-type: application/json
//        And "Blythe" should be in response payload
    }

    @Test
    public void test2(){
//        TASK
//        Given accept type is Json
        Response response = given().accept(ContentType.JSON)
                .and().pathParam("id", 500)
                .when().get("/api/spartans/{id}");
//        And Id parameter value is 500
//        When user sends GET request to /api/spartans/{id}
//        Then response status code should be 404


            Assertions.assertTrue(response.statusCode()==404);
//        And response content-type: application/json
            Assertions.assertTrue(response.contentType().equals("application/json"));
//        And "Not Found" message should be in response payload
            Assertions.assertTrue(response.prettyPrint().contains("Not Found"));


    }

    @Test
    public void test3(){
//        Given accept type is Json
//        And query parameter values are:
//        gender|Female
//        nameContains|e
//        When user sends GET request to /api/spartans/search
//        Then response status code should be 200
//        And response content-type: application/json
//        And "Female" should be in response payload
//        And "Janette" should be in response payload
    }
}
