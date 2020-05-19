package com.revenant.shipper.bean.ShipperBean;

import java.util.List;

/**
 * @ProjectName: Shipper
 * @Package: com.revenant.shipper.bean.ShipperBean
 * @ClassName: ShuCheChaKanBean
 * @Description: java类作用描述
 * @Author: Revenant
 * @CreateDate: 2020-02-05 13:02
 * @UpdateUser: 更新者
 * @UpdateDate: 2020-02-05 13:02
 * @UpdateRemark: 更新说明
 * @Version: 1.0
 */
public class ShuCheChaKanBean {

    /**
     * code : 0
     * msg : SUCCESS
     * sign : null
     * timestamp : 0
     * data : {"id":5,"accountId":77,"member":"3,75","isDel":false,"ctime":"2020-02-12T07:51:05.000+0000","mtime":"2020-02-12T07:51:05.000+0000","accountList":[{"id":3,"mobile":"13364221022","password":null,"username":"王连丽","photo":"http://221.203.29.44/group1/M00/00/01/3csdLF5CdQyABO0_AABUUUcGVo880.jpeg","type":null,"isVip":null,"isDel":null,"isLock":null,"ctime":null,"mtime":null,"entName":null},{"id":75,"mobile":"13573123379","password":null,"username":null,"photo":null,"type":null,"isVip":null,"isDel":null,"isLock":null,"ctime":null,"mtime":null,"entName":null}]}
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
         * id : 5
         * accountId : 77
         * member : 3,75
         * isDel : false
         * ctime : 2020-02-12T07:51:05.000+0000
         * mtime : 2020-02-12T07:51:05.000+0000
         * accountList : [{"id":3,"mobile":"13364221022","password":null,"username":"王连丽","photo":"http://221.203.29.44/group1/M00/00/01/3csdLF5CdQyABO0_AABUUUcGVo880.jpeg","type":null,"isVip":null,"isDel":null,"isLock":null,"ctime":null,"mtime":null,"entName":null},{"id":75,"mobile":"13573123379","password":null,"username":null,"photo":null,"type":null,"isVip":null,"isDel":null,"isLock":null,"ctime":null,"mtime":null,"entName":null}]
         */

        private int id;
        private int accountId;
        private String member;
        private boolean isDel;
        private String ctime;
        private String mtime;
        private List<AccountListBean> accountList;

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

        public String getMember() {
            return member;
        }

        public void setMember(String member) {
            this.member = member;
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

        public String getMtime() {
            return mtime;
        }

        public void setMtime(String mtime) {
            this.mtime = mtime;
        }

        public List<AccountListBean> getAccountList() {
            return accountList;
        }

        public void setAccountList(List<AccountListBean> accountList) {
            this.accountList = accountList;
        }

        public static class AccountListBean {
            /**
             * id : 3
             * mobile : 13364221022
             * password : null
             * username : 王连丽
             * photo : http://221.203.29.44/group1/M00/00/01/3csdLF5CdQyABO0_AABUUUcGVo880.jpeg
             * type : null
             * isVip : null
             * isDel : null
             * isLock : null
             * ctime : null
             * mtime : null
             * entName : null
             */

            private int id;
            private String mobile;
            private Object password;
            private String username;
            private String photo;
            private Object type;
            private Object isVip;
            private Object isDel;
            private Object isLock;
            private Object ctime;
            private Object mtime;
            private Object entName;

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

            public Object getPassword() {
                return password;
            }

            public void setPassword(Object password) {
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

            public Object getType() {
                return type;
            }

            public void setType(Object type) {
                this.type = type;
            }

            public Object getIsVip() {
                return isVip;
            }

            public void setIsVip(Object isVip) {
                this.isVip = isVip;
            }

            public Object getIsDel() {
                return isDel;
            }

            public void setIsDel(Object isDel) {
                this.isDel = isDel;
            }

            public Object getIsLock() {
                return isLock;
            }

            public void setIsLock(Object isLock) {
                this.isLock = isLock;
            }

            public Object getCtime() {
                return ctime;
            }

            public void setCtime(Object ctime) {
                this.ctime = ctime;
            }

            public Object getMtime() {
                return mtime;
            }

            public void setMtime(Object mtime) {
                this.mtime = mtime;
            }

            public Object getEntName() {
                return entName;
            }

            public void setEntName(Object entName) {
                this.entName = entName;
            }
        }
    }
}
