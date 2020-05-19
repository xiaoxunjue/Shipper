package com.revenant.shipper.bean;

import java.util.List;

public class FaPiaoListItemBean {


    /**
     * code : 0
     * msg : SUCCESS
     * sign : null
     * timestamp : 0
     * data : {"total":4,"result":[{"id":40,"orderNo":"2020021415061324379","goodsId":35,"driverId":82,"carId":32,"isDel":0,"ctime":"2020-02-14T07:06:13.000+0000","mtime":"2020-02-14T07:06:13.000+0000","despatchTime":"2020-02-14T07:31:49.000+0000","receiptTime":"2020-02-14T07:55:51.000+0000","amount":130,"loading":"湖北省-荆州市-沙市区","unload":"江苏省-镇江市-京口区","goodsType":"不限","status":5,"isBill":true,"shipperId":null},{"id":39,"orderNo":"2020021414485837801","goodsId":36,"driverId":82,"carId":32,"isDel":0,"ctime":"2020-02-14T06:48:58.000+0000","mtime":"2020-02-14T06:48:58.000+0000","despatchTime":"2020-02-14T06:50:10.000+0000","receiptTime":"2020-02-14T07:36:15.000+0000","amount":130,"loading":"浙江省-金华市-婺城区","unload":"福建省-泉州市-鲤城区","goodsType":"不限","status":5,"isBill":true,"shipperId":null},{"id":32,"orderNo":"2020021414260023199","goodsId":35,"driverId":82,"carId":32,"isDel":0,"ctime":"2020-02-14T06:26:00.000+0000","mtime":"2020-02-14T06:26:00.000+0000","despatchTime":"2020-02-14T06:26:52.000+0000","receiptTime":"2020-02-14T06:27:03.000+0000","amount":130,"loading":"湖北省-荆州市-沙市区","unload":"江苏省-镇江市-京口区","goodsType":"不限","status":5,"isBill":true,"shipperId":null},{"id":31,"orderNo":"2020021413464758114","goodsId":36,"driverId":82,"carId":32,"isDel":0,"ctime":"2020-02-14T05:46:48.000+0000","mtime":"2020-02-14T05:46:48.000+0000","despatchTime":"2020-02-14T05:51:33.000+0000","receiptTime":"2020-02-14T06:05:00.000+0000","amount":130,"loading":"浙江省-金华市-婺城区","unload":"福建省-泉州市-鲤城区","goodsType":"不限","status":5,"isBill":true,"shipperId":null}],"pageNum":1,"pageSize":10,"pages":1,"size":4}
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
         * result : [{"id":40,"orderNo":"2020021415061324379","goodsId":35,"driverId":82,"carId":32,"isDel":0,"ctime":"2020-02-14T07:06:13.000+0000","mtime":"2020-02-14T07:06:13.000+0000","despatchTime":"2020-02-14T07:31:49.000+0000","receiptTime":"2020-02-14T07:55:51.000+0000","amount":130,"loading":"湖北省-荆州市-沙市区","unload":"江苏省-镇江市-京口区","goodsType":"不限","status":5,"isBill":true,"shipperId":null},{"id":39,"orderNo":"2020021414485837801","goodsId":36,"driverId":82,"carId":32,"isDel":0,"ctime":"2020-02-14T06:48:58.000+0000","mtime":"2020-02-14T06:48:58.000+0000","despatchTime":"2020-02-14T06:50:10.000+0000","receiptTime":"2020-02-14T07:36:15.000+0000","amount":130,"loading":"浙江省-金华市-婺城区","unload":"福建省-泉州市-鲤城区","goodsType":"不限","status":5,"isBill":true,"shipperId":null},{"id":32,"orderNo":"2020021414260023199","goodsId":35,"driverId":82,"carId":32,"isDel":0,"ctime":"2020-02-14T06:26:00.000+0000","mtime":"2020-02-14T06:26:00.000+0000","despatchTime":"2020-02-14T06:26:52.000+0000","receiptTime":"2020-02-14T06:27:03.000+0000","amount":130,"loading":"湖北省-荆州市-沙市区","unload":"江苏省-镇江市-京口区","goodsType":"不限","status":5,"isBill":true,"shipperId":null},{"id":31,"orderNo":"2020021413464758114","goodsId":36,"driverId":82,"carId":32,"isDel":0,"ctime":"2020-02-14T05:46:48.000+0000","mtime":"2020-02-14T05:46:48.000+0000","despatchTime":"2020-02-14T05:51:33.000+0000","receiptTime":"2020-02-14T06:05:00.000+0000","amount":130,"loading":"浙江省-金华市-婺城区","unload":"福建省-泉州市-鲤城区","goodsType":"不限","status":5,"isBill":true,"shipperId":null}]
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
             * id : 40
             * orderNo : 2020021415061324379
             * goodsId : 35
             * driverId : 82
             * carId : 32
             * isDel : 0
             * ctime : 2020-02-14T07:06:13.000+0000
             * mtime : 2020-02-14T07:06:13.000+0000
             * despatchTime : 2020-02-14T07:31:49.000+0000
             * receiptTime : 2020-02-14T07:55:51.000+0000
             * amount : 130
             * loading : 湖北省-荆州市-沙市区
             * unload : 江苏省-镇江市-京口区
             * goodsType : 不限
             * status : 5
             * isBill : true
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
            private String amount;
            private String loading;
            private String unload;
            private String goodsType;
            private int status;
            private boolean isBill;
            private Object shipperId;

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

            public String getAmount() {
                return amount;
            }

            public void setAmount(String amount) {
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
        }
    }
}
