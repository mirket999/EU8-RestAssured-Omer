package com.cydeo.day11;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

public class ParameterizedTests {


    @ParameterizedTest
    @ValueSource(ints = {22030, 22031, 22032, 22033, 22034, 22035, 22036})
    public void test(int zipcode) {
//        Response response = RestAssured.given()
//                .when()
//                .get("https://api.zippopotam.us/us/" + zipcode)
//                .then()
//                .statusCode(200)
//                .extract().response();

        Response response =
        RestAssured.given()
                        .baseUri("https://api.zippopotam.us")
                        .pathParam("zipcode", zipcode)
                 .when()
                        .get("/us/{zipcode}")
                        .then()
                        .statusCode(200)
                        .extract().response();

        System.out.println("Country name for zipcode: " + zipcode + ": " + response.jsonPath().getString("country"));

    }
}
