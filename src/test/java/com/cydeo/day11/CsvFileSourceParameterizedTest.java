package com.cydeo.day11;

import io.restassured.RestAssured;
import static io.restassured.RestAssured.*;

import io.restassured.http.ContentType;
import static org.hamcrest.MatcherAssert.*;
import static org.hamcrest.Matchers.*;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

public class CsvFileSourceParameterizedTest {


    @ParameterizedTest
    @CsvFileSource(resources = "/zipcode.csv", numLinesToSkip = 1)
    public void test3(String state, String city, int zipCount) {

        System.out.println("state = " + state);
        System.out.println("city = " + city);
        System.out.println("zipCode = " + zipCount);

        given()
                .accept(ContentType.JSON)
                .pathParams("state", state,
                        "city", city)
                .when()
                .get("https://api.zippopotam.us/us/{state}/{city}")
                .then()
                .statusCode(200)
                .body("places", hasSize(zipCount));

        //Second way of verification:
        /* int numberOfZipCountAPI = given()
                .accept(ContentType.JSON)
                .pathParams("state", state,
                        "city", city)
                .when()
                .get("https://api.zippopotam.us/us/{state}/{city}")
                .then()
                .statusCode(200)
                .extract().response().jsonPath().getList("places").size();

        assertThat(numberOfZipCountAPI, is(equalTo(zipCount)));

*/

    }

}
