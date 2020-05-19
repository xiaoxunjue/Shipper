package com.revenant.shipper.bean.ShipperBean;

/**
 * @ProjectName: Shipper
 * @Package: com.revenant.shipper.bean.ShipperBean
 * @ClassName: ChaKanBangDan
 * @Description: java类作用描述
 * @Author: Revenant
 * @CreateDate: 2020-02-06 01:03
 * @UpdateUser: 更新者
 * @UpdateDate: 2020-02-06 01:03
 * @UpdateRemark: 更新说明
 * @Version: 1.0
 */
public class ChaKanBangDan {


    /**
     * code : 0
     * msg : SUCCESS
     * sign :
     * timestamp : 0
     * data : {"id":"","orderId":"","shipperId":"","shipperContent":"","shipperPhoto":"","driverId":"","firstbillContent":"","firstbillPhoto":"","secondbillContent":"","secondbillPhoto":"","isDel":"","ctime":"","mtime":"","phone":"13390324422","unloadTime":""}
     */

    private String code;
    private String   msg;
    private String   sign;
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

    public String getSign() {
        return sign;
    }

    public void setSign(String sign) {
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
         * id :
         * orderId :
         * shipperId :
         * shipperContent :
         * shipperPhoto :
         * driverId :
         * firstbillContent :
         * firstbillPhoto :
         * secondbillContent :
         * secondbillPhoto :
         * isDel :
         * ctime :
         * mtime :
         * phone : 13390324422
         * unloadTime :
         */

        private String id;
        private String orderId;
        private String shipperId;
        private String shipperContent;
        private String shipperPhoto;
        private String driverId;
        private String firstbillContent;
        private String firstbillPhoto;
        private String secondbillContent;
        private String secondbillPhoto;
        private String isDel;
        private String ctime;
        private String mtime;
        private String phone;
        private String unloadTime;
        private int danWei;


        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getOrderId() {
            return orderId;
        }

        public void setOrderId(String orderId) {
            this.orderId = orderId;
        }

        public String getShipperId() {
            return shipperId;
        }

        public void setShipperId(String shipperId) {
            this.shipperId = shipperId;
        }

        public String getShipperContent() {
            return shipperContent;
        }

        public void setShipperContent(String shipperContent) {
            this.shipperContent = shipperContent;
        }

        public String getShipperPhoto() {
            return shipperPhoto;
        }

        public void setShipperPhoto(String shipperPhoto) {
            this.shipperPhoto = shipperPhoto;
        }

        public String getDriverId() {
            return driverId;
        }

        public void setDriverId(String driverId) {
            this.driverId = driverId;
        }

        public String getFirstbillContent() {
            return firstbillContent;
        }

        public void setFirstbillContent(String firstbillContent) {
            this.firstbillContent = firstbillContent;
        }

        public String getFirstbillPhoto() {
            return firstbillPhoto;
        }

        public void setFirstbillPhoto(String firstbillPhoto) {
            this.firstbillPhoto = firstbillPhoto;
        }

        public String getSecondbillContent() {
            return secondbillContent;
        }

        public void setSecondbillContent(String secondbillContent) {
            this.secondbillContent = secondbillContent;
        }

        public String getSecondbillPhoto() {
            return secondbillPhoto;
        }

        public void setSecondbillPhoto(String secondbillPhoto) {
            this.secondbillPhoto = secondbillPhoto;
        }

        public String getIsDel() {
            return isDel;
        }

        public void setIsDel(String isDel) {
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

        public String getPhone() {
            return phone;
        }

        public void setPhone(String phone) {
            this.phone = phone;
        }

        public String getUnloadTime() {
            return unloadTime;
        }

        public void setUnloadTime(String unloadTime) {
            this.unloadTime = unloadTime;
        }

        public int getDanWei() {
            return danWei;
        }

        public void setDanWei(int danWei) {
            this.danWei = danWei;
        }
    }
}
