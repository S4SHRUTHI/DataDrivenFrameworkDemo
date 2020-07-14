package com.citiustech.testcases;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.citiustech.base.TestBase;

public class AddCustomerTest extends TestBase{
	
	public AddCustomerTest(String path) {
		super(path);
		// TODO Auto-generated constructor stub
	}

	@Test(dataProvider="getData")
	public void addCustomer(String firstname, String lastname, String postCode) {
		
		
	}
	
	@DataProvider
	public Object[][] getData(){
		
		String sheetName = "AddCustomerTest";
		int rows = excel.getRowCount(sheetName);
//		int cols = excel.getC
		return null;
		
	}

}
