package com.revenant.shipper.ui.activity;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import com.google.android.material.tabs.TabLayout;
import com.revenant.shipper.R;
import com.revenant.shipper.adapter.NewMainAdapter;
import com.revenant.shipper.base.BaseActivity;
import com.revenant.shipper.ui.fragment.AssignmentFragment;
import com.revenant.shipper.ui.fragment.ConversantVehicleDesignateFragment;
import com.revenant.shipper.ui.fragment.ConversantVehicleGroupFragment;
import com.revenant.shipper.ui.fragment.NetworkingFragment;
import com.revenant.shipper.ui.fragment.NetworkingPublishFragment;
import com.revenant.shipper.ui.fragment.PlatformFragment;
import com.revenant.shipper.ui.fragment.PublishPlatformFragment;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class PublishedSupplyOfGoodsActivity extends BaseActivity {
    @BindView(R.id.publishtablayout)
    TabLayout publishtablayout;
    @BindView(R.id.publishviewpager)
    ViewPager publishviewpager;
    private List<String> titleList = new ArrayList<>();

    private List<Fragment> fragmentList = new ArrayList<>();

    private NewMainAdapter newMainAdapter;

    @Override
    public int getContentViewResId() {
        return R.layout.activity_published_supply_of_goods;
    }

    @Override
    public void initView() {

    }

    @Override
    public void initData() {

        fragmentList.add(PublishPlatformFragment.newInstance("PublishPlatform"));
        fragmentList.add(ConversantVehicleGroupFragment.newInstance("ConversantVehicleGroup"));
        fragmentList.add(ConversantVehicleDesignateFragment.newInstance("ConversantVehicleDesignate"));
        fragmentList.add(NetworkingPublishFragment.newInstance("NetworkingPublish"));

        titleList.add("平台");
        titleList.add("熟车群抢单");
        titleList.add("熟车指派");
        titleList.add("联网发布");
        newMainAdapter = new NewMainAdapter(getSupportFragmentManager(), this, fragmentList, titleList);
//
        publishviewpager.setAdapter(newMainAdapter);
        publishtablayout.setupWithViewPager(publishviewpager);

        setcenterTitle("发布货源");

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
