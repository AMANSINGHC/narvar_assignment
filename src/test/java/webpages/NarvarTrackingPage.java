package webpages;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class NarvarTrackingPage {

	WebDriver driver;
	
	@FindBy(css="textarea#feedback-comment-input")
	WebElement txtCommentInput;
	
	@FindBy(css="i.custom-icon.survey-stars-star.icon-star-empty")
	List<WebElement> listStarRating;
	
	@FindBy(css="div#feedback-comment-header")
	WebElement txtStarRating;
	
	@FindBy(xpath="//li[@ng-repeat='menuitem in menu']")
	List<WebElement> listMenuNavigationBtns;
	
	@FindBy(css="button#send-feedback-btn")
	WebElement btnSendFeedback;
	
	@FindBy(css="a#missing-package-tooltip")
	WebElement eleFindPackage;
	
	@FindBy(css="a.tooltip_contact_link")
	WebElement linkContactUs;
	
	@FindBy(tagName="md-icon")
	WebElement btnOpenChatBox;

	public NarvarTrackingPage(WebDriver driver) 
	{
		this.driver = driver;
		
		PageFactory.initElements(driver, this);
	}	
	
	public WebElement getTextCommentInput()
	{
		return txtCommentInput;
	}
	
	public List<WebElement> getListStarRating()
	{
		return listStarRating;
	}
	
	public WebElement getTxtStarRating()
	{
		return txtStarRating;
	}
	
	public List<WebElement> getMenuNavigateBtns()
	{
		return listMenuNavigationBtns;
	}
	
	public WebElement getSendFeedbackBtn()
	{
		return btnSendFeedback;
	}
	
	public WebElement getFindPackageEle()
	{
		return eleFindPackage;
	}
	
	public WebElement getContactUsLink()
	{
		return linkContactUs;
	}
	
	public WebElement getOpenChatBoxBtn()
	{
		return btnOpenChatBox;
	}
}
