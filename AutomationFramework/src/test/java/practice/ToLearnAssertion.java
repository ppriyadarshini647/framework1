package practice;

import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

public class ToLearnAssertion 
{
	@Test
	public void sample() {
		System.out.println("--Step1--");
		System.out.println("--Step2--");
		
		//Validation using Hard Assert
		Assert.assertEquals(false, false);
		
		//Validation using Soft Assert
		SoftAssert sa = new SoftAssert();
		sa.assertEquals(false, true);
		
		System.out.println("--Step3--");
		System.out.println("--Step4--");
		
		sa.assertAll();
	}

}
