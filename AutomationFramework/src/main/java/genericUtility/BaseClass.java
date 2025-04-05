package genericUtility;

import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Reporter;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import objectRepository.contactsPage;
import objectRepository.homePage;
import objectRepository.loginPage;

public class BaseClass {
	propertyFileUtility putil = new propertyFileUtility();
	WebDriverUtility wutil = new WebDriverUtility();
	public WebDriver driver = null;
	public static WebDriver sdriver; //used for listener

	@BeforeSuite(groups = { "smoke", "regression" })
	public void beforeSuiteConfiguration() {
		Reporter.log("--Database connection established--", true);
	}

//	@Parameters("browser") // for cross brow testing
//	@BeforeTest // for cross brow testing

	@BeforeClass(groups = {"smoke","regression"})
	public void beforeClassConfiguration(/*String BROWSER*/) throws IOException {
		String BROWSER = putil.toReadDataFromPropertyFile("browser"); 
		String URL = putil.toReadDataFromPropertyFile("url");
		if (BROWSER.equals("chrome")) {
			driver = new ChromeDriver();
		} else if (BROWSER.equals("edge")) {
			driver = new EdgeDriver();
		} else if (BROWSER.equals("firefox")) {
			driver = new FirefoxDriver();
		}
		sdriver = driver; // used for Listener
		Reporter.log("--BROWSER got Launched--", true);
		wutil.toMaximizeTheBrowser(driver);
		wutil.toImplicitWait(driver);
		driver.get(URL);

	}


	@BeforeMethod(groups = { "smoke", "regression" })
	public void beforeMethodConfiguration() throws IOException {
		String USERNAME = putil.toReadDataFromPropertyFile("username");
		String PASSWORD = putil.toReadDataFromPropertyFile("password");
		loginPage lp = new loginPage(driver);
		lp.getUsernameTextField().sendKeys(USERNAME);
		lp.getPasswordTextField().sendKeys(PASSWORD);
		lp.getLoginButton().click();
		Reporter.log("--Logged In Successfully--", true);
	}

	@AfterMethod(groups = { "smoke", "regression" })
	public void afterMethodConfiguration() {
		homePage hp = new homePage(driver);
		wutil.toMouseHover(driver, hp.getLogOutLink());
		hp.getSignOutButton().click();
		Reporter.log("--Logged out Successfully--", true);
	}


	@AfterClass(groups = { "smoke", "regression" })
	public void afterClassConfiguration() {
		driver.quit();
		Reporter.log("--Browser got closed successfully--", true);
	}


	@AfterSuite(groups = { "smoke", "regression" })
	public void afterSuiteConfiguration() {
		Reporter.log("--Database connection Disconnected--", true);
	}

}
