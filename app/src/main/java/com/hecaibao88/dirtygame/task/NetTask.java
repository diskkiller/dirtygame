package com.hecaibao88.dirtygame.task;

import android.text.TextUtils;

import com.hecaibao88.dirtygame.http.QHttpClient;
import com.hecaibao88.dirtygame.http.QHttpClientImpl;
import com.hecaibao88.dirtygame.http.QHttpHeader;
import com.hecaibao88.dirtygame.utils.C;
import com.hecaibao88.dirtygame.utils.L;

import org.json.JSONObject;

import java.util.Iterator;
import java.util.List;
import java.util.Map;


/**
 * @author wangguowei
 * @ClassName: NetTask
 * @Description: 网络任务
 * @date 15/10/31 下午9:05
 */
public class NetTask {

    private static final String TAG = "nettask";


    public static final String STATUS_SUCCESS = "10000";
    public static final String TELGAME_LIST = "/game/dirty/alls";
    public static final String GAME_CF = "/game/dirty/cf";

    /**
     * 兑换金币
     */
    public static final String EXCHANGE_GOLDS = "/lotteryGoldsExchangeRecord/lottery/exchangeGoldsFromSystemToSP";
    /**
     * 查询商家库存  get
     */
    public static final String INTENT_QUERY = "/intant/merchant/query";
    /**
     * 支付   post
     */
    public static final String INTENT_ORDER = "/game/dirty/order";



    public static final String GAME_DIRTY = "/game/dirty/cell";

    /**
     * 执行网络请求  post请求
     *
     * @param reqId
     * @param url
     * @param handler
     */
    private static void executeRequestByPost(String reqId, String url, Map<String, String> params,
                                            QHttpClient.RequestHandler handler, List<QHttpHeader>
                                                    headers) {
        if (TextUtils.isEmpty(reqId) || TextUtils.isEmpty(url)) {
            com.hecaibao88.dirtygame.utils.L.debug(TAG, "错误的请求参数,reqId:" + reqId + ",url:" + url);
        }
        QHttpClient client = new QHttpClientImpl();
        com.hecaibao88.dirtygame.utils.L.debug(TAG, "reqId: " + reqId + " url: " + url);

        //        访问2.0版本服务器
        client.executeByPost(reqId, url, params, headers, handler);
    }


    public static void executeRequestByPost(String reqId, Map<String, String>
            params, QHttpClient.RequestHandler handler) {
        JSONObject jsonObject = new JSONObject(params);
        com.hecaibao88.dirtygame.utils.L.debug(TAG, "request jsonObject = " + jsonObject.toString());

        if (com.hecaibao88.dirtygame.utils.L.debug)
            executeRequestByPost(reqId, C.TEST_URL+reqId, params, handler, null);
        else
            executeRequestByPost(reqId, C.API_URL+reqId, params, handler, null);
    }

    public static void executeRequestByPostGlods(String reqId, Map<String, String>
            params, QHttpClient.RequestHandler handler) {
        JSONObject jsonObject = new JSONObject(params);
        com.hecaibao88.dirtygame.utils.L.debug(TAG, "request jsonObject = " + jsonObject.toString());

        if (com.hecaibao88.dirtygame.utils.L.debug)
            executeRequestByPost(reqId, C.TEST_EXCHANGE_GOLDS_URL+reqId, params, handler, null);
        else
            executeRequestByPost(reqId, C.API_EXCHANGE_GOLDS_URL+reqId, params, handler, null);
    }


    private static void executeRequestByGet(String reqId, String url,
                                            QHttpClient.RequestHandler handler, List<QHttpHeader>
                                                    headers) {
        if (TextUtils.isEmpty(reqId) || TextUtils.isEmpty(url)) {
            com.hecaibao88.dirtygame.utils.L.debug(TAG, "错误的请求参数,reqId:" + reqId + ",url:" + url);
        }


        QHttpClient client = new QHttpClientImpl();
        com.hecaibao88.dirtygame.utils.L.debug(TAG, "reqId: " + reqId + "url: " + url);

        client.execute(reqId, url, headers, handler);
    }

    public static void executeRequestByGet(String reqId, Map<String, String> params, QHttpClient.RequestHandler
            handler) {


        String url = null;
        if(params == null)
            if (L.debug)
                url = C.TEST_URL+reqId;
            else{
                url = C.API_URL+reqId;
            }
        else{
            com.hecaibao88.dirtygame.utils.L.debug(TAG, "request jsonObject = " + params.toString());
            url = getUrl(reqId,params);
        }

        executeRequestByGet(reqId,url,handler,null);
    }

    public static String getUrl(String reqId, Map<String, String> params) {
        String url ="";
        if (L.debug)
            url = C.TEST_URL+reqId;
        else{
            url = C.API_URL+reqId;
        }
        // 添加url参数
        if (params != null) {
            Iterator<String> it = params.keySet().iterator();
            StringBuffer sb = null;
            while (it.hasNext()) {
                String key = it.next();
                String value = params.get(key);
                if (sb == null) {
                    sb = new StringBuffer();
                    sb.append("?");
                } else {
                    sb.append("&");
                }
                sb.append(key);
                sb.append("=");
                sb.append(value);
            }
            url += sb.toString();
        }
        return url;
    }


}
