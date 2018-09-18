package icreateDemo;

import java.io.File;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.NoSuchWindowException;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;
import com.relevantcodes.extentreports.NetworkMode;

public class DemoIcreate {
	
	public static WebDriver driver;
	public static ExtentReports report;
	public static ExtentTest log;
	public static String path =System.getProperty("user.dir");
	
	public static void main(String args[]) throws Exception
	{
		try {
				
				System.out.println("*******************************************************");
				System.out.println("------------WELCOME TO Icreate FRAMEWORK ---------------");
				System.out.println("*******************************************************");
				System.out.println("------------------Its a MAIN Class---------------------");
				report=new ExtentReports(path+"//icreate.html", true, NetworkMode.ONLINE);
				report.loadConfig(new File(path+"//extent-config.xml"));
				System.setProperty("webdriver.chrome.driver",path+"/headless/chromedriver.exe");
				ChromeOptions chromeOptions = new ChromeOptions();
			    chromeOptions.addArguments("--headless");
			    chromeOptions.addArguments("--no-sandbox");
			    chromeOptions.addArguments("window-size=1200x600");
			    driver = new ChromeDriver(chromeOptions);
				System.out.println("Chrome is Selected");	
				//driver.manage().window().maximize();
				log=report.startTest("Icreate Login");
				driver.get("http://192.168.2.126/login");
				log.log(LogStatus.PASS, "Url loaded successfully", "Url");
				Thread.sleep(5000);
				WaitForElementToDisplay(By.xpath("//input[@placeholder='Enter username']"), "place holder");
				driver.findElement(By.xpath("//input[@placeholder='Enter username']")).sendKeys("FTO_author4");
				Thread.sleep(1000);
				WaitForElementToDisplay(By.xpath("//input[@placeholder='Enter password']"), "place holder");
				driver.findElement(By.xpath("//input[@placeholder='Enter password']")).sendKeys("FTO_author4");
				Thread.sleep(1000);
				WaitForElementToDisplay(By.xpath("//button[@type='submit']"), "place holder");
				driver.findElement(By.xpath("//button[@type='submit']")).click();
				Thread.sleep(10000);
				log.log(LogStatus.PASS, "User logged in successfully","User logged in");
				report.endTest(log);
				report.flush();
				driver.close();
				driver.quit();
			
				
			
		}
		catch( Exception e){
			
			e.printStackTrace();
		}
		
		
	}
	
	public static boolean WaitForElementToDisplay (By elementQuery , String strElementName ) throws Exception
	{
	
		int  Time =60;
		boolean  Status =false;
	try
	{
		driver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);
		FluentWait<WebDriver> pWait = new FluentWait<WebDriver>(driver)
				.withTimeout(Time, TimeUnit.SECONDS)
				.pollingEvery(250, TimeUnit.MILLISECONDS)
				.ignoring(NoSuchElementException.class, StaleElementReferenceException.class)
				.ignoring(WebDriverException.class);
		pWait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(elementQuery));
		Status =true;
		//FrameworkFunctions.screenShot(strElementName);
		
	}
	catch (TimeoutException e) {
		
				
	}
	catch (NoSuchWindowException eW) {
		log.log(LogStatus.FAIL, "Browser closed during Execution ",	 eW.getMessage());
	}
	if(Status){
		log.log(LogStatus.PASS, strElementName +"</b> validation and </b>" +   strElementName + " is present ");
	}
	else{
		log.log(LogStatus.FAIL, strElementName +" </b> validation and </b>" + strElementName+ "<b> is not present </b>");
	}
	return Status;
	}


}
