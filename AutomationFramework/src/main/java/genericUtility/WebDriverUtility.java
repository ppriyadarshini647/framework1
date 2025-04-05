package genericUtility;

import java.io.File;
import java.io.IOException;
import java.time.Duration;
import java.util.Set;

import org.openqa.selenium.Alert;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.io.FileHandler;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

    /**
    * This class is consist of DropDown, browser related actions, actions, PopUp,
    * ScreenShot
    */

    public class WebDriverUtility {
	/**
	 * This method is used to Maximize the browser
	 * 
	 * @param driver
	 */
	public void toMaximizeTheBrowser(WebDriver driver) {
		driver.manage().window().maximize();
	}
	

	/**
	 * The method is used to minimize the browser
	 * 
	 * @param driver
	 */
	public void toMinimizeTheBrowser(WebDriver driver) {
		driver.manage().window().minimize();
	}
	

	/**
	 * The method is wait until the element is loaded in the WebPage(implicit wait)
	 * 
	 * @param driver
	 */
	public void toImplicitWait(WebDriver driver) {
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
	}
	

	/**
	 * The Method is wait until the element is ClickAble provided driver and element
	 * until the condition is satisfied(explicit Wait)
	 * 
	 * @param driver
	 * @param element
	 */
     public void toExplicitWait(WebDriver driver, WebElement element) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
		wait.until(ExpectedConditions.elementToBeClickable(element));
	}
     

	/**
	 * The Method will wait until the element is visible provided driver and element
	 * 
	 * @param driver
	 * @param element
	 */
	public void toWaitUntilVisibilityOfElement(WebDriver driver, WebElement element) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
		wait.until(ExpectedConditions.visibilityOf(element));
	}
	

	/**
	 * This method is used to handle DropDown by using Index
	 * 
	 * @param element
	 * @param index
	 */

     public void toHandleDropdown(WebElement element, int index) {
		Select select = new Select(element);
		select.selectByIndex(index);
	}
     

	/**
	 * This method is used to handle DropDown by using Value
	 * 
	 * @param element
	 * @param value
	 */
	public void toHandleDropdown(WebElement element, String value) {
		Select select = new Select(element);
		select.selectByValue(value);
	}
	

	/**
	 * This method is used to handle DropDown by using VisibleText
	 * 
	 * @param text
	 * @param element
	 */
     public void toHandleDropdown(String text, WebElement element) {
		Select select = new Select(element);
		select.selectByVisibleText(text);
	}
     

	/**
	 * This method is used to perform MouseHover provided driver and element
	 * 
	 * @param driver
	 * @param element
	 */
     public void toMouseHover(WebDriver driver, WebElement element) {
		Actions action = new Actions(driver);
		action.moveToElement(element).perform();
     }
     

	/**
	 * This Method is used to perform RightClick Provided driver and element
	 * 
	 * @param driver
	 * @param element
	 */
     public void toRightClick(WebDriver driver, WebElement element) {
		Actions action = new Actions(driver);
		action.contextClick(element).perform();
     }
     

	/**
	 * This method is used to perform DoubleClick provided driver and element
	 * 
	 * @param driver
	 * @param element
	 */
     public void toDoubleClick(WebDriver driver, WebElement element) {
		Actions action = new Actions(driver);
		action.doubleClick(element).perform();
	}
     

	/**
	 * This Method is used to perform DragAndDrop provided driver and WebElements
	 * 
	 * @param driver
	 * @param src
	 * @param target
	 */
	public void toDragAndDrop(WebDriver driver, WebElement src, WebElement target) {
		Actions action = new Actions(driver);
		action.dragAndDrop(src, target).perform();
	}
	

	/**
	 * This method is used to handle frame using index
	 * 
	 * @param driver
	 * @param index
	 */
     public void toHandleFrame(WebDriver driver, int index) {
		driver.switchTo().frame(index);
	}
     

	/**
	 * This method is used to handle frame using Id or Name
	 * 
	 * @param driver
	 * @param Id_name
	 */
	public void toHandleFrame(WebDriver driver, String Id_name) {
		driver.switchTo().frame(Id_name);
	}
	

	/**
	 * This Method is used to handle frame using webElement
	 * 
	 * @param driver
	 * @param element
	 */
	public void toHandleWebElement(WebDriver driver, WebElement element) {
		driver.switchTo().frame(element);
	}
	

	/**
	 * This Method is used to switch back from Frame
	 * 
	 * @param driver
	 */
     public void toSwitchBackFromFrame(WebDriver driver) {
		driver.switchTo().defaultContent();
	}
     
 

	/**
	 * This Method is used to handle alert popUp by accepting it
	 * 
	 * @param driver
	 */
	public void toSwitchToAlertAndAccept(WebDriver driver) {
		driver.switchTo().alert().accept();
	}
	

	/**
	 * This Method is used to handle alert popUp by Dismissing it
	 * 
	 * @param driver
	 */
	public void toSwitchToAlertAndDismiss(WebDriver driver) {
		driver.switchTo().alert().dismiss();
	}
	

	/**
	 * This Method is used to handle alert popUp and capture the text and accept it
	 * 
	 * @param driver
	 * @return
	 */
	public String toSwitchToAlertAndCaptureMessage(WebDriver driver) {
		Alert alertPopup = driver.switchTo().alert();
		String message = alertPopup.getText();
		alertPopup.accept();
		return message;
	}
	

	/**
	 * This method is used to take screenshot provided driver and screenShotname
	 * 
	 * @param driver
	 * @param screenshotname
	 * @throws IOException
	 */
     public void toTakeScreenshot(WebDriver driver, String screenshotname) throws IOException{
		TakesScreenshot ts = (TakesScreenshot) driver;
		File temp = ts.getScreenshotAs(OutputType.FILE);
		File src = new File("./errorShots/" + screenshotname + ".png");
		FileHandler.copy(temp, src);
      }
     
     /**
      * This method is used to switch the window provided driver 
      * @param driver
      * @param PartialTitle
      */
     public void toSwitchWindow(WebDriver driver,String PartialTitle) {
    	  Set<String> allIds = driver.getWindowHandles();
    	  for(String Id:allIds)
    	  {
    		  String Title = driver.switchTo().window(Id).getTitle();
    		  if(Title.contains(PartialTitle))
    		  {
    			  break;
    		  }
    	  }
     }
     
     
     

     
     
     
     
     
     
     
     
     
     
     
     
     
     
     
     
     
     
     
     
     
     
     
     
     
     
     
     
     
     
     
     
     
     
     
     
     
     

}
