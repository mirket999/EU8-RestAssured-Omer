package com.cydeo.day7;

import com.cydeo.POJO.Spartan;
import com.cydeo.utilities.SpartanTestBase;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.hamcrest.MatcherAssert;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.LinkedHashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;

public class SpartanPostRequestDemo extends SpartanTestBase {
    @DisplayName("POST request")
    @Test
    public void test1() {
         /*
    Given accept type and Content type is JSON
    And request json body is:
    {
      "gender":"Male",
      "name":"Severus",
      "phone":8877445596
   }
    When user sends POST request to '/api/spartans'
    Then status code 201
    And content type should be application/json
    And json payload/response/body should contain:
    "A Spartan is Born!" message
    and same data what is posted
 */
        given().accept(ContentType.JSON)
                .and().contentType(ContentType.JSON)
                .body("{\n" +
                        "      \"gender\":\"Male\",\n" +
                        "      \"name\":\"Severus\",\n" +
                        "      \"phone\":8877445596\n" +
                        "   }")
                .when().post("/api/spartans")
                .then().assertThat().statusCode(201)
                .contentType("application/json")
                .body("success", is("A Spartan is Born!"),
                        "data.name", is("Severus"),
                        "data.gender", is("Male")).log().all();
    }

    @DisplayName("POST Request with Map")
    @Test
    public void test2() {
            /*
    Given accept type and Content type is JSON
    And request json body is:
    {
      "gender":"Male",
      "name":"Severus",
      "phone":8877445596
   }
    When user sends POST request to '/api/spartans'
    Then status code 201
    And content type should be application/json
    And json payload/response/body should contain:
    "A Spartan is Born!" message
    and same data what is posted
 */
        Map<String, Object> requestJsonMap = new LinkedHashMap<>();
        requestJsonMap.put("gender", "Male");
        requestJsonMap.put("name", "Severus");
        requestJsonMap.put("phone", 8877445596L);

        Response response = given().accept(ContentType.JSON)
                .contentType(ContentType.JSON)
                .body(requestJsonMap)
                .when().post("/api/spartans");

        MatcherAssert.assertThat("success", is("A Spartan is Born!"));
        assertThat("data.gender", is("Male"));
        assertThat("data.name", equalTo("Severus"));


    }

    @DisplayName("POST request with POJO")
    @Test
    public void test3() {
        Spartan spartan = new Spartan();
        spartan.setName("Severus");
        spartan.setGender("Male");
        spartan.setPhone(8877445596L);

        Response response = given().accept(ContentType.JSON)
                .contentType(ContentType.JSON)
                .body(spartan)
                .when().post("/api/spartans");

        assertThat(response.statusCode(), is(201));

        assertThat(response.body().path("success"), is("A Spartan is Born!"));
        assertThat(response.path("data.gender"), is("Male"));
        assertThat(response.jsonPath().getString("data.name"), equalTo("Severus"));
        response.prettyPrint();
    }

    @DisplayName("POST request and verify with Get")
    @Test
    public void test4() {
    Map<String,Object> postRequestMap = new LinkedHashMap<>();
    postRequestMap.put("name","Omer" );
    postRequestMap.put("gender", "Male");
    postRequestMap.put("phone", 1234567890L);

        int idFromPost = given().accept(ContentType.JSON)
                .and().contentType(ContentType.JSON)
                .body(postRequestMap)
                .when().post("/api/spartans/")
                .then().statusCode(201)
                .contentType("application/json")
                .extract().response().jsonPath().getInt("data.id");

        Spartan spartanPosted = given().accept(ContentType.JSON)
                .and().pathParam("id", idFromPost)
                .when().get("/api/spartans/{id}")
                .then().statusCode(200)
                .extract().as(Spartan.class);

        assertThat(postRequestMap.get("name"), is(spartanPosted.getName()));
        assertThat(postRequestMap.get("gender"), is(spartanPosted.getGender()));
        assertThat(postRequestMap.get("phone"), is(spartanPosted.getPhone()));

        System.out.println("spartanPosted = " + spartanPosted);

        given().pathParam("id", idFromPost)
                .when().delete("api/spartans/{id}")
                .then().statusCode(204);
    }

    @DisplayName("POST request and verify with POJO")
    @Test
    public void test5(){
         /*
    Given accept type and Content type is JSON
    And request json body is:
    {
      "gender":"Female",
      "name":"Merve",
      "phone":4567891234
   }
    When user sends POST request to '/api/spartans'
    Then status code 201
    And content type should be application/json
    And json payload/response/body should contain:
    "A Spartan is Born!" message
    and same data what is posted
 */
        Spartan spartanRequest = new Spartan();
        spartanRequest.setName("Merve");
        spartanRequest.setGender("Female");
        spartanRequest.setPhone(4567891234L);

        int postRequestID = given().accept(ContentType.JSON)
                .and().contentType(ContentType.JSON)
                .body(spartanRequest)
                .when()
                .post("/api/spartans")
                .then()
                .statusCode(201).contentType("application/json")
                .body("success", is("A Spartan is Born!"),
                        "data.name", is("Merve"),
                        "data.gender", is("Female"))
                .extract().response().jsonPath().getInt("data.id");
        System.out.println("postRequestID = " + postRequestID);


        int getRequestID = given().accept(ContentType.JSON)
                .and().pathParam("id", postRequestID)
                .when().get("/api/spartans/{id}")
                .then().statusCode(200)
                .body("id", is(postRequestID))
                .extract().jsonPath().getInt("id");

        assertThat(postRequestID, is(getRequestID));


    }


}
