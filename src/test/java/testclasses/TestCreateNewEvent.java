package testclasses;

import java.io.IOException;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import baseclasses.Testbase;
import pomclass.AccountDetailsPage;
import pomclass.ContactsPage;
import pomclass.CreateNewEventPage;
import pomclass.Homepage;
import pomclass.LoginPage;
import pomclass.MainPage;
import pomclass.NewContactPage;

public class TestCreateNewEvent extends Testbase
{
	MainPage MainPage;
	LoginPage LoginPage;
	Homepage Homepage;
	ContactsPage ContactsPage;
	NewContactPage NewContactsPage;
	AccountDetailsPage AccountDetailsPage;
	CreateNewEventPage CreateNewEventPage;

	public TestCreateNewEvent() throws IOException
	{
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
		NewContactsPage=new NewContactPage(driver);
		CreateNewEventPage=new CreateNewEventPage(driver);
		AccountDetailsPage=new AccountDetailsPage(driver);
	
		MainPage.clickLoginButton(driver);
		Thread.sleep(2000);
		LoginPage.loginUser(driver,prop.getProperty("username"),prop.getProperty("password"));
		Homepage.viewContactLink(driver);
		Homepage.EnterContactsPage(driver);
		
		NewContactsPage.clickSearchBox();
		ContactsPage.openContactDetail(driver,"dada ji jiwtode");
		AccountDetailsPage.clickAddEventButton(driver);
		
		
	}
	@Test(enabled=false)
	public void enterEventTitle() 
	{
		
		CreateNewEventPage.enterEventTitle(prop.getProperty("EventTitle"),driver);
	}
	@Test(enabled=false)
	public void testPreselectedDate()
	{
		CreateNewEventPage.validatePrefilledStartDate(driver);
	}
	@Test(enabled=false)
	public void testSelectStartDate() throws InterruptedException
	{
		CreateNewEventPage.selectStartDate(driver, "October", "2023", "14","04:30");
		CreateNewEventPage.validateSelectedDate(prop.getProperty("ExpectedSelectedDate"));
	}
	@Test(enabled=false)
	public void testSelectEndDate() throws InterruptedException
	{
		CreateNewEventPage.selectEndDate(driver, "October", "2023", "15","01:30");
		
	}
	@Test(enabled=false)
	public void testSelectCategory() {
		CreateNewEventPage.clickCategoryDropdown();
		CreateNewEventPage.selectCategory(driver, "Social");
		//CreateNewEventPage.selectCategoryy("Social");
		CreateNewEventPage.validateCategory("Social");
	}
	@Test(enabled=false)
	public void testEnterTags() throws InterruptedException
	{
		CreateNewEventPage.enterTags(driver,prop.getProperty("tags1"));
		Thread.sleep(2000);
		CreateNewEventPage.validateTagsEntry(driver,prop.getProperty("tags1"));
	}
	@Test(enabled=false)
	public void testPrefilledAlertBefore() {
		CreateNewEventPage.validatePrefilledAlertBefore(prop.getProperty("expectedPrefilledAlerttime"));
		CreateNewEventPage.selectAlertBefore(driver, prop.getProperty("Alerttime"));
		CreateNewEventPage.validateAlertBefore( prop.getProperty("Alerttime"));
	}
	@Test(enabled=false)
	public void testSelectedParticipant() throws InterruptedException 
	{
		CreateNewEventPage.sendParticipantName("sanke");
		CreateNewEventPage.selectParticipant("sanket vishnu jiwtode");
		CreateNewEventPage.validateParticipant("sanku v jiwtode",driver);
		CreateNewEventPage.validateParticipant("sanket vishnu jiwtode",driver);
	}
	@Test
	public void testRecurrenceSet() throws InterruptedException 
	{
		
		CreateNewEventPage.setRecurrenceClick(driver);
		CreateNewEventPage.validatePreselectedFreq("Weekly");
		CreateNewEventPage.setRecurrenceFreq(driver,"Yearly");
		CreateNewEventPage.validateFreq("Yearly");
		
		CreateNewEventPage.ClickDayRecurrence();
		CreateNewEventPage.setDayRecurrence(driver, "Monday");
		CreateNewEventPage.setDayRecurrence(driver, "Tuesday");
		
		CreateNewEventPage.validateDaySelected(driver,"Monday");
		CreateNewEventPage.validateDaySelected(driver,"Tuesday"); 
		CreateNewEventPage.setInterval(prop.getProperty("interval"));
		CreateNewEventPage.setEndDate("October", driver, "14", "2026","04:00");
		CreateNewEventPage.validateEventEndDate(prop.getProperty("ExpectedeventEndDate"));
		CreateNewEventPage.setRecurrenceButtonClick();
		CreateNewEventPage.validateEventSet(driver, "years", "Monday, Tuesday", "10/14/2026", "4:00:00 AM", "10");
	}

	@AfterMethod
	public void tearDown()
	{
		driver.quit();
	}
	

}
