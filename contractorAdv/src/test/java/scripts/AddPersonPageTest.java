package scripts;


import java.io.IOException;

import org.apache.log4j.Logger;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import pageObject.AddNewPersonPage;
import pageObject.AgreementPage;
import pageObject.HomePage;
import pageObject.LoginPage;
import pageObject.VendorSearchPage;
import testBase.GenericClass;
import testBase.TestBase;

public class AddPersonPageTest extends TestBase {
	
	public static final Logger log = Logger.getLogger(AddPersonPageTest.class.getName());
	String sheetName = "addUser";
	
	@DataProvider
	public Object[][] getAddNewPersonData(){
		Object data[][] = GenericClass.getExcelData(sheetName);
		return data;
	}
	
	@Test(dataProvider="getAddNewPersonData")
	public void addPersonTest(String firstName, String lastName, String ssnnumber, String dateOfBirth, String address, String City, String zipnumber) throws IOException{
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
		
		HomePage homePage = new HomePage(driver);
		log.info("selecting customer");
		homePage.selectABCCompany();
		log.info("clicking on search vendor");
		homePage.clickOnSearchVendor();
		
		
		VendorSearchPage vendorSearchPage = new VendorSearchPage(driver);
		log.info("clicking on abc contractor");
		vendorSearchPage.clickonABCContractor();
		log.info("Navigating to add new person page");
		vendorSearchPage.clickOnAddNewPerson();
		
		AddNewPersonPage addNewPersonPage = new AddNewPersonPage(driver);
		addNewPersonPage.addNewPerson(firstName, lastName, ssnnumber, dateOfBirth, address, City, zipnumber);
		
	}
	
	

}
