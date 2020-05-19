package com.revenant.shipper.ui.activity;


import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;

import com.manyi.mobile.etcsdk.activity.GSETC;
import com.manyi.mobile.etcsdk.activity.PreChangeEtc;
import com.manyi.mobile.etcsdk.activity.ReadETCBlueTooth;
import com.manyi.mobile.etcsdk.entity.AuthParam;
import com.manyi.mobile.etcsdk.interfaces.MYRequestCallBack;
import com.manyi.mobile.utils.Common;
import com.revenant.shipper.R;
import com.revenant.shipper.base.BaseActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ETCStartActivity extends BaseActivity {
    @BindView(R.id.apply_setc_card)
    LinearLayout applySetcCard;
    @BindView(R.id.setc_balance)
    LinearLayout setcBalance;
    @BindView(R.id.setc_charge)
    LinearLayout setcCharge;
    @BindView(R.id.setc_write_card)
    LinearLayout setcWriteCard;
    @BindView(R.id.card_layout)
    LinearLayout cardLayout;
    private String phonenum="18654517708";
    private String appkey="4556636767";
    private static final int REQUEST_ETC = 0x1;
    private static final int REQUEST_RECHARGE = 0x3;
    private static final int REQUEST_OPENCARD = 0x4;
    @Override
    public int getContentViewResId() {
        return R.layout.activity_etc_start;
    }

    @Override
    public void initView() {
        setcenterTitle("我的ETC");
        initETC();
    }

    @Override
    public void leftbarclick() {
        super.leftbarclick();
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

    private void initETC() {
        /**
         * 调用这句话，使用测试环境 不调用或者GSETC.getInstance(this).setDebug(false)为生产环境
         */
        GSETC.getInstance(this).setDebug(true);
        GSETC.getInstance(this).iniGSETC(this,
                new AuthParam(appkey, phonenum));
        GSETC.getInstance(this).checkApp(this, null, new MYRequestCallBack() {

            @Override
            public void onSuccess(String resp) {
                // TODO Auto-generated method stub
                Common.printLog(resp + "通过");
            }

            @Override
            public void onFailed(String resp) {
                // TODO Auto-generated method stub
                Common.printLog(resp + "失败");
            }
        });
    }

    @OnClick({R.id.apply_setc_card, R.id.setc_balance, R.id.setc_charge, R.id.setc_write_card, R.id.card_layout})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.apply_setc_card: //申请ETC卡


                break;
            case R.id.setc_balance: //ETC余额
                startActivity(new Intent(this, ReadETCBlueTooth.class).putExtra(
                        "isRead", true).addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP));
                break;
            case R.id.setc_charge: //ETC充值
                Intent intent = new Intent(this, PreChangeEtc.class)
                        .putExtra("noCardCharge", 1).putExtra("position", 0)
                        .putExtra("packageName", "com.manyi.mobile.etc")
                        .addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
                break;
            case R.id.setc_write_card: //ETC圈存写卡
                startActivity(SetcWriteCardActivity.class);
                break;
        }
    }
}
