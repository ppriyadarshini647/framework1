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

public class ToCreateOrgwithIndustryDropdownTest extends BaseClass
{
	@Test(groups = "regression")
	public void tocreateOrgwithIndustry_004() throws EncryptedDocumentException, IOException 
	{
		homePage hp = new homePage(driver);
		hp.getOrganizationsLink().click();
		organizationPage op = new organizationPage(driver);
		op.getOrganizationLookUpImage().click();
		createOrganizationPage cop = new createOrganizationPage(driver);
		excelFileUtility eutil = new excelFileUtility();
		String OrgName = eutil.toReadTheDataFromExcelFile("Organizations", 4, 2);
		Random r = new Random();
		int random = r.nextInt(1000);
		cop.getOrganizationNameTextField().sendKeys(OrgName+random);
		String IndDropdown = eutil.toReadTheDataFromExcelFile("Organizations", 4, 3);
		cop.getIndustryDropDown().sendKeys(IndDropdown);
		cop.getSaveButton().click();
		organizationInformationPage oip = new organizationInformationPage(driver);
		String ORGNAMETEXT = oip.getOrgaHeaderText().getText();
		Assert.assertEquals(ORGNAMETEXT.contains(OrgName), true);
	}
	}

