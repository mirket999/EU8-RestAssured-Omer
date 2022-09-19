package com.cydeo.day6;

import com.cydeo.POJO.Employee;
import com.cydeo.POJO.Region;
import com.cydeo.utilities.HRTestBase;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.get;
import static io.restassured.RestAssured.given;

public class HrPOJOGetRequestTest extends HRTestBase {
    @Test
    void test1() {
        Region object = given().accept(ContentType.JSON)
                .when().get("/regions")
                .then().statusCode(200)
                .extract().jsonPath().getObject("items[0]", Region.class);

        System.out.println("object.getRegion_id() = " + object.getRegionId());
        System.out.println("object.getRegion_name() = " + object.getRegion_name());
        System.out.println("object.getLinks().get(0).getHref() = " + object.getLinks().get(0).getHref());
    }

    @DisplayName("@JsonPropertiesIgnore")
    @Test
    void test2() {
        Employee employee1 = get("/employees").then().statusCode(200).extract().jsonPath().getObject("items[0]", Employee.class);
        System.out.println("employee1 = " + employee1);
    }
}
