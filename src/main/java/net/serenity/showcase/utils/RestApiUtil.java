package net.serenity.showcase.utils;

import org.apache.http.HttpException;
import org.apache.log4j.Logger;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;

/**
 * A utility class for Rest Api.
 * 
 * @author Manish Lalwani
 * 
 */
public class RestApiUtil {
	private static Logger appLogs = Logger.getLogger(RestApiUtil.class);

	/**
	 * Constructor.
	 */
	public RestApiUtil() {
		appLogs.info(Util.getMethodName(0) + "()");
	}

	private static Client client = null;

	/**
	 * REST call w/ JSON to specific url.
	 * 
	 * @param url
	 *            url to call
	 * @param contentType
	 *            content type
	 * @return JSON string
	 * @throws HttpException
	 *             thrown when anything other than a 200 response code received
	 */
	public ClientResponse restCallResponseGet(final String url, final String contentType) throws HttpException {
		appLogs.info(Util.getMethodName(0) + "()");

		if (client == null) {
			client = Client.create();
		}

		WebResource webResource = client.resource(url);
		ClientResponse response = webResource.accept(contentType).get(ClientResponse.class);
		appLogs.info(Util.getMethodName(0) + "() response= " + response);
		return response;
	}


}
