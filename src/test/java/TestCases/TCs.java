package TestCases;

import java.io.File;
import java.io.IOException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.commons.io.FileUtils;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.io.FileHandler;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import org.testng. Assert; 
import org.testng. annotations.AfterClass;
import org.testng.annotations.AfterTest;
import org.testng. annotations.BeforeClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng. annotations. Test;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

import MyUtilities.ReadConfig;
import io.github.bonigarcia.wdm.WebDriverManager; 

public class TCs {
	
	ChromeDriver driver; 
	Logger logger;
	
	ExtentReports extent;
	ExtentSparkReporter spark;
	ExtentTest test;
	
	ReadConfig readConfig;
	public String firstname;
	public String lastname;
	public String pincode;
	
	JavascriptExecutor js;

	@BeforeClass 
	public void invokeBrowser() { 
		//Logger logger;
		logger = LogManager.getLogger(TCs.class);
		WebDriverManager.chromedriver().setup();
	 	ChromeOptions opt = new ChromeOptions();
		opt.addArguments("--remote-allow-origins=*");
		driver = new ChromeDriver(opt);
		driver.manage().window().maximize(); 
		driver.manage().deleteAllCookies();
		driver.get("https://www.saucedemo.com/"); 
		logger.info("URL opened");
					
	}
	 

	@AfterClass 
	public void closeBrowser() { 
		driver.quit(); 
	}
	
	@BeforeTest
	public void testLaunch() {
		extent = new ExtentReports();
		spark = new ExtentSparkReporter("ExtentTest.html");
		
		spark.config().setTheme(Theme.DARK);
		spark.config().setDocumentTitle("My Report");
		
		extent.attachReporter(spark);
				
	}
	
	@AfterTest
	public void tearDown() {
		extent.flush();
	}


	@Test 
	public void TC1_verifyPageTitle() { 
		String actualTitle = driver.getTitle(); 
		String expectedTitle = "Swag Labs"; 
		Assert.assertEquals(actualTitle, expectedTitle);
		
		logger.info("Verified Page Title");
		
		test = extent.createTest("Verify the Page Title").assignAuthor("Saloni");
		test.info("I am verifying page title");
		test.info("Captured Page Title is " + actualTitle);
	} 
		
	
//	@Test(dataProvider="credentials")
//	public void TC2_verifyLoginCredentials(String type,String username,String pwd) {
//		
//		WebDriverWait wait = new WebDriverWait(driver,3);
//		
//		driver.findElement(By.name("user-name")).sendKeys(username);
//		driver.findElement(By.name("password")).sendKeys(pwd);
//		driver.findElement(By.id("login-button")).click();	
//		
//		if(type.equals("invalid")) {
//                   wait.until(ExpectedConditions.presenceOfElementLocated(
//                		   By.cssSelector("[data-test='error']")))  ; 
//                   driver.navigate().refresh();
//                   logger.error("Login was not successful");
//		}
//		else  {
//			wait.until(ExpectedConditions.presenceOfElementLocated(
//         		   By.cssSelector("[class='title']")))  ;
//				   logger.info("Login was successful");
//		}
//		
//		
//	} 
	
	@Test(dataProvider="credentials")
	public void TC2_verifyLoginCredentials(String type,String username,String pwd) throws InterruptedException {
		
		WebDriverWait wait = new WebDriverWait(driver,3);
		
		test = extent.createTest("Verify login credentials").assignAuthor("Saloni");
		
		driver.findElement(By.name("user-name")).sendKeys(username);
		driver.findElement(By.name("password")).sendKeys(pwd);
		driver.findElement(By.id("login-button")).click();	
		
		if(type.equals("invalid")) {
                   wait.until(ExpectedConditions.presenceOfElementLocated(
                		   By.cssSelector("[data-test='error']")))  ;
                   
                   test.addScreenCaptureFromPath(capturescreenshot(driver));
                   Thread.sleep(2000);
                   driver.navigate().refresh();
                   
                   logger.error("Login was not successful"); 
                   
                   test.fail("Incorrect Credentials");
                   test.info("Captured Username " + username + " Captured Password " + pwd);
                   
		}
		else  {
			wait.until(ExpectedConditions.presenceOfElementLocated(
         		   By.cssSelector("[class='title']")))  ;
			
				   logger.info("Login was successful");
				   
				   test.pass("Successfully Loggd In");
				   test.info("Captured Username " + username + " Captured Password " + pwd);
		}
		
		
	} 
	
	@DataProvider(name="credentials")
	public Object[][] getData(){
		
		return new Object[][] {
			{"invalid","standard_user","123"},
			{"valid","standard_user","secret_sauce"}
			
		};
	}

	
	@Test
	public void TC3_dropdown() throws InterruptedException  { 
		test = extent.createTest("Checking filter").assignAuthor("Mitansh");
		
		WebElement selDropdown = driver.findElement(By.tagName("select"));
		Select filter = new Select(selDropdown);
		logger.info("Drop down menu opened");
		Thread.sleep(2000);
		filter.selectByValue("lohi");
		
		js = (JavascriptExecutor) driver;
		js.executeScript("window.scrollBy(0,650)", "");	
		Thread.sleep(1000);	
		
		js = (JavascriptExecutor) driver;
		js.executeScript("window.scrollBy(650,0)", "");	
		Thread.sleep(1000);	
		
		logger.info("Low to High Filter Selected");
		test.pass("Filter successfully applied");
	} 	
	
/*	@Test
	public void TC4_addToCart() throws InterruptedException {
		driver.findElement(By.id("add-to-cart-sauce-labs-onesie")).click();
		logger.info("Item1 successfully added to cart");
		Thread.sleep(3000);
		driver.findElement(By.id("add-to-cart-sauce-labs-bike-light")).click();
		logger.info("Item2 successfully added to cart");
		Thread.sleep(3000);
		driver.findElement(By.id("add-to-cart-test.allthethings()-t-shirt-(red)")).click();
		logger.info("Item3 successfully added to cart");
		Thread.sleep(3000);
		driver.findElement(By.id("remove-sauce-labs-bike-light")).click();
		logger.warn("Item2 removed from cart");
		Thread.sleep(3000);
		driver.findElement(By.className("shopping_cart_link")).click();
		logger.info("Items were successfully added to cart");
		Thread.sleep(3000);
		
	}*/
	@Test
	public void TC4_addToCart() throws InterruptedException {
		
    	ExtentTest test = extent.createTest("Add and remove item from the cart").assignAuthor("Ishan");
    	
    	driver.findElement(By.id("add-to-cart-sauce-labs-onesie")).click();
		logger.info("Item1 successfully added to cart");
		Thread.sleep(1000);
		driver.findElement(By.id("add-to-cart-sauce-labs-bike-light")).click();
		logger.info("Item2 successfully added to cart");
		Thread.sleep(1000);
		driver.findElement(By.id("add-to-cart-test.allthethings()-t-shirt-(red)")).click();
		logger.info("Item3 successfully added to cart");
		Thread.sleep(1000);
		driver.findElement(By.id("remove-sauce-labs-bike-light")).click();
		logger.warn("Item2 removed from cart");
		Thread.sleep(1000);
		driver.findElement(By.className("shopping_cart_link")).click();
		logger.info("Items were successfully added to cart");
		Thread.sleep(1000);	
		
    	test.pass("Added and removed items was successfull");
    	test.addScreenCaptureFromPath(capturescreenshot(driver));
	}
	
	/*@Test
	public void TC5_checkout() throws InterruptedException {
		driver.findElement(By.id("checkout")).click();
		Thread.sleep(3000);
		driver.findElement(By.id ("first-name")).sendKeys("Saloni");
		driver.findElement(By.id ("last-name")).sendKeys("Andey");
		driver.findElement(By.id ("postal-code")).sendKeys("201307");
		Thread.sleep(3000);
		driver.findElement(By.id("continue")).click();
		Thread.sleep(3000);
		driver.findElement(By.id("finish")).click();
		Thread.sleep(3000);
		logger.info("Checkout successfully completed");
		driver.findElement(By.name("back-to-products")).click();
		logger.info("Returned to home page successfully after checkout");
		Thread.sleep(3000);
	}*/
	
	@Test
	public void TC5_checkout() throws InterruptedException {
		
		readConfig = new ReadConfig();
		
		driver.findElement(By.id("checkout")).click();
		Thread.sleep(1000);
		
//		public String 
		firstname = readConfig.getfirstname(); 
		lastname = readConfig.getlastname();
		pincode = readConfig.getpincode();
		
		
		ExtentTest test = extent.createTest("Verify the checkout").assignAuthor("Mitansh");		
    	/*try {
    		driver.findElement(By.id ("first-name")).sendKeys("Saloni");
    		driver.findElement(By.id ("last-name")).sendKeys("Andey");
    		driver.findElement(By.id ("postal-code")).sendKeys("201307");
    		logger.info("Details successfully entered");
    		Thread.sleep(1000);
    		driver.findElement(By.id("continue")).click();
    		Thread.sleep(1000);	
    		
    		test.pass("Checkout was successfull");
    		test.addScreenCaptureFromPath(capturescreenshot(driver));
    	}
    	catch(Exception e) {
    		test.fail("was not able to checkout"+e.getMessage());
    	}*/
		
		try {
    		driver.findElement(By.id ("first-name")).sendKeys(firstname);
    		driver.findElement(By.id ("last-name")).sendKeys(lastname);
    		driver.findElement(By.id ("postal-code")).sendKeys(pincode);
    		logger.info("Details successfully entered");
    		Thread.sleep(1000);
    		
    		js = (JavascriptExecutor) driver;
    		js.executeScript("window.scrollBy(0,450)", "");
    		
    		driver.findElement(By.id("continue")).click();
    		Thread.sleep(1000);	
    		
    		test.pass("Checkout was successfull");
    		test.addScreenCaptureFromPath(capturescreenshot(driver));
    	}
    	catch(Exception e) {
    		test.fail("was not able to checkout"+e.getMessage());
    	}
		
		
		
		
		js = (JavascriptExecutor) driver;
		js.executeScript("window.scrollBy(0,350)", "");
		
		Thread.sleep(1000);	
		
		driver.findElement(By.id("finish")).click();
		logger.info("Successfully completed checkout");
		driver.findElement(By.name("back-to-products")).click();
		logger.info("Returned to the home page");
		
	}

	@Test
	public void TC6_logout() throws InterruptedException  {
		
		ExtentTest test = extent.createTest("Check Logout").assignAuthor("Ishan");
		
		driver.findElement(By.id("react-burger-menu-btn")).click();	
		logger.info("Side menu opened");
		Thread.sleep(1000);
		driver.findElement(By.id("logout_sidebar_link")).click();	
		Thread.sleep(1000);
		logger.info("Clicked on logging out");
		logger.info("Logging Out");
		
		test.pass("Logged Out");
		
	}
	
	public static String capturescreenshot(WebDriver driver) {
    	File srcFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
    	File destinationFilePath = new File("src/../images/screenshot"+System.currentTimeMillis()+".png");
    //	File destinationFilePath = new File("ProjectN/Screenshots"+System.currentTimeMillis()+".png");
    	String absolutePathLocation = destinationFilePath.getAbsolutePath();
    	try {
    		FileUtils.copyFile(srcFile, destinationFilePath);
    	//	FileHandler.copy(srcFile, destinationFilePath);
    	}catch(IOException e) {
    		e.printStackTrace();
    	}
    	return absolutePathLocation;
    	
    }
	
	
	
}
