package com.cydeo.day6;

import com.cydeo.POJO.LinkPojo_Task;
import com.cydeo.POJO.Region;
import com.cydeo.POJO.RegionPOJO_Task;
import com.cydeo.POJO.Regions;
import com.cydeo.utilities.HRTestBase;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.hamcrest.MatcherAssert;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class RegionsPOJO_TaskPractice extends HRTestBase {

    @Test
    public void test1(){
        JsonPath jsonPath = RestAssured.given().accept(ContentType.JSON)
                .when().get("/regions")
                .then().statusCode(200).extract().jsonPath();

        RegionPOJO_Task region1 = jsonPath.getObject("items[0]", RegionPOJO_Task.class);
        System.out.println("region1 = " + region1);
        List<RegionPOJO_Task> regionsList = jsonPath.getList("items", RegionPOJO_Task.class);
        System.out.println("regionsList.get(1).getRegionName() = " + regionsList.get(1).getRegionName());

        List<Integer> regionIDs = jsonPath.getList("items.region_id");

    }

    @DisplayName("POJO Practice")
    @Test
    public void test2(){
            /* send a get request to regions
        verify that region ids are 1,2,3,4
        verify that regions names Europe ,Americas , Asia, Middle East and Africa
        verify that count is 4
        try to use pojo as much as possible
        ignore non used fields

     */
        Response response = RestAssured.given().accept(ContentType.JSON)
                .when().get("/regions")
                .then().statusCode(200).contentType("application/json")
                .extract().response();
        Regions regions = response.as(Regions.class);

        List<Integer> actual_regionIDs = new ArrayList<>();
        List<String> actual_regionNames = new ArrayList<>();

        for (Region region : regions.getItems()) {
            actual_regionIDs.add(region.getRegionId());
            actual_regionNames.add(region.getRegion_name());
        }

        List<Integer> expectedRegionIds = Arrays.asList(1,2,3,4);
        List<String> expectedRegionNames = Arrays.asList("Europe", "Americas", "Asia", "Middle East and Africa");

        System.out.println("actual_regionIDs = " + actual_regionIDs);
        System.out.println("expectedRegionIds = " + expectedRegionIds);
        System.out.println("actual_regionNames = " + actual_regionNames);
        System.out.println("expectedRegionNames = " + expectedRegionNames);



        MatcherAssert.assertThat(actual_regionIDs, Matchers.equalTo(expectedRegionIds));
        Assertions.assertArrayEquals(actual_regionIDs.toArray(),expectedRegionIds.toArray());
        Assertions.assertTrue(actual_regionNames.equals(expectedRegionNames));


        List<Object> listOfRegionIDs = response.jsonPath().getList("items.region_id");
        System.out.println("listOfRegionIDs = " + listOfRegionIDs);
        Object path = response.path("items.region_id");

    }
}
