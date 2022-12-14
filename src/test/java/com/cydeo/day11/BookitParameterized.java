package com.cydeo.day11;

import com.cydeo.utilities.ExcelUtil;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.apache.groovy.util.Maps;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.List;
import java.util.Map;

public class BookitParameterized {

    public static List<Map<String, String>> getExcelData (){
        ExcelUtil bookitExcel = new ExcelUtil("src/test/resources/BookItQa3.xlsx", "QA3");
        return bookitExcel.getDataList();
    }

    @ParameterizedTest
    @MethodSource("getExcelData")
    public void test1(Map<String, String> userInfo){
        System.out.println(userInfo.get("email"));
        System.out.println(userInfo.get("password"));

        RestAssured.given()
                .baseUri("https://cybertek-reservation-api-qa2.herokuapp.com")
                .accept(ContentType.JSON)
                .queryParams(userInfo)
        .when()
                .get("/sign")
        .then()
                .statusCode(200)
                .log().body();

    }

}
