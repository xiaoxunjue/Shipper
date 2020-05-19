package com.revenant.shipper.bean;

public class UpdateBean {


    /**
     * code : 0
     * msg : SUCCESS
     * sign : null
     * timestamp : 0
     * data : {"id":3,"title":"3","content":"3","version":"3","isAuto":false,"url":"3","isDel":false,"ctime":"2020-02-18T05:01:57.000+0000","utime":"2020-02-18T05:02:00.000+0000","platform":true,"update":true}
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
         * id : 3
         * title : 3
         * content : 3
         * version : 3
         * isAuto : false
         * url : 3
         * isDel : false
         * ctime : 2020-02-18T05:01:57.000+0000
         * utime : 2020-02-18T05:02:00.000+0000
         * platform : true
         * update : true
         */

        private int id;
        private String title;
        private String content;
        private String version;
        private boolean isAuto;
        private String url;
        private boolean isDel;
        private String ctime;
        private String utime;
        private boolean platform;
        private boolean update;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getContent() {
            return content;
        }

        public void setContent(String content) {
            this.content = content;
        }

        public String getVersion() {
            return version;
        }

        public void setVersion(String version) {
            this.version = version;
        }

        public boolean isIsAuto() {
            return isAuto;
        }

        public void setIsAuto(boolean isAuto) {
            this.isAuto = isAuto;
        }

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }

        public boolean isIsDel() {
            return isDel;
        }

        public void setIsDel(boolean isDel) {
            this.isDel = isDel;
        }

        public String getCtime() {
            return ctime;
        }

        public void setCtime(String ctime) {
            this.ctime = ctime;
        }

        public String getUtime() {
            return utime;
        }

        public void setUtime(String utime) {
            this.utime = utime;
        }

        public boolean isPlatform() {
            return platform;
        }

        public void setPlatform(boolean platform) {
            this.platform = platform;
        }

        public boolean isUpdate() {
            return update;
        }

        public void setUpdate(boolean update) {
            this.update = update;
        }
    }
}
