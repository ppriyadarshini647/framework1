package genericUtility;

import java.util.Date;
import java.util.Random;

/**
 * This methods is used to get the random number and to get system date and time
 */
public class JavaUtility 
{
	/**
	 * This methods is used to generate random numbers
	 * @return
	 */
	public int toGetRandomNumber() {
		Random r = new Random();
		int value = r.nextInt(10000);
		return value;
	}
	
	/**
	 * This methods is used to get system date and time in format
	 * @return
	 */
	
	public String toGetSystemDataAndTime() {
		Date d = new Date();
		String date[]=d.toString().split(" ");
		String day = date[0];
		String month = date[1];
		String date1 = date[2];
		String time = date[3].replace(":", "-");
		String year = date[5];
		String finalDate = day+" "+month+" "+date1+" "+time+" "+year;
		return finalDate;
		
	}

}
