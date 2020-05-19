package com.revenant.shipper.utils;


import android.content.Context;
import android.content.Intent;

import com.apkfuns.logutils.LogUtils;
import com.blankj.utilcode.util.AppUtils;
import com.blankj.utilcode.util.ToastUtils;
import com.lzy.okgo.OkGo;
import com.revenant.shipper.App;
import com.revenant.shipper.base.SplashActivity;
import com.revenant.shipper.bean.BaseBean.MessageEvent;
import com.revenant.shipper.ui.activity.LoginActivity;
import com.revenant.shipper.ui.activity.LoginTokenActivity;

import java.io.IOException;
import java.nio.charset.Charset;
import java.util.Arrays;
import java.util.List;


import cn.hutool.core.date.DateUtil;
import cn.hutool.crypto.Mode;
import cn.hutool.crypto.Padding;
import cn.hutool.crypto.SecureUtil;
import cn.hutool.crypto.symmetric.AES;
import okhttp3.Interceptor;
import okhttp3.MediaType;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.ResponseBody;
import okio.Buffer;
import okio.BufferedSource;

import static android.content.Intent.FLAG_ACTIVITY_NEW_TASK;
import static com.revenant.shipper.bean.BaseBean.MyEventCode.Token_LOGIN_SIGAL;
import static com.revenant.shipper.utils.Constants.Name_header_token;


public class TokenHeadeInterceptor implements Interceptor {
    private Context context;
    private List<String> netListUrl = Arrays.asList(
            "userLogin",
            "register",
            "sendSms",
            "mobileLogin",
            "outLogin",
            "forgetPass",
            "checkVersion",
            "selectEmptyCarList",
            "getAll",
            "file/upload",
            "findInfomasterList"
    );

    private boolean isneturl;
    private boolean isjiami = true;

    public TokenHeadeInterceptor(Context context) {
        this.context = context;
    }

    public TokenHeadeInterceptor() {
    }

    private static final AES aes = new AES(Mode.CTS, Padding.PKCS5Padding, "0CoJUm6Qyw8W8jud".getBytes(), "0102030405060708".getBytes());


    @Override
    public Response intercept(Chain chain) throws IOException {

        Request request = chain.request();
        Request.Builder newBuilder = request.newBuilder();
        if (SPUtils.getUserToken(App.getApplication()).isEmpty()) {

            newBuilder.removeHeader("sign");
            newBuilder.removeHeader("X-Token");
            newBuilder.removeHeader("timestamp");
        } else {
            String timestamp = String.valueOf(DateUtil.current(false));//
            newBuilder.addHeader("sign", SecureUtil.md5(SecureUtil.md5(SecureUtil.md5(timestamp) + timestamp) + timestamp));
            newBuilder.addHeader("X-Token", SPUtils.getUserToken(App.getApplication()));
            newBuilder.addHeader(Name_header_token, String.valueOf(DateUtil.current(false)));
            newBuilder.addHeader("timestamp", timestamp);
        }

        Response responseheader = chain.proceed(newBuilder.build());

        ResponseBody responseBody = responseheader.body();
        if (responseBody != null && responseheader.code() == 200) {
            BufferedSource source = responseBody.source();
            source.request(Long.MAX_VALUE);
            Buffer buffer = source.buffer();
            Charset charset = Charset.forName("UTF-8");
            MediaType contentType = responseBody.contentType();
            if (contentType != null) {
                charset = contentType.charset(charset);
            }
            String bodyString = buffer.clone().readString(charset);
            String urltoken;
//            if (isjiami){
//                /*部分添加加密*/
//                if (isneturl) {
//                    urltoken = bodyString;
//                } else {
//
//                    urltoken = decryptStr(bodyString);
//                }
//            }else {
//                /*全部加密*/
//                urltoken = decryptStr(bodyString);
//            }

            isneturl = false;
            for (int i = 0; i < netListUrl.size(); i++) {
                if (request.url().toString().contains(netListUrl.get(i))) {
                    isneturl = true;
                    break;
                }
            }

            LogUtils.d("判断加密数据" + bodyString);
            if (bodyString.startsWith("{")) {
                urltoken = bodyString;
            } else {
                urltoken = decryptStr(bodyString);
            }
//            if (isneturl) {
//                urltoken = bodyString;
//            } else {
//                urltoken = decryptStr(bodyString);
//            }
//            LogUtils.d("判断解密数据" + urltoken);


//            String urltoken = decryptStr(bodyString);
//            String urltoken = bodyString;

            ResponseBody responseBodyjiemi = ResponseBody.create(contentType, urltoken);
            responseheader = responseheader.newBuilder().body(responseBodyjiemi).build();
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
                    LogUtils.d("拦截器code:" + statuscode + " url:" + request.url());
//                    OkGo.getInstance().cancelAll();
                    ToastUtils.showShort("登录失效请重新登录");
                    SPUtils.clearUserToken(App.getApplication());
                    SPUtils.clearAccounId(App.getApplication());
                    SPUtils.clearUsermobile(App.getApplication());
                    SPUtils.clearJpRidToken(App.getApplication());
                    AppUtils.relaunchApp();
//                    ActivityUtils.returnToActivity(SplashActivity.class);
//                    Intent intent = new Intent();
//                    intent.setClass(context, SplashActivity.class);
//                    intent.setFlags(FLAG_ACTIVITY_NEW_TASK);
//                    com.blankj.utilcode.util.ActivityUtils.startActivity(intent);

//                    System.exit(0);
//                    OkGo.getInstance().cancelAll();
//                    SPUtils.clearUserToken(App.getApplication());
//                    SPUtils.clearAccounId(App.getApplication());
//                    ActivityUtils.removeAllActivity();
//                    Intent intent = new Intent();
//                    intent.setClass(context, SplashActivity.class);
//                    intent.setFlags(FLAG_ACTIVITY_NEW_TASK);
//                    context.startActivity(intent);
//                    responseheader.body().string();
//                    responseheader.body().string();
//                    responseheader.body().string();
//
//                    ActivityUtils.removeAllActivity();
////                    System.exit(0);
//                    Intent intent = new Intent();
//                    intent.setClass(context, SplashActivity.class);
//                    intent.setFlags(FLAG_ACTIVITY_NEW_TASK);
//                    context.startActivity(intent);
//                    OkGo.getInstance().cancelAll();

//                    Intent i = new Intent(context, SplashActivity.class);
//                    i.setAction(Intent.ACTION_MAIN);
//                    i.addCategory(Intent.CATEGORY_LAUNCHER);
//                    i.setFlags(FLAG_ACTIVITY_NEW_TASK);
//                    context.startActivity(i);
                }
            }
        }
//        BufferedSource source = responseBody.source();
//        source.request(Long.MAX_VALUE); // Buffer the entire body.
//        Buffer buffer = source.buffer();
//        Charset charset = Charset.forName("UTF-8");
//        MediaType contentType = responseBody.contentType();
//        if (contentType != null) {
//            charset = contentType.charset(charset);
//        }
//        String bodyString = buffer.clone().readString(charset);
//
//        String urltoken = decryptStr(bodyString);
//        int i = 0;
//        LogUtils.d("解密 -" + (i++) + urltoken);
//        ResponseBody responseBodyjiemi = ResponseBody.create(contentType, urltoken);
//        responseheader = responseheader.newBuilder().body(responseBodyjiemi).build();
//        if (urltoken.contains("code")) {
//            String[] split = urltoken.split(",");
//            String[] split1 = split[0].split(":");
//            String replaceAll = split1[1].replaceAll("\"", "");
//            Integer statuscode = Integer.valueOf(replaceAll);
//            if (statuscode == 20 ||
//                    statuscode == 21 ||
//                    statuscode == 24 ||
//                    statuscode == 25
//            ) {
//                System.exit(0);
//                ActivityUtils.removeAllActivity();
//                OkGo.getInstance().cancelAll();
//                SPUtils.clearUserToken(App.getApplication());
//                SPUtils.clearAccounId(App.getApplication());
//                Intent intent = new Intent();
//                intent.setClass(context, SplashActivity.class);
//                context.startActivity(intent);
//                ActivityUtils.removeAllActivity();
//                MessageEvent event = new MessageEvent(Token_LOGIN_SIGAL);
//                EventBusUtil.sendEvent(event);
//            }
//        }
        return responseheader;
    }

    public static String decryptStr(String content) {
        AESHelper aesDes = AESHelper.getInstance();
        String baseDecode = cn.hutool.core.codec.Base64.decodeStr(content);
        return aesDes.decrypt(baseDecode);

    }
}