package com.hecaibao88.dirtygame.http;

/**
 * @author wangguowei
 * @ClassName: Result
 * @Description: 网络请求返回结果
 * @date 15/11/1 下午11:21
 */
public class QResult {

    private String result;
    private String msg;
    private String mesid;
    private String data;
    private String body;

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String getMesid() {
        return mesid;
    }

    public void setMesid(String mesid) {
        this.mesid = mesid;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "{ data : " + data + "}";
    }
}
