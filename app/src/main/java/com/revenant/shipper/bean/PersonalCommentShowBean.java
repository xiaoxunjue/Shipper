package com.revenant.shipper.bean;

import java.util.List;

/**
 * @ProjectName: Driver
 * @Package: com.revenant.driver.bean
 * @ClassName: PersonalCommentShowBean
 * @Description: java类作用描述
 * @Author: Revenant
 * @CreateDate: 2020/4/18 8:29
 * @UpdateUser: 更新者
 * @UpdateDate: 2020/4/18 8:29
 * @UpdateRemark: 更新说明
 * @Version: 1.0
 */
public class PersonalCommentShowBean {


    /**
     * code : 0
     * msg : SUCCESS
     * sign : null
     * timestamp : 0
     * data : {"score":"0.0","rate":"0.0%","phone":"18899988805","evaluateCount":3,"orderCount":701,"ctime":"2020-02-16 13:50:00","photo":"http://221.203.29.44/group1/M00/00/7C/3csdLF5zN-eAWlwkAAFZpzB4jiA96.jpeg","userName":"测试五","list":[{"id":1,"shipperScore":8,"driverScore":1,"ctime":"2020-04-17T02:08:47.000+0000","orderId":1520,"shipperContent":"很好","driverContent":"很好1","shipperTime":"2020-04-17 13:40:38","driverTime":"2020-04-17 12:32:12","shipperId":87,"driverId":97,"shipperName":null,"driverName":"测试十五"},{"id":2,"shipperScore":6,"driverScore":10,"ctime":"2020-04-17T02:49:55.000+0000","orderId":110,"shipperContent":"很好","driverContent":"很好","shipperTime":"2020-04-17 13:39:52","driverTime":"2020-04-17 12:32:15","shipperId":87,"driverId":97,"shipperName":null,"driverName":"测试十五"},{"id":3,"shipperScore":10,"driverScore":10,"ctime":"2020-04-17T04:31:47.000+0000","orderId":1510,"shipperContent":"很好","driverContent":"很好","shipperTime":"2020-04-17 13:39:52","driverTime":"2020-04-17 12:32:04","shipperId":87,"driverId":97,"shipperName":null,"driverName":"测试十五"}],"goodsount":481,"status":1}
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
         * score : 0.0
         * rate : 0.0%
         * phone : 18899988805
         * evaluateCount : 3
         * orderCount : 701
         * ctime : 2020-02-16 13:50:00
         * photo : http://221.203.29.44/group1/M00/00/7C/3csdLF5zN-eAWlwkAAFZpzB4jiA96.jpeg
         * userName : 测试五
         * list : [{"id":1,"shipperScore":8,"driverScore":1,"ctime":"2020-04-17T02:08:47.000+0000","orderId":1520,"shipperContent":"很好","driverContent":"很好1","shipperTime":"2020-04-17 13:40:38","driverTime":"2020-04-17 12:32:12","shipperId":87,"driverId":97,"shipperName":null,"driverName":"测试十五"},{"id":2,"shipperScore":6,"driverScore":10,"ctime":"2020-04-17T02:49:55.000+0000","orderId":110,"shipperContent":"很好","driverContent":"很好","shipperTime":"2020-04-17 13:39:52","driverTime":"2020-04-17 12:32:15","shipperId":87,"driverId":97,"shipperName":null,"driverName":"测试十五"},{"id":3,"shipperScore":10,"driverScore":10,"ctime":"2020-04-17T04:31:47.000+0000","orderId":1510,"shipperContent":"很好","driverContent":"很好","shipperTime":"2020-04-17 13:39:52","driverTime":"2020-04-17 12:32:04","shipperId":87,"driverId":97,"shipperName":null,"driverName":"测试十五"}]
         * goodsount : 481
         * status : 1
         */

        private String score;
        private String rate;
        private String phone;
        private int evaluateCount;
        private int orderCount;
        private String ctime;
        private String photo;
        private String userName;
        private int goodsount;
        private int status;
        private List<ListBean> list;

        public String getScore() {
            return score;
        }

        public void setScore(String score) {
            this.score = score;
        }

        public String getRate() {
            return rate;
        }

        public void setRate(String rate) {
            this.rate = rate;
        }

        public String getPhone() {
            return phone;
        }

        public void setPhone(String phone) {
            this.phone = phone;
        }

        public int getEvaluateCount() {
            return evaluateCount;
        }

        public void setEvaluateCount(int evaluateCount) {
            this.evaluateCount = evaluateCount;
        }

        public int getOrderCount() {
            return orderCount;
        }

        public void setOrderCount(int orderCount) {
            this.orderCount = orderCount;
        }

        public String getCtime() {
            return ctime;
        }

        public void setCtime(String ctime) {
            this.ctime = ctime;
        }

        public String getPhoto() {
            return photo;
        }

        public void setPhoto(String photo) {
            this.photo = photo;
        }

        public String getUserName() {
            return userName;
        }

        public void setUserName(String userName) {
            this.userName = userName;
        }

        public int getGoodsount() {
            return goodsount;
        }

        public void setGoodsount(int goodsount) {
            this.goodsount = goodsount;
        }

        public int getStatus() {
            return status;
        }

        public void setStatus(int status) {
            this.status = status;
        }

        public List<ListBean> getList() {
            return list;
        }

        public void setList(List<ListBean> list) {
            this.list = list;
        }

        public static class ListBean {
            /**
             * id : 1
             * shipperScore : 8
             * driverScore : 1
             * ctime : 2020-04-17T02:08:47.000+0000
             * orderId : 1520
             * shipperContent : 很好
             * driverContent : 很好1
             * shipperTime : 2020-04-17 13:40:38
             * driverTime : 2020-04-17 12:32:12
             * shipperId : 87
             * driverId : 97
             * shipperName : null
             * driverName : 测试十五
             */

            private int id;
            private int shipperScore;
            private int driverScore;
            private String ctime;
            private int orderId;
            private String shipperContent;
            private String driverContent;
            private String shipperTime;
            private String driverTime;
            private int shipperId;
            private int driverId;
            private String shipperName;
            private String driverName;

            public String getShipperPhoto() {
                return shipperPhoto;
            }

            public void setShipperPhoto(String shipperPhoto) {
                this.shipperPhoto = shipperPhoto;
            }

            public String getDriverPhoto() {
                return driverPhoto;
            }

            public void setDriverPhoto(String driverPhoto) {
                this.driverPhoto = driverPhoto;
            }

            private String shipperPhoto;
            private String driverPhoto;

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public int getShipperScore() {
                return shipperScore;
            }

            public void setShipperScore(int shipperScore) {
                this.shipperScore = shipperScore;
            }

            public int getDriverScore() {
                return driverScore;
            }

            public void setDriverScore(int driverScore) {
                this.driverScore = driverScore;
            }

            public String getCtime() {
                return ctime;
            }

            public void setCtime(String ctime) {
                this.ctime = ctime;
            }

            public int getOrderId() {
                return orderId;
            }

            public void setOrderId(int orderId) {
                this.orderId = orderId;
            }

            public String getShipperContent() {
                return shipperContent;
            }

            public void setShipperContent(String shipperContent) {
                this.shipperContent = shipperContent;
            }

            public String getDriverContent() {
                return driverContent;
            }

            public void setDriverContent(String driverContent) {
                this.driverContent = driverContent;
            }

            public String getShipperTime() {
                return shipperTime;
            }

            public void setShipperTime(String shipperTime) {
                this.shipperTime = shipperTime;
            }

            public String getDriverTime() {
                return driverTime;
            }

            public void setDriverTime(String driverTime) {
                this.driverTime = driverTime;
            }

            public int getShipperId() {
                return shipperId;
            }

            public void setShipperId(int shipperId) {
                this.shipperId = shipperId;
            }

            public int getDriverId() {
                return driverId;
            }

            public void setDriverId(int driverId) {
                this.driverId = driverId;
            }

            public String getShipperName() {
                return shipperName;
            }

            public void setShipperName(String shipperName) {
                this.shipperName = shipperName;
            }

            public String getDriverName() {
                return driverName;
            }

            public void setDriverName(String driverName) {
                this.driverName = driverName;
            }
        }
    }
}
