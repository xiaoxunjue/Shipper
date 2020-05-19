package com.revenant.shipper.ui.activity;

import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.revenant.shipper.R;
import com.revenant.shipper.base.BaseActivity;
import com.revenant.shipper.bean.BaseBean.MessageEvent;
import com.revenant.shipper.utils.EventBusUtil;
import com.revenant.shipper.utils.SPUtils;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

import static com.revenant.shipper.bean.BaseBean.MyEventCode.Order_Type_SIGAL;

public class PaySuccessActivity extends BaseActivity {

    @BindView(R.id.price)
    TextView price;
    @BindView(R.id.click_pay)
    LinearLayout clickPay;

    @Override
    public int getContentViewResId() {
        return R.layout.activity_pay_success;
    }

    @Override
    public void initView() {
        String extra = getIntent().getStringExtra("money");
        price.setText(extra);


    }

    @Override
    public void leftbarclick() {
        super.leftbarclick();
        finish();
    }

    @Override
    public void initData() {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                MessageEvent event = new MessageEvent(Order_Type_SIGAL);
                EventBusUtil.sendEvent(event);
                finish();

            }
        }, 4000);


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


    @OnClick({R.id.click_pay})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.click_pay:
                MessageEvent event = new MessageEvent(Order_Type_SIGAL);
                EventBusUtil.sendEvent(event);
                finish();
                break;
        }
    }
}
