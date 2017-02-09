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
    @Given("the user is in home page")
    public void givenTheUserIsInHomePage () {
    	logger.log(Level.INFO, Util.getMethodName(0) + " Instantiated");
    	homePage.open();
    }
    
    /**
     * Select careers options under About Us Menu
     */
    @When("the user look for careers under about us menu")
    public void theUserLookForCareersUnderAboutUsMenu () {
    	logger.log(Level.INFO, Util.getMethodName(0) + " Instantiated");
    	homePage.moveToElement("buttonAboutUs");
    	Util.pause(WaitingTime.SHORT);
    	homePage.moveToElementAndClick("optionCareers");
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
   
}


