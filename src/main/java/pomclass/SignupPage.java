package pomclass;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class SignupPage {
	
	@FindBy (xpath="(//span[@class='mif-home font19 head-icon-shadow fg-white'])[1]") private WebElement HomeLogo;
	@FindBy (xpath="(//span[@class='mif-forth font17 head-icon-shadow fg-white'])[1]") private WebElement navigateArrow;
	@FindBy (xpath="(//span[@class='mif-history2 font20 head-icon-shadow icon fg-white'])[1]") private WebElement RecentPlaces;
	@FindBy (xpath="//a[@id='encounterMegaMenu']") private WebElement Encounters;
	@FindBy (xpath="(//a[@class='dropdown-toggle menu-large recentmodule'])[3]") private WebElement EncountersDropdown;
	
	public SignupPage(WebDriver driver) {
		PageFactory.initElements(driver,this);
	}
	public String verifyTitle(WebDriver driver) {
		return driver.getTitle();
	}
	public boolean verifyHomelogo() {
		return HomeLogo.isDisplayed();
	}
	public boolean verifyHomelogoClickability() {
		return HomeLogo.isEnabled();
	}
	public boolean verifyNavigateArrow() {
		return navigateArrow.isDisplayed();
	}
	public boolean verifyNavigateArrowClickability() {
		return navigateArrow.isEnabled();
	}
	public boolean verifyRecentPlaces() {
		return RecentPlaces.isEnabled();
	}
	public void verifyRecentPlacesPopup() {
		RecentPlaces.click();
		
	}
	public NewContactPage verifyEncounters(WebDriver driver) {
		Encounters.click();
		
		return new NewContactPage(driver);
	}
	
	
}
