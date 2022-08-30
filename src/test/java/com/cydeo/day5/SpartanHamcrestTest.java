package com.cydeo.day5;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import static org.hamcrest.Matchers.*;

import io.restassured.response.Response;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Map;

import static io.restassured.RestAssured.*;

public class SpartanHamcrestTest {
    @BeforeAll
    public static void init() {
        baseURI = "http://54.152.222.159:8000/";
    }

    @Test
    void test1() {
       List < Map <String, Object> > map= get("/api/spartans/").then().statusCode(200).extract()
                .body().as(List.class);

        System.out.println("map.get(1).get(\"name\") = " + map.get(0).get("id"));

    }
}
