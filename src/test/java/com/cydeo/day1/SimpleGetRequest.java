package com.cydeo.day1;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.jupiter.api.Test;

public class SimpleGetRequest {
    String url = "http://54.152.222.159:8000/api/spartans";
@Test
    public void Test1(){
    Response response = RestAssured.get(url);
    System.out.println("response.statusCode() = " + response.statusCode());

}

}
