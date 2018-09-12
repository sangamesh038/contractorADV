package pageObject;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

import testBase.GenericClass;

public class VendorSearchPage {
  private WebDriver driver;
	// private static WebDriver driver;
	//Actions actions= new Actions(driver);
	@FindBy(name="custVendorNum")
	private WebElement vendorAccount;
	
	@FindBy(id="vendorName")
	private WebElement vendorName;
	
	@FindBy(name="firstName")
	private WebElement contactFirstName;
	
	@FindBy(name="lastName")
	private WebElement contactLastName;
	
	@FindBy(name="vendorStatus")
	private WebElement vendorStatus;
	
	@FindBy(xpath="//input[@value='Search']")
	private WebElement search;
	
	@FindBy(xpath="//input[@value='Clear']")
	private WebElement clear;
	
	@FindBy(xpath="//b[text()='Vendor Search :  ABC Company']")
	private WebElement vendorSearchPageHeading;
	
	@FindBy(linkText="ABC CONTRACTOR")
	private WebElement abcContractor;
	
	@FindBy(linkText="> People")
	private WebElement peopleTab;
	
	@FindBy(linkText="Search People ")
	private WebElement searchPeople;
	
	@FindBy(linkText="Add New Person")
	private WebElement addNewPerson;
	
	@FindBy(linkText="People Batch Load")
	private WebElement peopleBatchLoad;
	
	
	public VendorSearchPage(WebDriver driver){
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	
	public void clickonABCContractor(){
		Select select = new Select(vendorStatus);
		select.selectByValue("ACTIVE");
		search.click();
		GenericClass.elementTobeClickable(driver, 30, abcContractor);
		abcContractor.click();
		}
	
	public void clickOnAddNewPerson(){
		Actions actions = new Actions(driver);
		actions.moveToElement(peopleTab).perform();
		GenericClass.elementTobeVisible(driver, 30, addNewPerson);
		addNewPerson.click();
	}
	
	public void clickOnSearchPeople(){
		Actions actions = new Actions(driver);
		actions.moveToElement(peopleTab).perform();
		searchPeople.click();
	}
	
	public void clickOnPeopleBatchLoad(){
		Actions actions = new Actions(driver);
		actions.moveToElement(peopleTab).perform();
		peopleBatchLoad.click();
	}
	
}
