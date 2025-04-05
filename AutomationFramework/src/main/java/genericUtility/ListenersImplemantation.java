package genericUtility;

import java.io.IOException;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

public class ListenersImplemantation implements ITestListener
{
	ExtentReports report;

	@Override
	public void onTestStart(ITestResult result) {
		String methodname = result.getMethod().getMethodName();
		System.out.println(methodname+"---Started--");
		report.createTest(methodname); //additional
		
		
	}

	@Override
	public void onTestSuccess(ITestResult result) {
		String methodname = result.getMethod().getMethodName();
		System.out.println(methodname+"---Passed--");
	}

	@Override
	public void onTestFailure(ITestResult result) {
		String methodname = result.getMethod().getMethodName();
		System.out.println(methodname+"---Failed--");
		
		WebDriverUtility wutil = new WebDriverUtility();
		JavaUtility jutil = new JavaUtility();
		String screenshotname = methodname+"-"+jutil.toGetSystemDataAndTime();
		try {
			wutil.toTakeScreenshot(BaseClass.sdriver, screenshotname);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	@Override
	public void onTestSkipped(ITestResult result) {
		String methodname = result.getMethod().getMethodName();
		System.out.println(methodname+"---Skipped--");
		
	}

	@Override
	public void onStart(ITestContext context) {
		System.out.println("---Test Execution started---");
		


	
	//ExtentSparkReporter
	ExtentSparkReporter htmlReport = new ExtentSparkReporter("./ExtendReports/Reports-"+new JavaUtility().toGetSystemDataAndTime()+".html");
	htmlReport.config().setDocumentTitle("Vtiger Execution Report");
	htmlReport.config().setTheme(Theme.DARK);
	htmlReport.config().setReportName("VTIGER EXECUTION REPORT");

	report = new ExtentReports();
	report.attachReporter(htmlReport);
	report.setSystemInfo("BaseUrl", "http://localhost:8888/");
	report.setSystemInfo("Username", "admin");
	report.setSystemInfo("Password", "123");
	report.setSystemInfo("Reporter Name", "Prakruti");
    }

	@Override
	public void onFinish(ITestContext context) {
		System.out.println("---Test Execution finished---");
		report.flush(); 
		
	}
	

}
