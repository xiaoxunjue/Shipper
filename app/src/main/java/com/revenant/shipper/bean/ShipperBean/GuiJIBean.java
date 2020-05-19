package com.revenant.shipper.bean.ShipperBean;

import java.util.List;

/**
 * @ProjectName: Shipper
 * @Package: com.revenant.shipper.bean.ShipperBean
 * @ClassName: GuiJIBean
 * @Description: java类作用描述
 * @Author: Revenant
 * @CreateDate: 2020-02-06 16:59
 * @UpdateUser: 更新者
 * @UpdateDate: 2020-02-06 16:59
 * @UpdateRemark: 更新说明
 * @Version: 1.0
 */
public class GuiJIBean {
    /**
     * code : 0
     * msg : SUCCESS
     * sign : null
     * timestamp : 0
     * data : ["[1,1]","[3,3]","[5,5]","[7,7]","[10,10]"]
     */

    private String code;
    private String       msg;
    private Object       sign;
    private int          timestamp;
    private List<String> data;

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

    public List<String> getData() {
        return data;
    }

    public void setData(List<String> data) {
        this.data = data;
    }
}
