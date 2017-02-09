package net.serenity.showcase.steps;

import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.hasItems;
import net.serenity.showcase.utils.Constants;
import net.serenity.showcase.utils.RestApiUtil;
import net.serenity.showcase.utils.ThreadContext;
import net.serenity.showcase.utils.Util;
import net.serenitybdd.rest.SerenityRest;
import net.thucydides.core.steps.ScenarioSteps;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.codehaus.jettison.json.JSONException;
import org.jbehave.core.annotations.Given;
import org.jbehave.core.annotations.Then;

import com.sun.jersey.api.client.ClientResponse;

/**
 * author :  Manish Lalwani
 */

@SuppressWarnings("serial")
public class RestApiSteps extends ScenarioSteps {

	private static Logger logger = Logger.getLogger(RestApiSteps.class);
	
	private ThreadContext threadContext = ThreadContext.get();
	
	/*
	 * This method triggers the location api with given city name
	 */
	@Given("the user access google location api to search city '<inValidCityName>'")
	public void givenTheUserAccessGoogleLocationApiToSearchInValidCity (final String inValidCityName) throws Exception {
	   	logger.log(Level.INFO, Util.getMethodName(0) + " Instantiated");
		    	
		StringBuffer finalUrl = new StringBuffer(new StringBuffer(Constants.RESTAPI_URL_PREFIX + inValidCityName + Constants.RESTAPI_URL_SUFFIX));
		logger.log(Level.INFO, Util.getMethodName(0) + " finalUrl :" + finalUrl);
		threadContext.setFinalUrl(finalUrl);
		
		RestApiUtil restApiUtil = new RestApiUtil();
		ClientResponse response;
		response = restApiUtil
				.restCallResponseGet(threadContext.getFinalUrl().toString(), "test");
		storeResponseGet(response);	
		logger.log(Level.INFO, Util.getMethodName(0) + "(): response= " + response);
		    	
	}
	

	/*
	 * This method triggers the location api with given city name
	 */
	@Given("the user access google location api to search city '<validCityName>'")
	public void givenTheUserAccessGoogleLocationApiToSearchValidCity (final String validCityName) throws Exception {
	   	logger.log(Level.INFO, Util.getMethodName(0) + " Instantiated");
		    	
		StringBuffer finalUrl = new StringBuffer(new StringBuffer(Constants.RESTAPI_URL_PREFIX + validCityName + Constants.RESTAPI_URL_SUFFIX));
		logger.log(Level.INFO, Util.getMethodName(0) + " finalUrl :" + finalUrl);
		threadContext.setFinalUrl(finalUrl);
		    	
	}
	
	
	/*
	 * This method sets response string
	 */
	private void storeResponseGet(final ClientResponse response) {
		threadContext.setResponseStr(response.getEntity(String.class));
		threadContext.setHttpCode(Integer.toString(response.getStatus()));
	}
			
	/*
	 * This method verifies the response status code from restapi
	 */
	@Then("the user should receive http code '$statusCode'")
	public void theUserShouldReceiveHttpCode(final Integer statusCode) {
		
		logger.info(Util.getMethodName(0) + "(): URL= " + threadContext.getFinalUrl().toString());
		
		SerenityRest.given().get(threadContext.getFinalUrl().toString()).then().statusCode(statusCode);
	}
	
	/*
	 * This method verifies the city field is not empty in the response message and has the expected value.
	 * 
	 * @throws JSONException
	 *             JSON exception
	 */
	@SuppressWarnings("static-access")
	@Then("the user should see the response contains searched city '<inValidCityName>'")
		public void theUserShouldSeeTheResponseContainsSearchedInvalidCity(final String inValidCityName) throws JSONException {
		logger.info(Util.getMethodName(0) + "()");
			
		SerenityRest.expect().body("results.geometry.location_type", hasItems("APPROXIMATE")).when().get((threadContext.getFinalUrl().toString()));
	}


	/*
	 * This method verifies the city field is not empty in the response message and has the expected value.
	 * 
	 * @throws JSONException
	 *             JSON exception
	 */
	@SuppressWarnings("static-access")
	@Then("the user should see the response contains searched city '<validCityName>'")
	public void theUserShouldSeeTheResponseContainsSearchedValidCity(final String validCityName) throws JSONException {
		logger.info(Util.getMethodName(0) + "()");
		
	
		SerenityRest.expect().body("results.geometry.location_type", hasItems("APPROXIMATE")).when().get((threadContext.getFinalUrl().toString()));

		
	}

}


