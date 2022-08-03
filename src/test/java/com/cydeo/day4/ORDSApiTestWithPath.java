package com.cydeo.day4;

import com.cydeo.utilities.HRTestBase;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;

import static io.restassured.RestAssured.*;

public class ORDSApiTestWithPath extends HRTestBase {

    @Test
    public void test1() {
        Response response = given().accept(ContentType.JSON)
      //          .and().queryParam("q", "{\"region_id\":2}")
                .when().get("/employees");

        System.out.println("response.contentType() = " + response.contentType());

//        List<Integer> allEmpID = response.path("items.employee_id");
//        for (Integer eachID : allEmpID) {
//            System.out.println("eachID = " + eachID);
//        }
        String pathOfFirst = response.path("items[0].first_name");
        System.out.println("pathOfFirst = " + pathOfFirst);

        Object pathOfHref = response.path("items[0].links[0].href");
        System.out.println("pathOfHref = " + pathOfHref);
        JsonPath jsonPath = response.jsonPath();
        List<String> namesOfITPROG = jsonPath.getList("items.findAll {it.job_id==\"IT_PROG\"}.first_name");
        for (String each : namesOfITPROG) {
            System.out.println(each);
        }

    }

    @Test
    void test2() {
        //get me all email of employees who is working as IT_PROG
        Response response = given().contentType(ContentType.JSON)
                .when().get("/employees");
        int statusCode = response.statusCode();
        System.out.println("statusCode = " + statusCode);
        JsonPath jsonPath = response.jsonPath();
       List<String> emails = jsonPath.getList("items.findAll {it.job_id==\"IT_PROG\"}.email");
        for (String email : emails) {
            System.out.println(email.toLowerCase()+"@gmail.com");
        }
        //get me first name of employees who is making more than 10000



    }
}
