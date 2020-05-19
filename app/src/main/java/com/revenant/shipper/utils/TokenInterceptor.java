package com.revenant.shipper.utils;

import androidx.annotation.NonNull;

import com.apkfuns.logutils.LogUtils;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.model.HttpHeaders;
import com.manyi.mobile.lib.db.annotation.NotNull;
import com.revenant.shipper.App;
import com.revenant.shipper.bean.BaseBean.MessageEvent;
import com.revenant.shipper.bean.ShipperBean.TokenLoginBean;

import java.io.IOException;

import cn.hutool.core.date.DateUtil;
import cn.hutool.crypto.SecureUtil;
import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

import static com.revenant.shipper.bean.BaseBean.MyEventCode.Token_LOGIN_SIGAL;

public class TokenInterceptor implements Interceptor {
    @Override
    public Response intercept(Chain chain) throws IOException {

        Request request = chain.request();
//        Request.Builder header = request.newBuilder();
//        if (SPUtils.getUserToken(App.getApplication()).isEmpty()) {
//            header.removeHeader("X-Token");
//            header.removeHeader("sign");
//            header.removeHeader("timestamp");
//        }

        long t1 = System.nanoTime();

//        LogUtils.d(String.format("Sending request %s on %s%n%s", request.url(), chain.connection(), request.headers()));
//        LogUtils.d("----  response code --- url:", request.url(), chain.connection(), request.headers());
        Response response = chain.proceed(request);
//        LogUtils.d("----  response code --- Token:" + SPUtils.getUserToken(App.getApplication()));
        String userToken = SPUtils.getUserToken(App.getApplication());

//        HttpHeaders headers = new HttpHeaders();
//        if (!SPUtils.getUserToken(App.getApplication()).isEmpty()) {
//            String timestamp = String.valueOf(DateUtil.current(false));//header不支持中文，不允许有特殊字符
//            headers.put("sign", SecureUtil.md5(SecureUtil.md5(SecureUtil.md5(timestamp) + timestamp) + timestamp));   //header不支持中文，不允许有特殊字符
//            headers.put("timestamp", timestamp);
//            headers.put("X-Token", SPUtils.getUserToken(App.getApplication()));
//        }


        if (userToken.isEmpty()) {

            return response;
        } else {

//            String timestamp = String.valueOf(DateUtil.current(false));//header不支持中文，不允许有特殊字符
//        headers.put("sign", SecureUtil.md5(SecureUtil.md5(SecureUtil.md5(timestamp) + timestamp) + timestamp));   //header不支持中文，不允许有特殊字符
//        headers.put("timestamp", timestamp);
//        headers.put("X-Token", SPUtils.getUserToken(mContext));
            String timestamp = String.valueOf(DateUtil.current(false));//
            // header不支持中文，不允许有特殊字符
            Request updateRequest = request.newBuilder().
//                    header("X-Token", SPUtils.getUserToken(App.getApplication())).
//                    header("sign", SecureUtil.md5(SecureUtil.md5(SecureUtil.md5(timestamp) + timestamp) + timestamp)).
                    header("timestamp", timestamp).
                    build();

            long t2 = System.nanoTime();


//            LogUtils.d(String.format("----  response code   %s in %.1fms%n%s",
//                    response.request().url(), (t2 - t1) / 1e6d, response.headers()));

//            LogUtils.d("----  response code --- code:" + response.code());
            String urltoken = response.body().string();
//            LogUtils.d("----  response code --- body:" + urltoken);
            if (urltoken.contains("code")) {
                String[] split = urltoken.split(",");
                String[] split1 = split[0].split(":");
                String replaceAll = split1[1].replaceAll("\"", "");
                Integer statuscode = Integer.valueOf(replaceAll);
                if (statuscode == 20 ||
                        statuscode == 21 ||
                        statuscode == 24 ||
                        statuscode == 25
                ) {
                    OkGo.getInstance().cancelAll();
//                    SPUtils.clearUserToken(App.getApplication());
                    ActivityUtils.removeAllActivity();
//                    LogUtils.d("----  response code --- EventBusSend");
                    MessageEvent event = new MessageEvent(Token_LOGIN_SIGAL);
                    EventBusUtil.sendEvent(event);
                }
            } else {

            }
            return chain.proceed(updateRequest);
        }
    }
//    @Override
//    public Response intercept(Interceptor.Chain chain) throws IOException {
//        Response response = chain.proceed(chain.request());
//        int code = response.code();
//        if (code == 401) {
//            InterceptorUtil.InterceptorCallback callBack = InterceptorUtil.getInstance().getCallBack();
//            if (callBack != null) {
//                callBack.on401();
//            }
//        }
//        return response;
//    }
}