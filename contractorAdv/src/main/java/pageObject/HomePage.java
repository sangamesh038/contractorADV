package pageObject;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

import testBase.GenericClass;

public class HomePage {
	private WebDriver driver;
	@FindBy(xpath="//select[@name='orgId']")
	private WebElement organigationNameDropDown;
	
	@FindBy(linkText="> Vendor")
	private WebElement vendorTab;
	
	@FindBy(linkText="Search Vendor")
	private WebElement searchVendor;
	
	@FindBy(linkText="Log Out")
	private WebElement logout;
	
	public HomePage(WebDriver driver){
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	
	
	public void selectABCCompany(){
		GenericClass.elementTobeVisible(driver, 30, organigationNameDropDown);
		Select select = new Select(organigationNameDropDown);
		select.selectByVisibleText("ABC Company");
		
	}
	
	public void clickOnSearchVendor(){
		Actions actions = new Actions(driver);
		GenericClass.elementTobeVisible(driver, 30, vendorTab);
		actions.moveToElement(vendorTab).perform();
		GenericClass.elementTobeClickable(driver, 30, searchVendor);
		searchVendor.click();
		
	}
	
	
	
	
	
	

}
