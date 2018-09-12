package pageObject;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

public class AgreementPage {
	
	@FindBy(xpath="//input[@value='ACCEPT FCRA AGREEMENT']")
	private WebElement acceptFRCAAggrement;
	
	@FindBy(xpath="//input[@value='DECLINE FCRA AGREEMENT']")
	private WebElement declineFRCAAggrement;
	
	@FindBy(xpath="//input[@value='Proceed with Login']")
	private WebElement proceedWithLogin;
	
	public AgreementPage(WebDriver driver){
		PageFactory.initElements(driver, this);
	}
	
	public void clickAcceptFRCAAggrement(){
		acceptFRCAAggrement.click();
	}
	
	public void clickDeclineFRCAAggrement(){
		declineFRCAAggrement.click();
		
	}
	
	public void clickOnProceedWithLogin(){
		proceedWithLogin.click();
		
	}
	
	public void clickonAgreement(){
		if(acceptFRCAAggrement.isDisplayed()){
			acceptFRCAAggrement.click();
		}
		
		else if (proceedWithLogin.isDisplayed()){
			proceedWithLogin.click();
		}
	}

}
