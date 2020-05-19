package com.revenant.shipper.bean.ShipperBean;

import java.util.List;

/**
 * @ProjectName: Shipper
 * @Package: com.revenant.shipper.bean.ShipperBean
 * @ClassName: QueRenSiBean
 * @Description: java类作用描述
 * @Author: Revenant
 * @CreateDate: 2020-02-06 18:50
 * @UpdateUser: 更新者
 * @UpdateDate: 2020-02-06 18:50
 * @UpdateRemark: 更新说明
 * @Version: 1.0
 */
public class QueRenSiBean {
    /**
     * code : 0
     * msg : SUCCESS
     * sign : null
     * timestamp : 0
     * data : [{"id":2,"goodsId":18,"carId":1,"carNo":"辽A123456"}]
     */

    private String code;
    private String         msg;
    private Object         sign;
    private int            timestamp;
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
         * id : 2
         * goodsId : 18
         * carId : 1
         * carNo : 辽A123456
         */

        private int id;
        private int    goodsId;
        private int    carId;
        private String carNo;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public int getGoodsId() {
            return goodsId;
        }

        public void setGoodsId(int goodsId) {
            this.goodsId = goodsId;
        }

        public int getCarId() {
            return carId;
        }

        public void setCarId(int carId) {
            this.carId = carId;
        }

        public String getCarNo() {
            return carNo;
        }

        public void setCarNo(String carNo) {
            this.carNo = carNo;
        }
    }
}
