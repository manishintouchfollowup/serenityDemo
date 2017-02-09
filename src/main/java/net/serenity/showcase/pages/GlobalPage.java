package net.serenity.showcase.pages;

import java.util.Properties;

import net.serenity.showcase.utils.Util;
import net.serenitybdd.core.pages.PageObject;
import net.thucydides.core.annotations.DefaultUrl;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;




public class GlobalPage extends PageObject {
	
	private static Logger logger = Logger.getLogger(GlobalPage.class);
	private WebDriver webDriver;
	private Properties propertyFile;

	/**
	 * Default Constructor.
	 */  
    public GlobalPage(final WebDriver driver) {
    	super(driver);
    	logger.log(Level.INFO, Util.getMethodName(0) + " Instantiated");
    	webDriver = driver;
    }
    
	/**
	 * Constructor with different parameters
	 */  
	public GlobalPage(final WebDriver driver, final Properties properties) {
		this(driver);
		this.propertyFile = properties;
	}
    
	/**
	 * Custom function to click on elementName
	 * @param elementName
	 */
	public void clickOn(String elementName) {
		logger.log(Level.INFO, Util.getMethodName(0) + " Instantiated");
		String elementProperty = propertyFile.getProperty(elementName);
		$(elementProperty).waitUntilClickable().click();
	}
    
	/**
	 * Custom function to get text of elementName
	 * @param elementName
	 */
	public String getTextOf(String elementName) {
		logger.log(Level.INFO, Util.getMethodName(0) + " Instantiated");
		String elementProperty = propertyFile.getProperty(elementName);
		return $(elementProperty).waitUntilVisible().getText();
	}
	
	/**
	 * Custom function to move mouse to elementName
	 */
	public void moveToElement(String elementName){
		logger.log(Level.INFO, Util.getMethodName(0) + " Instantiated");
		String elementProperty = propertyFile.getProperty(elementName);
		Actions action = new Actions(webDriver);
		action.moveToElement($(elementProperty)).build().perform();
	}
	
	/**
	 * Custom function to move mouse to elementName and click on it
	 */
	public void moveToElementAndClick(String elementName){
		logger.log(Level.INFO, Util.getMethodName(0) + " Instantiated");
		String elementProperty = propertyFile.getProperty(elementName);
		Actions action = new Actions(webDriver);
		action.moveToElement($(elementProperty)).click().build().perform();
	}
	
	public String getValue(String elementName) {
		String elementProperty = propertyFile.getProperty(elementName);
		return $(elementProperty).getValue();
	}

	public Boolean isPresent(String elementName) {
		String elementProperty = propertyFile.getProperty(elementName);
		return $(elementProperty).isPresent();		
	}
	
	public boolean isCountPositive(String elementName) {
		String elementProperty = propertyFile.getProperty(elementName);
		return webDriver.findElements(By.xpath(elementProperty)).size() > 0;
	}
	
	public void typeIn(String elementName, String keyword) {
		String elementProperty = propertyFile.getProperty(elementName);
		$(elementProperty).waitUntilEnabled().type(keyword);		
	}

	
	public void clickIfNotChecked(String elementName) {
		String elementProperty = propertyFile.getProperty(elementName);
		if ($(elementProperty).getAttribute("checked") != null) 
			$(elementProperty).click();
	}
	
	public void clickByKeyboard(String elementName) {
		String elementProperty = propertyFile.getProperty(elementName);
		$(elementProperty).sendKeys(Keys.RETURN);

	}
	
		
	//new javascript methods
	
	public void clickByJS(String elementName) {
		String elementProperty = propertyFile.getProperty(elementName);
		WebElement element = webDriver.findElement(By.xpath(elementProperty));
		((JavascriptExecutor) webDriver).executeScript("arguments[0].click();",	element);
	}
	
	public void clickByJavaScriptID(String elementId) {		
		JavascriptExecutor jsExecutor = (JavascriptExecutor)webDriver;
		String jsCommand = "document.getElementById('" + elementId + "').click()";
		jsExecutor.executeScript(jsCommand);
	}
	
	public void scrollAndClickOn(String elementName) {
		String elementProperty = propertyFile.getProperty(elementName);		
		((JavascriptExecutor) getDriver()).executeScript("arguments[0].scrollIntoView(true);", $(elementProperty));
		this.clickByJS(elementName);
		
	}
	
	public void switchToFrame(String elementName) {
		String elementProperty = propertyFile.getProperty(elementName);		
		WebElement frame = webDriver.findElement(By.xpath(elementProperty));
		webDriver.switchTo().frame(frame);

	}

	public String getTitle() {
		return webDriver.getTitle();
	}
	
	public void selectByVisibleText(final String elementName, final String text) { 
		String elementProperty = propertyFile.getProperty(elementName);		
			WebElement list = webDriver.findElement(By.xpath(elementProperty));
			Select select = new Select(list); 
			select.selectByVisibleText(text);
		
	}
              
}


