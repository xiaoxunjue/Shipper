package com.revenant.shipper.bean.ShipperBean;

public class TokenLoginBean  {


    /**
     * code : 20
     * data : {}
     * msg : 登录过期，请重新登录
     * timestamp : 0
     */

    private String code;
    private DataBean data;
    private String msg;
    private int timestamp;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public int getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(int timestamp) {
        this.timestamp = timestamp;
    }

    public static class DataBean {
    }
}
