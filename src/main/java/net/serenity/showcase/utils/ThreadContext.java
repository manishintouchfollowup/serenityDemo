package net.serenity.showcase.utils;




/**
 * ThreadLocal for communication with rest api
 * 
 * @author Manish Lalwani
 * 
 */
public class ThreadContext {
	
	private StringBuffer finalUrl = new StringBuffer();
	
	private String requestStr;
	private String responseStr; 
	private String httpCode; 


	private static ThreadLocal threadLocal = new ThreadLocal() {
		@Override
		protected ThreadContext initialValue() {
			return new ThreadContext();
		}
	};

	/**
	 * Constructor.
	 * 
	 * @return an instance
	 * 
	 */
	public static ThreadContext get() {
		return (ThreadContext) threadLocal.get();
	}

	public StringBuffer getFinalUrl() {
		return finalUrl;
	}

	public void setFinalUrl(final StringBuffer finalUrl) {
		this.finalUrl = finalUrl;
	}

	public String getHttpCode() {
		return httpCode;
	}

	public void setHttpCode(final String httpCode) {
		this.httpCode = httpCode;
	}

	public void setRequestStr(final String reqStr) {
		this.requestStr = reqStr;
	}
	
	public String getRequestStr() {
		return requestStr;
	}
	
	public String getResponseStr() {
		return responseStr;
	}

	public void setResponseStr(final String respStr) {
		this.responseStr = respStr;
	}
}
