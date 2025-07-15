package pages;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.time.Duration;
import java.util.Random;

import org.apache.commons.lang3.RandomStringUtils;
import org.apache.poi.hssf.record.chart.ObjectLinkRecord;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class AboutUsPage {
	public WebDriver driver;
	public AboutUsPage (WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
}

	@FindBy(xpath = "//a[normalize-space()='About us']")
    WebElement menuAboutUs;
	
	@FindBy(xpath = "//*[@id=\"videoModal\"]/div/div/div[1]/button")
	WebElement closedIcon;

	@FindBy(xpath = "//button[@data-dismiss='modal' and contains(text(),'Close')]")
	WebElement closedButton;
	public void clickAboutUs() {
		menuAboutUs.click();
	}
	
	public boolean closeAboutUsPopup() {
		   WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		   wait.until(ExpectedConditions.elementToBeClickable(closedIcon));
		   closedIcon.click();
		   boolean status = wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//*[@id=\"videoModal\"]/div/div")));  
		   return status;
	}
	
	
}
