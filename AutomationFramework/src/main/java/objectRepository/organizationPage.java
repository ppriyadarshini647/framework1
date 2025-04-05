package objectRepository;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindAll;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class organizationPage 
{
	public organizationPage(WebDriver driver) {
		PageFactory.initElements(driver, this);
	}
	
	@FindAll({@FindBy(xpath ="//img[@title='Create Organization...']"),@FindBy(xpath ="//img[@alt='Create Organization...']")})
	private WebElement organizationLookUpImage;

	public WebElement getOrganizationLookUpImage() {
		return organizationLookUpImage;
	}
	

}
