package baseclasses;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.events.EventFiringWebDriver;



public class Testbase {
	public static WebDriver driver;
	public static Properties prop;
	public  static EventFiringWebDriver e_driver;
	public static WebEventListener eventListener;
	

	public Testbase() throws IOException  {
		
		 prop = new Properties();
		    try {
		        FileInputStream f = new FileInputStream("C:\\Users\\sanke\\eclipse-workspace\\CrmCogment0\\src\\main\\java\\configurations\\configurations.properties");
		        prop.load(f);
		    }
		    catch (FileNotFoundException e) {
		        e.printStackTrace();
		    }
		    catch (IOException e) {
		        e.printStackTrace();
		    }
	}
	
	public static void initialization() {
		
		String browsername=prop.getProperty("browser");
		
		if(browsername.equalsIgnoreCase("chrome")) {
			
			driver=new ChromeDriver();
		    driver.get(prop.getProperty("url"));		
			
		}
		else if(browsername.equalsIgnoreCase("firefox"))
{
			driver=new FirefoxDriver();
			driver.get(prop.getProperty("url"));
			
		}
		/*e_driver = new EventFiringWebDriver(driver);
		// Now create object of EventListerHandler to register it with EventFiringWebDriver
		eventListener = new WebEventListener();
		e_driver.register(eventListener);
		driver = e_driver;*/
		
		driver.manage().window().maximize();
		driver.manage().deleteAllCookies();
		driver.manage().timeouts().pageLoadTimeout(20,TimeUnit.SECONDS);
		
	}
	
}
