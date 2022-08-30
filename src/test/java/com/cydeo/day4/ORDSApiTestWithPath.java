package com.cydeo.day4;

import com.cydeo.utilities.HRTestBase;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;

import static io.restassured.RestAssured.*;

public class ORDSApiTestWithPath {

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
            System.out.println(email.toLowerCase() + "@gmail.com");
        }
        //get me first name of employees who is making more than 10000
        List<String> salaries = jsonPath.getList("items.findAll {it.salary>10000}.first_name");
        System.out.println(salaries);


    }

    @Test
    void name() {
        //send a get request to student id 23401 as a path parameter and accept header application/json
        baseURI = "http://api.cybertektraining.com/";
        Response response = given().accept(ContentType.JSON)
                .and().pathParam("id", 32881)
                .when().get("/student/{id}");

        System.out.println("response.statusCode() = " + response.statusCode());
        System.out.println("response.contentType() = " + response.contentType());


        //verify status code=200 /content type=application/json;charset=UTF-8 /Content-Encoding = gzip
        assertAll(
                () -> assertTrue(response.statusCode() == 200),
                () -> assertTrue(response.contentType().equals("application/json;charset=UTF-8"))

        );

        //verify Date header exists
        assertTrue(response.headers().hasHeaderWithName("Date"));
        //assert that
            /*
                firstName Vera
                batch 14
                section 12
                emailAddress aaa@gmail.com
                companyName Cybertek
                state IL
                zipCode 60606
                using JsonPath
             */

        String firstName = response.path("students[0].firstName");
        int batch = response.path("students[0].batch");
        String section = response.path("students[0].section");
        String emailAddress = response.path("students[0].contact.emailAddress");
        String companyName = response.path("students[0].company.companyName");
        String state = response.path("students[0].company.address.state");
        int zipCode = response.path("students[0].company.address.zipCode");

        assertAll(
                () -> assertTrue(firstName.equals("Vera")),
                () -> assertTrue(batch == 14),
                () -> assertTrue(section.equals("12")),
                () -> assertTrue(emailAddress.equals("aaa@gmail.com")),
                () -> assertTrue(companyName.equals("Cybertek")),
                () -> assertTrue(state.equals("IL")),
                () -> assertTrue(zipCode == 60606)

        );

    }
}
