package com.cydeo.day8;

import com.cydeo.utilities.SpartanAuthTestBase;
import static io.restassured.RestAssured.*;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.internal.http.HttpResponseException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class SpartanWithAuthTests extends SpartanAuthTestBase {
    @DisplayName("GET spartan as guest and get 401")
    @Test
    public void test1(){

        try{
            RestAssured.get("/api/spartans")
                    .then().statusCode(401)
                    .log().all();
        } catch (Exception e){
            System.out.println("Status code is 401 as expected");
        }
    }

    @Test
    public void test2(){
                given().accept(ContentType.JSON)
                    .auth().basic("admin", "admin")
                .when().get("api/spartans")
                        .then().statusCode(200)
                        .log().all();

    }

    @Test
    public void test3(){

    }

}
