package pageObjects;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class ProductPage {
	
	@FindBy(xpath="//h2[contains(@class,'name')]")
	public static WebElement productDesc;
	
	@FindBy(linkText="Add to cart")
	public static WebElement addToCart;
	
	@FindBy(xpath="//a[@id='cartur']")
	public static WebElement cartList;
	
	@FindBy(xpath="//*[text()='Product description']")
	public static WebElement descTxt;

}
 	