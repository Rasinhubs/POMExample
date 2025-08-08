package org.example.testCases;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;
import commonFunctions.CommonFunctions;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import pageObjects.PlaceOrderPage;
import pageObjects.ProductPage;

public class ClickPlaceOrderWithoutAdding extends CommonFunctions {
	
	@Test
	public void placeOrderWithoutAdd() throws InterruptedException {
		
		Logger logger = LogManager.getLogger(ClickPlaceOrderWithoutAdding.class);
		
		PageFactory.initElements(driver, ProductPage.class);
		logger.info("Click the cart to see the added items");
        ProductPage.cartList.click();
		
		PageFactory.initElements(driver, PlaceOrderPage.class);
		
		WebDriverWait wait = new WebDriverWait(driver,30);
		logger.info("Clicking the Place order");
		PlaceOrderPage.placeOrderBtn.click();
		
		logger.info("Clicking the Purchase Button.");
		PlaceOrderPage.purchaseBtn.click();
		
		wait.until(ExpectedConditions.alertIsPresent());
		String errorTxt = driver.switchTo().alert().getText();
		Assert.assertEquals(errorTxt, "Please fill out Name and Creditcard.");
		logger.info("The error is matches as expected and the error message is: "+errorTxt);
		driver.switchTo().alert().accept();
		
		
	}
	
	
}
