package com.revenant.shipper.bean;

/**
 * @ProjectName: Shipper
 * @Package: com.revenant.shipper.bean
 * @ClassName: YanZhengma
 * @Description: java类作用描述
 * @Author: Revenant
 * @CreateDate: 2020-02-10 14:35
 * @UpdateUser: 更新者
 * @UpdateDate: 2020-02-10 14:35
 * @UpdateRemark: 更新说明
 * @Version: 1.0
 */
public class YanZhengma {
    /**
     * code : 0
     * msg : SUCCESS
     * sign : null
     * timestamp : 0
     * data : {"access_token":"01d618e4e4d9a24abe82421e8dc4e6f65215813164724333VzIO","UserAccountSub":{"id":53,"accountId":63,"level":0,"balance":0,"freeze":0,"payNo":"123378","agentId":0,"companyId":0,"ctime":"2020-01-21 11:31:48","mtime":"2020-01-21 11:31:48","isDel":false,"cancleCount":0,"status":0,"finStatus":1,"subAccount":null,"wand":null},"user":{"id":63,"mobile":"13573123378","password":"123456","username":null,"photo":null,"type":true,"isVip":false,"isDel":false,"isLock":false,"ctime":"2020-01-21T03:31:48.000+0000","mtime":"2020-01-21T03:31:48.000+0000","auroraEquipment":"","status":null,"openId":null}}
     */

    private String code;
    private String   msg;
    private Object   sign;
    private int      timestamp;
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
         * access_token : 01d618e4e4d9a24abe82421e8dc4e6f65215813164724333VzIO
         * UserAccountSub : {"id":53,"accountId":63,"level":0,"balance":0,"freeze":0,"payNo":"123378","agentId":0,"companyId":0,"ctime":"2020-01-21 11:31:48","mtime":"2020-01-21 11:31:48","isDel":false,"cancleCount":0,"status":0,"finStatus":1,"subAccount":null,"wand":null}
         * user : {"id":63,"mobile":"13573123378","password":"123456","username":null,"photo":null,"type":true,"isVip":false,"isDel":false,"isLock":false,"ctime":"2020-01-21T03:31:48.000+0000","mtime":"2020-01-21T03:31:48.000+0000","auroraEquipment":"","status":null,"openId":null}
         */

        private String access_token;
        private UserAccountSubBean UserAccountSub;
        private UserBean           user;

        public String getAccess_token() {
            return access_token;
        }

        public void setAccess_token(String access_token) {
            this.access_token = access_token;
        }

        public UserAccountSubBean getUserAccountSub() {
            return UserAccountSub;
        }

        public void setUserAccountSub(UserAccountSubBean UserAccountSub) {
            this.UserAccountSub = UserAccountSub;
        }

        public UserBean getUser() {
            return user;
        }

        public void setUser(UserBean user) {
            this.user = user;
        }

        public static class UserAccountSubBean {
            /**
             * id : 53
             * accountId : 63
             * level : 0
             * balance : 0
             * freeze : 0
             * payNo : 123378
             * agentId : 0
             * companyId : 0
             * ctime : 2020-01-21 11:31:48
             * mtime : 2020-01-21 11:31:48
             * isDel : false
             * cancleCount : 0
             * status : 0
             * finStatus : 1
             * subAccount : null
             * wand : null
             */

            private int id;
            private int     accountId;
            private int     level;
            private int     balance;
            private String     freeze;
            private String  payNo;
            private int     agentId;
            private int     companyId;
            private String  ctime;
            private String  mtime;
            private boolean isDel;
            private int     cancleCount;
            private int     status;
            private int     finStatus;
            private Object  subAccount;
            private Object  wand;

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public int getAccountId() {
                return accountId;
            }

            public void setAccountId(int accountId) {
                this.accountId = accountId;
            }

            public int getLevel() {
                return level;
            }

            public void setLevel(int level) {
                this.level = level;
            }

            public int getBalance() {
                return balance;
            }

            public void setBalance(int balance) {
                this.balance = balance;
            }

            public String getFreeze() {
                return freeze;
            }

            public void setFreeze(String freeze) {
                this.freeze = freeze;
            }

            public String getPayNo() {
                return payNo;
            }

            public void setPayNo(String payNo) {
                this.payNo = payNo;
            }

            public int getAgentId() {
                return agentId;
            }

            public void setAgentId(int agentId) {
                this.agentId = agentId;
            }

            public int getCompanyId() {
                return companyId;
            }

            public void setCompanyId(int companyId) {
                this.companyId = companyId;
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

            public boolean isIsDel() {
                return isDel;
            }

            public void setIsDel(boolean isDel) {
                this.isDel = isDel;
            }

            public int getCancleCount() {
                return cancleCount;
            }

            public void setCancleCount(int cancleCount) {
                this.cancleCount = cancleCount;
            }

            public int getStatus() {
                return status;
            }

            public void setStatus(int status) {
                this.status = status;
            }

            public int getFinStatus() {
                return finStatus;
            }

            public void setFinStatus(int finStatus) {
                this.finStatus = finStatus;
            }

            public Object getSubAccount() {
                return subAccount;
            }

            public void setSubAccount(Object subAccount) {
                this.subAccount = subAccount;
            }

            public Object getWand() {
                return wand;
            }

            public void setWand(Object wand) {
                this.wand = wand;
            }
        }

        public static class UserBean {
            /**
             * id : 63
             * mobile : 13573123378
             * password : 123456
             * username : null
             * photo : null
             * type : true
             * isVip : false
             * isDel : false
             * isLock : false
             * ctime : 2020-01-21T03:31:48.000+0000
             * mtime : 2020-01-21T03:31:48.000+0000
             * auroraEquipment :
             * status : null
             * openId : null
             */

            private int id;
            private String  mobile;
            private String  password;
            private Object  username;
            private Object  photo;
            private boolean type;
            private boolean isVip;
            private boolean isDel;
            private boolean isLock;
            private String  ctime;
            private String  mtime;
            private String  auroraEquipment;
            private Object  status;
            private Object  openId;

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

            public Object getUsername() {
                return username;
            }

            public void setUsername(Object username) {
                this.username = username;
            }

            public Object getPhoto() {
                return photo;
            }

            public void setPhoto(Object photo) {
                this.photo = photo;
            }

            public boolean isType() {
                return type;
            }

            public void setType(boolean type) {
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

            public Object getStatus() {
                return status;
            }

            public void setStatus(Object status) {
                this.status = status;
            }

            public Object getOpenId() {
                return openId;
            }

            public void setOpenId(Object openId) {
                this.openId = openId;
            }
        }
    }
}
