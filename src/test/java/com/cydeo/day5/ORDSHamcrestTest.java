package com.cydeo.day5;

import com.cydeo.day2.HrGetRequests;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import static org.hamcrest.MatcherAssert.*;
import static org.hamcrest.Matchers.*;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

public class ORDSHamcrestTest extends HrGetRequests {
    @Test
    void test1() {
List<String> names = Arrays.asList("Alexander", "Bruce", "David", "Diana");
        RestAssured.given().accept(ContentType.JSON)
                .and().queryParam("q", "{\"job_id\":\"IT_PROG\"}")
                .when().get("/employees")
                .then()
                .statusCode(200)
                .contentType("application/json")
                .body("items.job_id", everyItem(equalTo("IT_PROG")),
                        "items.first_name", equalTo(names),
                        "items.email", containsInAnyOrder());


    }
}
