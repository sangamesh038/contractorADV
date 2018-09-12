package scripts;

import java.io.IOException;


import org.apache.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.Test;
import pageObject.AgreementPage;
import pageObject.LoginPage;
import testBase.GenericClass;
import testBase.TestBase;

public class LoginPageTest extends TestBase {

	public static final Logger log = Logger.getLogger(LoginPageTest.class.getName());
	

	@Test
	public void LoginTest() throws IOException {
		LoginPage loginpage = new LoginPage(driver);
		GenericClass getdata = new GenericClass();
		AgreementPage agreementpage = new AgreementPage(driver);
		

		String un = getdata.readPropertyData("username");

		String pwd = getdata.readPropertyData("password");

		loginpage.setUserName(un);

		loginpage.setPassword(pwd);
		log.debug("clicking on login button");
		loginpage.clickLogin();
		agreementpage.clickonAgreement();

		log.info("home page displayed......");
		// throw new IOException("IOException");
		// Assert.fail();

	}

}
