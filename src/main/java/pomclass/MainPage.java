package pomclass;

import java.util.List;

import org.openqa.selenium.Dimension;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

import baseclasses.Utility;

public class MainPage extends Utility{
	

	@FindBy(xpath="(//span[@class='brand-slogan'])[1]")private WebElement logo;
	@FindBy(xpath="//a[text()='Sign Up']")private WebElement signupLink;
	@FindBy(xpath="//a[text()='CRM']")private WebElement CRMDropdown;
	@FindBy(xpath="//li[@class='active rd-navbar--has-dropdown rd-navbar-submenu']/ul/li/a")private List <WebElement> CRMDropdownOptions;
	@FindBy(xpath="//span[text()='Log In']")private WebElement loginButton;
	@FindBy(xpath="//span[text()=' sign up']")private WebElement signupButton;
	@FindBy(xpath="//span[contains(text(),'Log')]")private WebElement LoginButton;
	@FindBy(xpath="//a[@id='ui-to-top']")private WebElement scrollToTopButton;
	@FindBy(xpath="//a[text()='Small Medium Business SMB CRM']")private WebElement SMBlink;
	@FindBy(xpath="//div[@aria-label='Close Intercom Messenger']")private WebElement messageButton;
	@FindBy(xpath="//div[@class='intercom-3q5hpq e4w4mux0']")private WebElement sendMessage;
	@FindBy(xpath="//input[@name='email']")private WebElement mailTextbox;

public MainPage(WebDriver driver) {
	PageFactory.initElements(driver,this);
	
}

public String validateMainPage(WebDriver driver) {
	
	String title = driver.getTitle();
	
	return title;
}
public boolean validateLogo() {
	
	return logo.isDisplayed();
}
public SignupPage validateSignupLink(WebDriver driver) {
	
	signupLink.click();
	return new SignupPage(driver);   //above actions returns the object of signupPage
}
public int validateCRMDropdownSize(WebDriver driver) {
	int actualSize = CRMDropdownOptions.size();
	return actualSize;
		
}
public void validateCRMDropdown(WebDriver driver) {
	
	Actions act=new Actions(driver);
	act.moveToElement(CRMDropdown).build().perform();
    
	for(int i=0;i<=CRMDropdownOptions.size();i++) {
		
		 String option = CRMDropdownOptions.get(i).getText();
		 if(option.contains("CRM")) {
			 System.out.println(option);
		 }
		 else {
			Assert.fail();
		 }	}
	}
public LoginPage clickLoginButton(WebDriver driver) throws InterruptedException {
	
	LoginButton.click();
	Utility.iwait(driver, 10);
	return new LoginPage(driver);   //above actions returns the object of LoginPage
	
}
public int validateScrollToTopButton(WebDriver driver) throws InterruptedException {
	
	scrollintoview(driver, SMBlink);
	Thread.sleep(2000);
	
	scrollToTopButton.click();
	Thread.sleep(2000);
	
	int finalScrollPosition = ((Long)((JavascriptExecutor) driver).executeScript("return window.pageYOffset;")).intValue();
	
	return finalScrollPosition;
	}

public boolean validateMessageButton(WebDriver driver) throws InterruptedException {
	scrollintoview(driver, SMBlink);
	
	Thread.sleep(5000);
	
	
	driver.switchTo().frame("intercom-launcher-frame");
	
	
	Thread.sleep(2000);
	messageButton.click();
	
	return sendMessage.isDisplayed();
	//return mailTextbox.isDisplayed();
	
}















}