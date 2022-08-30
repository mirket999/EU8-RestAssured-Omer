package com.cydeo.day4;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Practice {
   @BeforeAll
   public static void init(){
       RestAssured.baseURI = "http://54.152.222.159:8000";
   }


    @Test
    void test1() {
        Response response = RestAssured.get("/api/spartans");

     //   System.out.println("response.prettyPrint() = " + response.prettyPrint());
      //  System.out.println("response.peek().asString() = " + response.peek().asString());
        System.out.println("response.prettyPeek().asString() = " + response.asPrettyString());
    }

    @Test
    void test2() {
        Response response = RestAssured.given().accept(ContentType.JSON)
                .and().pathParam("id", 10)
                .get("/api/spartans/{id}") ;

        Assertions.assertEquals(200, response.statusCode());

        System.out.println("response.body().path(\"id\") = " + response.body().path("id"));
        System.out.println("response.path(\"id\") = " + response.path("id"));

    }

    @Test
    void test3() {
        Response response = RestAssured.get("/api/spartans");
        int firstID = response.path("id[-100]");
        System.out.println("firstID = " + firstID);
       List<Integer> allIDs = response.path("id");
        System.out.println("allIDs = " + allIDs);
    }

    @Test
    void test4() {
        Response response = RestAssured.given().accept(ContentType.JSON)
                .and().pathParam("id", 11)
                .get("/api/spartans/{id}");

        JsonPath jsonPath = response.jsonPath();
    }

    @Test
    void test5() {
     RestAssured.given().accept(ContentType.JSON)
             .and().pathParam("id", 15)
             .when().get("/api/spartans/{id}")
             //respose
             .then().assertThat().statusCode(200)
             .and().assertThat().contentType("application/json")
             .and().assertThat().body("id", Matchers.equalTo(15), "name", Matchers.equalTo("Meta"),
                     "gender", Matchers.equalTo("Female"));
    }

    @Test
    void test6() {
        Response response = RestAssured.given().accept(ContentType.JSON)
                .and().pathParam("id", 11)
                .when().get("/api/spartans/{id}");

        Map spartanMap = response.body().as(Map.class);

//        System.out.println("spartanMap.get(\"name\") = " + spartanMap.get("name"));
//        System.out.println("spartanMap.get(\"id\") = " + spartanMap.get("id"));
//
//        spartanMap.put("gender", "Female");
//
//        System.out.println("spartanMap.get(\"gender\") = " + spartanMap.get("gender"));


    }

    @Test
    void test7() {
        Response response = RestAssured.given().accept(ContentType.JSON)
                     .when().get("/api/spartans");
        List<Map<String, Object>> allSpartanMap = response.body().as(List.class);
        System.out.println("allSpartanMap.get(0) = " + allSpartanMap.get(0));

        int count =0;
        for (Map<String, Object> eachMap : allSpartanMap) {
            System.out.println(count++ + "- " + eachMap);
        }
    }

}
