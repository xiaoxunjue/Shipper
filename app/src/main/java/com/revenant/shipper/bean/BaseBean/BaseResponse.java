package com.revenant.shipper.bean.BaseBean;

import android.text.TextUtils;

import java.io.Serializable;

public class BaseResponse<T> implements Serializable {
    protected int code;
    protected String msg;
    protected T data;

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }


    public String getMsg() {
        if (!TextUtils.isEmpty(msg)) {
            return msg;
        }
        return "操作成功";
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    @Override
    public String toString() {
        return "BaseResponse{" +
                "code='" + code + '\'' +
                ", msg='" + msg + '\'' +
                ", data=" + data +
                '}';
    }

}
