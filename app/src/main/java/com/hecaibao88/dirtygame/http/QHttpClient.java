package com.hecaibao88.dirtygame.http;


import java.util.List;
import java.util.Map;

public interface QHttpClient {

	/**
	 * 同步的http请求
	 */
	public QResponse post(String url, Map<String, String> params);

	/**
	 * 同步的http请求
	 */
	public QResponse post(String url);

	/**
	 * 同步的http请求
	 */
	public QResponse get(String url);
	
	/**
	 * 异步的http请求(get)
	 */
	public void execute(String reqId, String url, List<QHttpHeader> headers, RequestHandler
			handler);

	/**
	 * 异步的http请求(get),带passthrough
	 */
	public void execute(String reqId, String url, List<QHttpHeader> headers, RequestHandler
			handler, Map<String, Object> passthrough);

	/**
	 * 异步的http请求(post)
	 */
	public void executeByPost(String reqId, String url, Map<String, String> params,
							  List<QHttpHeader> headers, RequestHandler handler);
	/**
	 * 异步的http请求(post)
	 */
	public void executeByPost(String reqId, String url, String body, List<QHttpHeader> headers,
							  RequestHandler handler);


	/**
	 * 异步的表单上传请求(post)
	 */
	public void executeFormSubmit(String reqId, String url, Map<String, String> strParams,
								  List<QFileEntry> fileParams, List<QHttpHeader> headers,
								  RequestHandler handler);
	
	public interface RequestHandler{
		public void onFinish(QResult resut, String reqId);
		public void onFail(QResponse response, String reqId);
	}
}
