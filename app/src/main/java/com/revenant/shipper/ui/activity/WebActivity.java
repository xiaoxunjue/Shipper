package com.revenant.shipper.ui.activity;

import android.content.Context;
import android.os.Bundle;
import android.view.KeyEvent;

import com.revenant.shipper.R;
import com.revenant.shipper.base.BaseActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import wendu.dsbridge.DWebView;

import static android.view.KeyEvent.KEYCODE_BACK;

public class WebActivity extends BaseActivity {


    @BindView(R.id.mywalletweb)
    DWebView mywalletweb;
    private Context context;
    private String weburl="";

    @Override
    public int getContentViewResId() {
        context = this;
        return R.layout.activity_web;
    }

    @Override
    public void initView() {
        setcenterTitle("保险");
    }

    @Override
    public void leftbarclick() {
        super.leftbarclick();
        finish();
    }

    @Override
    public void initData() {
        weburl=getIntent().getStringExtra("weburl");
        mywalletweb.loadUrl(weburl);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if ((keyCode == KEYCODE_BACK) && mywalletweb.canGoBack()) {
            mywalletweb.goBack();
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }
}
