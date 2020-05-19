package com.revenant.shipper.bean.ShipperBean;

import java.util.List;

/**
 * @ProjectName: Shipper
 * @Package: com.revenant.shipper.bean.ShipperBean
 * @ClassName: OrderListBean
 * @Description: java类作用描述
 * @Author: Revenant
 * @CreateDate: 2020-02-02 18:33
 * @UpdateUser: 更新者
 * @UpdateDate: 2020-02-02 18:33
 * @UpdateRemark: 更新说明
 * @Version: 1.0
 */
public class OrderListBean {


    /**
     * code : 0
     * msg : SUCCESS
     * sign :
     * timestamp : 0
     * data : {"total":1,"result":[{"id":1,"orderNo":"","goodsId":"","driverId":"","carId":"","isDel":"","ctime":"2020-01-15T08:16:36.000+0000","mtime":"2020-01-15T08:16:39.000+0000","despatchTime":"","receiptTime":"","amount":3000.56,"loading":"123","unload":"123","goodsType":"","status":0,"isBill":"","shipperId":""}],"pageNum":1,"pageSize":10,"pages":1,"size":1}
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
         * total : 1
         * result : [{"id":1,"orderNo":"","goodsId":"","driverId":"","carId":"","isDel":"","ctime":"2020-01-15T08:16:36.000+0000","mtime":"2020-01-15T08:16:39.000+0000","despatchTime":"","receiptTime":"","amount":3000.56,"loading":"123","unload":"123","goodsType":"","status":0,"isBill":"","shipperId":""}]
         * pageNum : 1
         * pageSize : 10
         * pages : 1
         * size : 1
         */

        private int total;
        private int              pageNum;
        private int              pageSize;
        private int              pages;
        private int              size;
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
             * orderNo :
             * goodsId :
             * driverId :
             * carId :
             * isDel :
             * ctime : 2020-01-15T08:16:36.000+0000
             * mtime : 2020-01-15T08:16:39.000+0000
             * despatchTime :
             * receiptTime :
             * amount : 3000.56
             * loading : 123
             * unload : 123
             * goodsType :
             * status : 0
             * isBill :
             * shipperId :
             */

            private int id;
            private String orderNo;
            private String goodsId;
            private String driverId;
            private String carId;
            private String isDel;
            private String ctime;
            private String mtime;
            private String despatchTime;
            private String receiptTime;
            private double amount;
            private String loading;
            private String unload;
            private String goodsType;
            private int    status;
            private String isBill;
            private String shipperId;

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

            public String getGoodsId() {
                return goodsId;
            }

            public void setGoodsId(String goodsId) {
                this.goodsId = goodsId;
            }

            public String getDriverId() {
                return driverId;
            }

            public void setDriverId(String driverId) {
                this.driverId = driverId;
            }

            public String getCarId() {
                return carId;
            }

            public void setCarId(String carId) {
                this.carId = carId;
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

            public String getIsBill() {
                return isBill;
            }

            public void setIsBill(String isBill) {
                this.isBill = isBill;
            }

            public String getShipperId() {
                return shipperId;
            }

            public void setShipperId(String shipperId) {
                this.shipperId = shipperId;
            }
        }
    }
}
