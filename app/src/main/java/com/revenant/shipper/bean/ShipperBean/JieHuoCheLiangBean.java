package com.revenant.shipper.bean.ShipperBean;

import java.util.List;

public class JieHuoCheLiangBean {


    /**
     * code : 0
     * msg : SUCCESS
     * sign : null
     * timestamp : 0
     * data : {"total":15,"result":[{"id":41,"carId":2,"driverId":1,"loadCode":"110101","unloadCode":"110100","loading":"东城区","unloading":"北京市","loadCodeDetails":null,"unloadCodeDetails":null,"carInfo":"18.0|重型货车","carNo":"京A88888","phone":"123456","ctime":null,"mtime":null,"isDel":null},{"id":42,"carId":1,"driverId":1,"loadCode":"110101","unloadCode":"110100","loading":"东城区","unloading":"北京市","loadCodeDetails":null,"unloadCodeDetails":null,"carInfo":"18.0|重型货车","carNo":"辽A123456","phone":"123456","ctime":null,"mtime":null,"isDel":null},{"id":44,"carId":9,"driverId":1,"loadCode":"110101","unloadCode":"110100","loading":"东城区","unloading":"北京市","loadCodeDetails":null,"unloadCodeDetails":null,"carInfo":"10.0|重型普通货车","carNo":"辽A77766","phone":"123456","ctime":null,"mtime":null,"isDel":null},{"id":45,"carId":8,"driverId":1,"loadCode":"110101","unloadCode":"110100","loading":"东城区","unloading":"北京市","loadCodeDetails":null,"unloadCodeDetails":null,"carInfo":"10.0|重型普通货车","carNo":"辽A77666","phone":"123456","ctime":null,"mtime":null,"isDel":null},{"id":46,"carId":10,"driverId":1,"loadCode":"110101","unloadCode":"110100","loading":"东城区","unloading":"北京市","loadCodeDetails":null,"unloadCodeDetails":null,"carInfo":"10.0|重型普通货车","carNo":"辽A77776","phone":"123456","ctime":null,"mtime":null,"isDel":null},{"id":47,"carId":11,"driverId":1,"loadCode":"110101","unloadCode":"110100","loading":"东城区","unloading":"北京市","loadCodeDetails":null,"unloadCodeDetails":null,"carInfo":"10.0|重型普通货车","carNo":"辽A77778","phone":"123456","ctime":null,"mtime":null,"isDel":null},{"id":48,"carId":12,"driverId":1,"loadCode":"110101","unloadCode":"110100","loading":"东城区","unloading":"北京市","loadCodeDetails":null,"unloadCodeDetails":null,"carInfo":"10.0|重型普通货车","carNo":"辽A77779","phone":"123456","ctime":null,"mtime":null,"isDel":null},{"id":49,"carId":13,"driverId":1,"loadCode":"110101","unloadCode":"110100","loading":"东城区","unloading":"北京市","loadCodeDetails":null,"unloadCodeDetails":null,"carInfo":"10.0|重型普通货车","carNo":"辽A77788","phone":"123456","ctime":null,"mtime":null,"isDel":null},{"id":50,"carId":14,"driverId":1,"loadCode":"110101","unloadCode":"110100","loading":"东城区","unloading":"北京市","loadCodeDetails":null,"unloadCodeDetails":null,"carInfo":"10.0|重型普通货车","carNo":"辽A77799","phone":"123456","ctime":null,"mtime":null,"isDel":null},{"id":51,"carId":15,"driverId":1,"loadCode":"110101","unloadCode":"110100","loading":"东城区","unloading":"北京市","loadCodeDetails":null,"unloadCodeDetails":null,"carInfo":"10.0|重型普通货车","carNo":"辽A77700","phone":"123456","ctime":null,"mtime":null,"isDel":null}],"pageNum":1,"pageSize":10,"pages":2,"size":10}
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
         * total : 15
         * result : [{"id":41,"carId":2,"driverId":1,"loadCode":"110101","unloadCode":"110100","loading":"东城区","unloading":"北京市","loadCodeDetails":null,"unloadCodeDetails":null,"carInfo":"18.0|重型货车","carNo":"京A88888","phone":"123456","ctime":null,"mtime":null,"isDel":null},{"id":42,"carId":1,"driverId":1,"loadCode":"110101","unloadCode":"110100","loading":"东城区","unloading":"北京市","loadCodeDetails":null,"unloadCodeDetails":null,"carInfo":"18.0|重型货车","carNo":"辽A123456","phone":"123456","ctime":null,"mtime":null,"isDel":null},{"id":44,"carId":9,"driverId":1,"loadCode":"110101","unloadCode":"110100","loading":"东城区","unloading":"北京市","loadCodeDetails":null,"unloadCodeDetails":null,"carInfo":"10.0|重型普通货车","carNo":"辽A77766","phone":"123456","ctime":null,"mtime":null,"isDel":null},{"id":45,"carId":8,"driverId":1,"loadCode":"110101","unloadCode":"110100","loading":"东城区","unloading":"北京市","loadCodeDetails":null,"unloadCodeDetails":null,"carInfo":"10.0|重型普通货车","carNo":"辽A77666","phone":"123456","ctime":null,"mtime":null,"isDel":null},{"id":46,"carId":10,"driverId":1,"loadCode":"110101","unloadCode":"110100","loading":"东城区","unloading":"北京市","loadCodeDetails":null,"unloadCodeDetails":null,"carInfo":"10.0|重型普通货车","carNo":"辽A77776","phone":"123456","ctime":null,"mtime":null,"isDel":null},{"id":47,"carId":11,"driverId":1,"loadCode":"110101","unloadCode":"110100","loading":"东城区","unloading":"北京市","loadCodeDetails":null,"unloadCodeDetails":null,"carInfo":"10.0|重型普通货车","carNo":"辽A77778","phone":"123456","ctime":null,"mtime":null,"isDel":null},{"id":48,"carId":12,"driverId":1,"loadCode":"110101","unloadCode":"110100","loading":"东城区","unloading":"北京市","loadCodeDetails":null,"unloadCodeDetails":null,"carInfo":"10.0|重型普通货车","carNo":"辽A77779","phone":"123456","ctime":null,"mtime":null,"isDel":null},{"id":49,"carId":13,"driverId":1,"loadCode":"110101","unloadCode":"110100","loading":"东城区","unloading":"北京市","loadCodeDetails":null,"unloadCodeDetails":null,"carInfo":"10.0|重型普通货车","carNo":"辽A77788","phone":"123456","ctime":null,"mtime":null,"isDel":null},{"id":50,"carId":14,"driverId":1,"loadCode":"110101","unloadCode":"110100","loading":"东城区","unloading":"北京市","loadCodeDetails":null,"unloadCodeDetails":null,"carInfo":"10.0|重型普通货车","carNo":"辽A77799","phone":"123456","ctime":null,"mtime":null,"isDel":null},{"id":51,"carId":15,"driverId":1,"loadCode":"110101","unloadCode":"110100","loading":"东城区","unloading":"北京市","loadCodeDetails":null,"unloadCodeDetails":null,"carInfo":"10.0|重型普通货车","carNo":"辽A77700","phone":"123456","ctime":null,"mtime":null,"isDel":null}]
         * pageNum : 1
         * pageSize : 10
         * pages : 2
         * size : 10
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
             * id : 41
             * carId : 2
             * driverId : 1
             * loadCode : 110101
             * unloadCode : 110100
             * loading : 东城区
             * unloading : 北京市
             * loadCodeDetails : null
             * unloadCodeDetails : null
             * carInfo : 18.0|重型货车
             * carNo : 京A88888
             * phone : 123456
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
            private int danWei=1;

            public int getDanwei() {
                return danWei;
            }

            public void setDanwei(int danwei) {
                this.danWei = danwei;
            }
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
}
