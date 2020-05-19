package com.revenant.shipper.utils;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.revenant.shipper.R;
import com.revenant.shipper.base.BaseActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import wendu.dsbridge.DWebView;

public class WebContentActivity extends BaseActivity {
    @BindView(R.id.webtest)
    DWebView webtest;
    private String WebTest="http://web.cltkj.net//clt-wallet/privacypolicy.html";

    @Override
    public int getContentViewResId() {
        return R.layout.activity_web_content;
    }

    @Override
    public void initView() {
        setcenterTitle("用户协议");
        webtest.loadUrl(WebTest);
    }

    @Override
    public void leftbarclick() {
        finish();
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
}
