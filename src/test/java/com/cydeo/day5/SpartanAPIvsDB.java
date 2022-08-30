package com.cydeo.day5;

import com.cydeo.utilities.SpartanTestBase;
import static io.restassured.RestAssured.*;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import static org.hamcrest.Matchers.*;

import static org.hamcrest.MatcherAssert.*;

import org.hamcrest.MatcherAssert;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.sql.*;
import java.util.HashMap;
import java.util.Map;

public class SpartanAPIvsDB extends SpartanTestBase {

    @DisplayName("Compare API with DB")
    @Test
    void test1() throws SQLException {
        Map <String, Object> mapAPI= given().accept(ContentType.JSON)
                .and().pathParam("id", 15)
                .when()
                .get("/api/spartans/{id}")
                .then()
                .statusCode(200)
                .contentType("application/json")
                .body("name", is("Meta"),
                        "gender", equalTo("Female"),
                        "phone", equalTo(1938695106)).extract().response().as(Map.class);

        System.out.println("mapAPI = " + mapAPI);

        String dbUrl = "jdbc:oracle:thin:@54.152.222.159:1521:xe";
        String dbUsername = "SP";
        String dbPassword = "SP";

        Connection connection = DriverManager.getConnection(dbUrl, dbUsername, dbPassword);
        Statement statement = connection.createStatement(1004, 1007);
        ResultSet resultSet = statement.executeQuery("SELECT * FROM SPARTANS where SPARTAN_ID = 15");

        resultSet.next();
        ResultSetMetaData rsmd = resultSet.getMetaData();

        Map<String, Object> mapDB = new HashMap<>();
        for (int i = 1; i <= rsmd.getColumnCount(); i++) {
            mapDB.put(rsmd.getColumnName(i), resultSet.getObject(i));

        }

        System.out.println("mapDB = " + mapDB);

        //Assertions.assertEquals(mapAPI.get("id").toString(), mapDB.get("SPARTAN_ID").toString());
        Assertions.assertEquals(String.valueOf(mapAPI.get("id")), String.valueOf(mapDB.get("SPARTAN_ID")));
        assertThat(String.valueOf(mapAPI.get("id")), is(String.valueOf(mapDB.get("SPARTAN_ID"))));

        Assertions.assertAll(
                ()->  assertThat(String.valueOf(mapAPI.get("id")), is(String.valueOf(mapDB.get("SPARTAN_ID")))),
                ()->  assertThat(String.valueOf(mapAPI.get("name")), is(String.valueOf(mapDB.get("NAME")))),
                ()->  assertThat(String.valueOf(mapAPI.get("gender")), is(String.valueOf(mapDB.get("GENDER")))),
                ()->  assertThat(String.valueOf(mapAPI.get("phone")), is(String.valueOf(mapDB.get("PHONE"))))

        );


    }
}
