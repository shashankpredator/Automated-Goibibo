package testingproject;


import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;

import Utils.ExcelUtils;

public class ChromeTest {
	

    static WebDriver driver ; 
    //static ExtentTest test;
    static ExtentReports report;
    static ExtentReports extent;
    static ExtentHtmlReporter htmlReporter;
    @BeforeTest
    public void setup()
	{
	System.setProperty("webdriver.chrome.driver", "C:\\Users\\shash\\eclipse-workspace\\testingproject\\drivers\\drivers\\chromedriver.exe");
    driver = new ChromeDriver();
    htmlReporter = new ExtentHtmlReporter("extent.html");
    extent = new ExtentReports();
    extent.attachReporter(htmlReporter);
	}
	@Test(dataProvider="TestData")
    public void test(String From , String To ,String adj ) throws Exception
    {
		ExtentTest test = extent.createTest("Goibibo Automation");
		//Opening Browser
		driver.get("https://www.goibibo.com/");
	    //Weblocator1
		WebElement ele = driver.findElement(By.id("gosuggest_inputSrc"));
		ele.sendKeys(From);
		Thread.sleep(3000);
		ele.sendKeys(Keys.ARROW_DOWN);
		ele.sendKeys(Keys.ENTER);
		test.log(Status.PASS, "First WebLocator ");
	    //WebLocator2
		WebElement ele2 = driver.findElement(By.id("gosuggest_inputDest"));
	    ele2.sendKeys(To);
		Thread.sleep(3000);
		ele2.sendKeys(Keys.ARROW_DOWN);
		ele2.sendKeys(Keys.ENTER);
		test.log(Status.PASS, "Second WebLocator ");
		//Dateselector
		WebElement date = driver.findElement(By.id("departureCalendar"));	
		date.click();
		String fareid = "fare_";
		fareid = fareid+ adj;
		WebElement dateselect = driver.findElement(By.id(fareid));
		dateselect.click();
		test.log(Status.PASS, "Date WebLocator ");
		driver.findElement(By.id("gi_search_btn")).click();
		Thread.sleep(5000);
		test.log(Status.PASS, "Search Button ");
		test.pass("Test Passed");
		
    }
	@DataProvider
	 
    public Object[][] TestData() throws Exception{
 
         Object[][] testObjArray = ExcelUtils.getTableArray("C:\\Users\\shash\\eclipse-workspace\\testingproject\\Excel\\Data.xlsx","Sheet1");
 
         return (testObjArray);
	}
	@AfterTest
	public void teardown()
	{
		driver.close();
		extent.flush();
	}

}
