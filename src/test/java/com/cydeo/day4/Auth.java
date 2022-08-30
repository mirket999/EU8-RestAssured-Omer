package com.cydeo.day4;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

public class Auth {
//    {
//        "accessToken": "eyJhbGciOiJIUzI1NiJ9.eyJqdGkiOiI4NiIsImF1ZCI6InN0dWRlbnQtdGVhbS1sZWFkZXIifQ.lEfjcu6RpBfcZ4qWthzZU8uH8fX4FCJFfxBnPNgh4Mo",
//            "refreshToken": "eyJhbGciOiJIUzI1NiJ9.eyJqdGkiOiI4NiIsImF1ZCI6InN0dWRlbnQtdGVhbS1sZWFkZXIifQ.lEfjcu6RpBfcZ4qWthzZU8uH8fX4FCJFfxBnPNgh4Mo"
//    }

    @BeforeAll
    public static void init(){
        RestAssured.baseURI="https://cybertek-reservation-api-qa3.herokuapp.com";
    }
String accessToken = "eyJhbGciOiJIUzI1NiJ9.eyJqdGkiOiI4NiIsImF1ZCI6InN0dWRlbnQtdGVhbS1sZWFkZXIifQ.lEfjcu6RpBfcZ4qWthzZU8uH8fX4FCJFfxBnPNgh4Mo";

    @Test
    void test1() {
        Response response = RestAssured.given().header("Authorization", accessToken)
                .when().get("/api/campuses");

        System.out.println("response.prettyPrint() = " + response.prettyPrint());
    }
}
