package com.revenant.shipper.bean.ShipperBean;

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
public class NesListDetailBean {


    /**
     * code : 0
     * msg : SUCCESS
     * sign : null
     * timestamp : 0
     * data : {"msg":"司机：吴琦，接单成功，运单号：2020011710223615306,运送：王胖子,xxxxxxxxx-sssssssss,吴琦13390324422","createTime":"2020-01-17T02:22:37.000+0000","title":"私人消息"}
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
         * msg : 司机：吴琦，接单成功，运单号：2020011710223615306,运送：王胖子,xxxxxxxxx-sssssssss,吴琦13390324422
         * createTime : 2020-01-17T02:22:37.000+0000
         * title : 私人消息
         */

        private String msg;
        private String createTime;
        private String title;

        public String getMsg() {
            return msg;
        }

        public void setMsg(String msg) {
            this.msg = msg;
        }

        public String getCreateTime() {
            return createTime;
        }

        public void setCreateTime(String createTime) {
            this.createTime = createTime;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }
    }
}
