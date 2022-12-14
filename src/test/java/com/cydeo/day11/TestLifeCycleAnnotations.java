package com.cydeo.day11;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

public class TestLifeCycleAnnotations {
    @BeforeAll
    public static void testBeforeAll(){
        System.out.println("Before All is running");
    }
    @Test
    public void test1(){
        System.out.println("Test1 is running");
    }

}
