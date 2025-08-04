package base;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.lang.System.Logger;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;
import java.util.Properties;
import org.apache.logging.log4j.LogManager;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.Platform;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openxmlformats.schemas.drawingml.x2006.chart.CTPageSetup;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;

	


public class BaseTest {
public static WebDriver driver;
public Properties p;

public org.apache.logging.log4j.Logger logger = LogManager.getLogger(BaseTest.class);
@Parameters({"os", "browser"})
@BeforeMethod(groups = {"ui", "sanity", "smoke","functional", "critical","cart", "regression","signup","contact","login","aboutus"})
	public void setup(String os,String browser) throws IOException {
	logger.info("===== Starting test setup =====");
	FileReader file = new FileReader("D:\\DucThanh\\demoblaze\\src\\test\\resources\\config.properties");
		p = new Properties(); 
		p.load(file);
		if(p.getProperty("execution_env").equalsIgnoreCase("remote")) {
			DesiredCapabilities capabilities = new DesiredCapabilities();
			if(os.equalsIgnoreCase("windows")) {
				capabilities.setPlatform(Platform.WIN11);
			}else if (os.equalsIgnoreCase("mac")) {
				capabilities.setPlatform(Platform.MAC);
			}else {
				System.out.println("No matching os");
				return;
			}
			switch (browser.toLowerCase()) {
			case "chrome": capabilities.setBrowserName("chrome");break;
			case "edge": capabilities.setBrowserName("MicrosoftEdge");break;
			default: System.out.println("No matching"); return;
			}
			driver = new RemoteWebDriver(new URL("http://localhost:4444/wd/hub"), capabilities);
		}
		
		if(p.getProperty("execution_env").equalsIgnoreCase("local")) {
			String br = p.getProperty("browser");
        	 if (br == null || br.equalsIgnoreCase("chrome")) {
                 driver = new ChromeDriver();
                 logger.info("ChromeDriver initialized.");
             } else if (br.equalsIgnoreCase("edge")) {
                 driver = new EdgeDriver();
                 logger.info("EdgeDriver initialized."); 
             } else {
                 System.out.println("Invalid browser specified in config.properties.");
                 logger.warn("Invalid browser specified, defaulting to Chrome."); 
                 driver = new ChromeDriver();
                 logger.info("ChromeDriver initialized (default)."); 
             }
        }
		driver.manage().deleteAllCookies();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
		driver.get("https://www.demoblaze.com/index.html");
		driver.manage().window().maximize();
	}
	
@AfterMethod(groups = {"ui", "sanity", "smoke","functional", "critical","cart", "regression","signup","contact","login","aboutus"})
	public void tearDown() {
		logger.info("===== Starting test teardown =====");
		driver.quit();
		logger.info("===== Teardown complete =====");
	}


	public String captureScreen(String name) {
		String timeStamp = new SimpleDateFormat("yyyyMMddHHmmss").format(new Date());
	    TakesScreenshot takesScreenshot = (TakesScreenshot) driver;
	    File sourceFile = takesScreenshot.getScreenshotAs(OutputType.FILE);
	    String targetFilePath = System.getProperty("user.dir") + "\\screenshots\\" + name + "_" + timeStamp + ".png";
	    File targetFile = new File(targetFilePath);
	      try {
	          Files.copy(sourceFile.toPath(), targetFile.toPath(), StandardCopyOption.REPLACE_EXISTING);
	      }catch (IOException e) {
	          e.printStackTrace();
	      }
	      	return targetFilePath;
	}
}