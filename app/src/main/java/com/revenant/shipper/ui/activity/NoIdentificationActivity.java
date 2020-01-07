package com.revenant.shipper.ui.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.revenant.shipper.R;
import com.revenant.shipper.base.BaseActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class NoIdentificationActivity extends BaseActivity {


    @BindView(R.id.left_base)
    ImageView leftBase;
    @BindView(R.id.right_base)
    ImageView rightBase;
    @BindView(R.id.startingplace)
    TextView startingplace;
    @BindView(R.id.endingplace)
    TextView endingplace;

    @Override
    public int getContentViewResId() {
        return R.layout.activity_noidentification;
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

    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }

    @OnClick({R.id.left_base, R.id.right_base})
    public void onViewClickedId(View view) {
        switch (view.getId()) {
            case R.id.left_base:
                startActivity(CustomerServiceActivity.class);
                break;
            case R.id.right_base:
                startActivity(NewsActivity.class);
                break;
        }
    }
}
