package practice;

import java.io.FileInputStream;
import java.io.IOException;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

public class ToReadDataFromExceldataDrivenTesting {

	public static void main(String[] args) throws EncryptedDocumentException, IOException 
	{
		//STEP1 : Create an object of FileInputStream
		FileInputStream fis = new FileInputStream(".\\src\\test\\resources\\TestDataAdvanceBatch.xlsx");
		
		//STEP2 : Create an object of excel file
		Workbook wb = WorkbookFactory.create(fis);
		
		//STEP3 : Call Methods
		String lastname = wb.getSheet("Contacts").getRow(1).getCell(2).toString();
		System.out.println(lastname);
		
		String orgName = wb.getSheet("Contacts").getRow(4).getCell(3).toString();
		System.out.println(orgName);

	}

}
