package com.revenant.shipper.bean.ShipperBean;

/**
 * @ProjectName: Shipper
 * @Package: com.revenant.shipper.bean.ShipperBean
 * @ClassName: PayShowFirstBean
 * @Description: java类作用描述
 * @Author: Revenant
 * @CreateDate: 2020-02-06 13:09
 * @UpdateUser: 更新者
 * @UpdateDate: 2020-02-06 13:09
 * @UpdateRemark: 更新说明
 * @Version: 1.0
 */
public class PayShowFirstBean {


    /**
     * code : 0
     * msg : SUCCESS
     * sign : null
     * timestamp : 0
     * data : {"amount":6,"freeze":"1.20","balance":"49940.00","subAccount":"200201801000102"}
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
         * amount : 6
         * freeze : 1.20
         * balance : 49940.00
         * subAccount : 200201801000102
         */

        private String amount;
        private String freeze;
        private String balance;
        private String subAccount;

        public String getAmount() {
            return amount;
        }

        public void setAmount(String amount) {
            this.amount = amount;
        }

        public String getFreeze() {
            return freeze;
        }

        public void setFreeze(String freeze) {
            this.freeze = freeze;
        }

        public String getBalance() {
            return balance;
        }

        public void setBalance(String balance) {
            this.balance = balance;
        }

        public String getSubAccount() {
            return subAccount;
        }

        public void setSubAccount(String subAccount) {
            this.subAccount = subAccount;
        }
    }
}
