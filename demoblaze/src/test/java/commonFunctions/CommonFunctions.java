package commonFunctions;

import java.io.FileInputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

import org.apache.logging.log4j.LogManager; //logger import
import org.apache.logging.log4j.Logger;

public class CommonFunctions {
	public static Properties properties = null;
	public static WebDriver driver;
	public static ExtentReports extentReport;
	public static ExtentSparkReporter htmlReporter;
	public static ExtentTest test;
    public static Date date = new Date();
    public static SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy HH-mm-ss");
    public static String dt = formatter.format(date);
    public static String reportDestination = "reports/report_" + dt + ".html";
	
	  Logger logger = LogManager.getLogger(CommonFunctions.class);
	
	//For loading Property file from config.properties file
	public Properties loadPropertyFile() throws IOException {
		FileInputStream fs = new FileInputStream("D:\\Studies\\eclipse-workspace\\demoblaze\\config\\config.properties");
		properties = new Properties();
		properties.load(fs);
		return properties;
	} 
	
	@BeforeSuite (alwaysRun = true)
	public void setup() throws IOException {
		
		logger.info("Loading the Property file");
		loadPropertyFile();
		extentReportSpark();
		launchBrowser();
		
	}
	public void launchBrowser() throws IOException {
		
		String browser=properties.getProperty("browser");
		String url = properties.getProperty("url");
		String driverLoc = properties.getProperty("driverLoc");
		if(browser.equalsIgnoreCase("chrome")) {
			System.setProperty("webdriver.chrome.driver",driverLoc);
			logger.info("Launching Chrome");
			driver = new ChromeDriver();
			driver.manage().window().maximize();
			driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		} else if(browser.equalsIgnoreCase("firefox")) {
			System.setProperty("webdriver.gecko.driver",driverLoc);
			logger.info("Launching Firefox");
			driver = new FirefoxDriver();
			driver.manage().window().maximize();
			driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		}
		logger.info("Navigating to the application");
		driver.get(url);
		
	}
	
	public void extentReportSpark() {
		
		htmlReporter = new ExtentSparkReporter(reportDestination);
		extentReport = new ExtentReports();
		extentReport.attachReporter(htmlReporter);
		
		extentReport.setSystemInfo("OS", System.getProperty("os.name"));
		extentReport.setSystemInfo("Browser Name", properties.getProperty("browser"));
		extentReport.setSystemInfo("Environment", properties.getProperty("Environment"));
		extentReport.setSystemInfo("Browser Name", properties.getProperty("browser"));
		extentReport.setSystemInfo("User Name", properties.getProperty("username"));
		
		htmlReporter.config().setDocumentTitle("My First POM Framework Project");
		htmlReporter.config().setReportName("Sample Automation Test Suite");
		htmlReporter.config().setTimelineEnabled(Boolean.TRUE);
		htmlReporter.config().setOfflineMode(Boolean.TRUE);
		htmlReporter.config().setTheme(Theme.STANDARD);
		htmlReporter.config().setTimeStampFormat("EEEE, MMMM dd, yyyy, hh:mm a '('zzz')'");
		htmlReporter.config().setTimelineEnabled(Boolean.TRUE);
	}
	
	
	@AfterSuite (alwaysRun = true)
	public void tearDown() {
		logger.info("Closing the Browser");
		driver.quit();
		extentReport.flush();
		
	}

}
