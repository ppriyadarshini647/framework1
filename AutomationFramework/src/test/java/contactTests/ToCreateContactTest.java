package contactTests;

import java.io.IOException;

import org.apache.poi.EncryptedDocumentException;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import genericUtility.BaseClass;
import genericUtility.excelFileUtility;
import objectRepository.contactsInformationpage;
import objectRepository.contactsPage;
import objectRepository.createContactPage;
import objectRepository.homePage;
@Listeners(genericUtility.ListenersImplemantation.class)
public class ToCreateContactTest extends BaseClass
{
	@Test
	public void toCreateContact_001() throws EncryptedDocumentException, IOException {
		homePage hp = new homePage(driver);
		hp.getContactsLink().click();
		contactsPage cp = new contactsPage(driver);
		cp.getContactLookUpImage().click();
		createContactPage ccp = new createContactPage(driver);
		excelFileUtility eutil = new excelFileUtility();
		String LASTNAME = eutil.toReadTheDataFromExcelFile("Contacts", 1, 2);
		ccp.getLastNameTextField().sendKeys(LASTNAME);
		ccp.getSaveButton().click();
		contactsInformationpage cip = new contactsInformationpage(driver);
		String name = cip.getHeaderText().getText();
		//failed test script
//		Assert.fail(); 
//		if(name.contains(LASTNAME)) {
//			Reporter.log(name+"--passed--", true);
//		}
//		else {
//			Reporter.log(name+"--failed--", true);
//		}
		Assert.assertTrue(name.contains(LASTNAME));

	}
	
	
	
}
