package pomclass;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import baseclasses.Utility;

public class Homepage {

	@FindBy (xpath="//div//div//a//i[@class='users icon']") private WebElement contactsicon;
	@FindBy (xpath="//div//div//a//span[text()='Contacts']")private WebElement contacts;
	@FindBy (xpath="(//i[@class='plus inverted icon'])[2]") private WebElement AddContactButton;
	@FindBy (xpath="//div//span[@class='user-display']") private WebElement AccountUser;
	@FindBy (xpath="//input[@placeholder='Search']") private WebElement SearchBox;
	
	public Homepage(WebDriver driver)
	{
		PageFactory.initElements(driver,this);
	}
	public String validatePageTitle(WebDriver driver)
	{
		return driver.getTitle();
	}
	public String validateAccountUserTitle()
	{
		String User=AccountUser.getText();
		return User;
	}
	
	public void viewContactLink(WebDriver driver) throws InterruptedException 
	{
		Utility.Ewait(driver,contactsicon);
		Actions act =new Actions(driver);
		act.moveToElement(contactsicon).build().perform();
		Thread.sleep(2000);
		
		}
	public void EnterContactsPage(WebDriver driver) throws InterruptedException {
		
		Utility.Ewait(driver,contactsicon);
		Actions act =new Actions(driver);
		act.moveToElement(contactsicon).build().perform();
		Utility.Ewait(driver,contacts);
		act.moveToElement(contacts).click().build().perform();
		act.moveToElement(SearchBox).click().build().perform();
	}
	public NewContactPage EnterAddContactsPage(WebDriver driver) {
		AddContactButton.click();
		Actions act =new Actions(driver);
		act.moveToElement(SearchBox).click().build().perform();
		
		return new NewContactPage(driver);
	}
}
