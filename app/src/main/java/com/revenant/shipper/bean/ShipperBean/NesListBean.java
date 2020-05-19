package com.revenant.shipper.bean.ShipperBean;

import java.util.List;

/**
 * @ProjectName: Shipper
 * @Package: com.revenant.shipper.bean.ShipperBean
 * @ClassName: NesListBean
 * @Description: java类作用描述
 * @Author: Revenant
 * @CreateDate: 2020-02-02 10:52
 * @UpdateUser: 更新者
 * @UpdateDate: 2020-02-02 10:52
 * @UpdateRemark: 更新说明
 * @Version: 1.0
 */
public class NesListBean {


    /**
     * code : 0
     * msg : SUCCESS
     * sign : null
     * timestamp : 0
     * data : {"total":4,"result":[{"id":160,"accepter":78,"msgId":147,"title":"系统消息","shortMsg":"您申请货主实名认证已审核通过","type":0,"ctime":"2020-02-11T13:05:42.000+0000"},{"id":161,"accepter":78,"msgId":148,"title":"系统消息","shortMsg":"您申请货主实名认证已审核通过","type":0,"ctime":"2020-02-11T13:06:53.000+0000"},{"id":162,"accepter":78,"msgId":149,"title":"系统消息","shortMsg":"您申请货主实名认证已审核通过","type":0,"ctime":"2020-02-11T13:09:09.000+0000"},{"id":163,"accepter":78,"msgId":150,"title":"系统消息","shortMsg":"您申请货主实名认证已审核通过","type":0,"ctime":"2020-02-11T13:10:37.000+0000"}],"pageNum":1,"pageSize":10,"pages":1,"size":4}
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
         * total : 4
         * result : [{"id":160,"accepter":78,"msgId":147,"title":"系统消息","shortMsg":"您申请货主实名认证已审核通过","type":0,"ctime":"2020-02-11T13:05:42.000+0000"},{"id":161,"accepter":78,"msgId":148,"title":"系统消息","shortMsg":"您申请货主实名认证已审核通过","type":0,"ctime":"2020-02-11T13:06:53.000+0000"},{"id":162,"accepter":78,"msgId":149,"title":"系统消息","shortMsg":"您申请货主实名认证已审核通过","type":0,"ctime":"2020-02-11T13:09:09.000+0000"},{"id":163,"accepter":78,"msgId":150,"title":"系统消息","shortMsg":"您申请货主实名认证已审核通过","type":0,"ctime":"2020-02-11T13:10:37.000+0000"}]
         * pageNum : 1
         * pageSize : 10
         * pages : 1
         * size : 4
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
             * id : 160
             * accepter : 78
             * msgId : 147
             * title : 系统消息
             * shortMsg : 您申请货主实名认证已审核通过
             * type : 0
             * ctime : 2020-02-11T13:05:42.000+0000
             */

            private int id;
            private int accepter;
            private int msgId;
            private String title;
            private String shortMsg;
            private int type;
            private String ctime;

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public int getAccepter() {
                return accepter;
            }

            public void setAccepter(int accepter) {
                this.accepter = accepter;
            }

            public int getMsgId() {
                return msgId;
            }

            public void setMsgId(int msgId) {
                this.msgId = msgId;
            }

            public String getTitle() {
                return title;
            }

            public void setTitle(String title) {
                this.title = title;
            }

            public String getShortMsg() {
                return shortMsg;
            }

            public void setShortMsg(String shortMsg) {
                this.shortMsg = shortMsg;
            }

            public int getType() {
                return type;
            }

            public void setType(int type) {
                this.type = type;
            }

            public String getCtime() {
                return ctime;
            }

            public void setCtime(String ctime) {
                this.ctime = ctime;
            }
        }
    }
}
