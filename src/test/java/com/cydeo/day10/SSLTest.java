package com.cydeo.day10;

import io.restassured.RestAssured;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class SSLTest {
    @DisplayName("SSL Test")
    @Test
    public void SSLTest(){
        RestAssured.given()
                .relaxedHTTPSValidation()
                .get("https://untrusted-root.badssl.com/")
                .prettyPrint();
    }


}
