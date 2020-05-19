package com.revenant.shipper.ui.activity;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import com.google.android.material.tabs.TabLayout;
import com.revenant.shipper.R;
import com.revenant.shipper.adapter.NewMainAdapter;
import com.revenant.shipper.base.BaseActivity;
import com.revenant.shipper.ui.fragment.AssessmentFragment;
import com.revenant.shipper.ui.fragment.DaiJieSuanFragment;
import com.revenant.shipper.ui.fragment.DaiPingJiaFragment;
import com.revenant.shipper.ui.fragment.DaiZhuanghuoFragment;
import com.revenant.shipper.ui.fragment.InTransitFragment;
import com.revenant.shipper.ui.fragment.OrderAllFragment;
import com.revenant.shipper.ui.fragment.UnconfirmedFragment;
import com.revenant.shipper.ui.fragment.YiXieHuoFragment;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class OrderActivity extends BaseActivity {
    @BindView(R.id.tablayoutorder)
    TabLayout tablayoutorder;
    @BindView(R.id.viewpagerorder)
    ViewPager viewpagerorder;
    private int defaultselect = 0;
    private List<Fragment> fragmentList;
    private List<String> titleList;
    private NewMainAdapter newMainAdapter;

    @Override
    public int getContentViewResId() {
        return R.layout.activity_order;
    }

    @Override
    public void initView() {
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            defaultselect = extras.getInt("orderselect");
        }
        fragmentList = new ArrayList<>();
        titleList = new ArrayList<>();
    }

    @Override
    public void initData() {
        setcenterTitle("我的订单");

        fragmentList.add(OrderAllFragment.newInstance("OrderAll"));
        fragmentList.add(DaiZhuanghuoFragment.newInstance("DaiZhuanghuo"));
        fragmentList.add(InTransitFragment.newInstance("YunShuZhong"));
        fragmentList.add(DaiJieSuanFragment.newInstance("DaiJieSuan"));
//        fragmentList.add(DaiPingJiaFragment.newInstance("DaiPingJia"));
//        fragmentList.add(UnconfirmedFragment.newInstance("DaiQueRen"));
        fragmentList.add(AssessmentFragment.newInstance("YiWanCheng"));
        fragmentList.add(YiXieHuoFragment.newInstance("YiXieHuo"));

        titleList.add("全部");
        titleList.add("待装货");
        titleList.add("运输中");
        titleList.add("待结算");
//        titleList.add("待评价");
//        titleList.add("待确认");
        titleList.add("已完成");
        titleList.add("已卸货");

        newMainAdapter = new NewMainAdapter(getSupportFragmentManager(), this, fragmentList, titleList);
//
        viewpagerorder.setAdapter(newMainAdapter);
        tablayoutorder.setupWithViewPager(viewpagerorder);
        tablayoutorder.getTabAt(defaultselect).select();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }

    @Override
    public void leftbarclick() {
        super.leftbarclick();
        finish();
    }
}
