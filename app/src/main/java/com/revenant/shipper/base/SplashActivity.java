package com.revenant.shipper.base;

import android.os.Handler;

import com.revenant.shipper.MainActivity;
import com.revenant.shipper.R;
import com.revenant.shipper.ui.activity.NewMainActivity;
import com.revenant.shipper.ui.activity.NoIdentificationActivity;
import com.revenant.shipper.ui.activity.PublishedSupplyOfGoodsActivity;

public class SplashActivity extends BaseActivity {

    @Override
    public int getContentViewResId() {
        return R.layout.activity_splash;
    }

    @Override
    public void initView() {

    }

    @Override
    protected boolean isshowtitlebar() {
        return false;
    }

    @Override
    public void initData() {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                startActivity(PublishedSupplyOfGoodsActivity.class);
                finish();
            }
        }, 2000);
    }
}
