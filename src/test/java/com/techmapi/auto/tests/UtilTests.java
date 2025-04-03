package com.techmapi.auto.tests;

import java.util.List;
import java.util.Map;

import org.hamcrest.Matchers;
import org.hamcrest.collection.IsCollectionWithSize;
import org.hamcrest.core.IsNot;
import org.testng.annotations.Test;

import com.techmapi.auto.utils.ExcelHandler;
import static org.hamcrest.MatcherAssert.*;
import static org.hamcrest.Matchers.*;

public class UtilTests {

	@Test
	public void readExcel() {
		ExcelHandler excelHandler = new ExcelHandler();
		List<Map<String, String>> excelData =  excelHandler.getExcelData("./src/test/resources/users.xlsx");
		for (Map<String, String> row : excelData) {
		  for (Map.Entry<String, String> entry : row.entrySet()) {
		      System.out.println(entry.getKey() + ": " + entry.getValue());
		  }
		  System.out.println("---------------");		  
		}
		assertThat(excelData.size(), Matchers.greaterThan(0));
	}
}
