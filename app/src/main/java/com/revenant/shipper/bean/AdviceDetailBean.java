package com.revenant.shipper.bean;

/**
 * @ProjectName: Driver
 * @Package: com.revenant.driver.bean.Driver
 * @ClassName: AdviceDetailBean
 * @Description: java类作用描述
 * @Author: Revenant
 * @CreateDate: 2020/4/16 13:11
 * @UpdateUser: 更新者
 * @UpdateDate: 2020/4/16 13:11
 * @UpdateRemark: 更新说明
 * @Version: 1.0
 */
public class AdviceDetailBean {

    /**
     * code : 0
     * msg : SUCCESS
     * sign : null
     * timestamp : 0
     * data : {"sysComplaint":{"id":1,"status":"0","mtime":null,"ctime":"2020-04-15 00:00:00","pid":"0","accountId":"1","content":"222"},"reply":{"id":2,"status":"0","mtime":"2020-04-16 08:54:38","ctime":"2020-04-16 08:54:40","pid":"1","accountId":"97","content":"司机投诉意见1"}}
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
         * sysComplaint : {"id":1,"status":"0","mtime":null,"ctime":"2020-04-15 00:00:00","pid":"0","accountId":"1","content":"222"}
         * reply : {"id":2,"status":"0","mtime":"2020-04-16 08:54:38","ctime":"2020-04-16 08:54:40","pid":"1","accountId":"97","content":"司机投诉意见1"}
         */

        private SysComplaintBean sysComplaint;
        private ReplyBean reply;

        public SysComplaintBean getSysComplaint() {
            return sysComplaint;
        }

        public void setSysComplaint(SysComplaintBean sysComplaint) {
            this.sysComplaint = sysComplaint;
        }

        public ReplyBean getReply() {
            return reply;
        }

        public void setReply(ReplyBean reply) {
            this.reply = reply;
        }

        public static class SysComplaintBean {
            /**
             * id : 1
             * status : 0
             * mtime : null
             * ctime : 2020-04-15 00:00:00
             * pid : 0
             * accountId : 1
             * content : 222
             */

            private String id;
            private String status;
            private String mtime;
            private String ctime;
            private String pid;
            private String accountId;
            private String content;

            public String getId() {
                return id;
            }

            public void setId(String id) {
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

            public void setMtime(String mtime) {
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

        public static class ReplyBean {
            /**
             * id : 2
             * status : 0
             * mtime : 2020-04-16 08:54:38
             * ctime : 2020-04-16 08:54:40
             * pid : 1
             * accountId : 97
             * content : 司机投诉意见1
             */

            private String id;
            private String status;
            private String mtime;
            private String ctime;
            private String pid;
            private String accountId;
            private String content;

            public String getId() {
                return id;
            }

            public void setId(String id) {
                this.id = id;
            }

            public String getStatus() {
                return status;
            }

            public void setStatus(String status) {
                this.status = status;
            }

            public String getMtime() {
                return mtime;
            }

            public void setMtime(String mtime) {
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
