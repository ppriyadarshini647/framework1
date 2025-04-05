package practice;

import java.time.Duration;
import java.util.Random;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;

public class ToCreateOrganizationWithIndustryDropdown {

	public static void main(String[] args) 
	{
		//Step1 :- Launch Browser
		WebDriver driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		
		//Step2 :- Login to application with valid credentials
		driver.get("http://localhost:8888/");
		driver.findElement(By.name("user_name")).sendKeys("admin");
		driver.findElement(By.name("user_password")).sendKeys("123");
		driver.findElement(By.id("submitButton")).click();
		
		//Step3 :- Navigate to organization link
		driver.findElement(By.linkText("Organizations")).click();
		
		//Step4 :- click on create organization look up image 
		driver.findElement(By.xpath("//img[@alt='Create Organization...']")).click();
		
		//Step5 :- create organization with mandatory fields 
		Random r = new Random();
		int random = r.nextInt(10000);
		
		driver.findElement(By.name("accountname")).sendKeys("TechPyramidd"+random);
		
		//Step6 :- select "Chemicals" in the industry DropDown
		WebElement industryDropdown = driver.findElement(By.name("industry"));
		Select a = new Select(industryDropdown);
		a.selectByValue("Chemicals");
		
		driver.findElement(By.xpath("//input[@name='assigntype' and @value='U' ]")).click();
		driver.findElement(By.xpath("//input[@title='Save [Alt+S]']")).click();
		
		//Step7 :- Save and Verify
		String organizationName = driver.findElement(By.xpath("//span[@class='dvHeaderText']")).getText();
		if(organizationName.contains("TechPyramidd"+random)) {
			System.out.println(organizationName+"---Passed");
		}else {
			System.out.println(organizationName+"----Failed");
		}
		
		//Step8 :- Logout of Application
		WebElement logout = driver.findElement(By.xpath("//img[@src='themes/softed/images/user.PNG']"));
		Actions action = new Actions(driver);
		action.moveToElement(logout).perform();
		driver.findElement(By.linkText("Sign Out")).click();

	}

}
