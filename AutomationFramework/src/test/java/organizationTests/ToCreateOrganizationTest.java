package organizationTests;

import java.io.IOException;
import java.util.Random;

import org.apache.poi.EncryptedDocumentException;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import genericUtility.BaseClass;
import genericUtility.excelFileUtility; 
import objectRepository.createOrganizationPage;
import objectRepository.homePage;
import objectRepository.organizationInformationPage;
import objectRepository.organizationPage;
@Listeners(genericUtility.ListenersImplemantation.class)
public class ToCreateOrganizationTest extends BaseClass
{
	@Test
	public void toCreateOrg_002 () throws EncryptedDocumentException, IOException 
	{
		homePage hp = new homePage(driver);
		hp.getOrganizationsLink().click();
		organizationPage op = new organizationPage(driver);
		op.getOrganizationLookUpImage().click();
		createOrganizationPage cop = new createOrganizationPage(driver);
		excelFileUtility eutil = new excelFileUtility();
		String OrgName = eutil.toReadTheDataFromExcelFile("Organizations", 1, 2);
		Random r = new Random();
		int random = r.nextInt(1000);
		cop.getOrganizationNameTextField().sendKeys(OrgName+random);
		//Failed
//		Assert.fail();
		cop.getSaveButton().click();
		organizationInformationPage oip = new organizationInformationPage(driver);
		String ORGANIZATIONName = oip.getOrgaHeaderText().getText();
//		if(ORGANIZATIONName.contains(OrgName))
//		{
//			Reporter.log(ORGANIZATIONName+random+"---passed---", true);
//		}
//		else {
//			Reporter.log(ORGANIZATIONName+random+"---failed---", true);
//		}
		Assert.assertTrue(ORGANIZATIONName.contains(OrgName));

		
	}

}
