package pageObject;



import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;
import org.testng.asserts.SoftAssert;

import testBase.GenericClass;

public class AddNewPersonPage {
	public static final Logger log = Logger.getLogger(AddNewPersonPage.class.getName());
	private WebDriver driver;
	
	String vendorLocationValue = "101 - 101 - Atlanta, GA - Branch 101- 22,atlanta,GA-30305";
	String stateName = "Georgia";
	
	
	@FindBy(xpath="//b[contains(text(), 'Person Information :']")
	private WebElement addNewPersonPageHeading;
	
	@FindBy(name="firstName")
	private WebElement legalFirstName;
	
	@FindBy(xpath="//input[contains(text(), 'Yes']")
	private WebElement middleNameYesCheckBox;
	
	@FindBy(xpath="//input[contains(text(), 'No']")
	private WebElement middleNameNoCheckBox;
	
	@FindBy(name="lastName")
	private WebElement legalLastName;
	
	@FindBy(xpath="//input[@name='isSSNRequired' and @title='Yes']")
	private WebElement ssnYesCheckBox;
	
	@FindBy(xpath="//input[@name='isSSNRequired' and @title='No']")
	private WebElement ssnNoCheckBox;
	
	@FindBy(name="ssn")
	private WebElement ssn;
	
	@FindBy(xpath="//img[@title='Date selector']")
	private WebElement dobCalendar;
	
	@FindBy(name="dob")
	private WebElement dob;
	
	@FindBy(name="email")
	private WebElement email;
	
	@FindBy(name="peopleType")
	private WebElement type;
	
	@FindBy(name="locationId")
	private WebElement vendorLocation;
	
	@FindBy(name="addressForm.countryCode")
	private WebElement country;
	
	@FindBy(name="addressForm.addrLine1")
	private WebElement addressLine1;
	
	@FindBy(name="addressForm.cityName")
	private WebElement city;
	
	@FindBy(name="addressForm.stateCode")
	private WebElement state;
	
	@FindBy(name="addressForm.zipCode")
	private WebElement zipCode;
	
	@FindBy(xpath="//input[@value='SAVE']")
	private WebElement save;
	
	@FindBy(xpath="html/body/div[6]/table/thead/tr[1]/td[2]")
	private WebElement monthAndYear;
	
	@FindBy(xpath="html/body/div[8]/table/thead/tr[2]/td[1]/div")
	private WebElement yearNextButton;
	
	@FindBy(xpath="//b[contains(text(), 'Person Status Detail')]")
	private WebElement personDetailsPageHeading;
	
	public AddNewPersonPage(WebDriver driver){
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	
	
	
	public void addNewPerson(String firstName, String lastName, String ssnnumber, String dateofBirth, String address, String City, String zipnumber ){
		legalFirstName.sendKeys(firstName);
		legalLastName.sendKeys(lastName);
		if(ssnYesCheckBox.isSelected()){
			System.out.println("ssn check box selected");
		}
		else if(ssnYesCheckBox.isDisplayed()){
			ssnYesCheckBox.click();
			
		}
		
		ssn.sendKeys(ssnnumber);
		
		dob.sendKeys(dateofBirth.replaceAll("/", ""));
		Select select = new Select(type);
		select.selectByVisibleText("Employee");
		Select vlocation = new Select(vendorLocation);
		vlocation.selectByVisibleText(vendorLocationValue);
		addressLine1.sendKeys(address);
		city.sendKeys(City);
		Select State = new Select(state);
		State.selectByVisibleText(stateName);
		zipCode.sendKeys(zipnumber);
		save.click();
		GenericClass.elementTobeVisible(driver, 30, personDetailsPageHeading);
		String PersonDetailsPageHeading = personDetailsPageHeading.getText();
		String expected = "Person Status Detail : ABC CONTRACTOR - (28671)";
		if(expected.contains(PersonDetailsPageHeading)){
			log.info("person added successfully");
		}
		else{
			log.error("person is not added");
		}
			
	}
}
