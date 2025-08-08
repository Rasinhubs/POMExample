package pageObjects;

import java.util.List;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class HomePage {
	
	@FindBy(linkText="Phones")
	public static WebElement phoneLink;
	
	@FindBy(xpath="//a[@class='hrefch']")
	public static List<WebElement> listOfPhones;
	
	@FindBy(linkText="Cart")
	public static WebElement cartLink;
	
	

}
