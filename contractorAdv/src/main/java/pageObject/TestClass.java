package pageObject;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;

import testBase.GenericClass;



public class TestClass {
	
	public static void main (String [] args) {
		// TODO Auto-generated method stub

	
	System.setProperty("webdriver.chrome.driver", "./driver/chromedriver.exe");
	
	WebDriver driver = new ChromeDriver();
	driver.manage().window().maximize();
	driver.get("https://ca.fadv.com/CA/welcome.do?fadv");
	driver.findElement(By.id("userLoginIdToShow")).sendKeys("swamysan");
	driver.findElement(By.id("passwordToShow")).sendKeys("August@2018");
	driver.findElement(By.xpath("//a[@class='login_button_down']")).click();
	WebElement agr = driver.findElement(By.xpath("//input[@value='ACCEPT FCRA AGREEMENT']"));
	GenericClass.elementTobeClickable(driver, 30, agr );
	
	driver.findElement(By.xpath("//input[@value='ACCEPT FCRA AGREEMENT']")).click();
	//GenericClass.elementTobeClickable(agreement);
	 WebElement org = driver.findElement(By.xpath("//select[@name='orgId']"));
	Select select = new Select(org);
	select.selectByVisibleText("ABC Company");
	
	
	
	
	}
	

}
