package testcases;

import java.awt.HeadlessException;
import java.awt.Toolkit;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.annotations.Test;

import webpages.NarvarTrackingPage;

public class FrontEndTCs extends BaseTestFrontEnd{

	NarvarTrackingPage narvarObj;
	
	@Test(priority=1, groups="UI Test", description="Check if textarea comment input field is enabled.")
	public void isEnabledCommentInput()
	{		
		narvarObj=new NarvarTrackingPage(driver);
		
		Assert.assertEquals(narvarObj.getTextCommentInput().isEnabled(), true, "Textarea comment input is disabled.");
	}
	
	@Test(priority=2, groups="UI Test", dependsOnMethods="isEnabledCommentInput", description="Check if the user is able to "
			+ "type in the textarea, and if the entered comment is same what user typed.")
	public void verifyCommentTyped() throws HeadlessException, UnsupportedFlavorException, IOException
	{
		String comment="Very Fast Deliver by Narvar. Thanks!!!";
		
		narvarObj.getTextCommentInput().sendKeys(comment+Keys.CONTROL+"a");
		narvarObj.getTextCommentInput().sendKeys(Keys.CONTROL, "c");
		
		Assert.assertEquals(Toolkit.getDefaultToolkit().getSystemClipboard().getData(DataFlavor.stringFlavor).toString(), 
				comment);
	}
	
	@Test(priority=3, groups="UI Test", description="Check if all the star ratings are enabled")
	public void isEnabledStarRating()
	{
		List<WebElement> listStarRating=narvarObj.getListStarRating();
		
		for(WebElement eachStarRating: listStarRating)
		{
			Assert.assertEquals(eachStarRating.isEnabled(), true, 
					"Rating_"+listStarRating.indexOf(eachStarRating)+" is disabled.");
		}
	}
	
	@Test(priority=4, groups="UI Test", dependsOnMethods="isEnabledStarRating", description="Check if terrible is printed "
			+ "for for 1st star rating, bad is printed for 2nd star rating and so on.")
	public void verifyTextForRating()
	{
		Map<Integer, String> map=new LinkedHashMap<>();
		map.put(1, "What made this experience terrible?");
		map.put(2, "What made this experience bad?");
		map.put(3, "What made this experience ok?");
		map.put(4, "What made this experience good?");
		map.put(5, "What made this experience excellent?");
		
		List<WebElement> listStarRating=narvarObj.getListStarRating();
		String txtStarRating;
		int size=listStarRating.size();
		
		for(int i=0; i<size; i++)
		{
			driver.findElement(By.xpath("//i[@class='custom-icon survey-stars-star icon-star-empty'][1]")).click();
			txtStarRating=narvarObj.getTxtStarRating().getAttribute("innerText");
			
			Assert.assertEquals(txtStarRating, map.get(i+1));
		}
	}
	
	@Test(priority=5, groups="UI Test", description="Verify the navigation of menu buttons")
	public void verifyPageNavigation()
	{
		Map<Integer, String> map=new LinkedHashMap<>();
		map.put(1, "Women's Shoes");
		map.put(2, "Men's Shoes");
		map.put(3, "Kids' Shoes");
		map.put(4, "Kids' Shoes");
		map.put(5, "Brands Category Landing Page");
		map.put(6, "Accessories");
		map.put(7, "Brands");
		map.put(8, "Shoes, Boots, Sneakers, Sandals, and more");
		
		List<WebElement> listMenuNavigateBtns=narvarObj.getMenuNavigateBtns();
		
		for(int i=0; i<listMenuNavigateBtns.size(); i++)
		{
			listMenuNavigateBtns.get(i).click();
			
			for(String eachTab: driver.getWindowHandles())
				driver.switchTo().window(eachTab);
			
			Assert.assertEquals(driver.getTitle(), map.get(i+1));
			
			driver.close();
			driver.switchTo().window(driver.getWindowHandles().iterator().next());
		}
	}
	
	@Test(priority=6,  groups="UI Test", description="Check if \"Send Feedback\" text is present in the Send Feedback button.")
	public void verifySendBtnText()
	{
		String btnText=narvarObj.getSendFeedbackBtn().getAttribute("innerText");
		
		Assert.assertEquals(btnText, "SEND FEEDBACK", "Text present in the btn is not as per requirement");
	}
	
	@Test(priority=7,  groups="UI Test", description="Check if the web page title is same as per requirement.")
	public void verifyPageTitle()
	{
		Assert.assertEquals(driver.getTitle(), "shoecarnival.narvar.com", "Title of the webpage is not as per requirement.");
	}
	
	@Test(priority=8,  groups="UI Test", description="Check if Customer Page gets opened successfully if user clicks on contact"
			+ " us present in the tooltip message.")
	public void verifyCustomerServiceNavigation()
	{
		new Actions(driver).moveToElement(narvarObj.getFindPackageEle()).build().perform();
		
		narvarObj.getContactUsLink().click();
		
		for(String eachTab: driver.getWindowHandles())
			driver.switchTo().window(eachTab);
		
		Assert.assertEquals(driver.getTitle(), "Customer Service", "User not navigated to the Customer Service Page.");
		
		driver.close();
		driver.switchTo().window(driver.getWindowHandles().iterator().next());
	}
	
//	@Test(priority=9, description="Check if Chatbox gets opened after clicking on the chatbox button.")
//	public void verifyOpenChatBox()
//	{
//		narvarObj.getOpenChatBoxBtn().click();
//		
//		Assert.assertEquals(actual, expected);
//	}
}
