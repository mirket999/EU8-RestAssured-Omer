package com.cydeo.day3;

import io.restassured.http.ContentType;
import io.restassured.internal.http.HttpResponseException;
import io.restassured.response.Response;
import org.apache.http.HttpStatus;
import org.junit.jupiter.api.*;

import static io.restassured.RestAssured.*;
import static org.junit.jupiter.api.Assertions.*;

public class ORDSApiTests {
    @BeforeAll
    public static void init() {
        baseURI = "http://54.152.222.159:8000";
    }

    @Test
    public void test1() {
//        Given accept type is Json
//        And parameters: q = {"region_id":2}
//        When users sends a GET request to "/countries"
        Response response = given().accept(ContentType.JSON)
                .and().queryParam("q", "{\"region_id\":2}")
                .when().get("/countries");

//        Then status code is 200
        assertEquals(200, response.statusCode());
//        And Content type is application/json
        assertTrue(response.contentType().equals("application/json"));
//        And Payload should contain "United States of America"
        assertTrue(response.prettyPrint().contains("United States of America"));
    }

    @Disabled
    @Test
    public void rest2() {
//Send a GET request to employees and get only employees who works as a IT_PROG

    }

    @Test
    void name() {
//        Given accept type is Json
//        And Id parameter value is 5
//        When user sends GET request to /api/spartans/{id}
        Response response = given().accept(ContentType.JSON)
                .and().pathParam("id", 5)
                .when().get("/api/spartans/{id}");

//        Then response status code should be 200
        assertEquals(200, response.statusCode());
//        And response content-type: application/json
        assertTrue(response.contentType().equals("application/json"));
//        And "Blythe" should be in response payload
        assertTrue(response.prettyPrint().contains("Blythe"));
    }

    @Test
    void name1() {
        Response response = given().accept(ContentType.JSON).log().all()
                .pathParam("id", 500)
                .when()
                .get("/api/spartans/{id}");

        //verify status code 404
        assertEquals(404, response.statusCode());
        //verify content type
        assertEquals("application/json", response.contentType());
        //verify NotFound in the json payload/body
        assertTrue(response.body().asString().contains("Not Found"));

//        assertAll(
//                ()->assertEquals(404, response.statusCode()),
//                ()-> assertTrue(response.contentType().equals("application/json")),
//                ()->assertTrue(response.prettyPrint().contains("Not Found"))
//        );
    }

    @Test
    void name3() {
//        Given accept type is Json
//        And query parameter values are:
//        gender|Female
//        nameContains|e
//        When user sends GET request to /api/spartans/search
        Response response = given().accept(ContentType.JSON)
                .and().queryParam("gender", "Female")
                .and().queryParam("nameContains", "Jan")
                .when().get("/api/spartans/search");

//        Then response status code should be 200
//        And response content-type: application/json
//        And "Female" should be in response payload
//        And "Janette" should be in response payload

//        assertEquals(205, response.statusCode());
//        assertTrue(response.contentType().equals("application/json"));
//        assertTrue(response.prettyPrint().contains("Female"));
//        assertTrue(response.prettyPrint().contains("Janette"));

        assertAll(
                () -> assertEquals(205, response.statusCode()),
                () -> assertTrue(response.contentType().equals("application/json")),
                () -> assertTrue(response.prettyPrint().contains("Female")),
                () -> assertTrue(response.prettyPrint().contains("Janette"))
        );

    }

    @Test
    void name4() {
//        Given accept type is json
//        And path param id is 10
//        When user sends a get request to "api/spartans/{id}"
        Response response = given().accept(ContentType.JSON)
                .and().pathParam("id", 10)

                .when().get("api/spartans/{id}");
//        Then status code is 200
        assertEquals(200, response.statusCode());
//        And content-type is "application/json"
        assertTrue(response.contentType().equals("application/json"));
//        And response payload values match the following:

//        id is 10,
//                name is "Lorenza",
//                gender is "Female",
//                phone is 3312820936
        Object id = response.path("id");
        Object name = response.path("name");
        Object gender = response.path("gender");
        Object phone = response.path("phone");
        System.out.println("id = " + id);
        System.out.println("name = " + name);
        System.out.println("gender = " + gender);
        System.out.println("phone = " + phone);


        assertTrue(id.equals(10));
        assertTrue(name.equals("Lorenza"));
        assertTrue(gender.equals("Female"));
        assertTrue(phone.equals(3312820936L));

    }
}
