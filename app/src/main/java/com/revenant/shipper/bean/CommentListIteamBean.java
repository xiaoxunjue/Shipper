package com.revenant.shipper.bean;

import java.util.List;

/**
 * @ProjectName: Driver
 * @Package: com.revenant.driver.bean
 * @ClassName: CommentListIteamBean
 * @Description: java类作用描述
 * @Author: Revenant
 * @CreateDate: 2020/4/16 17:57
 * @UpdateUser: 更新者
 * @UpdateDate: 2020/4/16 17:57
 * @UpdateRemark: 更新说明
 * @Version: 1.0
 */
public class CommentListIteamBean {


    /**
     * code : 0
     * msg : SUCCESS
     * sign : null
     * timestamp : 0
     * data : {"total":1,"result":[{"id":1,"orderNo":"2020011619412411698","goodsId":1,"driverId":1,"carId":1,"isDel":0,"ctime":"2020-01-15 16:16:36","mtime":"2020-01-15T08:16:39.000+0000","despatchTime":"2020-01-14T08:16:41.000+0000","receiptTime":"2020-01-15T08:16:43.000+0000","amount":666601.002,"loading":"123","unload":"123","goodsType":"石头","status":6,"isBill":false,"shipperId":null}],"pageNum":1,"pageSize":10,"pages":1,"size":1}
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
         * result : [{"id":1,"orderNo":"2020011619412411698","goodsId":1,"driverId":1,"carId":1,"isDel":0,"ctime":"2020-01-15 16:16:36","mtime":"2020-01-15T08:16:39.000+0000","despatchTime":"2020-01-14T08:16:41.000+0000","receiptTime":"2020-01-15T08:16:43.000+0000","amount":666601.002,"loading":"123","unload":"123","goodsType":"石头","status":6,"isBill":false,"shipperId":null}]
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
             * orderNo : 2020011619412411698
             * goodsId : 1
             * driverId : 1
             * carId : 1
             * isDel : 0
             * ctime : 2020-01-15 16:16:36
             * mtime : 2020-01-15T08:16:39.000+0000
             * despatchTime : 2020-01-14T08:16:41.000+0000
             * receiptTime : 2020-01-15T08:16:43.000+0000
             * amount : 666601.002
             * loading : 123
             * unload : 123
             * goodsType : 石头
             * status : 6
             * isBill : false
             * shipperId : null
             */

            private int id;
            private String orderNo;
            private int goodsId;
            private int driverId;
            private int carId;
            private int isDel;
            private String ctime;
            private String mtime;
            private String despatchTime;
            private String receiptTime;
            private double amount;
            private String loading;
            private String unload;
            private String goodsType;
            private int status;
            private boolean isBill;
            private Object shipperId;
            private String goodsInfo;
            private String driverName;

            public String getDriverPhoto() {
                return driverPhoto;
            }

            public void setDriverPhoto(String driverPhoto) {
                this.driverPhoto = driverPhoto;
            }

            public String getShipperPhoto() {
                return shipperPhoto;
            }

            public void setShipperPhoto(String shipperPhoto) {
                this.shipperPhoto = shipperPhoto;
            }

            private String driverPhoto;
            private String shipperName;
            private String shipperPhoto;

            public String getDriverName() {
                return driverName;
            }

            public void setDriverName(String driverName) {
                this.driverName = driverName;
            }

            public String getShipperName() {
                return shipperName;
            }

            public void setShipperName(String shipperName) {
                this.shipperName = shipperName;
            }

            public String getVehicleNumber() {
                return vehicleNumber;
            }

            public void setVehicleNumber(String vehicleNumber) {
                this.vehicleNumber = vehicleNumber;
            }

            private String vehicleNumber;

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public String getOrderNo() {
                return orderNo;
            }

            public void setOrderNo(String orderNo) {
                this.orderNo = orderNo;
            }

            public int getGoodsId() {
                return goodsId;
            }

            public void setGoodsId(int goodsId) {
                this.goodsId = goodsId;
            }

            public int getDriverId() {
                return driverId;
            }

            public void setDriverId(int driverId) {
                this.driverId = driverId;
            }

            public int getCarId() {
                return carId;
            }

            public void setCarId(int carId) {
                this.carId = carId;
            }

            public int getIsDel() {
                return isDel;
            }

            public void setIsDel(int isDel) {
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

            public String getDespatchTime() {
                return despatchTime;
            }

            public void setDespatchTime(String despatchTime) {
                this.despatchTime = despatchTime;
            }

            public String getReceiptTime() {
                return receiptTime;
            }

            public void setReceiptTime(String receiptTime) {
                this.receiptTime = receiptTime;
            }

            public double getAmount() {
                return amount;
            }

            public void setAmount(double amount) {
                this.amount = amount;
            }

            public String getLoading() {
                return loading;
            }

            public void setLoading(String loading) {
                this.loading = loading;
            }

            public String getUnload() {
                return unload;
            }

            public void setUnload(String unload) {
                this.unload = unload;
            }

            public String getGoodsType() {
                return goodsType;
            }

            public void setGoodsType(String goodsType) {
                this.goodsType = goodsType;
            }

            public int getStatus() {
                return status;
            }

            public void setStatus(int status) {
                this.status = status;
            }

            public boolean isIsBill() {
                return isBill;
            }

            public void setIsBill(boolean isBill) {
                this.isBill = isBill;
            }

            public Object getShipperId() {
                return shipperId;
            }

            public void setShipperId(Object shipperId) {
                this.shipperId = shipperId;
            }

            public String getGoodsInfo() {
                return goodsInfo;
            }

            public void setGoodsInfo(String goodsInfo) {
                this.goodsInfo = goodsInfo;
            }
        }
    }
}
