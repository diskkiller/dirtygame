package com.hecaibao88.dirtygame.http;


import android.content.Intent;
import android.support.annotation.NonNull;

import com.hecaibao88.dirtygame.task.NetTask;
import com.hecaibao88.dirtygame.utils.L;
import com.squareup.okhttp.FormEncodingBuilder;
import com.squareup.okhttp.Headers;
import com.squareup.okhttp.MediaType;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.RequestBody;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Callable;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;


public class QHttpClientImpl implements QHttpClient {

	private static final String TAG = "NetTask";

	private QThreadExecutor qThreadExecutor = null;

	public QHttpClientImpl() {
		qThreadExecutor = QThreadExecutor.create("xy-", 2, 4);
	}

	
	@Override
	public QResponse post(String url, Map<String, String> params) {
		QResponse response=new QResponse();
		try {
			SyncPostRequest task = new SyncPostRequest(url, params);
			Future<QResponse> future = qThreadExecutor.submit(task);
			response = future.get(60, TimeUnit.SECONDS);
		} catch (Exception e) {
			response.setErrmsg(e.getMessage()); 
		}
		return response;
	}

	/**
	 * 同步的http请求
	 */
	@Override
	public QResponse post(String url) {
		QResponse response = post(url, null);
		return response;
	}

	/**
	 * 同步的http请求
	 */
	@Override
	public QResponse get(String url) {
		QResponse response=new QResponse();
		try {
			SyncGetRequest task = new SyncGetRequest(url);
			Future<QResponse> future = qThreadExecutor.submit(task);
			response = future.get(60, TimeUnit.SECONDS);
		} catch (Exception e) {
			response.setErrmsg(e.getMessage());
		}

		return response;
	}

	/**
	 * 异步的http请求，get方式
	 */
	@Override
	public void execute(String reqId, String url, List<QHttpHeader> headers, RequestHandler handler) {
		AsyncRequest request = new AsyncRequest(reqId,url,headers, handler,null);
		qThreadExecutor.execute(request);
	}

	/**
	 * 异步的http请求，get方式
	 */
	@Override
	public void execute(String reqId, String url, List<QHttpHeader> headers, RequestHandler handler, Map<String,Object> passthrough) {
		AsyncRequest request = new AsyncRequest(reqId,url,headers, handler,passthrough);
		qThreadExecutor.execute(request);
	}

	/**
	 * 异步的http请求，post方式
	 */
	@Override
	public void executeByPost(String reqId, String url, Map<String,String> params, List<QHttpHeader> headers, RequestHandler handler) {
		AsyncPostRequest request = new AsyncPostRequest(reqId,url,params,headers, handler);
		qThreadExecutor.execute(request);
	}

	/**
	 * 异步的http请求，post方式
	 */
	@Override
	public void executeByPost(String reqId, String url, String body, List<QHttpHeader> headers, RequestHandler handler) {
		AsyncPostRequest request = new AsyncPostRequest(reqId,url,body,headers, handler);
		qThreadExecutor.execute(request);
	}

	/**
	 * 异步的表单上传请求(post)
	 */
	@Override
	public void executeFormSubmit(String reqId, String url, Map<String,String> strParams, List<QFileEntry> fileParams, List<QHttpHeader> headers, RequestHandler handler){
		OkHttpClient client=getOkHttpClient();
		AsyncFormSubmitRequest request=new 	AsyncFormSubmitRequest(client,reqId,url,strParams,fileParams,headers,handler);
		qThreadExecutor.execute(request);
	}

	private static class SyncPostRequest implements Callable<QResponse> {
		private String url;
		private Map<String, String> params;
		
		public SyncPostRequest(String url, Map<String, String> params) {
			this.url = url;
			this.params = params;
		}

		@Override
		public QResponse call() throws Exception {
			QResponse response=new QResponse();
			try {
				OkHttpClient client = getOkHttpClient();
				FormEncodingBuilder builder=new FormEncodingBuilder();
				if (params != null) {
					for (Map.Entry<String, String> entry : params.entrySet()) {
						builder.add(entry.getKey(),entry.getValue());
					}
				}
				Request post=new Request.Builder().url(url).post(builder.build()).build();
				com.squareup.okhttp.Response r = client.newCall(post).execute();
				parse(r, response);
			} catch (Exception e) {
				e.printStackTrace();
				response.setErrmsg(e.getMessage()+""); 
			}

			return response;

		}
	}

	private static class SyncGetRequest implements Callable<QResponse> {
		private String url;

		public SyncGetRequest(String url) {
			this.url = url;
		}

		@Override
		public QResponse call() throws Exception {
			return doGet(url);

		}

	}

	private static class AsyncPostRequest extends QAsyncTask {
		private String url;
		private QResponse response = null;
		private RequestHandler handler;
		private String reqId;
		private List<QHttpHeader> headers=null;
		private Map<String,String> params;
		private String body;
		private MediaType mediaType = MediaType.parse("application/octet-stream; charset=utf-8");
		private String jsondata = "";

		public AsyncPostRequest(String reqId, String url, Map<String,String> params, List<QHttpHeader> headers, RequestHandler handler) {
			this.reqId=reqId;
			this.url = url;
			this.headers=headers;
			this.handler = handler;
			this.params = params;
		}
		public AsyncPostRequest(String reqId, String url, String body, List<QHttpHeader> headers, RequestHandler handler) {
			this.reqId=reqId;
			this.url = url;
			this.headers=headers;
			this.handler = handler;
			this.body = body;
		}

		@Override
		public void doInBackground() {
			response = doPost2(url);
		}

		@Override
		public void onFinish(boolean canceled) {
			if (handler == null) {
				return;
			}
			if (response == null||response.getStatusCode()!=200) {
				handler.onFail(response,reqId);
			} else {
				try {
					QResult result = new QResult();
					if(response.getBody() != null){

						String body = response.getBody().toString();
						if(body!=null){
							L.debug(TAG,"response act = "+reqId+"    response body = "+body.toString());
							JSONObject object = new JSONObject(body);
							/*result.setResult(object.getString("state_code"));
							result.setMsg(object.getString("error_msg"));
							result.setData(object.getString("data"));
							result.setBody(body);*/
							if(reqId.equals(NetTask.EXCHANGE_GOLDS)){
								result.setResult(object.getString("code"));
								result.setMsg(object.getString("msg"));
							}else{
								result.setResult(object.getString("status"));
							}
							//result.setMsg(object.getString("error_msg"));
							result.setData(object.getString("data"));
							result.setBody(body);
						}
					}
					if(result.getMsg()!=null&&result.getMsg().equals("appkey error")){
						Intent intent = new Intent("");
					}

					handler.onFinish(result,reqId);
				} catch (JSONException e) {
					handler.onFail(response,reqId);
					e.printStackTrace();
				}
			}
		}

		QResponse doPost2(String url) {
			QResponse response=new QResponse();
			try {
				OkHttpClient client = getOkHttpClient();
				Request.Builder postBuilder=new Request.Builder().url(url);
				Request post = null;
				if(body!=null){
					RequestBody requestBody = RequestBody.create(mediaType,body);
					post=postBuilder.post(requestBody).build();
				}else{
					FormEncodingBuilder builder=new FormEncodingBuilder();

					if (params != null) {
						for (Map.Entry<String, String> entry : params.entrySet()) {
							builder.add(entry.getKey(),entry.getValue());
						}
					}


					if (headers!=null) {
						for(QHttpHeader header:headers){
							postBuilder.addHeader(header.name,header.value);
						}
					}
					post=postBuilder.post(builder.build()).build();
				}
				com.squareup.okhttp.Response r = client.newCall(post).execute();

				parse(r, response);
			} catch (Exception e) {
				e.printStackTrace();
				response.setErrmsg(e.getMessage()+"");
			}

			return response;
		}
	}

	@NonNull
	private static OkHttpClient getOkHttpClient() {
		OkHttpClient client = new OkHttpClient();
		client.setFollowRedirects(true);
		return client;
	}

	private static class AsyncRequest extends QAsyncTask {
		private String url;
		private QResponse response = null;
		private RequestHandler handler;
		private String reqId;
		private List<QHttpHeader> headers=null;
		private Map<String,Object> passthrough=null;
		private String jsondata = "";

		public AsyncRequest(String reqId, String url, List<QHttpHeader> headers, RequestHandler handler, Map<String,Object> passthrough) {
			this.reqId=reqId;
			this.url = url;
			this.headers=headers;
			this.handler = handler;
			this.passthrough=passthrough;
		}

		@Override
		public void doInBackground() {
			response = doGet2(url);
		}

		@Override
		public void onFinish(boolean canceled) {
			if (handler == null) {
				return;
			}

			if (response == null||response.getStatusCode()!=200) {
				handler.onFail(response,reqId);
			} else {
				try {
					QResult result = new QResult();
					if(response.getBody() != null){
						String body = response.getBody().toString();
						L.debug(TAG,"response act = "+reqId+"    response body = "+body);
						jsondata = body;
						JSONObject object = new JSONObject(body);
						result.setResult(object.getString("status"));
//						result.setMsg(object.getString("error_msg"));
						result.setData(object.getString("data"));
						result.setBody(jsondata);
					}

					handler.onFinish(result,reqId);
				} catch (JSONException e) {
					e.printStackTrace();
				}
			}
		}
		
		 QResponse doGet2(String url) {
			 QResponse response = new QResponse();
			 response.setPassthrough(passthrough);
			try {
				OkHttpClient client =getOkHttpClient();;
				Request.Builder getBuilder = new Request.Builder().url(url);
				 if (headers!=null) {
					 for(QHttpHeader header:headers){
						getBuilder.addHeader(header.name, header.value);
					 }
				}

				com.squareup.okhttp.Response r = client.newCall(getBuilder.build()).execute();
				parse(r, response);
			} catch (Exception e) {
				e.printStackTrace();
				if(response!=null){
					response.setErrmsg(e.getMessage());
				}
			}
			return response;
		}
	}

	private static QResponse doGet(String url) {
		 QResponse response = new QResponse();
		try {
			OkHttpClient client=getOkHttpClient();
			Request request=new Request.Builder().url(url).build();
			com.squareup.okhttp.Response r=client.newCall(request).execute();
			parse(r,response);
		} catch (Exception e) {
			e.printStackTrace();
			response.setErrmsg(e.getMessage());
		}
		return response;
	}
	
	private static QResponse parse(com.squareup.okhttp.Response hResp,QResponse r){
		try {
			int statusCode=-1;
				statusCode=hResp.code();
			Map<String,List<String>> headers=new HashMap<String,List<String>>();
			Headers   hs=hResp.headers();
			if (hs!=null){
				headers.putAll(hs.toMultimap());
			}
			String body= hResp.body().string();
			r.setStatusCode(statusCode);
			r.setHeaders(headers);
			r.setBody(body);
		} catch (Exception e) {
			e.printStackTrace();
			r.setErrmsg(e.getMessage());
		}
		return r;
	}
}
