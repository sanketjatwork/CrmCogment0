package testclasses;

import java.io.IOException;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import baseclasses.Testbase;
import baseclasses.Utility;
import pomclass.ContactsPage;
import pomclass.Homepage;
import pomclass.LoginPage;
import pomclass.MainPage;

public class TestContactsPage extends Testbase{

	MainPage MainPage;
	LoginPage LoginPage;
	Homepage Homepage;
	ContactsPage ContactsPage;
	
	public TestContactsPage() throws IOException {
		super();
	}
	@BeforeMethod
	public void setup() throws InterruptedException
	{
		initialization();
		MainPage=new MainPage(driver);
		LoginPage=new LoginPage(driver);
		Homepage=new Homepage (driver);
		ContactsPage=new ContactsPage(driver);
		
		MainPage.clickLoginButton(driver);
		LoginPage.loginUser(driver, prop.getProperty("username"), prop.getProperty("password"));
		Homepage.EnterContactsPage(driver);
		
	}
	@Test(enabled=false)
	public void testApplyFilters() throws InterruptedException 
	{
		ContactsPage.clickShowFilters();
		ContactsPage.clickFilterType();
		ContactsPage.selectFilterType(driver, "Address","");
		ContactsPage.clickOperatorFilter();
		ContactsPage.selectFilterOperator(driver, "Contains");
		ContactsPage.selectFilterValue(driver, "","Nagpur");
		ContactsPage.applyFilter();
		ContactsPage.validateFilters(driver, "","Nagpur");
	}
	@Test
	public void TestDeleteContact() throws InterruptedException
	{
		ContactsPage.tickContactCheckbox(driver,"sanket");
		ContactsPage.deleteContact();
		ContactsPage.completeAction();
		ContactsPage.confirmAction();
		Thread.sleep(3000);
		Utility.iwait(driver, 5000);
	}
	@AfterMethod
	public void tearDown() 
	{
		driver.quit();
	}
}
