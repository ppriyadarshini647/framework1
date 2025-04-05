package practice;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class ToReadDatafromPropertyFileDataDrivenTesting {

	public static void main(String[] args) throws IOException 
	{
		//Step1 :- Create an object of FileInputstream
		FileInputStream fis = new FileInputStream(".\\src\\test\\resources\\commonData.properties");
		
		//Step2 :- create an object of Property file
		Properties prop = new Properties();
		
		//Step3 :- Call the method
		prop.load(fis);
		String URL = prop.getProperty("url");
		String USERNAME = prop.getProperty("username");
		String PASSWORD = prop.getProperty("password");
		String BROWSER = prop.getProperty("browser");
		System.out.println(URL);
		System.out.println(USERNAME);
		System.out.println(PASSWORD);
		System.out.println(BROWSER);
		
		
		

	}

}
