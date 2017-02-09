package net.serenity.showcase.utils;



public interface Constants {
	
	
	/**
	 * Default text 
	 */
	String TEXTCURRENTOPENINGS = "Current job openings at InTouch Techology";
	
	/**
	 * Token for address type in rest api response
	 */
	String FORMATTED_ADDRESS_TYPE = "formatted_address";
	
	/**
	 * Prefix for construction rest api url
	 */
	String RESTAPI_URL_PREFIX = "http://maps.googleapis.com/maps/api/geocode/json?address=";
	
	/**
	 * Suffix for construction rest api url
	 */
	String RESTAPI_URL_SUFFIX = "&sensor=false&#8221";
	
	/**
	 * PAGE OBJECT VARIABLES DECLARATIONS
	 */
	String HOMEPAGE = "HomePage";


	/**
	 * 2000 milli seconds delay.
	 */
	int WAIT_FOR_2000MS = 2000;

	/**
	 * 4000 milli seconds delay.
	 */
	int WAIT_FOR_4000MS = 4000;
	
	/**
	 * 7000 milli seconds delay.
	 */
	int WAIT_FOR_7000MS = 7000;


	/**
	 * This method is an enumeration of the Waiting/delay time.
	 * 
	 * @author Manish Lalwani
	 * 
	 */
	public enum WaitingTime {
		/**
		 * 2 seconds delay.
		 */
		SHORT,
		/**
		 * 4 seconds delay.
		 */
		MEDIUM,
		/**
		 * 7 seconds delay.
		 */
		LONG,
	}

}


