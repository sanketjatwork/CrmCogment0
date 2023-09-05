package testclasses;

import java.io.IOException;

import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import baseclasses.Testbase;
import pomclass.Homepage;
import pomclass.LoginPage;
import pomclass.MainPage;

public class TestLoginPage extends Testbase {
	LoginPage l;
	Homepage h;
	
	public TestLoginPage() throws IOException {
		super();
	}
	
	@BeforeMethod
	public void setUp() throws InterruptedException {
		initialization();
		l=new LoginPage(driver);
		MainPage m=new MainPage(driver);
		m.clickLoginButton(driver);
		Thread.sleep(2000);
	
	}
	
	@Test(enabled=false)
	public void LoginPageTitleTest() 
	{
		
		String title = l.validateLoginPageTitle(driver);
		Assert.assertEquals(title,"Cogmento CRM");
		
		
	}
	@Test
	public void LoginTest() throws InterruptedException 
	{
		l.loginUser(driver, prop.getProperty("username"),prop.getProperty("password"));
		l.validateLoginUser(driver,prop.getProperty("user"));
}

	@AfterMethod
	public void tearDown()
{
		driver.quit();
	}
}
