package assertionTest;

import java.io.IOException;
import java.util.Random;

import org.apache.poi.EncryptedDocumentException;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.Test;

import genericUtility.BaseClass;
import genericUtility.excelFileUtility;
import objectRepository.createOrganizationPage;
import objectRepository.homePage;
import objectRepository.organizationInformationPage;
import objectRepository.organizationPage;

public class ToCreateOrgWithIndandTypeTest extends BaseClass
{
	@Test(groups = "regression")
	public void toCreateOrgwithIndustryandType_003() throws EncryptedDocumentException, IOException 
	{
		homePage hp = new homePage(driver);
		hp.getOrganizationsLink().click();
		organizationPage op = new organizationPage(driver);
		op.getOrganizationLookUpImage().click();
		createOrganizationPage cop = new createOrganizationPage(driver);
		excelFileUtility eutil = new excelFileUtility();
		Random r = new Random();
		int random = r.nextInt();
		String ORGNAME = eutil.toReadTheDataFromExcelFile("Organizations", 7, 2);
		cop.getOrganizationNameTextField().sendKeys(ORGNAME+random);
		String Industry = eutil.toReadTheDataFromExcelFile("Organizations", 7, 3);
		cop.getIndustryDropDown().sendKeys(Industry);
		String Type = eutil.toReadTheDataFromExcelFile("Organizations", 7, 4);
		cop.getTypeDropDown().sendKeys(Type);
		cop.getSaveButton().click();
		organizationInformationPage oip = new organizationInformationPage(driver);
		String organizationName = oip.getOrgaHeaderText().getText();
		Assert.assertEquals(organizationName.contains(ORGNAME), true);

}
}

