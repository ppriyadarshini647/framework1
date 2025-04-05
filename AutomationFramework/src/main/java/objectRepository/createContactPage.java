package objectRepository;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class createContactPage 
{
	public createContactPage(WebDriver driver) {
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(name="lastname")
	private WebElement lastNameTextField;
	
	@FindBy(xpath ="//input[@name='account_name']/following-sibling::img")
	private WebElement organizationNameLookUpImg;
	
	@FindBy(xpath="//input[@title='Save [Alt+S]']")
	WebElement saveButton;

	public WebElement getLastNameTextField() {
		return lastNameTextField;
	}

	public WebElement getOrganizationNameLookUpImg() {
		return organizationNameLookUpImg;
	}

	public WebElement getSaveButton() {
		return saveButton;
	}
	

}
