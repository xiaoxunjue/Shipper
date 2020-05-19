package com.revenant.shipper.bean.ShipperBean;

public class MyGoodsDetailBean {

    /**
     * code : 0
     * msg : SUCCESS
     * sign : null
     * timestamp : 0
     * data : {"jiaoyishu":5,"fahuoshu":14,"info":{"id":3,"name":"货物名称2","type":"1","loading":"装货地点2","loadingCode":"0411","unload":"卸车地点1","unloadCode":"0411","weight":7,"cube":1,"total":2,"accountId":3,"status":true,"ctime":"2020-01-07T08:38:24.000+0000","mtime":"2020-01-16T12:04:23.000+0000","isDel":0,"isPublish":2,"despatchDate":"2020-01-16","recieveName":"cy","recieveMobile":"188024","isAuto":true,"isOnline":false,"price":2,"danWei":2,"vehicleLeader":"1.6米,普货,平板","companyName":"王*丽","loadingDetail":"xxixixi","unloadingDetail":"wewqewqe","loadDate":"1-7","isInvoice":true,"remark":"wewewqewqe"}}
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
         * jiaoyishu : 5
         * fahuoshu : 14
         * info : {"id":3,"name":"货物名称2","type":"1","loading":"装货地点2","loadingCode":"0411","unload":"卸车地点1","unloadCode":"0411","weight":7,"cube":1,"total":2,"accountId":3,"status":true,"ctime":"2020-01-07T08:38:24.000+0000","mtime":"2020-01-16T12:04:23.000+0000","isDel":0,"isPublish":2,"despatchDate":"2020-01-16","recieveName":"cy","recieveMobile":"188024","isAuto":true,"isOnline":false,"price":2,"danWei":2,"vehicleLeader":"1.6米,普货,平板","companyName":"王*丽","loadingDetail":"xxixixi","unloadingDetail":"wewqewqe","loadDate":"1-7","isInvoice":true,"remark":"wewewqewqe"}
         */

        private int jiaoyishu;
        private int fahuoshu;
        private InfoBean info;

        public int getJiaoyishu() {
            return jiaoyishu;
        }

        public void setJiaoyishu(int jiaoyishu) {
            this.jiaoyishu = jiaoyishu;
        }

        public int getFahuoshu() {
            return fahuoshu;
        }

        public void setFahuoshu(int fahuoshu) {
            this.fahuoshu = fahuoshu;
        }

        public InfoBean getInfo() {
            return info;
        }

        public void setInfo(InfoBean info) {
            this.info = info;
        }

        public static class InfoBean {
            /**
             * id : 3
             * name : 货物名称2
             * type : 1
             * loading : 装货地点2
             * loadingCode : 0411
             * unload : 卸车地点1
             * unloadCode : 0411
             * weight : 7
             * cube : 1
             * total : 2
             * accountId : 3
             * status : true
             * ctime : 2020-01-07T08:38:24.000+0000
             * mtime : 2020-01-16T12:04:23.000+0000
             * isDel : 0
             * isPublish : 2
             * despatchDate : 2020-01-16
             * recieveName : cy
             * recieveMobile : 188024
             * isAuto : true
             * isOnline : false
             * price : 2
             * danWei : 2
             * vehicleLeader : 1.6米,普货,平板
             * companyName : 王*丽
             * loadingDetail : xxixixi
             * unloadingDetail : wewqewqe
             * loadDate : 1-7
             * isInvoice : true
             * remark : wewewqewqe
             */

            private int id;
            private String name;
            private String type;
            private String loading;
            private String loadingCode;
            private String unload;
            private String unloadCode;
            private String weight;
            private String cube;
            private String total;
            private int accountId;
            private boolean status;
            private String ctime;
            private String mtime;
            private int isDel;
            private int isPublish;
            private String despatchDate;
            private String recieveName;
            private String recieveMobile;
            private boolean isAuto;
            private boolean isOnline;
            private String price;
            private int danWei;
            private String vehicleLeader;
            private String companyName;
            private String loadingDetail;
            private String unloadingDetail;
            private String loadDate;
            private boolean isInvoice;
            private String remark;

            public String getAmount() {
                return amount;
            }

            public void setAmount(String amount) {
                this.amount = amount;
            }

            private String    amount;

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public String getType() {
                return type;
            }

            public void setType(String type) {
                this.type = type;
            }

            public String getLoading() {
                return loading;
            }

            public void setLoading(String loading) {
                this.loading = loading;
            }

            public String getLoadingCode() {
                return loadingCode;
            }

            public void setLoadingCode(String loadingCode) {
                this.loadingCode = loadingCode;
            }

            public String getUnload() {
                return unload;
            }

            public void setUnload(String unload) {
                this.unload = unload;
            }

            public String getUnloadCode() {
                return unloadCode;
            }

            public void setUnloadCode(String unloadCode) {
                this.unloadCode = unloadCode;
            }

            public String getWeight() {
                return weight;
            }

            public void setWeight(String weight) {
                this.weight = weight;
            }

            public String getCube() {
                return cube;
            }

            public void setCube(String cube) {
                this.cube = cube;
            }

            public String getTotal() {
                return total;
            }

            public void setTotal(String total) {
                this.total = total;
            }

            public int getAccountId() {
                return accountId;
            }

            public void setAccountId(int accountId) {
                this.accountId = accountId;
            }

            public boolean isStatus() {
                return status;
            }

            public void setStatus(boolean status) {
                this.status = status;
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

            public int getIsDel() {
                return isDel;
            }

            public void setIsDel(int isDel) {
                this.isDel = isDel;
            }

            public int getIsPublish() {
                return isPublish;
            }

            public void setIsPublish(int isPublish) {
                this.isPublish = isPublish;
            }

            public String getDespatchDate() {
                return despatchDate;
            }

            public void setDespatchDate(String despatchDate) {
                this.despatchDate = despatchDate;
            }

            public String getRecieveName() {
                return recieveName;
            }

            public void setRecieveName(String recieveName) {
                this.recieveName = recieveName;
            }

            public String getRecieveMobile() {
                return recieveMobile;
            }

            public void setRecieveMobile(String recieveMobile) {
                this.recieveMobile = recieveMobile;
            }

            public boolean isIsAuto() {
                return isAuto;
            }

            public void setIsAuto(boolean isAuto) {
                this.isAuto = isAuto;
            }

            public boolean isIsOnline() {
                return isOnline;
            }

            public void setIsOnline(boolean isOnline) {
                this.isOnline = isOnline;
            }

            public String getPrice() {
                return price;
            }

            public void setPrice(String price) {
                this.price = price;
            }

            public int getDanWei() {
                return danWei;
            }

            public void setDanWei(int danWei) {
                this.danWei = danWei;
            }

            public String getVehicleLeader() {
                return vehicleLeader;
            }

            public void setVehicleLeader(String vehicleLeader) {
                this.vehicleLeader = vehicleLeader;
            }

            public String getCompanyName() {
                return companyName;
            }

            public void setCompanyName(String companyName) {
                this.companyName = companyName;
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

            public String getLoadDate() {
                return loadDate;
            }

            public void setLoadDate(String loadDate) {
                this.loadDate = loadDate;
            }

            public boolean isIsInvoice() {
                return isInvoice;
            }

            public void setIsInvoice(boolean isInvoice) {
                this.isInvoice = isInvoice;
            }

            public String getRemark() {
                return remark;
            }

            public void setRemark(String remark) {
                this.remark = remark;
            }
        }
    }
}
