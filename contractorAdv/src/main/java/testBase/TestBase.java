package testBase;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Method;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

public abstract class TestBase implements IAutoConst {
	public static final Logger log = Logger.getLogger(TestBase.class.getName());

	public static WebDriver driver;
	public static ExtentReports extent;
	public static ExtentTest test;
	public ITestResult result;
	GenericClass getdata;

	static {
		Calendar calendar = Calendar.getInstance();
		SimpleDateFormat formater = new SimpleDateFormat("dd_MM_yyyy_hh_mm_ss");
		extent = new ExtentReports("./src/main/java/reports/report " + formater.format(calendar.getTime()) + ".html", true);
	}
    
	@BeforeMethod
	public void openApplication() throws IOException {
		getdata = new GenericClass();
		String browserName = getdata.readPropertyData("browser");
		String URL = getdata.readPropertyData("url");

		if (browserName.equalsIgnoreCase("chrome")) {
			System.setProperty(CHROME_KEY, CHROME_VALUE);
			log.info("driver is initializing");
			driver = new ChromeDriver();
		}

		else if (browserName.equalsIgnoreCase("firefox")) {
			System.setProperty(GECKO_KEY, GECKO_VALUE);
			driver = new FirefoxDriver();
			}
		
			driver.manage().window().maximize();
			driver.manage().deleteAllCookies();
			driver.manage().timeouts().pageLoadTimeout(60, TimeUnit.SECONDS);
			driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
			driver.get(URL);
	}
	
	public String getScreenshot(String result) throws IOException {

		File src = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
		Calendar calendar = Calendar.getInstance();
		SimpleDateFormat formater = new SimpleDateFormat("dd_MM_yyyy_hh_mm_ss");
		File dest = new File("./src/main/java/screenshots/" + result + formater.format(calendar.getTime()) + ".png");
		FileUtils.copyFile(src, dest);
		return dest.getAbsolutePath();

	}

	public void getResult(ITestResult result) throws IOException {

		if (result.getStatus() == ITestResult.SUCCESS) {
			test.log(LogStatus.PASS, result.getName() + "test is pass");
		} else if (result.getStatus() == ITestResult.SKIP) {
			test.log(LogStatus.SKIP, result.getName() + "test is skipped and skip reason is:-" + result.getThrowable());
		} else if (result.getStatus() == ITestResult.FAILURE) {
			test.log(LogStatus.FAIL, result.getName() + "Test is failed and failed reason is:-" + result.getThrowable());
			String screen = getScreenshot(result.getName());
			test.log(LogStatus.FAIL, test.addScreenCapture(screen));
		} else if (result.getStatus() == ITestResult.STARTED) {
			test.log(LogStatus.INFO, result.getName() + "test is started");
		}
	}

	@BeforeMethod
	public void beforeMethod(Method result) {
		test = extent.startTest(result.getName());
		test.log(LogStatus.INFO, result.getName() + "test started");
	}
    @AfterMethod
    public void logout(){
    	WebElement logOut = driver.findElement(By.linkText("Log Out"));
    	logOut.click();
    	Alert  alert = driver.switchTo().alert();
    	alert.accept();
    }
	@AfterMethod
	public void afterMethod(ITestResult result) throws IOException {
		getResult(result);
	}
    @BeforeClass
    public void startTest(){
    	log.info("before class is starting");
    }
	@AfterClass(alwaysRun = true)
	public void endTest() {
		driver.quit();
		extent.endTest(test);
		extent.flush();
	} 

}
