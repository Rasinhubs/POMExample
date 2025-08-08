package pageObjects;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class AddToCartPage {
	@FindBy(xpath="(//tr[@class='success']//td/following::td)[1]")
	public static WebElement productTile;
	
	@FindBy(linkText="Delete")
	public static WebElement deleteProduct;
	
	@FindBy(id="totalp")
	public static WebElement total;
	

}