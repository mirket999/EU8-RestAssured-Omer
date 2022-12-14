package com.cydeo.day10;

import com.cydeo.utilities.SpartanAuthTestBase;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

;import static org.hamcrest.Matchers.*;

public class ResponseTimeTest extends SpartanAuthTestBase {
 @Test
 public void test1(){
     Response response = RestAssured.given()
             .auth().basic("admin", "admin")
             .accept(ContentType.JSON)
             .when()
             .get("/api/spartans")
             .then()
             .statusCode(200)
            // .time(lessThanOrEqualTo(1500L))
             .time(both(greaterThan(500L)).and(lessThanOrEqualTo(1800L)))

             .extract().response();

     System.out.println("response.time() = " + response.time());
     long time = response.time();

     Assertions.assertTrue(time>=0&time<=1800);

 }
}
