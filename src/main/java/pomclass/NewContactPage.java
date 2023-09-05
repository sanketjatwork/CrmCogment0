package pomclass;

import java.util.List;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

import baseclasses.Utility;

public class NewContactPage {

	@FindBy(xpath="//input[@name='first_name']")private WebElement firstName;
	@FindBy(xpath="//input[@name='middle_name']")private WebElement middleName;
	@FindBy(xpath="//input[@name='last_name']")private WebElement lastName;
	@FindBy(xpath="//div[@name='company']//input[@aria-autocomplete='list']")private WebElement companyDropdown;
	@FindBy(xpath="//div[@class='visible menu transition']//div[@role='option']//span[@class='text']")private List<WebElement> companyDropdownSuggestions;
	@FindBy(xpath="//div[@name='company']//div[@class='divider text']")private WebElement selectedCompanyDropdownText;
	@FindBy(xpath="//button[@class='ui small fluid positive toggle button']")private WebElement access;
	@FindBy(xpath="//div[text()='Select users allowed access.']")private WebElement userAccessDropdown;
	@FindBy(xpath="//div[@class='visible menu transition']")private List<WebElement> userAccessDropdownOptions;
	@FindBy(xpath="//div//a[text()='shivam suroshe']")private WebElement selectedUser;
	@FindBy(xpath="//div[@class='ui selection upward dropdown']")private WebElement status;
	@FindBy(xpath="//input[@placeholder='Email address']")private WebElement emailAddress;
	@FindBy(xpath="(//div[@name='category'])[1]")private WebElement CategoryDropdown;
	@FindBy(xpath="//div[@class='visible menu transition']//div[@role='option']")private List <WebElement> CategoryDropdownOptions;
	@FindBy(xpath="//div[@name='category']//div[text()='Affiliate']")private WebElement CategoryDropdownSelectedOption;
	@FindBy(xpath="//div[@name='channel_type']")private WebElement socialChannel;
	@FindBy(xpath="//div[@class='visible menu transition']//span[text()='Facebook']")private WebElement SocialChannelFacebook;
	@FindBy(xpath="(//input[@name='value'])[2]")private WebElement socialChannelValue;
	@FindBy(xpath="(//div[@class='ui toggle checkbox'])[2]")private WebElement DoNotCallRadioButton;
	@FindBy(xpath="//input[@name='fileField']")private WebElement fileUpload;
	@FindBy(xpath="//button[@class='ui linkedin button']")private WebElement SaveButton;
	
	@FindBy (xpath="//input[@placeholder='Search']") private WebElement SearchBox;
	
	public NewContactPage(WebDriver driver) 
	{
		PageFactory.initElements(driver,this);
	}
	
	
	
	public void enterData(String FirstNameKey, String middleNameKey, String lastNameKey)
	{
		firstName.sendKeys(FirstNameKey);
		middleName.sendKeys(middleNameKey);
		lastName.sendKeys(lastNameKey);
	}
	public String validateTextboxFirstName(WebDriver driver) 
	{
			return firstName.getAttribute("value");
			}
	public void enterCompanyName(WebDriver driver, String companyName) throws InterruptedException 
	{
		Utility.Ewait(driver, companyDropdown);
		
		/* Actions class steps are used to create suggestions so that we can test if 
		option from suggestion is selected */
		
		Actions act=new Actions(driver);
		act.moveToElement(companyDropdown).click().sendKeys("tvs").build().perform();
		act.sendKeys(Keys.ENTER).build().perform();
		Thread.sleep(2000);
		companyDropdown.sendKeys("tcs");
		act.sendKeys(Keys.ENTER).build().perform();
		Thread.sleep(2000);
		
		// resumed test steps
		companyDropdown.sendKeys("t");
		
		Thread.sleep(2000);
		
		
		int companyDropdownOptionsSize = companyDropdownSuggestions.size();
		System.out.println(companyDropdownOptionsSize);
		
		for(int i=0;i<companyDropdownOptionsSize;i++) 
		{
			String companyText = companyDropdownSuggestions.get(i).getText();
				if(companyText.contains(companyName)) {
					companyDropdownSuggestions.get(i).click();
				break;
				}
				else {
					System.out.println("no element available");
				}
						}
	}
	public void validateCompanySelectedFromSuggestion (String expectedSuggestionValue)
	{

		String actualSuggestion = selectedCompanyDropdownText.getText();
		
		Assert.assertEquals(actualSuggestion, expectedSuggestionValue,"Verify that option "+ expectedSuggestionValue+" from suggestionslist successfully selected");
	}
	public void setAccess(WebDriver driver)
	{
		Utility.Ewait(driver, access);
		access.click();
		
	}
	public void selectUser(WebDriver driver,String user) throws InterruptedException
	{
		
		Utility.Ewait(driver,userAccessDropdown);
		userAccessDropdown.click();
		Thread.sleep(2000);
		int userAccessDropdownOptionsSize= userAccessDropdownOptions.size();
		System.out.println(userAccessDropdownOptionsSize);
		
		for(int i=0;i<userAccessDropdownOptionsSize;i++) {
			String userName = userAccessDropdownOptions.get(i).getText();
			
			if(userName.contains(user)) {
				userAccessDropdownOptions.get(i).click();
				
				System.out.println(userName);
			break;}
			else {
				System.out.println("no element available");
			}
		}
		
	}
	public void validateSelectedUser(String expectedUser)
	{
		String ActualUserSelected = selectedUser.getText();
		
		Assert.assertEquals(ActualUserSelected, expectedUser,"verify that user"+expectedUser+"successfully selected");
		
	}
	public void enterEmailAddress(String email) 
	{
		
		emailAddress.sendKeys(email);
	}
	public String validateEmailEntered() 
	{
		return emailAddress.getAttribute("value");
	}
	public void selectCategory() throws InterruptedException
	{
		
		CategoryDropdown.click();
		Thread.sleep(2000);
		
		int CategoryDropdownSize = CategoryDropdownOptions.size();
		System.out.println(CategoryDropdownSize);
		
		for(int i=0;i<CategoryDropdownSize;i++) {
			String optionText = CategoryDropdownOptions.get(i).getText();
			
			if(optionText.equalsIgnoreCase("affiliAte")) {
				CategoryDropdownOptions.get(i).click();
				System.out.println(optionText);
			}
			else {
				System.out.println("No text available");
			}
		}
		
	}
	public void validateCategorySelection(String expectedCategory) 
	{
		String actualCategory = CategoryDropdownSelectedOption.getText();
		
		Assert.assertEquals(actualCategory, expectedCategory, "verify that category"+expectedCategory+"successfully selected");
	}
	public void selectSocialChannel(WebDriver driver)
	{
		socialChannel.click();
		Utility.Ewait(driver, SocialChannelFacebook);
		SocialChannelFacebook.click();
	}
	public void validateSocialChannelValue(String expectedPlaceholder,String userProfile )
	{
		String actualPlaceholder = socialChannelValue.getAttribute("placeholder");
		System.out.println(actualPlaceholder);
		Assert.assertEquals(actualPlaceholder, expectedPlaceholder, "verify that placeholder"+expectedPlaceholder+"is present");
		socialChannelValue.sendKeys(userProfile);
	}
	public void clickDoNotCallRadioButton(WebDriver driver)
	{
		Utility.scrollintoview(driver,DoNotCallRadioButton );
		Utility.Ewait(driver,DoNotCallRadioButton);
		DoNotCallRadioButton.click();
	}
	public void validateDncRadioButton()
	{
		boolean DncButtonStatus = DoNotCallRadioButton.isEnabled();
		Assert.assertTrue(DncButtonStatus,"verify that button is enabled");
	}
	public void uploadFile(WebDriver driver) throws InterruptedException
	{
		Utility.scrollintoview(driver, fileUpload);
		
		fileUpload.click();
		fileUpload.sendKeys("C:\\Users\\sanke\\OneDrive\\Pictures\\Camera Roll\\WIN_20220816_18_59_51_Pro.jpg");
		Thread.sleep(2000);
		}
	public AccountDetailsPage saveButtonClick(WebDriver driver) {
		SaveButton.click();
		return new AccountDetailsPage(driver);
	}
	
	public void clickSearchBox() {
		SearchBox.click();
	}
	public AccountDetailsPage saveNewContact(String FirstNameKey, String middleNameKey, String lastNameKey, String companyName, WebDriver driver,String user, String email) throws InterruptedException 
	{
		firstName.sendKeys(FirstNameKey);
		middleName.sendKeys(middleNameKey);
		lastName.sendKeys(lastNameKey);
		
		Utility.Ewait(driver, companyDropdown);
		
		Actions act=new Actions(driver);
		act.moveToElement(companyDropdown).click().sendKeys("tvs").build().perform();
		act.sendKeys(Keys.ENTER).build().perform();
		Thread.sleep(2000);
		companyDropdown.sendKeys("tcs");
		act.sendKeys(Keys.ENTER).build().perform();
		Thread.sleep(2000);
		companyDropdown.sendKeys("t");
		Thread.sleep(2000);
		
		
		int companyDropdownOptionsSize = companyDropdownSuggestions.size();
		System.out.println(companyDropdownOptionsSize);
		
		for(int i=0;i<companyDropdownOptionsSize;i++) 
		{
			String companyText = companyDropdownSuggestions.get(i).getText();
				if(companyText.contains(companyName)) {
					companyDropdownSuggestions.get(i).click();
				break;
				}
				else {
					System.out.println("no element available");
				}
						}
		
		Utility.Ewait(driver, access);
		access.click();
		
		Utility.Ewait(driver,userAccessDropdown);
		userAccessDropdown.click();
		Thread.sleep(2000);
		int userAccessDropdownOptionsSize= userAccessDropdownOptions.size();
		System.out.println(userAccessDropdownOptionsSize);
		
		for(int i=0;i<userAccessDropdownOptionsSize;i++) {
			String userName = userAccessDropdownOptions.get(i).getText();
			
			if(userName.contains(user)) {
				userAccessDropdownOptions.get(i).click();
				
				System.out.println(userName);
			break;}
			else {
				System.out.println("no element available");
			}
		}
		emailAddress.sendKeys(email);
		
		CategoryDropdown.click();
		Thread.sleep(2000);
		
		int CategoryDropdownSize = CategoryDropdownOptions.size();
		System.out.println(CategoryDropdownSize);
		
		for(int i=0;i<CategoryDropdownSize;i++) {
			String optionText = CategoryDropdownOptions.get(i).getText();
			
			if(optionText.equalsIgnoreCase("affiliAte")) {
				CategoryDropdownOptions.get(i).click();
				System.out.println(optionText);
			}
			else {
				System.out.println("No text available");
			}
		}
		socialChannel.click();
		Utility.Ewait(driver, SocialChannelFacebook);
		SocialChannelFacebook.click();
	
		SaveButton.click();
		Thread.sleep(3000);
		return new AccountDetailsPage(driver);
	
	}
	
	
}
