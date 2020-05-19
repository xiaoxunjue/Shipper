package com.revenant.shipper.bean.ShipperBean;

import java.util.List;

/**
 * @ProjectName: Shipper
 * @Package: com.revenant.shipper.bean.ShipperBean
 * @ClassName: ShuCheZhiPaiBean
 * @Description: java类作用描述
 * @Author: Revenant
 * @CreateDate: 2020-02-01 09:47
 * @UpdateUser: 更新者
 * @UpdateDate: 2020-02-01 09:47
 * @UpdateRemark: 更新说明
 * @Version: 1.0
 */
public class ShuCheZhiPaiBean {

    /**
     * code : 0
     * msg : SUCCESS
     * sign : null
     * timestamp : 0
     * data : {"name":"软件1组","list":[{"id":1,"mobile":"13390324422","password":null,"username":"吴琦","photo":"\"dddddddddddddddddd\"","type":null,"isVip":null,"isDel":null,"isLock":null,"ctime":null,"mtime":null,"entName":null},{"id":2,"mobile":"18802426571","password":null,"username":"常宇","photo":"\"shiossss\"","type":null,"isVip":null,"isDel":null,"isLock":null,"ctime":null,"mtime":null,"entName":null}]}
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
         * name : 软件1组
         * list : [{"id":1,"mobile":"13390324422","password":null,"username":"吴琦","photo":"\"dddddddddddddddddd\"","type":null,"isVip":null,"isDel":null,"isLock":null,"ctime":null,"mtime":null,"entName":null},{"id":2,"mobile":"18802426571","password":null,"username":"常宇","photo":"\"shiossss\"","type":null,"isVip":null,"isDel":null,"isLock":null,"ctime":null,"mtime":null,"entName":null}]
         */

        private String name;
        private List<ListBean> list;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public List<ListBean> getList() {
            return list;
        }

        public void setList(List<ListBean> list) {
            this.list = list;
        }

        public static class ListBean {
            /**
             * id : 1
             * mobile : 13390324422
             * password : null
             * username : 吴琦
             * photo : "dddddddddddddddddd"
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
