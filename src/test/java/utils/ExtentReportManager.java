package utils;

import java.awt.Desktop;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentReporter;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

import base.BaseTest;
import freemarker.template.SimpleDate;


public class ExtentReportManager implements ITestListener{
	public ExtentSparkReporter sparkReporter;
	public ExtentReports reports;
	public ExtentTest test;
	String reportName;
	public void onStart(ITestContext testContext) {
		String timestamp = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date());
		reportName = "Test-Report-" + timestamp + ".html";
		sparkReporter = new ExtentSparkReporter(".\\reports\\"+reportName);
		sparkReporter.config().setDocumentTitle("Demo Blaze Report");
		sparkReporter.config().setReportName("Automation Report");
		sparkReporter.config().setTheme(Theme.DARK);
		reports = new ExtentReports();
		reports.attachReporter(sparkReporter);
		reports.setSystemInfo("Application", "DemoBlaze");
		reports.setSystemInfo("username", "Nguyen Duc Thanh");
		String os = testContext.getCurrentXmlTest().getParameter("os");
		reports.setSystemInfo("Operating System", os);
		reports.setSystemInfo("Enviroment", "QA");	
	}
	
	public void onTestSuccess(ITestResult result) {
		test = reports.createTest(result.getTestClass().getName());
		test.assignCategory(result.getMethod().getGroups());
		test.log(Status.INFO, result.getName() + " PASSED");
	}
	
	public void onTestFailure(ITestResult result) {
		test = reports.createTest(result.getTestClass().getName());
		test.assignCategory(result.getMethod().getGroups());
		test.log(Status.FAIL, result.getName() + " FAILED");
		test.log(Status.INFO, result.getThrowable().getMessage());
		try {
			String imgPath = new BaseTest().captureScreen(result.getName());
			test.addScreenCaptureFromPath(imgPath);
		}catch (Exception e1) {
			e1.printStackTrace();
		}
	}
	
	public void onTestSkipped(ITestResult result) {
		test = reports.createTest(result.getTestClass().getName());
		test.assignCategory(result.getMethod().getGroups());
		test.log(Status.SKIP, result.getName() + " SKIPPED");
		test.log(Status.INFO, result.getThrowable().getMessage());
	}
	
	public void onFinish(ITestContext testContext) {
		reports.flush();
		String reportPath = "D:\\DucThanh\\demoblaze\\reports\\"+reportName;
		File extentReport = new File(reportPath);
		try {
			Desktop.getDesktop().browse(extentReport.toURI());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
