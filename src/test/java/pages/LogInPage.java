package pages;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Path;
import java.time.Duration;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import net.bytebuddy.asm.Advice.This;

public class LogInPage {
	public WebDriver driver;
	public XSSFWorkbook workbook;
	public XSSFSheet  sheet;
	public XSSFRow row;
	public XSSFCell cell;
	String path = "D:\\DucThanh\\demoblaze\\testdata\\Data.xlsx";
	public LogInPage (WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver	, this);
	}
	
	@FindBy(xpath = "//a[@id='login2']")
	WebElement menuLogin; 
	
	@FindBy(xpath = "//*[@id=\"loginusername\"]")
	WebElement usernameLogin; 
	
	@FindBy(xpath = "//*[@id=\"loginpassword\"]")
	WebElement passwordLogin; 
	
	@FindBy(xpath = "//button[@onclick='logIn()']")
	WebElement loginButton; 
	
	@FindBy(xpath = "//a[@id='logout2']")
	WebElement menuLogOut; 
	public void clickMenuLogIn() {
		menuLogin.click();
	}
	
	public Object[][] readDataInExcel() throws IOException {
		FileInputStream input = new FileInputStream(path);
		workbook = new XSSFWorkbook(input);
		sheet = workbook.getSheet("Sheet1");
		int rows = sheet.getLastRowNum();
		Object[][] data = new Object[rows][2];
		for(int r = 1; r <= rows; r++ ) {
			row = sheet.getRow(r);
			data[r-1][0] = row.getCell(0).getStringCellValue();
			data[r-1][1] = row.getCell(1).getStringCellValue();
		}
		workbook.close();
		input.close();
		return data;
	}
	
    public void login(String username, String password) {
        menuLogin.click();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        wait.until(ExpectedConditions.visibilityOf(usernameLogin));
        usernameLogin.clear();
        usernameLogin.sendKeys(username);
        passwordLogin.clear();
        passwordLogin.sendKeys(password);
        loginButton.click();
    }
    
    public boolean loginSuccess() {
    	WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    	WebElement LogOut =  wait.until(ExpectedConditions.visibilityOf(menuLogOut));
    	return LogOut.isDisplayed();
    }
    public void Logout() {
    	 WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
         wait.until(ExpectedConditions.visibilityOf(menuLogOut));
         menuLogOut.click();
    }
}
