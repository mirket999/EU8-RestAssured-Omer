package com.cydeo.day12;

import org.junit.jupiter.api.*;

public class ExampleTest {

    @BeforeAll
    public static void beforeClass() {
        System.out.println("beforeClass");
    }

    @AfterAll
    public static void afterClass() {
        System.out.println("afterClass");
    }

    @BeforeEach
    public void before() {
        System.out.println(this + "\tbefore");
        int testBeforeEachHash = System.identityHashCode(this);
        System.out.println("testBeforeEachHash = " + testBeforeEachHash);
    }

    @AfterEach
    public void after() {
        System.out.println(this + "\tafter");
        int testAfterEachHash = System.identityHashCode(this);
        System.out.println("testAfterEachHash = " + testAfterEachHash);
    }

    @Test
    public void test1() {
        System.out.println(this + "\ttest1");
        int test1Hash = System.identityHashCode(this);
        System.out.println("test1Hash = " + test1Hash);
    }

    @Test
    public void test2() {
        System.out.println(this + "\ttest2");
        int test2Hash = System.identityHashCode(this);
        System.out.println("test2Hash = " + test2Hash);
    }

    @Test
    public void test3() {
        System.out.println(this + "\ttest3");
        int test3Hash = System.identityHashCode(this);
        System.out.println("test3Hash = " + test3Hash);
    }
}
