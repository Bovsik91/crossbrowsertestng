package parallelTest;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.testng.annotations.*;

public class CrossBrowserScript {

	WebDriver driver;
	
	/**
	 * This function will execute before each Test tag in testng.xml
	 * @param browser
	 * @throws Exception
	 */

	@BeforeTest
	@Parameters("browser")
	public void setup(@Optional("firefox") String browser) throws Exception{
		//Check if parameter passed from TestNG is 'firefox'
		if(browser.equalsIgnoreCase("firefox")){
		//create firefox instance
			driver = new FirefoxDriver();
		}
		//Check if parameter passed as 'chrome'
		else if(browser.equalsIgnoreCase("chrome")){
			//set path to chromedriver.exe
			System.setProperty("webdriver.chrome.driver","D:\\java\\Library\\Drivers\\chromedriver.exe");
			//create chrome instance
			driver = new ChromeDriver();
		}
		//Check if parameter passed as 'IE'
				else if(browser.equalsIgnoreCase("ie")){
					//set path to IE.exe
					System.setProperty("webdriver.ie.driver","D:\\java\\Library\\Drivers\\IEDriverServer.exe");
					//create IE instance
					driver = new InternetExplorerDriver();
				}
		else{
			//If no browser passed throw exception
			throw new Exception("Browser is not correct");
		}
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	}
	
	@Test
	public void testParameterWithXML() throws InterruptedException{
		driver.get("http://demo.guru99.com/V4/");
		//Find user name
		WebElement userName = driver.findElement(By.name("uid"));
		//Fill user name
		userName.sendKeys("guru99");
		//Find password
		WebElement password = driver.findElement(By.name("password"));
		//Fill password
		password.sendKeys("guru99");
	}
@AfterTest
	public void close(){
		driver.close();
	}
}
