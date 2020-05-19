package com.revenant.shipper.bean;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.List;

public class GoodsInfoEditBean implements Parcelable {


    /**
     * code : 0
     * msg : SUCCESS
     * sign : null
     * timestamp : 0
     * data : {"jiaoyishu":16,"goodsRelatedList":[97,98],"phone":"13364221022","groupIdList":[97,98],"fahuoshu":26,"photo":"http://221.203.29.44/group1/M00/00/55/3csdLF5nS5-AHwppAAFS_Xxjzio39.jpeg","userName":"王连丽","info":{"id":2537,"name":"王胖子66","type":null,"loading":"辽宁 鞍山","loadingCode":"110101","unload":"辽宁 本溪","unloadCode":"110102","weight":100,"cube":0,"total":2,"accountId":3,"status":false,"ctime":"2020-03-17 11:27:40","mtime":"2020-03-17 11:27:40","isDel":0,"isPublish":1,"despatchDate":"2020-03-17","recieveName":"cy","recieveMobile":"123","isAuto":false,"isOnline":true,"price":100,"danWei":1,"vehicleLeader":"普通货车| 4.6米 | 煤炭及制品","companyName":"王*丽","loadingDetail":"xxxxxxxxx","unloadingDetail":"sssssssss","loadDate":"2020-01-16 -2020-01-19","isInvoice":true,"remark":"1122","isPlatform":2,"number":2}}
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
         * jiaoyishu : 16
         * goodsRelatedList : [97,98]
         * phone : 13364221022
         * groupIdList : [97,98]
         * fahuoshu : 26
         * photo : http://221.203.29.44/group1/M00/00/55/3csdLF5nS5-AHwppAAFS_Xxjzio39.jpeg
         * userName : 王连丽
         * info : {"id":2537,"name":"王胖子66","type":null,"loading":"辽宁 鞍山","loadingCode":"110101","unload":"辽宁 本溪","unloadCode":"110102","weight":100,"cube":0,"total":2,"accountId":3,"status":false,"ctime":"2020-03-17 11:27:40","mtime":"2020-03-17 11:27:40","isDel":0,"isPublish":1,"despatchDate":"2020-03-17","recieveName":"cy","recieveMobile":"123","isAuto":false,"isOnline":true,"price":100,"danWei":1,"vehicleLeader":"普通货车| 4.6米 | 煤炭及制品","companyName":"王*丽","loadingDetail":"xxxxxxxxx","unloadingDetail":"sssssssss","loadDate":"2020-01-16 -2020-01-19","isInvoice":true,"remark":"1122","isPlatform":2,"number":2}
         */

        private int jiaoyishu;
        private String phone;
        private int fahuoshu;
        private String photo;
        private String userName;
        private InfoBean info;
        private List<Integer> goodsRelatedList;
        private List<Integer> groupIdList;

        public int getJiaoyishu() {
            return jiaoyishu;
        }

        public void setJiaoyishu(int jiaoyishu) {
            this.jiaoyishu = jiaoyishu;
        }

        public String getPhone() {
            return phone;
        }

        public void setPhone(String phone) {
            this.phone = phone;
        }

        public int getFahuoshu() {
            return fahuoshu;
        }

        public void setFahuoshu(int fahuoshu) {
            this.fahuoshu = fahuoshu;
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

        public InfoBean getInfo() {
            return info;
        }

        public void setInfo(InfoBean info) {
            this.info = info;
        }

        public List<Integer> getGoodsRelatedList() {
            return goodsRelatedList;
        }

        public void setGoodsRelatedList(List<Integer> goodsRelatedList) {
            this.goodsRelatedList = goodsRelatedList;
        }

        public List<Integer> getGroupIdList() {
            return groupIdList;
        }

        public void setGroupIdList(List<Integer> groupIdList) {
            this.groupIdList = groupIdList;
        }

        public static class InfoBean {
            /**
             * id : 2537
             * name : 王胖子66
             * type : null
             * loading : 辽宁 鞍山
             * loadingCode : 110101
             * unload : 辽宁 本溪
             * unloadCode : 110102
             * weight : 100
             * cube : 0
             * total : 2
             * accountId : 3
             * status : false
             * ctime : 2020-03-17 11:27:40
             * mtime : 2020-03-17 11:27:40
             * isDel : 0
             * isPublish : 1
             * despatchDate : 2020-03-17
             * recieveName : cy
             * recieveMobile : 123
             * isAuto : false
             * isOnline : true
             * price : 100
             * danWei : 1
             * vehicleLeader : 普通货车| 4.6米 | 煤炭及制品
             * companyName : 王*丽
             * loadingDetail : xxxxxxxxx
             * unloadingDetail : sssssssss
             * loadDate : 2020-01-16 -2020-01-19
             * isInvoice : true
             * remark : 1122
             * isPlatform : 2
             * number : 2
             */

            private int id;
            private String name;
            private Object type;
            private String loading;
            private String loadingCode;
            private String unload;
            private String unloadCode;
            private String weight;
            private int cube;
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
            private int isPlatform;
            private int number;

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

            public Object getType() {
                return type;
            }

            public void setType(Object type) {
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

            public int getCube() {
                return cube;
            }

            public void setCube(int cube) {
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

            public int getIsPlatform() {
                return isPlatform;
            }

            public void setIsPlatform(int isPlatform) {
                this.isPlatform = isPlatform;
            }

            public int getNumber() {
                return number;
            }

            public void setNumber(int number) {
                this.number = number;
            }
        }
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.code);
        dest.writeString(this.msg);
        dest.writeParcelable((Parcelable) this.sign, flags);
        dest.writeInt(this.timestamp);
        dest.writeParcelable((Parcelable) this.data, flags);
    }

    public GoodsInfoEditBean() {
    }

    protected GoodsInfoEditBean(Parcel in) {
        this.code = in.readString();
        this.msg = in.readString();
        this.sign = in.readParcelable(Object.class.getClassLoader());
        this.timestamp = in.readInt();
        this.data = in.readParcelable(DataBean.class.getClassLoader());
    }

    public static final Parcelable.Creator<GoodsInfoEditBean> CREATOR = new Parcelable.Creator<GoodsInfoEditBean>() {
        @Override
        public GoodsInfoEditBean createFromParcel(Parcel source) {
            return new GoodsInfoEditBean(source);
        }

        @Override
        public GoodsInfoEditBean[] newArray(int size) {
            return new GoodsInfoEditBean[size];
        }
    };
}
