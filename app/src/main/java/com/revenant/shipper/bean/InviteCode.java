package com.revenant.shipper.bean;

/**
 * @ProjectName: Shipper
 * @Package: com.revenant.shipper.bean
 * @ClassName: InviteCode
 * @Description: java类作用描述
 * @Author: Revenant
 * @CreateDate: 2020/5/14 10:53
 * @UpdateUser: 更新者
 * @UpdateDate: 2020/5/14 10:53
 * @UpdateRemark: 更新说明
 * @Version: 1.0
 */
public class InviteCode {


    /**
     * code : 0
     * msg : SUCCESS
     * sign : null
     * timestamp : 0
     * data : {"inviteCode":"2222"}
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
         * inviteCode : 2222
         */

        private String inviteCode;

        public String getInviteCode() {
            return inviteCode;
        }

        public void setInviteCode(String inviteCode) {
            this.inviteCode = inviteCode;
        }
    }
}
