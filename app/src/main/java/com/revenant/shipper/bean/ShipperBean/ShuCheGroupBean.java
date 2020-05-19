package com.revenant.shipper.bean.ShipperBean;

import java.util.List;

/**
 * @ProjectName: Shipper
 * @Package: com.revenant.shipper.bean.ShipperBean
 * @ClassName: ShuCheGroupBean
 * @Description: java类作用描述
 * @Author: Revenant
 * @CreateDate: 2020-01-31 22:22
 * @UpdateUser: 更新者
 * @UpdateDate: 2020-01-31 22:22
 * @UpdateRemark: 更新说明
 * @Version: 1.0
 */
public class ShuCheGroupBean {
    /**
     * code : 0
     * msg : SUCCESS
     * sign : null
     * timestamp : 0
     * data : {"total":2,"result":[{"id":39,"accountId":77,"name":"我的","member":"","num":0,"isDel":0,"ctime":"2020-02-12 12:04:14","mtime":"2020-02-12 12:04:14","accountList":null},{"id":40,"accountId":77,"name":"你的","member":"","num":0,"isDel":0,"ctime":"2020-02-12 12:04:30","mtime":"2020-02-12 12:04:30","accountList":null}],"pageNum":1,"pageSize":10,"pages":1,"size":2}
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
         * total : 2
         * result : [{"id":39,"accountId":77,"name":"我的","member":"","num":0,"isDel":0,"ctime":"2020-02-12 12:04:14","mtime":"2020-02-12 12:04:14","accountList":null},{"id":40,"accountId":77,"name":"你的","member":"","num":0,"isDel":0,"ctime":"2020-02-12 12:04:30","mtime":"2020-02-12 12:04:30","accountList":null}]
         * pageNum : 1
         * pageSize : 10
         * pages : 1
         * size : 2
         */

        private int total;
        private int pageNum;
        private int pageSize;
        private int pages;
        private int size;
        private List<ResultBean> result;

        public int getTotal() {
            return total;
        }

        public void setTotal(int total) {
            this.total = total;
        }

        public int getPageNum() {
            return pageNum;
        }

        public void setPageNum(int pageNum) {
            this.pageNum = pageNum;
        }

        public int getPageSize() {
            return pageSize;
        }

        public void setPageSize(int pageSize) {
            this.pageSize = pageSize;
        }

        public int getPages() {
            return pages;
        }

        public void setPages(int pages) {
            this.pages = pages;
        }

        public int getSize() {
            return size;
        }

        public void setSize(int size) {
            this.size = size;
        }

        public List<ResultBean> getResult() {
            return result;
        }

        public void setResult(List<ResultBean> result) {
            this.result = result;
        }

        public static class ResultBean {
            /**
             * id : 39
             * accountId : 77
             * name : 我的
             * member :
             * num : 0
             * isDel : 0
             * ctime : 2020-02-12 12:04:14
             * mtime : 2020-02-12 12:04:14
             * accountList : null
             */

            private int id;
            private int accountId;
            private String name;
            private String member;
            private int num;
            private int isDel;
            private String ctime;
            private String mtime;
            private Object accountList;
            private boolean isIsselect=false;

            public boolean isIsselect() {
                return isIsselect;
            }

            public void setIsselect(boolean isselect) {
                isIsselect = isselect;
            }


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

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public String getMember() {
                return member;
            }

            public void setMember(String member) {
                this.member = member;
            }

            public int getNum() {
                return num;
            }

            public void setNum(int num) {
                this.num = num;
            }

            public int getIsDel() {
                return isDel;
            }

            public void setIsDel(int isDel) {
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

            public Object getAccountList() {
                return accountList;
            }

            public void setAccountList(Object accountList) {
                this.accountList = accountList;
            }
        }
    }

    /**
     * code : 0
     * msg : SUCCESS
     * sign : null
     * timestamp : 0
     * data : {"id":5,"accountId":77,"member":"3","isDel":false,"ctime":"2020-02-12T07:51:05.000+0000","mtime":"2020-02-12T07:51:05.000+0000","accountList":[{"id":3,"mobile":"13364221022","password":null,"username":"王连丽","photo":"http://221.203.29.44/group1/M00/00/01/3csdLF5CdQyABO0_AABUUUcGVo880.jpeg","type":null,"isVip":null,"isDel":null,"isLock":null,"ctime":null,"mtime":null,"entName":null}]}
     */

}
