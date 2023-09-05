package baseclasses;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.Duration;
import java.util.concurrent.TimeUnit;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.WebDriver.Timeouts;
import org.openqa.selenium.io.FileHandler;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Utility {
	WebDriver driver;
	
	public void screenshot() throws IOException {
		
		File src = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
		File dest=new File("C:\\Users\\sanke\\eclipse-workspace\\maximeyes\\target");
		FileHandler.copy(src,dest);
		
	}
	
	public String exceldata(String Sheet, int row, int col) throws EncryptedDocumentException, IOException {
		
		FileInputStream f=new FileInputStream("ggg");
		String data = WorkbookFactory.create(f).getSheet(Sheet).getRow(row).getCell(col).getStringCellValue();
		
		return data;
		
	}
	public static void scroll(WebDriver driver) {
		
		
		 ((JavascriptExecutor)driver).executeScript("0,4000");
		
	}
	public static void scrollintoview(WebDriver driver,WebElement element) {
		
		 ((JavascriptExecutor)driver).executeScript("arguments[0].scrollIntoView(true);",element);
		
	}
	public static Timeouts iwait(WebDriver driver,int time) {
		
		Timeouts sec = driver.manage().timeouts().implicitlyWait(time,TimeUnit.SECONDS);
		
		return  sec;
	}
	public static WebElement Ewait(WebDriver driver,WebElement element) {
		
		WebDriverWait w=new WebDriverWait(driver,Duration.ofSeconds(20));
		WebElement elementCondition = w.until(ExpectedConditions.elementToBeClickable(element));
		
		return elementCondition;
	}
	public static void ActionEnter(WebDriver driver, WebElement element, String inputkeys)
	{
		Actions act=new Actions(driver);
		act.moveToElement(element).sendKeys(inputkeys).perform();
		act.sendKeys(Keys.ENTER).build().perform();
	}


}
