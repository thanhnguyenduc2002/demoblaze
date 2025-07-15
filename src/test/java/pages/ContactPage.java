package pages;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.time.Duration;

import org.apache.commons.lang3.RandomStringUtils;
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

public class ContactPage {
	public WebDriver driver;
	public XSSFWorkbook workbook;
	public XSSFSheet sheet;
	public XSSFRow row;
	public XSSFCell cell;
	public ContactPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(xpath = "//a[normalize-space()='Contact']")
    WebElement menuContact;
	
	@FindBy(xpath = "//*[@id=\"recipient-email\"]")
	WebElement contactEmail;
	

	@FindBy(xpath = "//*[@id=\"recipient-name\"]")
	WebElement contactName;
	
	@FindBy(xpath = "//*[@id=\"message-text\"]")
	WebElement contactMessage;
	

	@FindBy(xpath = "//button[@onclick='send()']")
    WebElement sendMessage;
	
	String path = "D:\\DucThanh\\demoblaze\\testdata\\DataContact.xlsx";
	public void writeDataInExcel() throws IOException {
		String contactEmail = RandomStringUtils.randomAlphanumeric(10) + "@gmail.com";
		String contactName = RandomStringUtils.randomAlphabetic(6);
		String message = RandomStringUtils.randomAlphabetic(15);
		FileInputStream input = new FileInputStream(path);
		workbook = new XSSFWorkbook(input);
		sheet = workbook.getSheet("Sheet1");
		int rows = sheet.getLastRowNum();
		XSSFRow header = sheet.createRow(0);
		header.createCell(0).setCellValue("Contact Email:");
		header.createCell(1).setCellValue("Contact Name");
		header.createCell(2).setCellValue("Message");
		for(int r = 0; r < 1; r++) {
			row = sheet.createRow(r+rows+1);
			row.createCell(0).setCellValue(contactEmail);
			row.createCell(1).setCellValue(contactName);
			row.createCell(2).setCellValue(message);
		}
		input.close();
		FileOutputStream output = new FileOutputStream(path);
		workbook.write(output);
		output.close();
		workbook.close();
	}
	
	public Object[][] readDataInExcel() throws IOException{
		FileInputStream input = new FileInputStream(path);
		workbook = new XSSFWorkbook(input);
		sheet = workbook.getSheet("Sheet1");
		int rows = sheet.getLastRowNum();
		Object[][]data = new Object[rows][3];
		for(int r = 1; r <= rows; r++) {
			data[r-1][0] = row.getCell(0).getStringCellValue();
			data[r-1][1] = row.getCell(1).getStringCellValue();
			data[r-1][2] = row.getCell(2).getStringCellValue();
		}
		 workbook.close();
		 input.close();
		 return data;
	}
	
	public void contact(String email, String name, String message) {
		menuContact.click();
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        wait.until(ExpectedConditions.visibilityOf( contactEmail));
        contactEmail.sendKeys(email);
        contactName.sendKeys(name);  
        contactMessage.sendKeys(message); 
        sendMessage.click();
	}
	
	public String popUpSuccess () {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
		wait.until(ExpectedConditions.alertIsPresent());
		Alert alert = driver.switchTo().alert();
		String alertText = alert.getText();
		return alertText;
	}
	
}
