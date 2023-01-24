package com.selenium.utilities;

import java.io.File;
import java.io.FileInputStream;
import java.util.Properties;

public class ReadConfig {

	
	Properties pro;
	
	public ReadConfig()
	{
		File src =new File("./Configuration/config.properties");
		
		try {
			
			FileInputStream fls=new FileInputStream(src);
			
			pro= new Properties();
			pro.load(fls);
			
		}catch  (Exception e)
		{
			System.out.print("Exception is " + e.getMessage());
		}
	}
	
	
	public String getApplicationURL() {
		String url=pro.getProperty("baseURL");
		return url;
	}
	
	public String getUsername() {
		String username=pro.getProperty("username");
		return username;
	}
	
	public String getPassword() {
		String password=pro.getProperty("password");
		return password;
	}
	
	public String getChrome() {
		String Chromepath=pro.getProperty("Chromepath");
		return Chromepath;
	}
	
	
	
	public String getIEPath() {
		String iepath=pro.getProperty("iepath");
		return iepath;
	}
	
	
	
	public String getFireFoxPath() {
		String firefoxpath=pro.getProperty("firefoxpath");
		return firefoxpath;
	}
	
	
	
}
