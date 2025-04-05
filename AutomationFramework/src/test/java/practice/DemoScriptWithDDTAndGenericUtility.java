package practice;

import java.io.IOException;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;

import genericUtility.WebDriverUtility;
import genericUtility.excelFileUtility;
import genericUtility.propertyFileUtility;

public class DemoScriptWithDDTAndGenericUtility {

	public static void main(String[] args) throws IOException 
	{
		propertyFileUtility putil = new propertyFileUtility();
		excelFileUtility eutil = new excelFileUtility();
		WebDriverUtility wutil = new WebDriverUtility();
		
		//Read Data From Property File
		String URL = putil.toReadDataFromPropertyFile("url");
		String BROWSER = putil.toReadDataFromPropertyFile("browser");
		String USERNAME = putil.toReadDataFromPropertyFile("username");
		String PASSWORD = putil.toReadDataFromPropertyFile("password");
		
		//Read Data From Excel File
		String LASTNAME = eutil.toReadTheDataFromExcelFile("Contacts", 1, 2);
		
		//Launch Browser
		WebDriver driver = null;
		if(BROWSER.equals("chrome"))
		{
			driver = new ChromeDriver();
		}
		else if (BROWSER.equals("edge"))
		{
			driver = new EdgeDriver();
		}
		else if (BROWSER.equals("firefox"))
		{
			driver =  new FirefoxDriver();
		}
		
		wutil.toMaximizeTheBrowser(driver);
		wutil.toImplicitWait(driver);
		
		//Login with Valid Credentials
		driver.get(URL);
		driver.findElement(By.name("user_name")).sendKeys(USERNAME);
		driver.findElement(By.name("user_password")).sendKeys(PASSWORD);
		driver.findElement(By.id("submitButton")).click();
		
		//Click On Contacts Link
		driver.findElement(By.linkText("Contacts")).click();
		
		//Click on Contact LookUp Image
		driver.findElement(By.xpath("//img[@title='Create Contact...']")).click();
		
		//Fills all The Mandatory Fields
		driver.findElement(By.name("lastname")).sendKeys(LASTNAME);
		
		//Save and verify It
		driver.findElement(By.xpath("//input[@title='Save [Alt+S]']")).click();
		String Name = driver.findElement(By.xpath("//span[@class='dvHeaderText']")).getText();
		if(Name.contains(LASTNAME))
		{
			System.out.println(Name + "---passed");
		}
		else
		{
			System.out.println(Name + "---failed");
		}
		
		//LogOut an application
		WebElement LogOut = driver.findElement(By.xpath("//img[@src='themes/softed/images/user.PNG']"));
		wutil.toMouseHover(driver, LogOut);
		driver.findElement(By.linkText("Sign Out")).click();
		
		//Close the browser
		driver.quit();
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		

	}

}
