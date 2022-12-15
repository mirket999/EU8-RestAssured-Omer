package com.cydeo.day12;

import com.cydeo.utilities.SpartanNewBase;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.*;

public class SpartanSpecTest extends SpartanNewBase {

    @Test
    public void test1(){



        given()
                .spec(requestSpec)
        .when()
                .get("/spartans")
        .then()
                .spec(responseSpec);

//        RestAssured.given()
//                .accept(ContentType.JSON)
//                .auth().basic("admin", "admin")
//                .when()
//                .get("/spartans")
//                .then()
//                .statusCode(200)
//                .contentType(ContentType.JSON);

    }

    @Test
    public void test2(){

        given().spec(requestSpec)
                .pathParam("id", 15)
                .when()
                .get("/spartans/{id}")
                .then()
                .spec(responseSpec);

    }
}
