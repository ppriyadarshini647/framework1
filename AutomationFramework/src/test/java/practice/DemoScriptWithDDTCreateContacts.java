package practice;

import java.io.FileInputStream;
import java.io.IOException;
import java.time.Duration;
import java.util.Properties;

import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;

public class DemoScriptWithDDTCreateContacts {

	public static void main(String[] args) throws IOException 
	{
		//To read data from propertiesFile
		FileInputStream pfis = new FileInputStream(".\\src\\test\\resources\\commonData.properties");
		Properties prop = new Properties();
		prop.load(pfis);
		String BROWSER = prop.getProperty("browser");
		String URL = prop.getProperty("url");
		String USERNAME = prop.getProperty("username");
		String PASSWORD = prop.getProperty("password");
		
		//To Read Data from Excel file
		FileInputStream efis = new FileInputStream(".\\src\\test\\resources\\TestDataAdvanceBatch.xlsx");
		Workbook wb =  WorkbookFactory.create(efis);
		String LASTNAME = wb.getSheet("Contacts").getRow(1).getCell(2).toString();
		
		//Step 1 : Launch browser
		WebDriver driver = null;
		if(BROWSER.equals("chrome"))
		{
			driver = new ChromeDriver();
		}else if (BROWSER.equals("edge"))
		{
			driver =  new EdgeDriver();
		}else if (BROWSER.equals("firefox"))
		{
			driver = new FirefoxDriver();
		}
		
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));
		
		//Step2 :- Login to application with valid credentials
		driver.get(URL);
		driver.findElement(By.name("user_name")).sendKeys(USERNAME);
		driver.findElement(By.name("user_password")).sendKeys(PASSWORD);
		driver.findElement(By.id("submitButton")).click();
		
		//Step3 :- Navigate to Contacts link
		driver.findElement(By.linkText("Contacts")).click();
		
		//Step4 :- Click on create contact look up image
		driver.findElement(By.xpath("//img[@title='Create Contact...']")).click();
		
		//Step5 :- Fill all the mandatory fields
		driver.findElement(By.id("lastname")).sendKeys(LASTNAME);
		
		//Step6 :- Save and Verify it
		driver.findElement(By.xpath("//input[@title='Save [Alt+S]']")).click();
	    String Name = driver.findElement(By.xpath("//span[@class='dvHeaderText']")).getText();
	    if(Name.contains(LASTNAME))
	    {
	    	System.out.println(Name+"---Passed");
	    }else {
	    	System.out.println(Name+"---Failed");
	    }
		
	    //Step7 :- Logout the Application
	    WebElement LOGOUT = driver.findElement(By.xpath("//img[@src='themes/softed/images/user.PNG']"));
	    Actions action = new Actions(driver);
	    action.moveToElement(LOGOUT).perform();
	    driver.findElement(By.linkText("Sign Out")).click();
	    driver.quit();
	   
	    

		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		


		
		

	}

}
