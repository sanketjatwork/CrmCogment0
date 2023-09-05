package pomclass;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

import baseclasses.Utility;


public class LoginPage {
	
	@FindBy(xpath="//input[@name='email']")private WebElement email;
	@FindBy(xpath="//input[@name='password']")private WebElement password;
	@FindBy(xpath="//div[text()='Login']")private WebElement LoginButton;
	@FindBy(xpath="//a[text()='Sign Up']")private WebElement Signup;
	

	public LoginPage(WebDriver driver){
		
		PageFactory.initElements(driver,this);
	}
	public String validateLoginPageTitle(WebDriver driver)
	{
		return driver.getTitle();
				
	}
	
	public void loginUser(WebDriver driver,String emaildata,String passwordData) throws InterruptedException
	{
		
		email.sendKeys(emaildata);
		password.sendKeys(passwordData);
		Thread.sleep(1000);
		LoginButton.click();
		Thread.sleep(1000);
	
	}
	public void validateLoginUser(WebDriver driver, String user)
	{
		 boolean userDisplayed = driver.findElement(By.xpath("//div[@class='right menu']//span[text()='"+user+"']")).isDisplayed();
		Assert.assertTrue(userDisplayed);
	}
	
	public SignupPage validateSignupLink(WebDriver driver)
	{
		Signup.click();
		
		return new SignupPage(driver);
	}
	
	
	
}
