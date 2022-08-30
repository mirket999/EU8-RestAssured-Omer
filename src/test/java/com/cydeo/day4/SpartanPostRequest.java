package com.cydeo.day4;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

public class SpartanPostRequest {
    @BeforeAll
    public static void init(){
        RestAssured.baseURI = "http://54.152.222.159:8000";
    }

    @Test
    void test1() {

        Map<String, Object> map = new HashMap<>();
        map.put("gender", "fMale");
        map.put("name", "Zehra");
        map.put("phone", 1234567890L);

        System.out.println("map.get(\"gender\") = " + map.get("gender"));

        Response response = RestAssured.given().accept(ContentType.JSON)
             .and().contentType(ContentType.JSON)
             .body("{\n" +
                     "  \"gender\": \"Male\",\n" +
                     "  \"name\": \"Zehra\",\n" +
                     "  \"phone\": 1234567890\n" +
                     "}\n")
             .when().post("/api/spartans/");

        JsonPath jsonPath = response.jsonPath();

        Assertions.assertTrue(jsonPath.getString("data.name").equals("Zehra"));
    }

    @Test
    void test2() {
        Sparta sparta2 = new Sparta("Ali", "Male", 1234567896);

        Response response = RestAssured.given().accept(ContentType.JSON)
                .and().contentType(ContentType.JSON)
                .and().body(sparta2)
                .when().post("/api/spartans/");

        System.out.println("response.prettyPrint() = " + response.prettyPrint());

        Assertions.assertTrue(response.statusCode()==201);
        Assertions.assertTrue(response.contentType().equals("application/json"));

    }

    @Test
    void PUTRequest() {
//        Map<String, Object> PutMap = new HashMap<>();
//        PutMap.put("name", "Abuzikke");
//        PutMap.put("gender", "Female");
//        PutMap.put("phone", 9996663331L);
//
//        RestAssured.given().contentType(ContentType.JSON)
//
//                .and().pathParam("id", 109)
//                .and().body(PutMap)
//                .when().put("/api/spartans/{id}")
//                .then().statusCode(204);
//
//        Response response = RestAssured.given().pathParam("id", 109)
//                .get("/api/spartans/{id}");
//
//        System.out.println("response.prettyPrint() ORIGINAL= " + response.prettyPrint());

        Map<String, Object> PutMap_patch = new HashMap<>();
        PutMap_patch.put("name", "ABUZIKKEEEEEEE_PATCH");

        RestAssured.given().contentType(ContentType.JSON)
                .and().pathParam("id", 109)
                .and().body(PutMap_patch)
                .when().patch("/api/spartans/{id}")
                .then().assertThat().statusCode(204);
    }

    @Test
    void DELETE_Request() {
        RestAssured.given().pathParam("id", 109)
                .when().delete("/api/spartans/{id}")
                .then().assertThat().statusCode(204);
    }
}
