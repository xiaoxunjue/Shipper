package com.revenant.shipper.ui.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.revenant.shipper.R;
import com.revenant.shipper.base.BaseActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MineReceiptActivity extends BaseActivity {
    @BindView(R.id.receipt_write)
    Button receiptWrite;
    @BindView(R.id.receipt_title)
    Button receiptTitle;
    @BindView(R.id.receipt_history)
    Button receiptHistory;

    @Override
    public int getContentViewResId() {
        return R.layout.activity_mine_receipt;
    }

    @Override
    public void initView() {
        setcenterTitle("我的发票");

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

    @OnClick({R.id.receipt_write, R.id.receipt_title, R.id.receipt_history})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.receipt_write:
                startActivity(ReceiptWriteActivity.class);
                break;
            case R.id.receipt_title:
                startActivity(ReceiptTitleActivity.class);
                break;
            case R.id.receipt_history:
                startActivity(ReceiptHistoryActivity.class);
                break;
        }
    }

    @Override
    public void leftbarclick() {
        super.leftbarclick();
        finish();
    }
}
