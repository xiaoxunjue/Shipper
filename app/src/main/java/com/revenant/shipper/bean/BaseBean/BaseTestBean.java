package com.revenant.shipper.bean.BaseBean;

/**
 * @ProjectName: Driver
 * @Package: com.revenant.driver.bean.BaseBean
 * @ClassName: BaseTestBean
 * @Description: java类作用描述
 * @Author: Revenant
 * @CreateDate: 2020/4/16 11:29
 * @UpdateUser: 更新者
 * @UpdateDate: 2020/4/16 11:29
 * @UpdateRemark: 更新说明
 * @Version: 1.0
 */
public class BaseTestBean {


    /**
     * code : 0
     * msg : SUCCESS
     * sign : null
     * timestamp : 0
     * data : null
     */

    private String code;
    private String msg;
    private Object sign;
    private int timestamp;
    private Object data;

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

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }
}
