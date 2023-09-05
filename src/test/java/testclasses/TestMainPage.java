package testclasses;

import static org.testng.Assert.assertTrue;

import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import baseclasses.Testbase;
import pomclass.SignupPage;
import pomclass.MainPage;

public class TestMainPage extends Testbase
{
	MainPage l;
	SignupPage h;


	public TestMainPage() throws IOException {
		super();
		// to inherit the constructor from baseclass which will initialize properties
	}
	@BeforeMethod
	public void setup() {
		initialization();
		 l=new MainPage(driver);
		
	}
	@Test(enabled=false)
	public void mainPageTitleTest() {
		
		String title = l.validateMainPage(driver);
		System.out.println(title);
		Assert.assertEquals(title, "CRM Cloud Software from Free CRM");
	}
	@Test(enabled=false)
	public void logoTest() {
		boolean logoStatus = l.validateLogo();
		Assert.assertTrue(logoStatus);
	}
	@Test(enabled=false)
	public void signupLinkTest() {
		
		l.validateSignupLink(driver);// i.e. h=new SignupPage();
		
	}
	@Test(enabled=false)
	public void CRMDropdownSizeTest() throws InterruptedException {
		Thread.sleep(2000);
	
		if(l.validateCRMDropdownSize(driver)==10) {
			System.out.println("All options present");
			
		}else{
			Assert.fail();
		}
	}
	@Test(enabled=false)
	public void CRMDropdownOptionsTest() {
		l.validateCRMDropdown(driver);
		
	}
	@Test
	public void TestLoginButton() throws InterruptedException {
		l.clickLoginButton(driver);
	}
	@Test(enabled=false)
	public void TestScrollButton() throws InterruptedException {
		
		if(l.validateScrollToTopButton(driver)==0) {
			System.out.println("scrolled to top");
			
		}else{
			Assert.fail();
		}
		
	}
	@Test(enabled=false)
	public void TestMessageBox() throws InterruptedException {
		Assert.assertTrue(l.validateMessageButton(driver));
	}
	
	@AfterMethod
	public void teardown() {
		driver.quit();
	}

}
