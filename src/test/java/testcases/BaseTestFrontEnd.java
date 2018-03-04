package testcases;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.os.WindowsUtils;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;

public class BaseTestFrontEnd extends BaseSuite{
	
	WebDriver driver;
	JavascriptExecutor jse;
	
	@BeforeTest
	public void launchBrowser()
	{
		System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir")+"//libs//chromedriver.exe");
		
		driver=new ChromeDriver();
		
		driver.get(prop.getProperty("narvarTestURL"));
		driver.manage().window().maximize();		
		driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
	}
	
	@AfterTest
	public void closeBrowser()
	{
		driver.quit();
		
		WindowsUtils.killByName("chromedriver.exe");
	}
}
