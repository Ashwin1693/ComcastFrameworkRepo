package com.comcast.crm.generic.listenerUtility;

import java.util.Date;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.testng.ISuite;
import org.testng.ISuiteListener;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import org.testng.Reporter;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
import com.comcast.crm.generic.WebDriverUtility.UtilityClassObject;

public class ListenerImpClass implements ITestListener, ISuiteListener {

	public static ExtentTest test;
	public ExtentReports reports;

//We have implemented this method to configure the report
	@Override
	public void onStart(ISuite suite) {
		System.out.println("Report Configuration");
		String time = new Date().toString().replace(" ", "_").replace(":", "_");

		//ISuiteListener.super.onStart(suite);
		// Spark report config

		ExtentSparkReporter spark = new ExtentSparkReporter("./AdvanceReport/report_"+time+".html");
		spark.config().setDocumentTitle("CRM Test Suite Result");
		spark.config().setReportName("CRM Report");
		spark.config().setTheme(Theme.STANDARD);

		// add Environment info and create test
		reports = new ExtentReports();
		reports.attachReporter(spark);
		reports.setSystemInfo("OS", "Windows-11");
		reports.setSystemInfo("BROWSER", "Chrome-11");
		Reporter.log("onStart of Suite",true);
	}

	@Override
	public void onFinish(ISuite suite) {
		System.out.println("Report Back Up");
		Reporter.log("onFinish of Suite",true);
		reports.flush();
	}

//To create a test case inside the extentReport
	@Override
	public void onTestStart(ITestResult result) {
		
		test = reports.createTest(result.getMethod().getMethodName());
		test.log(Status.INFO,"==========="+result.getMethod().getMethodName()+"========STARTED========");
		Reporter.log("onTestStar of ITestresult",true);
		UtilityClassObject.setTest(test);
	}

	@Override
	public void onTestSuccess(ITestResult result) {
		System.out.println(result.getMethod().getMethodName() + "======success======");
		test.log(Status.PASS,"============="+ result.getMethod().getMethodName()+"======> COMPLETED <======");
		Reporter.log("onTestStar of ITestresult",true);
	}

	@Override
	public void onTestFailure(ITestResult result) {
		String TestName = result.getMethod().getMethodName();
		TakesScreenshot eDriver = (TakesScreenshot) UtilityClassObject.getDriver();
		String FilePath = eDriver.getScreenshotAs(OutputType.BASE64);
		
		String time = new Date().toString().replace(" ", "_").replace(":", "_");
		test.addScreenCaptureFromBase64String(FilePath,TestName+ time);
		test.log(Status.FAIL,"=========="+ result.getMethod().getMethodName()+"======> FAILED <======");
		Reporter.log("onTestStar of ITestresult",true);
	}

	@Override
	public void onTestSkipped(ITestResult result) {
		// TODO Auto-generated method stub
		Reporter.log("onTest Skipped of ITestresult",true);
	}

	@Override
	public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
		// TODO Auto-generated method stub
		Reporter.log("onTestFailedButWithinSuccessPercentage of ITestresult",true);
	}

	@Override
	public void onTestFailedWithTimeout(ITestResult result) {
		// TODO Auto-generated method stub
		Reporter.log("onTestFailedWithTimeout of ITestresult",true);
	}

	@Override
	public void onStart(ITestContext context) {
		// TODO Auto-generated method stub
		Reporter.log("onStart of ITestContext ",true);
	}

	@Override
	public void onFinish(ITestContext context) {
		// TODO Auto-generated method stub
		Reporter.log("onStart of ITestContext ",true);
	}

}
