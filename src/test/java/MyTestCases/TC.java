package MyTestCases;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.Test;

import MyPageObjects.LoginPage;
import MyPageObjects.HomePage;

public class TC extends BaseClass {
	
	@Test
	public void TC0_verifyPageTitle() throws InterruptedException { 
		//driver.get(baseURL); 
		//LoginPage lp = new LoginPage(driver); 
		String actualTitle = driver.getTitle(); 
		String expectedTitle = "Swag Labs"; 
		Assert.assertEquals(actualTitle, expectedTitle);
	}
	
	@Test
	public void TC1_loginTest() throws InterruptedException { 
		//driver.get(baseURL); 
		LoginPage lp = new LoginPage(driver); 
		lp.setUserName(username); 
		lp.setPassword(password); 
		lp.clickSubmit();
	}
	
/*	@Test
	public void TC2_dropdown()  { 
		//WebElement selDropdown = driver.findElement(By.tagName("select"));
	//	Select filter = new Select(selDropdown);
		filter.selectByValue("lohi");
	} 	*/

}
