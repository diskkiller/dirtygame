package com.hecaibao88.dirtygame.http;

import java.util.List;
import java.util.Map;

public class QResponse {
	private int statusCode;
	private String errmsg;
	private Map<String, List<String>> headers;
	private Map<String,Object> passthrough;
	private String body;

	public int getStatusCode() {
		return statusCode;
	}

	public void setStatusCode(int statusCode) {
		this.statusCode = statusCode;
	}

	public Map<String, List<String>> getHeaders() {
		return headers;
	}

	public void setHeaders(Map<String, List<String>> headers) {
		this.headers = headers;
	}

	public String getErrmsg() {
		return errmsg;
	}

	public void setErrmsg(String errmsg) {
		this.errmsg = errmsg;
	}

	public String getBody() {
		return body;
	}

	public void setBody(String body) {
		this.body = body;
	}


	public Map<String, Object> getPassthrough() {
		return passthrough;
	}

	public void setPassthrough(Map<String, Object> passthrough) {
		this.passthrough = passthrough;
	}

	@Override
	public String toString() {
		return "QResponse [statusCode=" + statusCode + ", errmsg=" + errmsg
				+ ", headers=" + headers + ", body=" + body + "]";
	}
 
}
