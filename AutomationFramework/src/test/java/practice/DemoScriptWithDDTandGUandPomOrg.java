package practice;

import java.io.IOException;
import java.util.Random;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import genericUtility.WebDriverUtility;
import genericUtility.excelFileUtility;
import genericUtility.propertyFileUtility;
import objectRepository.createOrganizationPage;
import objectRepository.homePage;
import objectRepository.loginPage;
import objectRepository.organizationInformationPage;
import objectRepository.organizationPage;

public class DemoScriptWithDDTandGUandPomOrg {

	public static void main(String[] args) throws IOException 
	{
		propertyFileUtility putil = new propertyFileUtility();
		excelFileUtility eutil = new excelFileUtility();
		WebDriverUtility wutil = new WebDriverUtility();
		
		//To Read data from property file
		String URL = putil.toReadDataFromPropertyFile("url");
		String BROWSER = putil.toReadDataFromPropertyFile("browser");
		String USERNAME = putil.toReadDataFromPropertyFile("username");
		String PASSWORD = putil.toReadDataFromPropertyFile("password");
		
		//To Read data from excel file
		String ORGNAME = eutil.toReadTheDataFromExcelFile("Organizations", 1, 2);
		
		//STEP1: LAUNCH BROWSER
		WebDriver driver = null;
		if(BROWSER.equals("chrome")) {
			driver = new ChromeDriver();
		} else if (BROWSER.equals("firefox")){
			driver = new FirefoxDriver();
		} else if (BROWSER.equals("egde")) {
			driver = new EdgeDriver();
		}
		
		wutil.toMaximizeTheBrowser(driver);
		wutil.toImplicitWait(driver);
		
		//STEP2: LOGIN WITH VALID CREDENTIALS
		driver.get(URL);
		loginPage lp = new loginPage(driver);
		lp.getUsernameTextField().sendKeys(USERNAME);
		lp.getPasswordTextField().sendKeys(PASSWORD);
		lp.getLoginButton().click();
		
		//STEP3: CLICK ON ORGANIZATIONS
		homePage hp = new homePage(driver);
		hp.getOrganizationsLink().click();
		
		//STEP4: CLICK ON CREATE ORG LOOKUP IMG
		organizationPage op = new organizationPage(driver);
		op.getOrganizationLookUpImage().click();
		
		//STEP5: FILL THE MANDATORY DETAILS
		Random r = new Random();
		int random = r.nextInt(1000);
		
		createOrganizationPage cop = new createOrganizationPage(driver);
		cop.getOrganizationNameTextField().sendKeys(ORGNAME+random);
		
		//STEP6: SAVE AND VERIFY
		cop.getSaveButton().click();
		organizationInformationPage oip = new organizationInformationPage(driver);
		String organizationName = oip.getOrgaHeaderText().getText();
		if(organizationName.contains(ORGNAME+random)) {
			System.out.println(organizationName+"---Passed");
		}
		else {
			System.out.println(organizationName+"---Failed");
		}
		
		//STEP7: LOGOUT
		wutil.toMouseHover(driver, hp.getLogOutLink());
		hp.getSignOutButton();
		
		//STEP8: CLOSE
		driver.quit();


	}

}
