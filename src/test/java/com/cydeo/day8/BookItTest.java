package com.cydeo.day8;

import com.cydeo.utilities.BookitTestBase;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.Test;

public class BookItTest extends BookitTestBase {
 @Test
 public void test1(){
     String bookitToken = "Bearer eyJhbGciOiJIUzI1NiJ9.eyJqdGkiOiIxMjAyIiwiYXVkIjoic3R1ZGVudC10ZWFtLW1lbWJlciJ9.UQnmL58LBoFW-Opm5OPIv7AgFvupRq4cANOIBQdOlpI";

     RestAssured.given().accept(ContentType.JSON)
             .header("Authorization", bookitToken)
             .when().get("/api/campuses")
             .then().statusCode(200).log().all();
 }
}
