package com.revenant.shipper.bean;

public class OrderDetailBean {



        /**
         * code : 0
         * msg : SUCCESS
         * sign : null
         * timestamp : 0
         * data : {"load":"辽宁 鞍山","unload":"辽宁 本溪","amount":10000,"carInfo":"18.0|重型货车","weight":100,"shipperPhoto":null,"firstbillPhoto":null,"secondbillPhoto":null,"loadDate":"2020-01-16 -2020-01-19","loadingDetail":"xxxxxxxxx","unloadingDetail":"sssssssss","shipperName":"王连丽","diverName":"吴琦","shipperMobile":"13364221022","shipperPhone":"\"hhhhhh\"","vehicleNumber":"辽A123456","shipperOrderNum":null,"shipperGoodsNum":15,"diverOrderNum":5}
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
             * load : 辽宁 鞍山
             * unload : 辽宁 本溪
             * amount : 10000
             * carInfo : 18.0|重型货车
             * weight : 100
             * shipperPhoto : null
             * firstbillPhoto : null
             * secondbillPhoto : null
             * loadDate : 2020-01-16 -2020-01-19
             * loadingDetail : xxxxxxxxx
             * unloadingDetail : sssssssss
             * shipperName : 王连丽
             * diverName : 吴琦
             * shipperMobile : 13364221022
             * shipperPhone : "hhhhhh"
             * vehicleNumber : 辽A123456
             * shipperOrderNum : null
             * shipperGoodsNum : 15
             * diverOrderNum : 5
             */

            private String load;
            private String unload;

            private String carInfo;
            private String    weight;
            private Object shipperPhoto;

            private String loadDate;
            private String loadingDetail;
            private String unloadingDetail;
            private String shipperName;
            private String diverName;
            private String shipperMobile;
            private String shipperPhone;
            private String vehicleNumber;
            private String firstbillPhoto;
            private String secondbillPhoto;
            private String shipperbillPhoto;

            private String firstbillContent;
            private String secondbillContent;
            private String shipperContent;
            private String danWei;
            private String    amount;
            public String getShipperbillPhoto() {
                return shipperbillPhoto;
            }

            public void setShipperbillPhoto(String shipperbillPhoto) {
                this.shipperbillPhoto = shipperbillPhoto;
            }

            public String getFirstbillContent() {
                return firstbillContent;
            }

            public void setFirstbillContent(String firstbillContent) {
                this.firstbillContent = firstbillContent;
            }

            public String getSecondbillContent() {
                return secondbillContent;
            }

            public void setSecondbillContent(String secondbillContent) {
                this.secondbillContent = secondbillContent;
            }

            public String getShipperContent() {
                return shipperContent;
            }

            public void setShipperContent(String shipperContent) {
                this.shipperContent = shipperContent;
            }

            public String getDanWei() {
                return danWei;
            }

            public void setDanWei(String danWei) {
                this.danWei = danWei;
            }



            public String getDiverScore() {
                return diverScore;
            }

            public void setDiverScore(String diverScore) {
                this.diverScore = diverScore;
            }

            private String diverScore;

            public String getDiverPhoto() {
                return diverPhoto;
            }

            public void setDiverPhoto(String diverPhoto) {
                this.diverPhoto = diverPhoto;
            }

            private String diverPhoto;

            public String getDiverRate() {
                return diverRate;
            }

            public void setDiverRate(String diverRate) {
                this.diverRate = diverRate;
            }

            private String diverRate;

            public String getDespathTime() {
                return despathTime;
            }

            public void setDespathTime(String despathTime) {
                this.despathTime = despathTime;
            }

            public String getShipperScore() {
                return shipperScore;
            }

            public void setShipperScore(String shipperScore) {
                this.shipperScore = shipperScore;
            }

            public String getOrderNo() {
                return orderNo;
            }

            public void setOrderNo(String orderNo) {
                this.orderNo = orderNo;
            }

            public String getShipperRate() {
                return shipperRate;
            }

            public void setShipperRate(String shipperRate) {
                this.shipperRate = shipperRate;
            }

            public String getReceipTime() {
                return receipTime;
            }

            public void setReceipTime(String receipTime) {
                this.receipTime = receipTime;
            }

            private String    despathTime;
            private String    shipperScore;
            private String    orderNo;
            private String    shipperRate;
            private String    receipTime;

            private String    shipperId;
            private String    diverId;

            public String getShipperId() {
                return shipperId;
            }

            public void setShipperId(String shipperId) {
                this.shipperId = shipperId;
            }

            public String getDiverId() {
                return diverId;
            }

            public void setDiverId(String diverId) {
                this.diverId = diverId;
            }

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            private String name;
            private Object shipperOrderNum;
            private int    shipperGoodsNum;
            private int    diverOrderNum;

            public String getLoad() {
                return load;
            }

            public void setLoad(String load) {
                this.load = load;
            }

            public String getUnload() {
                return unload;
            }

            public void setUnload(String unload) {
                this.unload = unload;
            }

            public String getAmount() {
                return amount;
            }

            public void setAmount(String amount) {
                this.amount = amount;
            }

            public String getCarInfo() {
                return carInfo;
            }

            public void setCarInfo(String carInfo) {
                this.carInfo = carInfo;
            }

            public String getWeight() {
                return weight;
            }

            public void setWeight(String weight) {
                this.weight = weight;
            }

            public Object getShipperPhoto() {
                return shipperPhoto;
            }

            public void setShipperPhoto(Object shipperPhoto) {
                this.shipperPhoto = shipperPhoto;
            }

            public String getFirstbillPhoto() {
                return firstbillPhoto;
            }

            public void setFirstbillPhoto(String firstbillPhoto) {
                this.firstbillPhoto = firstbillPhoto;
            }

            public String getSecondbillPhoto() {
                return secondbillPhoto;
            }

            public void setSecondbillPhoto(String secondbillPhoto) {
                this.secondbillPhoto = secondbillPhoto;
            }

            public String getLoadDate() {
                return loadDate;
            }

            public void setLoadDate(String loadDate) {
                this.loadDate = loadDate;
            }

            public String getLoadingDetail() {
                return loadingDetail;
            }

            public void setLoadingDetail(String loadingDetail) {
                this.loadingDetail = loadingDetail;
            }

            public String getUnloadingDetail() {
                return unloadingDetail;
            }

            public void setUnloadingDetail(String unloadingDetail) {
                this.unloadingDetail = unloadingDetail;
            }

            public String getShipperName() {
                return shipperName;
            }

            public void setShipperName(String shipperName) {
                this.shipperName = shipperName;
            }

            public String getDiverName() {
                return diverName;
            }

            public void setDiverName(String diverName) {
                this.diverName = diverName;
            }

            public String getShipperMobile() {
                return shipperMobile;
            }

            public void setShipperMobile(String shipperMobile) {
                this.shipperMobile = shipperMobile;
            }

            public String getShipperPhone() {
                return shipperPhone;
            }

            public void setShipperPhone(String shipperPhone) {
                this.shipperPhone = shipperPhone;
            }

            public String getVehicleNumber() {
                return vehicleNumber;
            }

            public void setVehicleNumber(String vehicleNumber) {
                this.vehicleNumber = vehicleNumber;
            }

            public Object getShipperOrderNum() {
                return shipperOrderNum;
            }

            public void setShipperOrderNum(Object shipperOrderNum) {
                this.shipperOrderNum = shipperOrderNum;
            }

            public int getShipperGoodsNum() {
                return shipperGoodsNum;
            }

            public void setShipperGoodsNum(int shipperGoodsNum) {
                this.shipperGoodsNum = shipperGoodsNum;
            }

            public int getDiverOrderNum() {
                return diverOrderNum;
            }

            public void setDiverOrderNum(int diverOrderNum) {
                this.diverOrderNum = diverOrderNum;
            }
        }
    }


