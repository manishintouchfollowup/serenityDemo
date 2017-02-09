package net.serenity.showcase.steps;

import java.io.IOException;
import java.net.MalformedURLException;
import java.util.Map;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.jbehave.core.annotations.AfterScenario;

import net.serenity.showcase.utils.Util;
import net.serenitybdd.core.Serenity;
import net.serenitybdd.jbehave.SerenityStories;
import net.thucydides.core.Thucydides;
import testlink.api.java.client.TestLinkAPIClient;
import testlink.api.java.client.TestLinkAPIException;
import testlink.api.java.client.TestLinkAPIResults;


/**
 * author :  Manish Lalwani
 */

@SuppressWarnings("deprecation")
public class AcceptanceTestSuite extends SerenityStories {
	
	private static Logger logger = Logger.getLogger(AcceptanceTestSuite.class);

	private String exception;
	
	/**
	 * Constructor.
	 * @throws Exception
	 */
	public AcceptanceTestSuite() throws Exception {
		super();
		logger.log(Level.INFO, Util.getMethodName(0) + " Instantiated");
		Util.initializing();
	}
	
	/**
	 * Executed after each passed scenario. This function updates Testlink with result of execution for current script
	 */
	
	@AfterScenario(uponOutcome=AfterScenario.Outcome.SUCCESS)
	public void afterSuccessfulScenario() throws MalformedURLException {
		
		logger.log(Level.INFO, Util.getMethodName(0) + " Instantiated");
		String testCaseResult = TestLinkAPIResults.TEST_PASSED;
		String testCaseId = null;

		Map<String, String> metadata = Serenity.getCurrentSession().getMetaData();
	    testCaseId= metadata.get("tagValue");
	    
	    logger.log(Level.INFO, Util.getMethodName(0) + "Updating Testlink for Test Case Id: " + testCaseId);
	    
	    String exception= null;
		try {
			this.updateTestLinkResult(testCaseId, exception, testCaseResult);
		} catch (TestLinkAPIException e) {
			e.printStackTrace();
			logger.log(Level.INFO, Util.getMethodName(0) + e);
		}

	}
	     
	/**
	 * Executed after each failed scenario. This function updates Testlink with result of execution for current script
	 */
	
	@AfterScenario(uponOutcome=AfterScenario.Outcome.FAILURE)
	public void afterFailedScenario() throws MalformedURLException {
		logger.log(Level.INFO, Util.getMethodName(0) + " Instantiated");

		String testCaseResult = TestLinkAPIResults.TEST_FAILED;
		String testCaseId = null;
	    
		Map<String, String> metadata = Serenity.getCurrentSession().getMetaData();
	    testCaseId= metadata.get("tagValue");
	    
	    logger.log(Level.INFO, Util.getMethodName(0) + "Updating Testlink for Test Case Id: " + testCaseId);
	    
		String exception= null;
		try {
			this.updateTestLinkResult(testCaseId, exception, testCaseResult);
		} catch (TestLinkAPIException e) {
			e.printStackTrace();
			logger.log(Level.INFO, Util.getMethodName(0) + e);
		}		
	}
	
	/**
	 * List of variables for updating Testlink. Ideally, these variables should be moved to input parameters or to config file
	 * for actual project
	 */
	
    public final String DEV_KEY = "2a0f11e7a0e72f0e2bda43bc63491cb5";
 
    public final String SERVER_URL = "http://ec2-35-165-192-147.us-west-2.compute.amazonaws.com:9080/testlink/lib/api/xmlrpc/v1/xmlrpc.php";
 
    public final String PROJECT_NAME = "Demo Project";
 
    public final String PLAN_NAME = "Regression";
 
    public final String BUILD_NAME = "Automated Tests";
    
    /**
	 * This function updates the testlink with results for execution for current test case 
	 */
    public void updateTestLinkResult(String testCase, String exception, String result) throws TestLinkAPIException {
        TestLinkAPIClient testlinkAPIClient = new TestLinkAPIClient(DEV_KEY,
                               SERVER_URL);
        testlinkAPIClient.reportTestCaseResult(PROJECT_NAME, PLAN_NAME,
                               testCase, BUILD_NAME, exception, result);
        logger.log(Level.INFO, Util.getMethodName(0) + " Instantiated");
   }
	
    
}
