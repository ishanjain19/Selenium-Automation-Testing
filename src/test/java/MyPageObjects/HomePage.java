package MyPageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

public class HomePage {
	WebDriver ldriver;
	 public HomePage(WebDriver rdriver){
		ldriver = rdriver;
		PageFactory.initElements(rdriver,this);
	}
	 
	 @FindBy(tagName = "select") 
		WebElement seldropdown;
	 
}
