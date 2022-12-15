package com.cydeo.day12;

import com.cydeo.utilities.SpartanNewBase;
import static io.restassured.RestAssured.*;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import static org.hamcrest.Matchers.*;

import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;

public class OldRestAssuredTest extends SpartanNewBase {

    @Test
    public void test1(){
        given().
                accept(ContentType.JSON)
                .auth().basic("admin", "admin")
                .when()
                .get("/spartans")
                .then()
                .statusCode(200)
                .body("id[0]", Matchers.is(10))
                .body("id[1]", Matchers.is(100))
                .log().body();
    }


    @Test
    public void test2(){
        given().
                accept(ContentType.JSON)
                .auth().basic("admin", "admin")
                .expect()
                .statusCode(200)
                .body("id[0]", Matchers.is(10))
                .body("id[1]", Matchers.is(100))
                .when()
                .get("/spartans");


    }

}
