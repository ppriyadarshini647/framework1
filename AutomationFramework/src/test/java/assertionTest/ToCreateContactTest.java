package assertionTest;

import java.io.IOException;

import org.apache.poi.EncryptedDocumentException;
import org.testng.Assert;
import org.testng.annotations.Test;

import genericUtility.BaseClass;
import genericUtility.excelFileUtility;
import objectRepository.contactsInformationpage;
import objectRepository.contactsPage;
import objectRepository.createContactPage;
import objectRepository.homePage;

public class ToCreateContactTest extends BaseClass
{
	@Test(groups = "smoke")
	public void toCreateContactWithAssert() throws EncryptedDocumentException, IOException 
	{
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
//		Assert.assertEquals(name.contains(LASTNAME), true);
		Assert.assertTrue(name.contains(LASTNAME));

}
}

