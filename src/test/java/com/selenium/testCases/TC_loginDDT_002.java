package com.selenium.testCases;

import java.io.IOException;

import org.openqa.selenium.NoAlertPresentException;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.selenium.baseClass.BaseClass;
import com.selenium.pageObject.LoginPage;
import com.selenium.utilities.XLUtils;

public class TC_loginDDT_002 extends BaseClass{
	
	
	
	
	@Test(dataProvider="LoginData")
	public void loginDDT(String user, String pwd) throws Exception {
		
		LoginPage  lp= new LoginPage(driver);
		lp.setUserName(user);
		logger.info("Username Provided");
		lp.setPassword(pwd);
		logger.info("Password Provided");
		lp.clickSubmit();
		logger.info("Clicked login button");
		
		Thread.sleep(3000);
		
		if(isAlertPresent()==true) {
			driver.switchTo().alert().accept(); // close alert
			driver.switchTo().defaultContent(); // focus on main page
			Assert.assertTrue(false);
			logger.warn("Login Failed");
			
		}else {
			Assert.assertTrue(true);
			logger.info("Login passed");
			lp.clickLogout();
			Thread.sleep(3000);
			driver.switchTo().alert().accept(); // close logout alert
			driver.switchTo().defaultContent(); // focus on the browser and focus on the login
			logger.info("Logout done");
		}
		
	}
	
	public boolean isAlertPresent() // User defined method to check alert
	{
		
		try {
			driver.switchTo().alert();
			return true;
		}
		catch (NoAlertPresentException e) {
			
			 return false;
		}
		  
	}
	
	
	
	
	
	@DataProvider(name="LoginData")  //Excel file name
	String [][] getData()  {
		
		try {
		String path= System.getProperty("user.dir")+"/src/test/java/com/selenium/testData/LoginData.xlsx";
		
		int rownum=XLUtils.getRowCount(path, "sheet1");        // count row
		int colcount=XLUtils.getCellCount(path, "sheet1", 1);   // count column
		
		String logindata[][] =new String[rownum][colcount];
		
		for(int i=1; i<=rownum; i++) {
			
			for(int j=0; j<colcount; j++) {
				
			logindata[i-1][j]=XLUtils.getCellData(path, "sheet1", i, j); // 1,0
			}
		}
		
		return logindata;
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return getData();		
	}

}
