package com.revenant.shipper.ui.activity;

import com.revenant.shipper.R;
import com.revenant.shipper.base.BaseActivity;

public class PersonalActivity extends BaseActivity {
    @Override
    public int getContentViewResId() {
        return R.layout.activity_personal;
    }

    @Override
    public void initView() {

    }

    @Override
    public void leftbarclick() {
        super.leftbarclick();

        startActivity(MineActivity.class);
    }

    @Override
    public void initData() {

    }
}
