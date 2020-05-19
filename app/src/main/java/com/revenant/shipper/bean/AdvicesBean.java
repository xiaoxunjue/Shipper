package com.revenant.shipper.bean;

import java.util.List;

/**
 * @ProjectName: Driver
 * @Package: com.revenant.driver.bean.Driver
 * @ClassName: AdvicesBean
 * @Description: java类作用描述
 * @Author: Revenant
 * @CreateDate: 2020/4/16 10:11
 * @UpdateUser: 更新者
 * @UpdateDate: 2020/4/16 10:11
 * @UpdateRemark: 更新说明
 * @Version: 1.0
 */
public class AdvicesBean {


    /**
     * code : 0
     * msg : SUCCESS
     * sign : null
     * timestamp : 0
     * data : {"total":1,"result":[{"id":1,"status":"0","mtime":null,"ctime":"2020-04-15 00:00:00","pid":"0","accountId":"1","content":"222"}],"pageNum":1,"pageSize":10,"pages":1,"size":1}
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
         * total : 1
         * result : [{"id":1,"status":"0","mtime":null,"ctime":"2020-04-15 00:00:00","pid":"0","accountId":"1","content":"222"}]
         * pageNum : 1
         * pageSize : 10
         * pages : 1
         * size : 1
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
             * id : 1
             * status : 0
             * mtime : null
             * ctime : 2020-04-15 00:00:00
             * pid : 0
             * accountId : 1
             * content : 222
             */

            private int id;
            private String status;
            private Object mtime;
            private String ctime;
            private String pid;
            private String accountId;
            private String content;

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public String getStatus() {
                return status;
            }

            public void setStatus(String status) {
                this.status = status;
            }

            public Object getMtime() {
                return mtime;
            }

            public void setMtime(Object mtime) {
                this.mtime = mtime;
            }

            public String getCtime() {
                return ctime;
            }

            public void setCtime(String ctime) {
                this.ctime = ctime;
            }

            public String getPid() {
                return pid;
            }

            public void setPid(String pid) {
                this.pid = pid;
            }

            public String getAccountId() {
                return accountId;
            }

            public void setAccountId(String accountId) {
                this.accountId = accountId;
            }

            public String getContent() {
                return content;
            }

            public void setContent(String content) {
                this.content = content;
            }
        }
    }
}
