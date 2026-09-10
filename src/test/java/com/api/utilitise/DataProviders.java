package com.api.utilitise;

import java.io.IOException;

import org.testng.annotations.DataProvider;

public class DataProviders {
	@DataProvider(name = "Data")
	public String[][] getAllData() throws IOException {
		String path = System.getProperty("user.dir") + "//testdata//testData.xlsx";
		ExcelUtil xl = new ExcelUtil(path);

		int rownum = xl.getRowCount("Userdata");
		int colcount = xl.getCellCount("Userdata", 1);

		String apidata[][] = new String[rownum][colcount];

		for (int i = 1; i <= rownum; i++) {

			for (int j = 0; j < colcount; j++) {
				apidata[i - 1][j] = xl.getCellData("Userdata", i, j);

			}
		}
		return apidata;

	}

	@DataProvider(name = "UserNames")
	public String[] getUserNames() throws IOException {
		String path = System.getProperty("user.dir") + "//testdata//testData.xlsx";
		ExcelUtil xl = new ExcelUtil(path);

		int rownum = xl.getRowCount("Userdata");

		String apidata[] = new String[rownum];

		for (int i = 1; i <= rownum; i++) {

			apidata[i - 1] = xl.getCellData("Userdata", i, 1);

		}

		return apidata;

	}
}
