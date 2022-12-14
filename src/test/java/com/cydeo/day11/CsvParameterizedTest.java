package com.cydeo.day11;

import static io.restassured.RestAssured.*;


import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class CsvParameterizedTest {

@ParameterizedTest
    @CsvSource({"1,2,3",
                "2,3,5",
                "8,7,15",})
    public void test(int num1, int num2, int sum){

    System.out.println("num1 = " + num1);
    System.out.println("num2 = " + num2);
    System.out.println("sum = " + sum);

assertThat(num1+num2, is(sum));

}

@ParameterizedTest
    @CsvSource({"NY, New York",
    "CO, Denver",
    "VA, Fairfax"})
    public void test2(String state, String city){

    int numberOfPlace = given()
            .accept(ContentType.JSON)
            .pathParams("state", state,
                    "city", city)
            .when()
            .get("https://api.zippopotam.us/us/{state}/{city}")
            .then()
            .assertThat().statusCode(200)
            .body("places.'place name'", everyItem(containsStringIgnoringCase(city)))

            //.log().body()
            .extract()
            .response().jsonPath().getList("places").size();

    System.out.println("numberOfPlace = " + numberOfPlace);

}

}
