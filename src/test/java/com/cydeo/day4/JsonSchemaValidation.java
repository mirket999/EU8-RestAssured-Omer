package com.cydeo.day4;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.module.jsv.JsonSchemaValidator;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

public class JsonSchemaValidation {
    @BeforeAll
    public static void init(){
        RestAssured.baseURI = "http://54.152.222.159:8000";
    }

    @Test
    void test1() {
RestAssured.given().accept(ContentType.JSON)
        .and().pathParam("id", 15)
        .when().get("/api/spartans/{id}")
        .then().assertThat().statusCode(200)
        .and().assertThat().body(JsonSchemaValidator.matchesJsonSchemaInClasspath("SingleSpartanSchema.json"));
    }
}
