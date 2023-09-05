package testclasses;

import java.io.IOException;

import org.junit.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import baseclasses.Testbase;
import pomclass.Homepage;
import pomclass.LoginPage;
import pomclass.MainPage;

public class TestHomePage extends Testbase {
	Homepage h;
	MainPage m;
	LoginPage l;

	public TestHomePage() throws IOException {
		super();
	}
	@BeforeMethod
	public void setUp() throws InterruptedException 
	{
		initialization();
		h=new Homepage(driver);
		 m=new MainPage(driver);
		m.clickLoginButton(driver);
		l=new LoginPage(driver);
		l.loginUser(driver, prop.getProperty("username"), prop.getProperty("password"));
		Thread.sleep(2000);
		
	}
	@Test
	public void TestTitle() 
	{
		h.validatePageTitle(driver);
	}
	
	@Test(enabled=false)
	public void TestAccountUserTitle() 
	{
		if(h.validateAccountUserTitle().contains("Shivam")) {
			System.out.println(h.validateAccountUserTitle());
		}
		else {
			Assert.fail();
		}
	}
	
	@Test
	public void testContactsLink() throws InterruptedException 
	{
		h.viewContactLink(driver);
		h.EnterAddContactsPage(driver);
	}
	
	@AfterMethod
	public void tearDown()
	{
		driver.quit();
	}
}
