package com.revenant.shipper.ui.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.revenant.shipper.R;
import com.revenant.shipper.base.BaseActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class EmptyCarManageActivity extends BaseActivity {
    @BindView(R.id.publishEmptyCar)
    TextView publishEmptyCar;
    @BindView(R.id.empty_car_recyclerveiw)
    RecyclerView emptyCarRecyclerveiw;

    @Override
    public int getContentViewResId() {
        return R.layout.activity_car_manage;
    }

    @Override
    public void initView() {

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

    @OnClick({R.id.publishEmptyCar})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.publishEmptyCar:
                startActivity(PublishEmptyCarActivity.class);
                break;
            default:
        }
    }

    @Override
    public void leftbarclick() {
        super.leftbarclick();
        finish();
    }
}
