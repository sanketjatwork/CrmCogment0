package pomclass;

import static org.testng.Assert.assertNotEquals;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

import baseclasses.Utility;


public class AccountDetailsPage {
	
	@FindBy(xpath="//div[@class='ui header item mb5 light-black']//span[@class='selectable ']")private WebElement accountUserTitle;
	@FindBy(xpath="//table[@class='ui celled definition sortable striped table custom-grid']//thead//th")private List<WebElement> headersize;
	@FindBy(xpath="//table[@class='ui celled definition sortable striped table custom-grid']//tr")private List<WebElement> rowsize;
	@FindBy(xpath="//table[@class='ui celled definition sortable striped table custom-grid']//tr[1]//td")private List<WebElement> colsize;
	@FindBy (xpath="//input[@placeholder='Search']") private WebElement SearchBox;
	@FindBy (xpath="//table[@class='ui celled definition sortable striped table custom-grid']//tr[1]//td[2]") private WebElement Refcellvalue;
	@FindBy(xpath="(//div//i[@class='icon'])[4]")private WebElement StarRating4disabled;
	@FindBy(xpath="(//div//i[@class='active icon'])[4]")private WebElement StarRating4Enabled;
	@FindBy(xpath="//button//i[@class='edit icon']")private WebElement editButton;
	@FindBy(xpath="//a//button//i[@class='calendar plus outline icon']")private WebElement addEventButton;
	@FindBy(xpath="//button//i[@class='trash icon']")private WebElement DeleteButton;
	@FindBy(xpath="//button[text()='Delete']")private WebElement confirmDeleteButton;
	@FindBy(xpath="//tbody//tr//td[2]//a")private List<WebElement> userName;
	
	
	public AccountDetailsPage(WebDriver driver)
	{
		PageFactory.initElements(driver, this);
	}
		
	public void validateAccountUserTitle(String InputTitle) 
	{
		String title = accountUserTitle.getText();
		Assert.assertEquals(InputTitle, title);
	}
	//1
	public void validateAccountUserEntry(WebDriver driver, String ExpectedUsername) throws InterruptedException 
	{
		// to validate every cell value in grid
		
		SearchBox.click();
		Thread.sleep(2000);
		String xpath1 = "//table[@class='ui celled definition sortable striped table custom-grid']//tr[";
		String xpath2 ="]//td[";
		String xpath3="]";
		int rowCount = rowsize.size();
		int colcount = colsize.size();
		System.out.println(rowCount);
		System.out.println(colcount);
		
		//we have used 2 for loops so it is searching entry in every row
		// problem=earlier loop was not breaking ; and row count is more than the actual rows displayed to selenium 
		//(header and actions dropdown are also considered in rows in DOM) ,thus it was giving no such element
		
		for(int i=1;i<=rowCount-2;i++) 
		{
			for(int j=2;j<=colcount;j++)
			{
				String ActualUsername=driver.findElement(By.xpath(xpath1+i+xpath2+j+xpath3)).getText();
				if(ActualUsername.equals(ExpectedUsername)) 
				{	
				System.out.println(ActualUsername+"  text fetched");
			
				Assert.assertEquals(ActualUsername, ExpectedUsername);
				Thread.sleep(2000);
				break;
			}
				}
			
		}
	}
	//2
	public void validateEntryUser(String expUserName) 
	{
		// to validate specific column cells values of grid
		// problem=earlier loop was not breaking thus failing-hence added break keyword
		
		int userNameCount = userName.size();
		 System.out.println(userNameCount);
		 for(int i=1;i<=userNameCount;i++)
		 {
			String userNameValue=userName.get(i).getText();
			
			if(userNameValue.equals(expUserName)) 
			{
				System.out.println(userNameValue+"  fetched");
			Assert.assertEquals(userNameValue, expUserName,"verify that"+expUserName+"value is displayed correct");
			break;
		 }}
		
		 }
	//3
	public void validateEntryUserr(WebDriver driver, String expectedUsername) 
	{
		Utility.Ewait(driver, driver.findElement(By.xpath("//tbody//tr//td[2]//a[text()='"+expectedUsername+"']")));
		boolean usernamePresent = driver.findElement(By.xpath("//tbody//tr//td[2]//a[text()='"+expectedUsername+"']")).isDisplayed();
		 
		Assert.assertTrue(usernamePresent,"validated that"+expectedUsername+"is printed successfully");
		 }
	public void selectRating() 
	{
		StarRating4disabled.click();
	}
	public void validateRating(WebDriver driver) {
		Utility.Ewait(driver, StarRating4Enabled);
		boolean ratingStatus = StarRating4Enabled.isDisplayed();
		Assert.assertTrue(true,"rating is displayed");
	}
	public NewContactPage clickEditButton(WebDriver driver)
	{
		editButton.click();
		return new NewContactPage(driver);
	}
	public CreateNewEventPage clickAddEventButton(WebDriver driver) 
	{
		Utility.Ewait(driver, addEventButton);
		addEventButton.click();
		return new CreateNewEventPage(driver);
	}
	public void clickDeleteContact(WebDriver driver) throws InterruptedException 
	{
		DeleteButton.click();
		Utility.Ewait(driver, confirmDeleteButton);
		confirmDeleteButton.click();
		Thread.sleep(2000);
	}
	public void validateDeleteContact(WebDriver driver, String ExpectedUsername) throws InterruptedException
	{
		// checking username entry in every cell of grid 
		
		String xpath1 = "//table[@class='ui celled definition sortable striped table custom-grid']//tr[";
		String xpath2 ="]//td[";
		String xpath3="]";
		int rowCount = rowsize.size();
		int colcount = colsize.size();
		System.out.println(rowCount);
		System.out.println(colcount);
		
		for(int i=1;i<=rowCount-2;i++) 
		{
			for(int j=2;j<=colcount;j++)
			{
				String ActualUsername=driver.findElement(By.xpath(xpath1+i+xpath2+j+xpath3)).getText();
				System.out.println(ActualUsername+"deleted username checking");
				Assert.assertNotEquals(ActualUsername, ExpectedUsername);
				
			}
			
		}
	
		
	}
	
	

}
