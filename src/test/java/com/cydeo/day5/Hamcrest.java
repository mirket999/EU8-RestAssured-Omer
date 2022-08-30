package com.cydeo.day5;

import com.cydeo.day4.Sparta;
import com.cydeo.utilities.SpartanTestBase;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.hamcrest.MatcherAssert;
import static org.hamcrest.MatcherAssert.*;
import org.hamcrest.Matchers;
import static org.hamcrest.Matchers.*;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

public class Hamcrest extends SpartanTestBase {
@Test
    public void test1(){
    assertThat(5+5, is(10));
    assertThat(5+5, greaterThan(9));

}

@Test
    public void test2(){
    String text = "EU8";
    assertThat(text, startsWith("E"));

}

@Test
    public void test3ForColectionWithHamcrest(){
    List<Integer> numbers = Arrays.asList(1,2,3,4,5);
    assertThat(numbers, hasItems(greaterThanOrEqualTo(5)));
}

@Test
    public void test4(){
RestAssured.given().accept(ContentType.JSON)
        .pathParam("id", 20)
        .when().get("/api/spartans/{id}")
        .then()
        .statusCode(200)
        .contentType("application/json")
        .body("id", is(20),
                "name", is("Lothario"),
                "gender", is("Male"),
                "phone", is(7551551687L));
}

    @Test
    public void test5() {


    RestAssured.given().accept(ContentType.JSON)
            .and().pathParam("id", 25)
            .when().get("/api/spartans/{id}")
            .then().statusCode(200)
            .and().contentType("application/json")
            .and().body("id", is(25),
                    "name", is("Valentin"),
                    "gender", is("Male"),
                    "phone", is(1536037088))
            .and().header("Connection", is("keep-alive"));
    }
}
