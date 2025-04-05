package practice;

import java.io.FileInputStream;
import java.io.IOException;
import java.time.Duration;
import java.util.Properties;
import java.util.Random;

import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;

public class DemoScriptWithDDTCreateOrganization {

	public static void main(String[] args) throws IOException 
	{
		//To Read the Data from Property File
		FileInputStream fis = new FileInputStream(".\\src\\test\\resources\\commonData.properties");
		Properties prop = new Properties();
		prop.load(fis);
		String URL = prop.getProperty("url");
		String USERNAME = prop.getProperty("username");
		String PASSWORD = prop.getProperty("password");
		String BROWSER = prop.getProperty("browser");
		
		//To Read The Data from Excel File
		FileInputStream efis = new FileInputStream(".\\test\\resources\\TestDataAdvanceBatch.xlsx");
		Workbook wb = WorkbookFactory.create(efis);
		String OrgName = wb.getSheet("Organizations").getRow(1).getCell(2).toString();
		
		//Step1 :- Launch browser
		WebDriver driver = null;
		if(BROWSER.equals("chrome"))
		{
			driver = new ChromeDriver();
		}
		else if (BROWSER.equals("firefox"))
		{
			driver = new FirefoxDriver();
		}
		else if(BROWSER.equals("edge"))
		{
			driver = new EdgeDriver();
		}
		
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));
		
		//Step2 :- login with valid credentials
		driver.get(URL);
		driver.findElement(By.name("user_name")).sendKeys(USERNAME);
		driver.findElement(By.name("user_password")).sendKeys(PASSWORD);
		driver.findElement(By.id("submitButton")).click();
		
		//Step3 :- Click on Organizations link
		driver.findElement(By.linkText("Organizations")).click();
		
		//Step4 :- click on Organization add look up image
		driver.findElement(By.xpath("//img[@src='themes/softed/images/btnL3Add.gif']")).click();
		
		//Step5 :- fill all the mandatory fields
		Random r = new Random();
		int random = r.nextInt(1000);
		driver.findElement(By.name("accountname")).sendKeys("OrgName"+random);
		
		//Step6 :- save and verify
		driver.findElement(By.xpath("//input[@title='Save [Alt+S]']")).click();
		String NAME = driver.findElement(By.xpath("//span[@class='dvHeaderText']")).getText();
		if(NAME.contains("OrgName"+random))
		{
			System.out.println(NAME+"---Passed");
		}
		else {
			System.out.println(NAME+"----failed");
		}
		
		//Step7 :- Logout the Application
	    WebElement LOGOUT = driver.findElement(By.xpath("//img[@src='themes/softed/images/user.PNG']"));
	    Actions action = new Actions(driver);
	    action.moveToElement(LOGOUT).perform();
	    driver.findElement(By.linkText("Sign Out")).click();
	    
	    driver.quit();
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		

	}

}
