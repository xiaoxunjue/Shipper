package com.revenant.shipper.ui.activity;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import com.google.android.material.tabs.TabLayout;
import com.revenant.shipper.R;
import com.revenant.shipper.adapter.NewMainAdapter;
import com.revenant.shipper.base.BaseActivity;
import com.revenant.shipper.ui.fragment.ReceiptedFragment;
import com.revenant.shipper.ui.fragment.ReceiptingFragment;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ReceiptHistoryActivity extends BaseActivity {
    @BindView(R.id.receipt_history_tablayout)
    TabLayout receiptHistoryTablayout;
    @BindView(R.id.receipt_history_viewpager)
    ViewPager receiptHistoryViewpager;
    private List<Fragment> fragmentList;
    private List<String> titleList;
    private NewMainAdapter newMainAdapter;

    @Override
    public int getContentViewResId() {
        return R.layout.activity_receipt_history;
    }

    @Override
    public void initView() {
        setcenterTitle("发票历史");
        fragmentList = new ArrayList<>();

        titleList = new ArrayList<>();

        newMainAdapter = new NewMainAdapter(getSupportFragmentManager(), this, fragmentList, titleList);
//


    }

    @Override
    public void leftbarclick() {
        super.leftbarclick();
        finish();
    }

    @Override
    public void initData() {
        fragmentList.add(ReceiptedFragment.newInstance("Receipted"));
        fragmentList.add(ReceiptingFragment.newInstance("Receipting"));

        titleList.add("开票中");
        titleList.add("已开发票");
        receiptHistoryViewpager.setAdapter(newMainAdapter);
        receiptHistoryTablayout.setupWithViewPager(receiptHistoryViewpager);

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }
}
