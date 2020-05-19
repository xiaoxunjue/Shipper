package com.revenant.shipper.bean.ShipperBean;

public class PersonalDetailBean {


    /**
     * code : 0
     * msg : SUCCESS
     * sign : null
     * timestamp : 0
     * data : {"id":3,"mobile":"13364221022","password":"123","username":"常宇","photo":"http://221.203.29.43/group1/M00/00/00/3csdK14eeAY5POAAEsbzEwe.jpg","type":1,"isVip":false,"isDel":false,"isLock":false,"ctime":"2020-01-02 15:52:37","mtime":"2020-01-02 15:52:40","auroraEquipment":"","status":null,"balance":546965}
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
         * mobile : 13364221022
         * password : 123
         * username : 常宇
         * photo : http://221.203.29.43/group1/M00/00/00/3csdK14eeAY5POAAEsbzEwe.jpg
         * type : 1
         * isVip : false
         * isDel : false
         * isLock : false
         * ctime : 2020-01-02 15:52:37
         * mtime : 2020-01-02 15:52:40
         * auroraEquipment :
         * status : null
         * balance : 546965
         */

        private int id;
        private String mobile;
        private String password;
        private String username;
        private String photo;
        private int type;
        private boolean isVip;
        private boolean isDel;
        private boolean isLock;
        private String ctime;
        private String mtime;
        private String auroraEquipment;
        private String status;
        private String balance;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getMobile() {
            return mobile;
        }

        public void setMobile(String mobile) {
            this.mobile = mobile;
        }

        public String getPassword() {
            return password;
        }

        public void setPassword(String password) {
            this.password = password;
        }

        public String getUsername() {
            return username;
        }

        public void setUsername(String username) {
            this.username = username;
        }

        public String getPhoto() {
            return photo;
        }

        public void setPhoto(String photo) {
            this.photo = photo;
        }

        public int getType() {
            return type;
        }

        public void setType(int type) {
            this.type = type;
        }

        public boolean isIsVip() {
            return isVip;
        }

        public void setIsVip(boolean isVip) {
            this.isVip = isVip;
        }

        public boolean isIsDel() {
            return isDel;
        }

        public void setIsDel(boolean isDel) {
            this.isDel = isDel;
        }

        public boolean isIsLock() {
            return isLock;
        }

        public void setIsLock(boolean isLock) {
            this.isLock = isLock;
        }

        public String getCtime() {
            return ctime;
        }

        public void setCtime(String ctime) {
            this.ctime = ctime;
        }

        public String getMtime() {
            return mtime;
        }

        public void setMtime(String mtime) {
            this.mtime = mtime;
        }

        public String getAuroraEquipment() {
            return auroraEquipment;
        }

        public void setAuroraEquipment(String auroraEquipment) {
            this.auroraEquipment = auroraEquipment;
        }

        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
        }

        public String getBalance() {
            return balance;
        }

        public void setBalance(String balance) {
            this.balance = balance;
        }
    }
}
