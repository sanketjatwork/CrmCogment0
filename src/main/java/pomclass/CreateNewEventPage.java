package pomclass;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

import baseclasses.Utility;

public class CreateNewEventPage {
	
	@FindBy(xpath="//input[@name='title']")private WebElement eventTitle;
	@FindBy(xpath="(//div[@class='ui field']//div[@class='react-datepicker__input-container ']//input[@type='text'])[1]")private WebElement preFilledStartDate;
	@FindBy(xpath="(//div[@class='ui field']//div[@class='react-datepicker__input-container ']//input[@type='text'])[2]")private WebElement preFilledEndDate;
    @FindBy(xpath="//button[@aria-label='Next Month']")private WebElement nextKey;
    @FindBy(xpath="//div[@name='category']//div[text()='Select']")private WebElement category;
    @FindBy(xpath="//div[@name='category']//div[@class='divider text']")private WebElement selectedCategory;
    @FindBy(xpath="//div[@name='category']")private List <WebElement> categoryOptions;
    @FindBy(xpath="(//div[@role='combobox'])[1]//input[@aria-autocomplete='list']")private WebElement tags;
    @FindBy(xpath="(//div[@role='combobox'])[1]//a[@class='ui label']")private WebElement enteredTag;
    @FindBy(xpath="//div[@name='minutesBefore']")private WebElement alertBefore;
    @FindBy(xpath="//div[@name='participants']//input[@class='search']")private WebElement addParticipants;
    @FindBy(xpath="//div[@name='participants']//div[@role='option']")private List<WebElement> ParticipantsOptions;
  //  @FindBy(xpath="//div[@name='participants']//a[@class='ui label']")private List<WebElement> selectedParticipants;
    @FindBy(xpath="//a[text()='No recurrence. Click to set.']")private WebElement recurrenceSet;
    @FindBy(xpath="//div[@class='field']//div[@name='freq']")private WebElement freqDropdown;
    @FindBy(xpath="//div[@class='ui input']//input[@name='count']")private WebElement time;
    @FindBy(xpath="//div[@name='byweekday']")private WebElement freqDayDrop;
   // @FindBy(xpath="//div[@class='ui active visible multiple selection dropdown']//a[contains(@class,ui)]")private List<WebElement> selectedDaysRecurrence;
    @FindBy(xpath="//input[@name='interval']")private WebElement interval;
    @FindBy(xpath="//form[@class='ui form']//input[@class='calendarField']")private WebElement endDate;
    @FindBy(xpath="//div[@class='react-datepicker__current-month']")private WebElement currentMonth;
    @FindBy(xpath="(//div[@class='field']//div[@class='react-datepicker__input-container ']//input[@type='text'])[3]")private WebElement eventEndDate;
    @FindBy(xpath="//button[text()='Set']")private WebElement setEventButton;
    @FindBy(xpath="(//div[@class='ui field']//a)[4]")private WebElement EventSet;

    
	
	public CreateNewEventPage(WebDriver driver) 
	{
		PageFactory.initElements(driver,this);
	}
	
	public void enterEventTitle(String eventTitleEntry, WebDriver driver)
	{
		Utility.Ewait(driver, eventTitle);
		eventTitle.sendKeys(eventTitleEntry);
	}
	public void validatePrefilledStartDate(WebDriver driver)
	{
DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH");
		
		Date date=new Date();
		 String expectedDate= dateFormat.format(date);
		 
		 System.out.println(expectedDate);
		 Utility.Ewait(driver, preFilledStartDate);
		
		 String actualDate=preFilledStartDate.getAttribute("value");
		 
		 Assert.assertEquals(actualDate, expectedDate+":00");
	
		 

	}
	public void selectStartDate(WebDriver driver, String month, String year, String date,String time) throws InterruptedException 
	{
		 preFilledStartDate.click();
		 String selectedMonth=currentMonth.getText();
		 String expMonth=month+" "+year;
		 
		 do {
			 Utility.Ewait(driver, nextKey);
			 nextKey.click();
			 Thread.sleep(2000);
			 selectedMonth=currentMonth.getText();
			
		 }
		 while((selectedMonth.equals(expMonth))!=true);
		 
		Thread.sleep(2000);
	    driver.findElement(By.xpath("//div[@class='react-datepicker__week']//div[text()='"+date+"']")).click();
	    driver.findElement(By.xpath("//ul[@class='react-datepicker__time-list']//li[text()='"+time+"']")).click();
		 
	}
	public void validateSelectedDate(String expectedDate)
	{
		String actualSelectedDate = preFilledStartDate.getAttribute("value");
			
		Assert.assertEquals(actualSelectedDate, expectedDate);
	}
	public void selectEndDate(WebDriver driver, String month, String year, String date, String time) throws InterruptedException 
	{
		preFilledEndDate.click();
		 String selectedMonth=currentMonth.getText();
		 String expMonth=month+" "+year;
		 
		 do {
			 Utility.Ewait(driver, nextKey);
			 nextKey.click();
			 Thread.sleep(2000);
			selectedMonth = currentMonth.getText();
			
		
		 }
		 while((selectedMonth.equals(expMonth))!=true);
		 
		Thread.sleep(2000);
	    driver.findElement(By.xpath("//div[@class='react-datepicker__week']//div[text()='"+date+"']")).click();
	    driver.findElement(By.xpath("//ul[@class='react-datepicker__time-list']//li[text()='"+time+"']")).click();;
		Thread.sleep(2000);
	}
	public void clickCategoryDropdown() 
	{
		category.click();
	}
	public void selectCategory(WebDriver driver, String inputcategoryoption)
	{
		driver.findElement(By.xpath("//div[@name='category']//span[text()='"+inputcategoryoption+"']")).click();
		
	}
	
	/*public void selectCategory(String inputcategoryOption)
	{
		int categoryOptionsCount = categoryOptions.size();
	
		for(int i=0;i<=categoryOptionsCount;i++)
	{
			String actualcategoryOptiontext=categoryOptions.get(i).getText();
			
			if(actualcategoryOptiontext.equals(inputcategoryOption))
			{
				categoryOptions.get(i).click();
				System.out.println(actualcategoryOptiontext);
			break;
			}
	}
	}*/
	public void validateCategory(String expectedCategory) 
	{
		String actualCategory = selectedCategory.getText();
		System.out.println(actualCategory);
		Assert.assertEquals(actualCategory, expectedCategory, "verify that"+expectedCategory+"is selected");
	}
	public void enterTags(WebDriver driver,String inputkeys) throws InterruptedException
	{
		tags.click();
		Utility.ActionEnter(driver, tags, inputkeys);
		Thread.sleep(5000);
	}
	public void validateTagsEntry(WebDriver driver,String expectedTags) 
	{
		
		String actualTags = enteredTag.getText();
		Assert.assertEquals(actualTags, expectedTags);
	}
	public void validatePrefilledAlertBefore(String expectedPrefilledAlerttime)
	{
		String actualAlertBefore=alertBefore.getText();
		
		System.out.println(actualAlertBefore+" "+expectedPrefilledAlerttime);
		
		Assert.assertEquals(actualAlertBefore, expectedPrefilledAlerttime);
	}
	public void selectAlertBefore(WebDriver driver, String time)
	{
		alertBefore.click();
		driver.findElement(By.xpath("//div[@name='minutesBefore']//div[@role='option']//span[text()='"+time+"']")).click();
		
	}
	public void validateAlertBefore(String expectedAlerttime)
	{
		String actualAlertBefore=alertBefore.getText();
		
		System.out.println(actualAlertBefore+" "+expectedAlerttime);
		
		Assert.assertEquals(actualAlertBefore, expectedAlerttime);
	}
	public void sendParticipantName(String ParticipantName) throws InterruptedException
	{
		addParticipants.sendKeys(ParticipantName);
		Thread.sleep(2000);
	}
	public void selectParticipant(String inputParticipant)
	{
		int participantsCount = ParticipantsOptions.size();
		
		
		for(int i=1;i<=participantsCount;i++)
		{
			String participantName = ParticipantsOptions.get(i).getText();
			if(participantName.equals(inputParticipant))
			{
				ParticipantsOptions.get(i).click();
			break;}
		}
	}
	public void validateParticipant(String expectedParticipant, WebDriver driver) throws InterruptedException
	{/*
		Thread.sleep(2000);
		int selectedParticipantsCount = selectedParticipants.size();
		System.out.println(selectedParticipantsCount);
		for(int i=0;i<=selectedParticipantsCount;i++)
		{
			System.out.println(i);
			String actualParticipant = selectedParticipants.get(i).getText();
			System.out.println(actualParticipant);
			if(actualParticipant.equals(expectedParticipant)) 
			{
				Assert.assertEquals(actualParticipant, expectedParticipant);
			break;}
		}*/
		boolean ParticipantSelected = driver.findElement(By.xpath("//div[@name='participants']//a[text()='"+expectedParticipant+"']")).isDisplayed();
		Assert.assertTrue(ParticipantSelected, "validated that"+expectedParticipant+" is selected successfully");
		// above code optimised by passing 'dynamic xpath in assertion'
	}
	public void setRecurrenceClick(WebDriver driver) throws InterruptedException {
		recurrenceSet.click();
		
	}
	public void validatePreselectedFreq(String expectedFreq)
	{
		String actualFreq = freqDropdown.getText();
		Assert.assertEquals(actualFreq, expectedFreq);
	}
	public void setRecurrenceFreq(WebDriver driver, String selectedFrequency) throws InterruptedException
	{
		Utility.Ewait(driver, freqDropdown);
		freqDropdown.click();
		Thread.sleep(2000);
		driver.findElement(By.xpath("//div//span[text()='"+selectedFrequency+"']")).click();
		
	}
	public void validateFreq(String expectedFreq)
	{
		String actualFreq = freqDropdown.getText();
		Assert.assertEquals(actualFreq, expectedFreq);
	}
	public void ClickDayRecurrence()
	{
		freqDayDrop.click();
		
	}
	public void setDayRecurrence(WebDriver driver, String SelectedDay) throws InterruptedException 
	{
		driver.findElement(By.xpath("//div//span[text()='"+SelectedDay+"']")).click();
		
	}
	public void validateDaySelected(WebDriver driver, String expectedDay) throws InterruptedException
	{/*
		Utility.iwait(driver, 10);
		int daysSize=selectedDaysRecurrence.size();
		System.out.println(daysSize);

		for(int i=0;i<=daysSize;i++)
		{
			System.out.println(i);
			String actualDay=selectedDaysRecurrence.get(i).getText();
			Thread.sleep(2000);
			System.out.println(actualDay);
			if(actualDay.equals(expectedDay))
			{
				Assert.assertEquals(actualDay, expectedDay);
			break;}
			*/
		boolean daySelected = driver.findElement(By.xpath("//div[@name='byweekday']//a[contains(text(),'"+expectedDay+"')]")).isDisplayed();
		Assert.assertTrue(daySelected,"validate that the"+expectedDay+" is selected successfully");
		}
		
	public void setInterval(String inputInterval) {
		interval.clear();
		interval.sendKeys(inputInterval);
	}
	public void setEndDate(String Month,WebDriver driver, String expectedDate, String year, String Time) throws InterruptedException
	{
		endDate.click();
		String selectedMonth = currentMonth.getText();
		
		String expectedMonth = Month+" "+year;
		System.out.println(expectedMonth);
		
		do {
			nextKey.click();
		
			selectedMonth = currentMonth.getText();
		}
		while(selectedMonth.equals(expectedMonth)!=true);

		Thread.sleep(2000);
		driver.findElement(By.xpath("//div[@class='react-datepicker__week']//div[text()='"+expectedDate+"']")).click();
		driver.findElement(By.xpath("//div[@class='react-datepicker__time']//ul//li[text()='"+Time+"']")).click();
	}

	public void validateEventEndDate(String expectedDate)
	{
		String actualDate = eventEndDate.getAttribute("value");
		System.out.println(actualDate);
		Assert.assertEquals(actualDate, expectedDate);
	}
	public void setRecurrenceButtonClick()
	{
		setEventButton.click();
	}
	public void validateEventSet(WebDriver driver, String Interval, String DaysSelected, String Date, String Time, String intervalCount )
	{
		Utility.Ewait(driver, EventSet);
		boolean actualEventSet= driver.findElement(By.xpath("//div//a[text()='every "+intervalCount+" "+Interval+", on "+DaysSelected+", until "+Date+", "+Time+"']")).isDisplayed();
		
		Assert.assertTrue(actualEventSet);
	}
}

	
	

