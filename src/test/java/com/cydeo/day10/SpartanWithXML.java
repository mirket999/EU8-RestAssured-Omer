package com.cydeo.day10;

import com.cydeo.utilities.SpartanAuthTestBase;

import static io.restassured.RestAssured.*;

import io.restassured.http.ContentType;
import io.restassured.path.xml.XmlPath;
import io.restassured.response.Response;

import static org.hamcrest.Matchers.*;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

public class SpartanWithXML extends SpartanAuthTestBase {

    @DisplayName("Get request to /api/spartans and verify xml")
    @Test
    public void getSpartanXml() {
        given().
                accept(ContentType.XML)
                .auth().basic("admin", "admin")
        .when()
                .get("/api/spartans")
        .then()
                .statusCode(200)
                .contentType("application/xml")
                .body("List.item[0].name", is("Meade"))
                .log().everything();


    }

    @Test
    public void testXml(){
        Response response = given()
                .accept(ContentType.XML) // we want xml response
                .and()
                .auth().basic("admin", "admin")
                .when()
                .get("/api/spartans");

        XmlPath xmlPath = response.xmlPath();
        String itemNameOf55 = xmlPath.getString("List.item[0].name");
        System.out.println("itemNameOf55 = " + itemNameOf55);

        long phoneNumber_3rdItem = xmlPath.getLong("List.item[2].phone");
       // int phoneNumber_3rdItem = (int)xmlPath.getLong("List.item[2].phone");
        System.out.println("phoneNumber_3rdItem = " + phoneNumber_3rdItem);

        //Lİst of all telephone numbers
        List<Object> listOfPhoneNumbers = xmlPath.getList("List.item.phone");
        System.out.println("listOfPhoneNumbers = " + listOfPhoneNumbers);

        //List of all names in payload
        List<String> list = xmlPath.getList("List.item.name");
        System.out.println("list = " + list);
        System.out.println("list.size() = " + list.size());

    }

}
