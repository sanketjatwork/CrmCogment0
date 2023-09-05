package testclasses;

import java.io.IOException;

import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import baseclasses.Testbase;
import baseclasses.Utility;
import pomclass.NewContactPage;
import pomclass.Homepage;
import pomclass.LoginPage;
import pomclass.MainPage;

public class TestNewContactPage extends Testbase{
	
	MainPage m;
	LoginPage l;
	Homepage h;
	NewContactPage contacts;

	public TestNewContactPage() throws IOException {
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
		 h.viewContactLink(driver);
		 h.EnterAddContactsPage(driver);
		
		 contacts=new NewContactPage(driver);
	}
	@Test
	public void sample() {
		h.validatePageTitle(driver);
	}
	@Test
	public void TestFirstNameTextBox() throws InterruptedException {
		
		contacts.enterData("FirstNameKey", "middleNameKey", "lastNameKey");
		
		 String ActualFirstNameTextboxValue = contacts.validateTextboxFirstName(driver);
		Assert.assertEquals("FirstNameKey",ActualFirstNameTextboxValue);
	}
	@Test
	public void TestAutosuggestionValue() throws InterruptedException {
		contacts.enterCompanyName(driver, "tcs");
		contacts.validateCompanySelectedFromSuggestion("tcs");
	}
	@Test
	public void TestAccessDropdown() throws InterruptedException {
		contacts.setAccess(driver);
		contacts.selectUser(driver,prop.getProperty("user"));
		contacts.validateSelectedUser(prop.getProperty("user"));
	}
	@Test
	public void TestCategoryDropdown() throws InterruptedException {
		contacts.selectCategory();
		contacts.validateCategorySelection(prop.getProperty("category"));
	}
	@Test
	public void TestSocialChannel()
	{
		contacts.selectSocialChannel(driver);
		contacts.validateSocialChannelValue("Facebook profile link",prop.getProperty("userProfile"));
	}
	@Test
	public void TestDncRadioButton() throws InterruptedException
	{
		contacts.clickDoNotCallRadioButton(driver);
		contacts.validateDncRadioButton();
		Thread.sleep(2000);
	}
	@Test(enabled=false)
	public void TestUploadFile() throws InterruptedException {
		
		contacts.uploadFile(driver);
		Thread.sleep(3000);
	}
	@Test
	public void TestRegistration() throws InterruptedException 
	{
		contacts.enterData("sanket", "v", "jiwtode");
		contacts.enterEmailAddress("sanketj@gmail.com");
		contacts.enterCompanyName(driver, "tcs");
		contacts.setAccess(driver);
		contacts.selectUser(driver,prop.getProperty("user"));
		contacts.selectSocialChannel(driver);
		contacts.selectCategory();
		contacts.clickDoNotCallRadioButton(driver);
		Thread.sleep(2000);
		contacts.saveButtonClick(driver);
		Thread.sleep(5000);
		
		
	}
	@AfterMethod
	public void tearDown() {
		driver.quit();
	}
	

}
