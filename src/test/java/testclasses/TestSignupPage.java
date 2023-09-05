package testclasses;

import java.io.IOException;

import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import baseclasses.Testbase;
import pomclass.SignupPage;
import pomclass.MainPage;

public class TestSignupPage extends Testbase {
	
	SignupPage h;
	MainPage l;
	
	public TestSignupPage() throws IOException {
		super();
	}
	@BeforeMethod
	public void setup() {
		initialization();
		h=new SignupPage(driver);
		l=new MainPage(driver);
		 
	}
	@Test
	public void pageTitleTest() {
		Assert.assertEquals(h.verifyTitle(driver),"MaximEyes");
	}
	
	@Test
	public void logoTest() throws InterruptedException {
	 
		Assert.assertTrue(h.verifyHomelogo());
	}
	@AfterMethod
	public void tearDown() {
		driver.quit();
	}

}
