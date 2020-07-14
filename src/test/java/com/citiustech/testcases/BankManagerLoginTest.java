package com.citiustech.testcases;

import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.citiustech.base.TestBase;

public class BankManagerLoginTest extends TestBase{

	public BankManagerLoginTest(String path) {
		super(path);
		// TODO Auto-generated constructor stub
	}

	@Test
	public void LoginAsBankManager() throws InterruptedException {
		
		log.debug("Inside Login Test");
		driver.findElement(By.cssSelector(OR.getProperty("bmlBtn"))).click();
		
		Assert.assertTrue(isElementPresent(By.cssSelector(OR.getProperty("addCustBtn"))), "Login not successfull");
		
		log.debug("Login successfully executed");
	}
	
}
