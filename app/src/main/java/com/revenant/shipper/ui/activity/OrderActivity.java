package com.revenant.shipper.ui.activity;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import com.google.android.material.tabs.TabLayout;
import com.revenant.shipper.R;
import com.revenant.shipper.adapter.NewMainAdapter;
import com.revenant.shipper.base.BaseActivity;
import com.revenant.shipper.ui.fragment.AssessmentFragment;
import com.revenant.shipper.ui.fragment.CancelFragment;
import com.revenant.shipper.ui.fragment.InTransitFragment;
import com.revenant.shipper.ui.fragment.OrderAllFragment;
import com.revenant.shipper.ui.fragment.SuggestionHistoryFragment;
import com.revenant.shipper.ui.fragment.SuggestionWriteFragment;
import com.revenant.shipper.ui.fragment.UnconfirmedFragment;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class OrderActivity extends BaseActivity {
    @BindView(R.id.tablayoutorder)
    TabLayout tablayoutorder;
    @BindView(R.id.viewpagerorder)
    ViewPager viewpagerorder;

    private List<Fragment> fragmentList;
    private List<String> titleList;
    private NewMainAdapter newMainAdapter;

    @Override
    public int getContentViewResId() {
        return R.layout.activity_order;
    }

    @Override
    public void initView() {
        fragmentList = new ArrayList<>();
        titleList = new ArrayList<>();
    }

    @Override
    public void initData() {

        fragmentList.add(OrderAllFragment.newInstance("OrderAll"));
        fragmentList.add(UnconfirmedFragment.newInstance("Unconfirmed"));
        fragmentList.add(InTransitFragment.newInstance("InTransit"));
        fragmentList.add(AssessmentFragment.newInstance("Assessment"));
        fragmentList.add(CancelFragment.newInstance("Cancel"));

        titleList.add("全部");
        titleList.add("待确认");
        titleList.add("运输中");
        titleList.add("待评价");
        titleList.add("取消/退款");

        newMainAdapter = new NewMainAdapter(getSupportFragmentManager(), this, fragmentList, titleList);
//
        viewpagerorder.setAdapter(newMainAdapter);
        tablayoutorder.setupWithViewPager(viewpagerorder);

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }
}
