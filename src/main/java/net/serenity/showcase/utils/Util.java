package net.serenity.showcase.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Map;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.JavascriptExecutor;

import net.serenity.showcase.utils.Constants.WaitingTime;


import net.serenity.showcase.utils.Constants;
import net.serenity.showcase.utils.Util;
import net.serenitybdd.core.Serenity;

import java.lang.reflect.Method;

/**
 * This class contains utility methods, which can be used in Steps and Pages
 * files.
 * 
 * @author Manish Lalwani
 */
public class Util {
	
	private static Logger logger = Logger.getLogger(Util.class);
	
	public static FirefoxDriver driver;
	
	/**
	 * Constructor.
	 * 
	 * @param driver
	 *            WebDriver
	 */
	public Util(final WebDriver driver) {
		logger.log(Level.INFO, Util.getMethodName(0) + " Instantiated");
	}
	
	
	
	/**
	 * @param waitTime
	 *            How many seconds should pause
	 */
	public static void pause(final WaitingTime waitTime) {
		logger.debug(Util.getMethodName(0) + "(): waitTime= " + waitTime);

		switch (waitTime) {
		case SHORT:
			waitFor(Constants.WAIT_FOR_2000MS);
			break;
		case MEDIUM:
			waitFor(Constants.WAIT_FOR_4000MS);
			break;
		case LONG:
			waitFor(Constants.WAIT_FOR_7000MS);
			break;
		default:
			logger.debug(Util.getMethodName(0) + "(): ERROR: invalid waiting time(" + waitTime + "). Please enter one of SHORT, MEDIUM, or LONG");
			break;
		}

	}
	
	/**
	 * This function scrolls to top of page
	 * @param driver
	 */
	public static void scrollToTop(WebDriver driver) {
		((JavascriptExecutor) driver).executeScript("scroll(0,10);");
	}
	
	/**
	 * This function scrolls to mid of page
	 * @param driver
	 */
	public static void scrollToMid(WebDriver driver) {
		((JavascriptExecutor) driver).executeScript("scroll(0,250);");
	}
	
	/**
	 * This function scrolls to bottom of page
	 * @param driver
	 */
	public static void scrollToBottom(WebDriver driver) {
		((JavascriptExecutor) driver).executeScript("scroll(0,1200);");
	}
	
	/**
	 * @param waitTime
	 *            How many seconds should pause
	 */
	private static void waitFor(final int waitTime) {
		logger.debug(Util.getMethodName(0) + "(): waitTime= " + waitTime);
		try {
			Thread.sleep(waitTime);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Initialization of variables and properties required during script execution
	 * @param page
	 * @throws IOException
	 */
	public static void initializing() throws IOException{
		loadHomePageProperties();
	}
	
	/**
	 * Methods to load property files
	 */
	private static void loadHomePageProperties() throws IOException {
		logger.log(Level.INFO, Util.getMethodName(0) + " Instantiated");
	 	setOrHomePage(new Properties());
	 	getOrHomePage().load(brandConfigFile(Constants.HOMEPAGE));
	 }
	
	
	
	/**
	 * Method to load properties file
	 * @param page
	 * @throws FileNotFoundException
	 */
	private static FileInputStream brandConfigFile(final String page) throws FileNotFoundException {
		return (new FileInputStream(System.getProperty("user.dir") + "//src//main//resources//" + page + ".properties"));
	}
	
	/**
	 * Properties files variable declarations
	 */
	private static Properties orHomePage;
	
	/**
	 * @return the orHomePage
	 */
	public static Properties getOrHomePage() {
		return orHomePage;
	}

	/**
	 * @param orHomePage the orHomePage to set
	 */
	public static void setOrHomePage(Properties orHomePage) {
		Util.orHomePage = orHomePage;
	}

	
	/**
	 * Method to get name of current method for log4j
	 */
	private static Method m;
	
	static {
		try {
			m = Throwable.class.getDeclaredMethod("getStackTraceElement", int.class);
			m.setAccessible(true);

		} catch (Exception e) {
			logger.info(e);
		}
	}
	
	/**
	 * This method returns a method name.
	 * 
	 * @param depth
	 *            depth in the call stack (0 means current method, 1 means call
	 *            method, ...)
	 * @return method name
	 */
	public static String getMethodName(final int depth) {
		try {
			StackTraceElement element = (StackTraceElement) m.invoke(new Throwable(), depth + 1);
			return element.getMethodName();

		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	

	/**
	 * This method takes the screen-shot from the screen.
	 * 
	 */
	@SuppressWarnings(value = {})
	public static void takeScreenshot() throws IOException {

		logger.log(Level.INFO, Util.getMethodName(0) + " Instantiated");
		
		Map<String, String> metadata = Serenity.getCurrentSession().getMetaData();
	    String testCaseId= metadata.get("tagValue");

		String screenshotLocation = System.getProperty("user.dir")
				+ "//TestResults//ScreenShots//";
		new File(screenshotLocation).mkdirs();
		String screenshotName = screenshotLocation
				+ testCaseId + ".jpg";

		driver = (FirefoxDriver) new org.openqa.selenium.remote.Augmenter()
				.augment(driver);
		File file = ((org.openqa.selenium.TakesScreenshot) driver)
				.getScreenshotAs(OutputType.FILE);
		FileUtils.copyFile(file, new File(screenshotName), true);

		logger.log(Level.INFO, Util.getMethodName(0) + "(): Screenshot Captured. ");

	}
}


