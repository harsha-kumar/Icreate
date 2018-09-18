package icreateDemo;

import java.io.File;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
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
				System.out.println("------------WELCOME TO Icreate FRAMEWORK -------");
				System.out.println("*******************************************************");
				System.out.println("------------------Its a MAIN Class---------------------");
				report=new ExtentReports("\\var\\lib\\jenkins\\workspace\\DemoICreate\\Icreate\\icreate.html", true, NetworkMode.ONLINE);
				report.loadConfig(new File("\\var\\lib\\jenkins\\workspace\\DemoICreate\\Icreate\\extent-config.xml"));
				System.setProperty("webdriver.chrome.driver","\\var\\lib\\jenkins\\workspace\\DemoICreate\\Icreate\\chromedriver");
				driver=new ChromeDriver();
				System.out.println("Chrome is Selected");	
				driver.manage().window().maximize();
				log=report.startTest("Icreate Login");
				driver.get("http://192.168.2.126/login");
				log.log(LogStatus.PASS, "Url loaded successfully", "Url");
				driver.findElement(By.xpath("//input[@placeholder='Enter username']")).sendKeys("FTO_author4");
				Thread.sleep(1000);
				driver.findElement(By.xpath("//input[@placeholder='Enter password']")).sendKeys("FTO_author4");
				Thread.sleep(1000);
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

}
