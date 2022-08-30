package com.cydeo.day4;

import com.google.gson.Gson;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

public class Deserialization {
    @BeforeAll
    public static void init(){
        RestAssured.baseURI = "http://54.152.222.159:8000";
    }

    @Test
    void test1(){
        Response response = RestAssured.given().accept(ContentType.JSON)
                .and().pathParam("id", 15)
                .when().get("/api/spartans/{id}");

        Sparta sparta1 = response.body().as(Sparta.class);
        System.out.println("sparta1.toString() = " + sparta1.toString());
    }

    @Test
    void test2(){
        String jSonBody = "{\n" +
                "    \"id\": 15,\n" +
                "    \"name\": \"Meta\",\n" +
                "    \"gender\": \"Female\",\n" +
                "    \"phone\": 1938695106\n" +
                "}";

        Gson gson = new Gson();
        Sparta spartaMeta = gson.fromJson(jSonBody, Sparta.class);
        System.out.println("spartaMeta.toString() = " + spartaMeta.toString());

       Sparta sparta = new Sparta("Omer", "Male", 123456L);

        System.out.println("sparta.toString() = " + sparta.toString());

        String toJson = gson.toJson(sparta);
        System.out.println("toJson = " + toJson);


    }


}
