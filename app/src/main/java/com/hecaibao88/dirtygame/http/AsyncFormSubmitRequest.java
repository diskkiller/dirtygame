package com.hecaibao88.dirtygame.http;

import com.hecaibao88.dirtygame.utils.AESUtil;
import com.squareup.okhttp.Headers;
import com.squareup.okhttp.MediaType;
import com.squareup.okhttp.MultipartBuilder;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.RequestBody;
import com.squareup.okhttp.Response;

import org.json.JSONException;
import org.json.JSONObject;

import java.net.URLEncoder;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 表单提交请求
 */
public class AsyncFormSubmitRequest extends QAsyncTask {
    private String reqId;
    private String url;
    private Map<String, String> strParams;
    private List<QFileEntry> fileParams;
    private List<QHttpHeader> headers;
    private QHttpClient.RequestHandler handler;
    private OkHttpClient httpClient;
    private QResponse qResponse=new QResponse();
    private String jsondata;

    public AsyncFormSubmitRequest(OkHttpClient client, String reqId, String url, Map<String, String> strParams, List<QFileEntry> fileParams, List<QHttpHeader> headers, QHttpClient.RequestHandler handler) {
        this.httpClient = client;
        this.reqId = reqId;
        this.url = url;
        this.strParams = strParams;
        this.fileParams = fileParams;
        this.headers=headers;
        this.handler = handler;
    }

    @Override
    public void doInBackground() {
        try {
            MultipartBuilder mBuilder = new MultipartBuilder().type(MultipartBuilder.FORM);
            if (strParams != null && strParams.size() > 0) {
                for (Map.Entry<String, String> entry : strParams.entrySet()) {
                    String name = String.format("form-data; name=\"%s\"", entry.getKey());
                    mBuilder.addPart(Headers.of("Content-Disposition", name), RequestBody.create(null, entry.getValue()));
                }
            }

            if (fileParams != null && fileParams.size() > 0) {
                for (QFileEntry entry : fileParams) {
                    RequestBody fileBody = RequestBody.create(MediaType.parse("application/octet-stream"), entry.file);
                    String name = String.format("form-data; name=\"%s\"; filename=\"%s\"", entry.name, URLEncoder.encode(entry.file.getName(),"UTF-8")); //注意其中的空格等都有要求
                    mBuilder.addPart(Headers.of("Content-Disposition", name), fileBody);
                }
            }
            RequestBody requestBody = mBuilder.build();

            Request.Builder builder=new Request.Builder().url(this.url);
            if (this.headers!=null&&this.headers.size()>0){
                for (QHttpHeader header:headers){
                    builder.addHeader(header.name,header.value);
                }
            }

            Request request = builder.post(requestBody).build();

            Response response = httpClient.newCall(request).execute();
            parseResponse(response);
        }catch (Exception e){
            e.printStackTrace();
            qResponse.setErrmsg(e.getMessage());
        }
    }


    @Override
    public void onFinish(boolean canceled) {
        if (handler == null) {
            return;
        }

        if (qResponse == null||qResponse.getStatusCode()!=200) {
            handler.onFail(qResponse,reqId);
        } else {
            try {
                QResult result = new QResult();
                if(qResponse.getBody() != null){
                    String body = AESUtil.decrypt(qResponse.getBody().toString());
                    jsondata = body;
                    JSONObject object = new JSONObject(body);
                    result.setResult(object.getString("state_code"));
                    result.setMsg(object.getString("error_msg"));
                    result.setData(object.getString("data"));
                }

                handler.onFinish(result,reqId);
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }

    }

    private   void parseResponse(Response hResp){
        try {
            int statusCode=-1;
            statusCode=hResp.code();

            Map<String,List<String>> headers=new HashMap<String,List<String>>();
            Headers   hs=hResp.headers();
            if (hs!=null){
                headers.putAll(hs.toMultimap());
            }

            String body= hResp.body().string();
            qResponse.setStatusCode(statusCode);
            qResponse.setHeaders(headers);
            qResponse.setBody(body);
        } catch (Exception e) {
            e.printStackTrace();;
            qResponse.setErrmsg(e.getMessage());
        }
    }
}
