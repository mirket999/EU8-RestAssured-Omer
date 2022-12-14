package com.cydeo.day10;

import com.cydeo.utilities.SpartanAuthTestBase;
import io.restassured.http.ContentType;
import io.restassured.module.jsv.JsonSchemaValidator;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.File;

import static io.restassured.RestAssured.*;

public class JsonSchemaValidationTest extends SpartanAuthTestBase {

    @DisplayName("GET request to verify one spartan against to schema")
    @Test
    public void schemaValidation(){
    given().
            accept(ContentType.JSON)
            .pathParam("id", 10)
            .auth().basic("admin", "admin")
    .when()
            .get("/api/spartans/{id}")
    .then()
            .statusCode(200)
            .body(JsonSchemaValidator.matchesJsonSchemaInClasspath("SingleSpartanSchema.json"))
            .log().all();

    }

    @DisplayName("GET request to all spartans and verify schema")
    @Test
    public void allSpartansSchemaTest(){
        given().
                accept(ContentType.JSON)
                .auth().basic("admin", "admin")
                .when()
                .get("/api/spartans")
                .then().assertThat()
                .statusCode(200)
                .body(JsonSchemaValidator.matchesJsonSchema(new File("src/test/resources/allSpartansSchema.json")))
                .log().everything();
    }

    @DisplayName("POST request to spartans and verify schema")
    @Test
    public void postSpartansSchemaTest(){

        given().
                accept(ContentType.JSON)
                .contentType(ContentType.JSON)
                .auth().basic("admin", "admin")
                .body("{\n" +
                        "        \n" +
                        "        \"name\": \"Abuziddin\",\n" +
                        "        \"gender\": \"Male\",\n" +
                        "        \"phone\": 1042902741\n" +
                        "    }")
        .when()
                .post("/api/spartans")
        .then().assertThat()
                .statusCode(201)
                .body(JsonSchemaValidator.matchesJsonSchemaInClasspath("spartanPostJsonSchema.json"))
                .log().everything();
    }

}
