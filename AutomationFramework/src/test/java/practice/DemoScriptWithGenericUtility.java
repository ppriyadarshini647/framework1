package practice;

import java.io.IOException;
import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;

import genericUtility.excelFileUtility;
import genericUtility.propertyFileUtility;

public class DemoScriptWithGenericUtility {

	public static void main(String[] args) throws IOException {
		// To Read the Data from Property file
		propertyFileUtility putil = new propertyFileUtility();
		String URL = putil.toReadDataFromPropertyFile("url");
		String USERNAME = putil.toReadDataFromPropertyFile("username");
		String PASSWORD = putil.toReadDataFromPropertyFile("password");
		String BROWSER = putil.toReadDataFromPropertyFile("browser");

		// To Read the Data From Excel file
		excelFileUtility eutil = new excelFileUtility();
		String LASTNAME = eutil.toReadTheDataFromExcelFile("Contacts", 1, 2);

		// Step1: launch browser
		WebDriver driver = null;
		if (BROWSER.equals("chrome")) {
			driver = new ChromeDriver();
		} else if (BROWSER.equals("edge")) {
			driver = new EdgeDriver();
		} else if (BROWSER.equals("firefox")) {
			driver = new FirefoxDriver();
		}

		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));

		// Step2: Login with valid credentials
		driver.findElement(By.name("user_name")).sendKeys(USERNAME);
		driver.findElement(By.name("user_password")).sendKeys(PASSWORD);
		driver.findElement(By.id("submitButton")).click();

		// Step3: click on Contacts link
		driver.findElement(By.linkText("Contacts")).click();

		// Step4: click on add contact lookup image
		driver.findElement(By.xpath("//img[@src='themes/softed/images/btnL3Add.gif']")).click();

		// Step5 :- Fill all the mandatory fields
		driver.findElement(By.id("lastname")).sendKeys(LASTNAME);

		// Step6 :- Save and Verify it
		driver.findElement(By.xpath("//input[@title='Save [Alt+S]']")).click();
		String Name = driver.findElement(By.xpath("//span[@class='dvHeaderText']")).getText();
		if (Name.contains(LASTNAME)) {
			System.out.println(Name + "---Passed");
		} else {
			System.out.println(Name + "---Failed");
		}

		// Step7 :- Logout the Application
		WebElement LOGOUT = driver.findElement(By.xpath("//img[@src='themes/softed/images/user.PNG']"));
		Actions action = new Actions(driver);
		action.moveToElement(LOGOUT).perform();
		driver.findElement(By.linkText("Sign Out")).click();
		driver.quit();

	}

}
