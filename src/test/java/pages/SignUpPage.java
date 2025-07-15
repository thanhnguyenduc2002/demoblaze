package pages;

import java.awt.im.InputContext;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.time.Duration;

import org.apache.commons.lang3.RandomStringUtils;
import org.apache.poi.sl.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.Alert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.google.common.collect.Table.Cell;

import base.BaseTest;
import io.reactivex.rxjava3.schedulers.SchedulerRunnableIntrospection;

public class SignUpPage {
	public WebDriver driver;
	public XSSFWorkbook workbook ;
	public XSSFSheet sheet;
	public XSSFRow row;
	public XSSFCell cell;
	
	public SignUpPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(xpath = "//a[@id='signin2']")
    WebElement menuSignUp;
	
	@FindBy(xpath = "//*[@id=\"sign-username\"]")
    WebElement usernameSignUp;
	
	@FindBy(xpath = "//*[@id=\"sign-password\"]")
    WebElement passwordSignUp;
	
	@FindBy(xpath = "//button[@onclick='register()']")
    WebElement signUp;
	
	public void clickMenuSignUp() {
		menuSignUp.click();
	}

	String path = "D:\\DucThanh\\demoblaze\\testdata\\Data.xlsx";
	public void writeDataInExcel() throws IOException {
		String username = RandomStringUtils.randomAlphabetic(10);
		String password = RandomStringUtils.randomNumeric(8);
		FileInputStream input = new FileInputStream(path);
		workbook = new XSSFWorkbook(input);
		sheet = workbook.getSheet("Sheet1");
		int rows = sheet.getLastRowNum();
//		int cells = sheet.getRow(1).getLastCellNum();	
		XSSFRow header = sheet.createRow(0);
		header.createCell(0).setCellValue("Username");
		header.createCell(1).setCellValue("Password");
		for(int r = 0; r < 1 ; r++) {
			row = sheet.createRow(r+1+rows);
			row.createCell(0).setCellValue(username);
	        row.createCell(1).setCellValue(password);
		}
		input.close();
		FileOutputStream output = new FileOutputStream(path);
		workbook.write(output);
		output.close();
		workbook.close();
	}
	
	public Object[][] readDataInExcel() throws IOException {
		FileInputStream input = new FileInputStream(path);
		workbook = new XSSFWorkbook(input);
		sheet = workbook.getSheet("Sheet1");
		int rows = sheet.getLastRowNum();	
		Object[][] data = new Object[rows][2];
		for(int r = 1; r <= rows ; r++) {
			row = sheet.getRow(r);
			data[r - 1][0] = row.getCell(0).getStringCellValue();
			data[r - 1][1] = row.getCell(1).getStringCellValue();
		}
		  workbook.close();
		  input.close();
		  return data;
		  
	}
		
	public void signUp(String username, String password) {
		menuSignUp.click();
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        wait.until(ExpectedConditions.visibilityOf(usernameSignUp));
        usernameSignUp.sendKeys(username);
        passwordSignUp.sendKeys(password);  
        signUp.click();
	}
	
	public String popUpSuccess () {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
		wait.until(ExpectedConditions.alertIsPresent());
		Alert alert = driver.switchTo().alert();
		String alertText = alert.getText();
		return alertText;
	}
}
