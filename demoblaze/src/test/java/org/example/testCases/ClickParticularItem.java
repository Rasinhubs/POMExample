package org.example.testCases;


import org.apache.logging.log4j.LogManager;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.aventstack.extentreports.Status;

import org.apache.logging.log4j.Logger;
import commonFunctions.CommonFunctions;
import pageObjects.AddToCartPage;
import pageObjects.HomePage;
import pageObjects.ProductPage;

public class ClickParticularItem extends CommonFunctions{
    
	@Test
	public void verifyClickingParticularItem() throws InterruptedException {
		
		Logger logger =LogManager.getLogger(ClickParticularItem.class);
		
		test = extentReport.createTest("Verifying whether the Product is able to add and view in the cart").assignAuthor("Raguvaran");
		PageFactory.initElements(driver, HomePage.class);
		logger.info("Clicking the Phone Products");
		test.log(Status.INFO,"Click the Phone Products to get the phone details.");
		HomePage.phoneLink.click();
		logger.info("Selecting the First Phone");
		test.log(Status.INFO, "Click the First Phone");
		String expTxt = selFirstPhone();
		
		PageFactory.initElements(driver, ProductPage.class);
		WebDriverWait wait = new WebDriverWait(driver, 30);
		wait.until(ExpectedConditions.elementToBeClickable(ProductPage.descTxt));
		
		logger.info("Getting the Product Description");
		test.log(Status.INFO, "Getting Product Description");
		
		String actTxt = ProductPage.productDesc.getText();
		Assert.assertEquals(actTxt, expTxt);
		logger.info("The product desc matches successfully and the text is: "+actTxt);
		test.log(Status.INFO, "The Product Description in the page is: "+actTxt);
		
		logger.info("Adding the Item to the cart");
		test.log(Status.INFO, "Adding the Item to the cart");
		ProductPage.addToCart.click();
		wait.until(ExpectedConditions.alertIsPresent());
		driver.switchTo().alert().accept();
		
		logger.info("Click the cart to see the added items");
		test.log(Status.INFO, "Click Cart to see the added product items");
        ProductPage.cartList.click();
		
		PageFactory.initElements(driver, AddToCartPage.class);
		boolean res = AddToCartPage.productTile.isDisplayed();
		Assert.assertTrue(res);
		
		logger.info("The product added matches as expected.");
		test.log(Status.INFO, "The product is available in the cart successfull.");
		wait.until(ExpectedConditions.elementToBeClickable(AddToCartPage.deleteProduct));
		
		logger.info("Deleting the Product from the list");
		test.log(Status.INFO, "Delet the product from the cart.");
		AddToCartPage.deleteProduct.click();
			
	}
	
	public String selFirstPhone() throws InterruptedException {
		WebElement firstItem =HomePage.listOfPhones.get(0);
		String text = firstItem.getText();
		firstItem.click();
		return text;
		
	}

}
