package com.revenant.shipper.bean.ShipperBean;

public class FileUploadBean {

    /**
     * code : 0
     * msg : SUCCESS
     * sign : null
     * timestamp : 0
     * data : {"url":"http://221.203.29.43group1/M00/00/01/3csdK14lSAyASyWfAArQq3jqvG8454.jpg"}
     */

    private String code;
    private String msg;
    private Object sign;
    private int timestamp;
    private DataBean data;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Object getSign() {
        return sign;
    }

    public void setSign(Object sign) {
        this.sign = sign;
    }

    public int getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(int timestamp) {
        this.timestamp = timestamp;
    }

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * url : http://221.203.29.43group1/M00/00/01/3csdK14lSAyASyWfAArQq3jqvG8454.jpg
         */

        private String url;

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }
    }
}
