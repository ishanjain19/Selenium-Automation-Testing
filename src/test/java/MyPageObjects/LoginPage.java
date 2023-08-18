package MyPageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginPage {
	WebDriver ldriver;
	 public LoginPage(WebDriver rdriver){
		ldriver = rdriver;
		PageFactory.initElements(rdriver,this);
	}
	
	@FindBy(name = "user-name") 
	WebElement txtUserName;
	@FindBy(name = "password")
	WebElement txtPassword; 
	@FindBy(id = "login-button")
	WebElement btnLogin; 
	
	public void setUserName(String uname) {
		txtUserName.sendKeys(uname); 
	} 
	 
	public void setPassword(String pwd) { 
		txtPassword.sendKeys(pwd);
		}

	public void clickSubmit() {
		btnLogin.click();
	}

}
