package com.revenant.shipper.ui.activity;

import android.os.Bundle;
import android.widget.Button;


import com.revenant.shipper.R;
import com.revenant.shipper.base.BaseActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class CommentAddSuccessActivity extends BaseActivity {


    @BindView(R.id.success_back)
    Button successBack;

    @Override
    public int getContentViewResId() {
        return R.layout.activity_comment_add_success;
    }

    @Override
    public void initView() {

    }

    @Override
    public void initData() {

    }

    @Override
    protected boolean isshowtitlebar() {
        return false;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }

    @OnClick(R.id.success_back)
    public void onViewClicked() {
        finish();
        startActivity(ShipperMainActivity.class);
    }
}
