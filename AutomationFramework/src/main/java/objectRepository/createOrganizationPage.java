package objectRepository;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class createOrganizationPage 
{
	public createOrganizationPage(WebDriver driver)
	{
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(name ="accountname")
	private WebElement organizationNameTextField;
	
	@FindBy(name ="industry")
	private WebElement industryDropDown;
	
	@FindBy(name ="accounttype")
	private WebElement typeDropDown;
	
	@FindBy(name="assigned_user_id")
	private WebElement assignedToDropdown;
	
	@FindBy(xpath ="//input[@title='Save [Alt+S]']")
	private WebElement SaveButton;

	public WebElement getOrganizationNameTextField() {
		return organizationNameTextField;
	}

	public WebElement getIndustryDropDown() {
		return industryDropDown;
	}

	public WebElement getTypeDropDown() {
		return typeDropDown;
	}

	public WebElement getAssignedToDropdown() {
		return assignedToDropdown;
	}

	public WebElement getSaveButton() {
		return SaveButton;
	}

	
	}
	
	

