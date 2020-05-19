package com.revenant.shipper.bean.ShipperBean;

import java.util.List;

public class NoLoginEmptyBean {


    /**
     * code : 0
     * msg : SUCCESS
     * sign : null
     * timestamp : 0
     * data : [{"id":143,"carId":48,"driverId":124,"loadCode":"330702","unloadCode":"340602","loading":"婺城区","unloading":"杜集区","loadCodeDetails":null,"unloadCodeDetails":null,"carInfo":"10.0|小型轿车|12.00","carNo":"鲁D28223","phone":"13101555965","ctime":null,"mtime":null,"isDel":null},{"id":130,"carId":3,"driverId":3,"loadCode":"210000,210300,210304","unloadCode":"0","loading":"辽宁省鞍山市立山区","unloading":"不限","loadCodeDetails":null,"unloadCodeDetails":null,"carInfo":"18.0|重型货车|1.00","carNo":"辽A55533","phone":"","ctime":null,"mtime":null,"isDel":null}]
     */

    private String code;
    private String msg;
    private Object sign;
    private int timestamp;
    private List<DataBean> data;

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

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * id : 143
         * carId : 48
         * driverId : 124
         * loadCode : 330702
         * unloadCode : 340602
         * loading : 婺城区
         * unloading : 杜集区
         * loadCodeDetails : null
         * unloadCodeDetails : null
         * carInfo : 10.0|小型轿车|12.00
         * carNo : 鲁D28223
         * phone : 13101555965
         * ctime : null
         * mtime : null
         * isDel : null
         */

        private int id;
        private int carId;
        private int driverId;
        private String loadCode;
        private String unloadCode;
        private String loading;
        private String unloading;
        private Object loadCodeDetails;
        private Object unloadCodeDetails;
        private String carInfo;
        private String carNo;
        private String phone;
        private Object ctime;
        private Object mtime;
        private Object isDel;

        public int getDanwei() {
            return danwei;
        }

        public void setDanwei(int danwei) {
            this.danwei = danwei;
        }

        private int danwei=1;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public int getCarId() {
            return carId;
        }

        public void setCarId(int carId) {
            this.carId = carId;
        }

        public int getDriverId() {
            return driverId;
        }

        public void setDriverId(int driverId) {
            this.driverId = driverId;
        }

        public String getLoadCode() {
            return loadCode;
        }

        public void setLoadCode(String loadCode) {
            this.loadCode = loadCode;
        }

        public String getUnloadCode() {
            return unloadCode;
        }

        public void setUnloadCode(String unloadCode) {
            this.unloadCode = unloadCode;
        }

        public String getLoading() {
            return loading;
        }

        public void setLoading(String loading) {
            this.loading = loading;
        }

        public String getUnloading() {
            return unloading;
        }

        public void setUnloading(String unloading) {
            this.unloading = unloading;
        }

        public Object getLoadCodeDetails() {
            return loadCodeDetails;
        }

        public void setLoadCodeDetails(Object loadCodeDetails) {
            this.loadCodeDetails = loadCodeDetails;
        }

        public Object getUnloadCodeDetails() {
            return unloadCodeDetails;
        }

        public void setUnloadCodeDetails(Object unloadCodeDetails) {
            this.unloadCodeDetails = unloadCodeDetails;
        }

        public String getCarInfo() {
            return carInfo;
        }

        public void setCarInfo(String carInfo) {
            this.carInfo = carInfo;
        }

        public String getCarNo() {
            return carNo;
        }

        public void setCarNo(String carNo) {
            this.carNo = carNo;
        }

        public String getPhone() {
            return phone;
        }

        public void setPhone(String phone) {
            this.phone = phone;
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

        public Object getIsDel() {
            return isDel;
        }

        public void setIsDel(Object isDel) {
            this.isDel = isDel;
        }
    }
}
