package pageObjects;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class PlaceOrderPage  {
	
	@FindBy(xpath="//button[contains(text(),'Place Order')]")
	public static WebElement placeOrderBtn;
	
	@FindBy(xpath="//button[contains(text(),'Purchase')]")
	public static WebElement purchaseBtn;

}
