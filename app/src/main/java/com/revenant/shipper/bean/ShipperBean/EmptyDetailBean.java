package com.revenant.shipper.bean.ShipperBean;

public class EmptyDetailBean {


    /**
     * code : 0
     * msg : SUCCESS
     * sign : null
     * timestamp : 0
     * data : {"driver_id":1,"mobile":"13390324422","count":1,"loading":"东城区","load_code":"110101","unload_code":"110100","accountId":1,"vehicle_number":"京A88888","id":41,"unloading":"北京市","car_info":"18.0|重型货车","car_id":2,"username":"吴琦"}
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
         * driver_id : 1
         * mobile : 13390324422
         * count : 1
         * loading : 东城区
         * load_code : 110101
         * unload_code : 110100
         * accountId : 1
         * vehicle_number : 京A88888
         * id : 41
         * unloading : 北京市
         * car_info : 18.0|重型货车
         * car_id : 2
         * username : 吴琦
         */

        private int driver_id;
        private String mobile;
        private int count;
        private String loading;
        private String load_code;
        private String unload_code;
        private int accountId;
        private String vehicle_number;
        private int id;
        private String unloading;
        private String car_info;
        private int car_id;
        private String username;

        public int getDriver_id() {
            return driver_id;
        }

        public void setDriver_id(int driver_id) {
            this.driver_id = driver_id;
        }

        public String getMobile() {
            return mobile;
        }

        public void setMobile(String mobile) {
            this.mobile = mobile;
        }

        public int getCount() {
            return count;
        }

        public void setCount(int count) {
            this.count = count;
        }

        public String getLoading() {
            return loading;
        }

        public void setLoading(String loading) {
            this.loading = loading;
        }

        public String getLoad_code() {
            return load_code;
        }

        public void setLoad_code(String load_code) {
            this.load_code = load_code;
        }

        public String getUnload_code() {
            return unload_code;
        }

        public void setUnload_code(String unload_code) {
            this.unload_code = unload_code;
        }

        public int getAccountId() {
            return accountId;
        }

        public void setAccountId(int accountId) {
            this.accountId = accountId;
        }

        public String getVehicle_number() {
            return vehicle_number;
        }

        public void setVehicle_number(String vehicle_number) {
            this.vehicle_number = vehicle_number;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getUnloading() {
            return unloading;
        }

        public void setUnloading(String unloading) {
            this.unloading = unloading;
        }

        public String getCar_info() {
            return car_info;
        }

        public void setCar_info(String car_info) {
            this.car_info = car_info;
        }

        public int getCar_id() {
            return car_id;
        }

        public void setCar_id(int car_id) {
            this.car_id = car_id;
        }

        public String getUsername() {
            return username;
        }

        public void setUsername(String username) {
            this.username = username;
        }
    }
}
