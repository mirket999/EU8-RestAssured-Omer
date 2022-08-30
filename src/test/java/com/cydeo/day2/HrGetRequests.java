package com.cydeo.day2;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.*;
import static org.junit.jupiter.api.Assertions.*;

public class HrGetRequests {

    @BeforeAll
    public static void init(){
        baseURI= "http://54.152.222.159:1000/ords/hr";
    }

    @Test
    public void test2(){
//        Given accept type is application/json
//        When user sends get request to /regions/2
//        Then response status code must be 200
//        and content type equals to application/json
//        and response body contains   Americas

    }

}
