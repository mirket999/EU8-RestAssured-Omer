package com.cydeo.day11;

import com.cydeo.utilities.ExcelUtil;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.List;
import java.util.Map;

public class ExcelUtilPractice {

   @Test
    public void test1(){
       ExcelUtil vytrack = new ExcelUtil("src/test/resources/Vytracktestdata.xlsx", "QA3-short");

       List<Map<String, String>> dataList = vytrack.getDataList();

       for (Map<String, String> eachMap : dataList) {
           System.out.println("First Name = " + eachMap.get("firstname"));
           System.out.println("Last Name = " + eachMap.get("lastname"));
       }
   }

}
