package pageObject;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginPage {
	
	@FindBy(id="userLoginIdToShow")
	private WebElement userName;
	
	@FindBy(id="passwordToShow")
	private WebElement password;
	
	@FindBy(xpath="//a[@class='login_button_down']")
	private WebElement login;
	
	public LoginPage(WebDriver driver){
		PageFactory.initElements(driver, this);
	}
	
	
	public void setUserName(String username){
		userName.sendKeys(username);
	}
	
	public void setPassword(String pwd){
		password.sendKeys(pwd);
	}
	
	public void clickLogin(){
		login.click();
		//return AgreementPage;
	}
	
	
}
	
	
	