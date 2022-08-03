package com.cydeo.day2;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.http.Headers;
import io.restassured.response.Response;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;


public class SpartanGetRequests {
    String baseURL = "http://54.152.222.159:8000";

    @Test
    public void test1(){
        Response response = RestAssured.given().accept(ContentType.JSON)
                .when().get(baseURL+"/api/spartans");

        System.out.println("response.statusCode() = " + response.statusCode());
        System.out.println("response.contentType() = " + response.contentType());


        Assertions.assertEquals(200, response.statusCode());
        Assertions.assertEquals("application/json", response.contentType());

    }

    @DisplayName("Get one spartan and verify")
    @Test
    public void test2() {
        Response response = RestAssured.given().accept(ContentType.JSON)
                .when().get(baseURL + "/api/spartans/3");
        System.out.println("response.contentType() = " + response.contentType());
        System.out.println("response.statusCode() = " + response.statusCode());

        Assertions.assertEquals(200, response.statusCode());
        Assertions.assertEquals("application/json", response.contentType());
        Assertions.assertTrue(response.body().asString().contains("Fidole"));

    }


    @Test
    public void test3() {
        Response response = RestAssured.when().get(baseURL + "/api/hello");
        System.out.println("response.contentType() = " + response.contentType());
        System.out.println("response.statusCode() = " + response.statusCode());

        Assertions.assertEquals(200, response.statusCode());
//        Assertions.assertEquals("application/json", response.contentType());
//        Assertions.assertTrue(response.body().asString().contains("Fidole"));

        System.out.println("response.body().asString().length() = " + response.body().asString().length());
        System.out.println("response.header(\"Content-Length\") = " + response.header("Content-Length"));
        System.out.println("response.getHeader(\"Content-Length\") = " + response.getHeader("Content-Length"));
        System.out.println("response.headers().getValue(\"Content-Length\") = " + response.headers().getValue("Content-Length"));


    }



}
