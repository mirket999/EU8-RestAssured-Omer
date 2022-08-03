package com.cydeo.day1;

import io.restassured.RestAssured;
import static io.restassured.RestAssured.*;
import io.restassured.response.Response;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class SimpleGetRequest {

    @BeforeAll
    public static void init(){
        baseURI = "http://54.152.222.159:8000/";
    }

    @Test
    public void test1(){
//send a get request and save response inside the Response object
        Response response = RestAssured.when().get("api/spartans");
        //print response status code
        System.out.println("response.statusCode() = " + response.statusCode());
        //print response body
        //System.out.println("response.prettyPrint() = " + response.prettyPrint());
        System.out.println("response.body() = " + response.body().asString());

    }

    @Test
    public void test2(){
        //    Given Accept type application/json
        Response response = given().accept("application/json").when().get("api/spartans");

//    When user send GET request to api/spartans end point
//    Then status code must 200
        Assertions.assertEquals(200, response.statusCode());
//    And response Content Type must be application/json
        Assertions.assertEquals("application/json", response.contentType());

//    And response body should include spartan result


    }

    @Test
    public void test3(){
//        Given accept header is application/json
//        When users sends a get request to /api/spartans/3
        Response response = given().accept("application/json").when().get("api/spartans/3");
//        Then status code should be 200
        Assertions.assertEquals(200, response.statusCode());
//        And content type should be application/json
        Assertions.assertEquals("application/json", response.contentType());
//        and json body should contain Fidole
        System.out.println(response.body().asString().contains("Fidole"));
    }

    @DisplayName("Verify that API")
    @Test
    public void test4(){
//        Given no headers provided
//        When Users sends GET request to /api/hello
        Response response = when().get("api/hello");
//        Then response status code should be 200
        Assertions.assertEquals(200, response.statusCode());
//        And Content type header should be “text/plain;charset=UTF-8”
        Assertions.assertTrue(response.contentType().equals("text/plain;charset=UTF-8"));
//        And header should contain date
        Assertions.assertTrue(response.headers().exist());

//        And Content-Length should be 17
        Assertions.assertTrue(response.header("Content-Length").equals("17"));
//        And body should be “Hello from Sparta"
        Assertions.assertTrue(response.body().asString().equals("Hello from Sparta"));
    }

}
