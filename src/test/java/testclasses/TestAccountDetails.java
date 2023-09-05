package testclasses;

import java.io.IOException;

import org.openqa.selenium.By;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import baseclasses.Testbase;
import baseclasses.Utility;
import pomclass.AccountDetailsPage;
import pomclass.NewContactPage;
import pomclass.Homepage;
import pomclass.LoginPage;
import pomclass.MainPage;

public class TestAccountDetails extends Testbase
{
	MainPage MainPage;
	LoginPage LoginPage;
	Homepage Homepage;
	NewContactPage NewContactsPage;
	AccountDetailsPage AccountDetailsPage;
	
	public TestAccountDetails() throws IOException {
		super();
	}
	@BeforeMethod
	public void setup() throws InterruptedException
	{
		initialization();
		MainPage=new MainPage(driver);
		LoginPage=new LoginPage(driver);
		Homepage=new Homepage (driver);
		NewContactsPage=new NewContactPage(driver);
		
		
		MainPage.clickLoginButton(driver);
		LoginPage.loginUser(driver, prop.getProperty("username"), prop.getProperty("password"));
		Homepage.viewContactLink(driver);
		Homepage.EnterAddContactsPage(driver);
		
		NewContactsPage.saveNewContact(prop.getProperty("firstname"), prop.getProperty("middlename"), prop.getProperty("lastname"),
				prop.getProperty("company"), driver, prop.getProperty("user"), prop.getProperty("email"));
		AccountDetailsPage=new AccountDetailsPage(driver);
	
		
	}
	@Test(enabled=false)
	public void TestAccountTitle() {
		AccountDetailsPage.validateAccountUserTitle(prop.getProperty("AccountUser"));
	}
	@Test//pass
	public void TestAccountUserEntry() throws InterruptedException {
		Homepage.EnterContactsPage(driver);
		Thread.sleep(2000);
		//AccountDetailsPage.validateEntryUserr(driver, "dada ji jiwtode");
		//AccountDetailsPage.validateAccountUserEntry(driver, prop.getProperty("ExpectedUser"));
		AccountDetailsPage.validateEntryUser(prop.getProperty("ExpectedUser"));
		Thread.sleep(5000);
		
	}
	@Test(enabled=false)//pass
	public void TestStarRating() 
	{
		AccountDetailsPage.selectRating();
		AccountDetailsPage.validateRating(driver);
	}
	@Test(enabled=false)//pass
	public void TestDeleteFunction() throws InterruptedException
	{
		AccountDetailsPage.clickDeleteContact(driver);
		
		AccountDetailsPage.validateDeleteContact(driver,prop.getProperty("ExpectedUser"));
	}
	
	@AfterMethod
	public void tearDown() {
		driver.quit();
	}
	

}
