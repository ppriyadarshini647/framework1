package practice;

import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import genericUtility.WebDriverUtility;
import genericUtility.excelFileUtility;
import genericUtility.propertyFileUtility;
import objectRepository.contactsInformationpage;
import objectRepository.contactsPage;
import objectRepository.createContactPage;
import objectRepository.homePage;
import objectRepository.loginPage;

public class DemoScriptWithDDTandGenericUtilityandPOM 
{
	public static void main(String[] args) throws IOException {
	propertyFileUtility putil = new propertyFileUtility();
	excelFileUtility eutil = new excelFileUtility();
	WebDriverUtility wutil = new WebDriverUtility();
	
	//To read the data from property file
	String URL=putil.toReadDataFromPropertyFile("url");
	String BROWSER = putil.toReadDataFromPropertyFile("browser");
	String USERNAME = putil.toReadDataFromPropertyFile("username");
	String PASSWORD = putil.toReadDataFromPropertyFile("password");
	
	//To read the data from excel file
	String LASTNAME = eutil.toReadTheDataFromExcelFile("Contacts", 1, 2);
	
	//TESTCASE 1 : Create Contacts link
	
	//Step1 : launch browser
	WebDriver driver = null;
	if(BROWSER.equals("chrome")) {
		driver = new ChromeDriver();
	} else if(BROWSER.equals("edge")) {
		driver = new EdgeDriver();
	} else if(BROWSER.equals("firefox")) {
		driver = new FirefoxDriver();
	}
	
	wutil.toMaximizeTheBrowser(driver);
	wutil.toImplicitWait(driver);
	
	//Step2 : Login with valid credential
	driver.get(URL);
	loginPage lp = new loginPage(driver);
	lp.getUsernameTextField().sendKeys(USERNAME);
	lp.getPasswordTextField().sendKeys(PASSWORD);
	lp.getLoginButton().click();
	
	//Step3 : Click on ContactsLink
	homePage hp = new homePage(driver);
	hp.getContactsLink().click();
	
	//Step4 : Click on Create Contacts Lookup Img
	contactsPage cp = new contactsPage(driver);
	cp.getContactLookUpImage().click();
	
	//Step5 : Fills all the mandatory details
	createContactPage ccp = new createContactPage(driver);
	ccp.getLastNameTextField().sendKeys(LASTNAME);
	
	//Step6 : Save and Verify
	ccp.getSaveButton().click();
	contactsInformationpage cip = new contactsInformationpage(driver);
	String name = cip.getHeaderText().getText();
	if(name.contains(LASTNAME)) 
	{
		System.out.println(name+"---Passed");
	}
	else{
		System.out.println(name+"---failed");
		
	}
	
	//Step7 : logout
	wutil.toMouseHover(driver, hp.getLogOutLink());
	hp.getSignOutButton().click();
	
	//Step8 : close
	driver.quit();
	}
}
