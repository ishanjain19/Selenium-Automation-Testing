package MyTestCases;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;

public class BaseClass {
	public String baseURL = "https://www.saucedemo.com/"; 
	public String username = "standard_user"; 
	public String password = "secret_sauce";
	public static WebDriver driver; 

	@BeforeClass  
	public void setup() {
		System.setProperty("webdriver.chrome.driver","C:\\Users\\Lenovo\\Lab2\\Drivers\\chromedriver.exe"); 
			ChromeOptions opt = new ChromeOptions();
			opt.addArguments("--remote-allow-origins=*");
			driver = new ChromeDriver(opt);
			driver.manage().window().maximize(); 
			driver.manage().deleteAllCookies();
			driver.get("https://www.saucedemo.com/");
	}
	@AfterClass  
	public void tearDown() { 
			driver.quit() ;
	}

}
