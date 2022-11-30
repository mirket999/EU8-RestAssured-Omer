package com.cydeo.utilities;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;

import static io.restassured.RestAssured.baseURI;
import static io.restassured.RestAssured.port;

public abstract class SpartanAuthTestBase {
    @BeforeAll
    public static void init(){
        //save baseurI inside this variable so that we dont need to type each http method.
        baseURI = "http://54.152.222.159:7000";


           }

}
