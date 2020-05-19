package com.revenant.shipper.bean.ShipperBean;

/**
 * @ProjectName: Shipper
 * @Package: com.revenant.shipper.bean.ShipperBean
 * @ClassName: MobileBean
 * @Description: java类作用描述
 * @Author: Revenant
 * @CreateDate: 2020-02-05 13:20
 * @UpdateUser: 更新者
 * @UpdateDate: 2020-02-05 13:20
 * @UpdateRemark: 更新说明
 * @Version: 1.0
 */
public class MobileBean {
    /**
     * code : 0
     * msg : SUCCESS
     * sign : null
     * timestamp : 0
     * data : 1
     */

    private String code;
    private String msg;
    private Object sign;
    private int    timestamp;
    private int    data;

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

    public int getData() {
        return data;
    }

    public void setData(int data) {
        this.data = data;
    }
}
