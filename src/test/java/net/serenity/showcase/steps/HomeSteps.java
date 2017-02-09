package net.serenity.showcase.steps;

import static org.hamcrest.MatcherAssert.assertThat;
import net.serenity.showcase.pages.HomePage;
import net.serenity.showcase.utils.Constants;
import net.serenity.showcase.utils.Util;
import net.serenity.showcase.utils.Constants.WaitingTime;
import net.thucydides.core.pages.Pages;
import net.thucydides.core.steps.ScenarioSteps;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.jbehave.core.annotations.Given;
import org.jbehave.core.annotations.Then;
import org.jbehave.core.annotations.When;


/**
 * author : Manish Lalwani
 */

@SuppressWarnings("serial")
public class HomeSteps extends ScenarioSteps {
	
	private static Logger logger = Logger.getLogger(HomeSteps.class);
	
	private HomePage homePage;

	/**
	 * Constructor.
	 */    
    public HomeSteps(final Pages pages) {
        super(pages);
        logger.log(Level.INFO, Util.getMethodName(0) + " Instantiated");
    }

     
    /**
     * Launch home page
     */
    @Given("the user is in login page")
    public void givenTheUserIsInLoginPage () {
    	logger.log(Level.INFO, Util.getMethodName(0) + " Instantiated");
    	homePage.open();
    }
    
    /**
     * click on login button
     */
    @When("the user click on login")
    public void theUserClickOnLogin () {
    	logger.log(Level.INFO, Util.getMethodName(0) + " Instantiated");
    	homePage.clickOn("buttonLogin");
    }
    
    
    /**
     * This method looks for the position required by user
     */
    @When("the user looks for position '<position>'")
    public void theUserEnterFirstName (final String position) {
    	logger.log(Level.INFO, Util.getMethodName(0) + " Instantiated");
    	homePage.clickOn("linkPosition");
    }
    
    /**
     * This method enters username
     */
    @When("the user enter the username '<usernameFullAdmin>'")
    public void theUserEnterUserName (final String usernameFullAdmin) {
    	logger.log(Level.INFO, Util.getMethodName(0) + " Instantiated");
    	homePage.typeIn("fieldUsername", usernameFullAdmin);
    }
    
    /**
     * This method enters password
     */
    @When("the user enter the password '<passwordPassword>'")
    public void theUserEnterThePassword (final String passwordPassword) {
    	logger.log(Level.INFO, Util.getMethodName(0) + " Instantiated");
    	homePage.typeIn("fieldPassword", passwordPassword);
    }
    
    
    /**
     * Assert presence of current job openings
     */
    @Then("the user should see current job openings")
    public void theUserShouldSeeCurrentJobOpenings () {
    	logger.log(Level.INFO, Util.getMethodName(0) + " Instantiated");
     	String expected = Constants.TEXTCURRENTOPENINGS;
     	String actual = homePage.getTextOf("textCurrentOpenings");
     	     	
     	assertThat("Expected Result: \"" + expected + "\"," + "Actual Result: \"" + actual + "\"", actual.toLowerCase().contains(expected.toLowerCase()));

    }
    
    /**
     * Assert title of job description
     */
    @Then("the user should job description for the '<position>'")
    public void theUserShouldSeeCurrentJobOpenings (final String position) {
    	logger.log(Level.INFO, Util.getMethodName(0) + " Instantiated");
    	
     	String expected = position;
      	
      	String actual = homePage.getTextOf("textJobDescriptionHeader");
      	logger.log(Level.INFO, Util.getMethodName(0) + " text value is " + actual);
      	
      	assertThat("Expected Result: \"" + expected + "\"," + "Actual Result: \"" + actual + "\"", actual.toLowerCase().contains(expected.toLowerCase()));
    }
    
    
    /**
     * Assert title of job description
     */
    @Then("the user should be on home page")
    public void theUserShouldBeOnHomePage () {
    	logger.log(Level.INFO, Util.getMethodName(0) + " Instantiated");
    	
     	String expected = Constants.TEXT_HOME_PAGE;
      	
      	String actual = homePage.getTextOf("textFollowUp");
      	logger.log(Level.INFO, Util.getMethodName(0) + " text value is " + actual);
      	
      	assertThat("Expected Result: \"" + expected + "\"," + "Actual Result: \"" + actual + "\"", actual.toLowerCase().contains(expected.toLowerCase()));
    }
   
    /**
     * click on add a lead
     */
    @When("the user add a lead")
    public void theUserAddALead () {
    	logger.log(Level.INFO, Util.getMethodName(0) + " Instantiated");
    	homePage.clickOn("buttonAddALead");
    }
    
    /**
     * This method enters details of lead
     */
    @When("the user add details of new lead")
    public void theUserAddDetailsOfNewLead () {
    	logger.log(Level.INFO, Util.getMethodName(0) + " Instantiated");
    	homePage.typeIn("fieldLeadName", "Testing Automation");
    	homePage.typeIn("fieldMobileNumber", "(778) 465-7465");
    	homePage.typeIn("fieldEmail", "testingautomation@gmail.com");
    	homePage.selectByVisibleText("fieldLeadOwner", "Full Admin");
    	homePage.selectByVisibleText("fieldContactMethod", "Lead Box");
    	homePage.selectByVisibleText("fieldLeadSources", "Corporate");
    	homePage.typeIn("fieldLeadSourceDetails", "Testing");
    	homePage.typeIn("fieldProductDetails", "Testing");
    	homePage.typeIn("fieldNotes", "Notes");
    }
    
    /**
     * click on save
     */
    @When("the user save the details")
    public void theUserSaveTheDetails () {
    	logger.log(Level.INFO, Util.getMethodName(0) + " Instantiated");
    	homePage.clickOn("buttonSave");
    }
    
    
    /**
     * Assert title of job description
     */
    @Then("the user should see Opt In preferences")
    public void theUserShouldSeeOptInPreferences () {
    	logger.log(Level.INFO, Util.getMethodName(0) + " Instantiated");
    	Util.pause(WaitingTime.MEDIUM);
    	
     	String expected = Constants.TEXT_OPT_IN;
      	
      	String actual = homePage.getTextOf("textOptIn");
      	logger.log(Level.INFO, Util.getMethodName(0) + " text value is " + actual);
      	
      	assertThat("Expected Result: \"" + expected + "\"," + "Actual Result: \"" + actual + "\"", actual.toLowerCase().contains(expected.toLowerCase()));
    }
}


