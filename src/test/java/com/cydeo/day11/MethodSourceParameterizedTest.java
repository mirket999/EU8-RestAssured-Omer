package com.cydeo.day11;

import com.cydeo.utilities.ExcelUtil;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.List;
import java.util.Map;

public class MethodSourceParameterizedTest {

    @ParameterizedTest
    @MethodSource("method1")
    public void test1(Map<String,String> userInfo) {

        System.out.println("firstname = " + userInfo.get("firstname"));
        System.out.println("lastname = " + userInfo.get("lastname"));

    }

    public static List<Map<String, String>> method1() {

        ExcelUtil vytrack = new ExcelUtil("src/test/resources/Vytracktestdata.xlsx", "QA3-short");

        List<Map<String, String>> dataList = vytrack.getDataList();

        return dataList;
    }

}
