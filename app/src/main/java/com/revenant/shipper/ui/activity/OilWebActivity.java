package com.revenant.shipper.ui.activity;

import android.content.Context;
import android.os.Bundle;
import android.view.KeyEvent;

import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.StringCallback;
import com.lzy.okgo.model.Response;
import com.revenant.shipper.R;
import com.revenant.shipper.base.BaseActivity;
import com.revenant.shipper.utils.Constants;
import com.revenant.shipper.utils.SPUtils;
import com.revenant.shipper.utils.apkUpdate.VersionUtils;

import butterknife.BindView;
import butterknife.ButterKnife;
import wendu.dsbridge.DWebView;

import static android.view.KeyEvent.KEYCODE_BACK;

public class OilWebActivity extends BaseActivity {
    @BindView(R.id.mywalletweb)
    DWebView mywalletweb;
    String weburl = "https://www.baidu.com/";
    String weburlwallet = "http://39.96.202.181:7580/clt-wallet/#/index?accountId=";
    private Context context;

    @Override
    public int getContentViewResId() {
        context = this;
        return R.layout.activity_oil_web;
    }

    @Override
    public void initView() {
        getUrl();
//        String maywallweburl = weburlwallet + SPUtils.getAccounId(context);
//        mywalletweb.loadUrl(maywallweburl);

//        //声明WebSettings子类
//        WebSettings webSettings = mywalletweb.getSettings();
//
////如果访问的页面中要与Javascript交互，则webview必须设置支持Javascript
//        webSettings.setJavaScriptEnabled(true);
//// 若加载的 html 里有JS 在执行动画等操作，会造成资源浪费（CPU、电量）
//// 在 onStop 和 onResume 里分别把 setJavaScriptEnabled() 给设置成 false 和 true 即可
//
////支持插件
//
////设置自适应屏幕，两者合用
//        webSettings.setUseWideViewPort(true); //将图片调整到适合webview的大小
//        webSettings.setLoadWithOverviewMode(true); // 缩放至屏幕的大小
//
////缩放操作
//        webSettings.setSupportZoom(true); //支持缩放，默认为true。是下面那个的前提。
//        webSettings.setBuiltInZoomControls(true); //设置内置的缩放控件。若为false，则该WebView不可缩放
//        webSettings.setDisplayZoomControls(false); //隐藏原生的缩放控件
//
////其他细节操作
//        webSettings.setCacheMode(WebSettings.LOAD_CACHE_ELSE_NETWORK); //关闭webview中缓存
//        webSettings.setAllowFileAccess(true); //设置可以访问文件
//        webSettings.setJavaScriptCanOpenWindowsAutomatically(true); //支持通过JS打开新窗口
//        webSettings.setLoadsImagesAutomatically(true); //支持自动加载图片
//        webSettings.setDefaultTextEncodingName("utf-8");//设置编码格式
    }

    @Override
    protected boolean isshowtitlebar() {
        return false;
    }

    @Override
    public void initData() {

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }

    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if ((keyCode == KEYCODE_BACK) && mywalletweb.canGoBack()) {
            mywalletweb.goBack();
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }

    private void getUrl() {
        OkGo.<String>post(Constants.Find_Oil)
                .params("mobile", SPUtils.getUserPhone(context))
                .execute(new StringCallback() {
                    @Override
                    public void onSuccess(Response<String> response) {


                    }
                });
    }
}
