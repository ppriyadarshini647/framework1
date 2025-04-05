package practice;

import java.time.Duration;
import java.util.Random;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;

public class ToCreateContactWithOrganizationName {

	public static void main(String[] args) 
	{
		//Step1 :- Launch Browser
		WebDriver driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		
		//Step2 :- Login to application with valid credentials
		driver.get("http://localhost:8888/");
		driver.findElement(By.name("user_name")).sendKeys("admin");
		
		//Step5 :- Click contact with mandatory fields
		driver.findElement(By.name("user_password")).sendKeys("123");
		driver.findElement(By.id("submitButton")).click();
		
		//Step3 :- Navigate to Contacts link
		driver.findElement(By.linkText("Contacts")).click();
		
		//Step4 :- Click on create contact look up image
		driver.findElement(By.xpath("//img[@alt='Create Contact...']")).click();
		
		//Step5 :- Click contact with mandatory fields
		Random r = new Random();
		int random = r.nextInt(1000);
		
		driver.findElement(By.name("lastname")).sendKeys("Prakruti p"+random);
		
	
		//Step6 :- Select the organization from organization look up image
		driver.findElement(By.xpath("(//img[@src='themes/softed/images/select.gif'])[1]")).click();
		//Capture all windows ids
		Set<String> allIds = driver.getWindowHandles();
		//remove parentId
		String parentID= driver.getWindowHandle();

		allIds.remove(parentID);
		for(String id : allIds )
		{
			driver.switchTo().window(id);
			driver.findElement(By.linkText("vtiger")).click();

		}
		driver.switchTo().window(parentID);
		
		//Step7 :- Save and verify
		driver.findElement(By.xpath("//input[@title='Save [Alt+S]']")).click();
		String lastname = driver.findElement(By.xpath("//span[@class='dvHeaderText']")).getText();
		if(lastname.contains("Prakruti p"+random)) {
			System.out.println(lastname+"----Passed");
		}else {
			System.out.println(lastname+"-----Failed");
		}
		
		//Step 8 :- Logout of Application
		
		WebElement logout = driver.findElement(By.xpath("//img[@src='themes/softed/images/user.PNG']"));
		Actions action = new Actions(driver);
		action.moveToElement(logout).perform();
		driver.findElement(By.linkText("Sign Out")).click();
		driver.quit();

		

	}

}
