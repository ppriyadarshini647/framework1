package assertionTest;

import java.io.IOException;
import java.util.Random;

import org.apache.poi.EncryptedDocumentException;
import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Test;

import genericUtility.BaseClass;
import genericUtility.WebDriverUtility;
import genericUtility.excelFileUtility;
import objectRepository.contactsPage;
import objectRepository.createContactPage;
import objectRepository.homePage;
import objectRepository.organizationInformationPage;

public class ToCreateContactWithOrgTest extends BaseClass
{
	@Test(groups = "smoke")
	public void toCreateContactwithOrg() throws EncryptedDocumentException, IOException 
	{

		//click on contacts
				homePage hp = new homePage(driver);
				hp.getContactsLink().click();
				contactsPage cp = new contactsPage(driver);
				cp.getContactLookUpImage().click();
				createContactPage ccp = new createContactPage(driver);
				ccp.getOrganizationNameLookUpImg().click();
				excelFileUtility eutil = new excelFileUtility();
				Random r = new Random(); //java utility
				int random = r.nextInt();
				String Lastname = eutil.toReadTheDataFromExcelFile("Contacts", 1, 2);
				ccp.getLastNameTextField().sendKeys(Lastname);
//				String orgName = eutil.toReadTheDataFromExcelFile("Contacts", 4, 3);
				ccp.getOrganizationNameLookUpImg().click();
				WebDriverUtility wutil = new WebDriverUtility();
				wutil.toSwitchWindow(driver, "Accounts&action");
				driver.findElement(By.linkText("Qspider")).click();
				wutil.toSwitchWindow(driver, "Contacts");
				ccp.getSaveButton().click();
				organizationInformationPage oip = new organizationInformationPage(driver);
				String ORGNM = oip.getOrgaHeaderText().getText();
//				Assert.assertEquals(ORGNM.contains(Lastname), true);
				Assert.assertTrue(ORGNM.contains(Lastname));

	}
	

}
