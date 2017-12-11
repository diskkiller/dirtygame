package com.hecaibao88.dirtygame.http;

import java.util.Map;

public class QRequest {
	private String url;
	private Map<String,String> params;
	private Map<String,String> headers;
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public Map<String, String> getParams() {
		return params;
	}
	public void setParams(Map<String, String> params) {
		this.params = params;
	}
	public Map<String, String> getHeaders() {
		return headers;
	}
	public void setHeaders(Map<String, String> headers) {
		this.headers = headers;
	}
	
	
}
