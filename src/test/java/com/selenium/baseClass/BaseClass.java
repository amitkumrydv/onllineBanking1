package com.selenium.baseClass;

import java.io.File;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;

import com.selenium.utilities.ReadConfig;

public class BaseClass {
	
	
	ReadConfig readConfig = new ReadConfig();

	public String baseURL = readConfig.getApplicationURL();
	public String username = readConfig.getUsername();
	public String password = readConfig.getPassword();
	public static WebDriver driver;
	public static Logger logger; 
	
	
	
    @Parameters("browser")
	@BeforeClass
	public void setup(String br) {
		
		logger=Logger.getLogger("ebanking");
		PropertyConfigurator.configure("Log4j.properties");
		
     if (br.equals("chrome"))
     {
    	 System.setProperty("webdriver.chrome.driver", readConfig.getChrome());
    	 driver = new ChromeDriver();
     }
     if (br.equals("firefox"))
     {
    	 System.setProperty("webdriver.gecko.driver", readConfig.getFireFoxPath());
    	 driver = new FirefoxDriver();
     }
     
     if (br.equals("ie"))
     {
    	 System.setProperty("webdriver.ie.driver", readConfig.getIEPath());
    	 driver = new InternetExplorerDriver();
     }
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
 	    driver.get(baseURL);
 	   driver.manage().window().maximize();
	}

	
	public void captureScreen(WebDriver driver, String tname) throws IOException   {
		
		TakesScreenshot ts=(TakesScreenshot) driver;
		File source =ts.getScreenshotAs(OutputType.FILE);
		File target=new File(System.getProperty("user.dir") + "/Screenshots/" + tname +".png" );
		FileUtils.copyFile(source, target);
		
		System.out.println("Screenshot taken");
		
	}
	
	
	public String randomestring()   //Generat rendom email user defne method method
	{
		String generatedstring=RandomStringUtils.randomAlphabetic(8);
		return (generatedstring)  ;
	}
	
	
	public String randomeNum()   //Generat rendom Number user defne method method
	{
		String generatedstring2=RandomStringUtils.randomNumeric(4);
		return (generatedstring2)  ;
	}
	
	
	@AfterClass
	public void tearDown() {

		driver.quit();
	}
	
}
