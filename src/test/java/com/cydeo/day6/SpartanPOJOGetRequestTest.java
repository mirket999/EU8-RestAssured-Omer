package com.cydeo.day6;

import com.cydeo.POJO.Spartan;
import com.cydeo.utilities.SpartanTestBase;
import com.google.gson.Gson;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.Reader;

import static io.restassured.RestAssured.given;

public class SpartanPOJOGetRequestTest extends SpartanTestBase {

    @DisplayName("Deserialization way 1")
    @Test
    void test1() {
        Response response = given().accept(ContentType.JSON)
                .and().pathParam("id", 15)
                .when()
                .get("/api/spartans/{id}")
                .then()
                .statusCode(200).extract().response();

//        String jSOnBody = response.body().toString();

        Spartan spartan1 = response.as(Spartan.class);

        JsonPath jsonPath = response.jsonPath();
        Spartan spartan_jsonPath = jsonPath.getObject("", Spartan.class);
        System.out.println("spartan1 = " + spartan1);
        System.out.println("spartan_jsonPath = " + spartan_jsonPath);

    }

    @Test
    void test2() {
        JsonPath jsonPath = given().accept(ContentType.JSON)
                .and().queryParams("nameContains", "a",
                        "gender", "Male")
                .when().get("/api/spartans/search")
                .then().statusCode(200)
                .extract().jsonPath();

        Spartan object = jsonPath.getObject("content[0]", Spartan.class);
        System.out.println("object = " + object);
    }
}
