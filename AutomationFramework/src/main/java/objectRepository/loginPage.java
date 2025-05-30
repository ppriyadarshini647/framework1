package objectRepository;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindAll;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class loginPage 
{   
	//Constructor
	public loginPage(WebDriver driver)
	{
		PageFactory.initElements(driver, this);
	}
	@FindBy(name ="user_name")
	private WebElement usernameTextField;
	
	@FindAll({@FindBy(name = "user_password"),@FindBy(xpath="//input[@type='password']")})
	private WebElement passwordTextField;
	
	@FindAll({@FindBy(id="submitButton"),@FindBy(xpath ="(//input[@value='Login'])[2]")})
	private WebElement loginButton;

	public WebElement getUsernameTextField() {
		return usernameTextField;
	}

	public WebElement getPasswordTextField() {
		return passwordTextField;
	}

	public WebElement getLoginButton() {
		return loginButton;
	}
	

}
