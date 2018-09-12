package pageObject;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class PeopleSearchPage {
	private WebDriver driver;
	@FindBy(xpath="//b[text()='People Search for  : ABC CONTRACTOR']")
	private WebElement peopleSearchPageHEading;
	
	@FindBy(name="firstName")
	private WebElement firstName;
	
	@FindBy(name="lastName")
	private WebElement lastName;
	
	@FindBy(name="complianceStatusCd")
	private WebElement complianceStstusDropDown;
	
	@FindBy(name="ssn")
	private WebElement ssn;
	
	@FindBy(name="inactiveReasonCd")
	private WebElement personStatus;
	
	@FindBy(name="peopleType")
	private WebElement type;
	
	@FindBy(name="searchByDate")
	private WebElement searchByDate;
	
	@FindBy(name="cpOrderUid")
	private WebElement CID;
	
	@FindBy(name="search")
	private WebElement search;
	
	public PeopleSearchPage(WebDriver driver){
		this.driver=driver;
		PageFactory.initElements(driver, this);
		
	}
	
	public void searcgPeopleWithssn(CharSequence[] ssn){
		this.ssn.sendKeys(ssn);
	}
	
	


}
