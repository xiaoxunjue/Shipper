package com.revenant.shipper.ui.activity;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;

import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import com.apkfuns.logutils.LogUtils;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.tabs.TabLayout;
import com.revenant.shipper.R;
import com.revenant.shipper.adapter.NewMainAdapter;
import com.revenant.shipper.base.BaseActivity;
import com.revenant.shipper.bean.AreaBeans;
import com.revenant.shipper.ui.fragment.AssignmentFragment;
import com.revenant.shipper.ui.fragment.NetworkingFragment;
import com.revenant.shipper.ui.fragment.PlatformFragment;
import com.revenant.shipper.utils.AreaSelect;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class NewMainActivity extends BaseActivity {
    @BindView(R.id.tablayout)
    TabLayout tablayout;
    @BindView(R.id.viewpager)
    ViewPager viewpager;
    @BindView(R.id.publishbotton)
    FloatingActionButton publishbotton;
    private List<Fragment> fragmentList;
    private List<String> titleList;
    private NewMainAdapter newMainAdapter;

    @Override
    public int getContentViewResId() {
        return R.layout.activity_newmain;
    }

    @Override
    public void initView() {

        fragmentList = new ArrayList<>();

        titleList = new ArrayList<>();

        Drawable lefftdrawable = ContextCompat.getDrawable(getApplicationContext(), R.mipmap.ic_launcher);
        setleftTitleImage(lefftdrawable);

        showrightimgae(true);
        Drawable righttdrawable = ContextCompat.getDrawable(getApplicationContext(), R.mipmap.ic_launcher);
        setrightTitleImage(righttdrawable);


    }

    @Override
    public void initData() {

        fragmentList.add(PlatformFragment.newInstance("Platform"));
        fragmentList.add(AssignmentFragment.newInstance("Assignment"));
        fragmentList.add(NetworkingFragment.newInstance("Networking"));

        titleList.add("平台");
        titleList.add("指派");
        titleList.add("联网");

        newMainAdapter = new NewMainAdapter(getSupportFragmentManager(), this, fragmentList, titleList);
//
        viewpager.setAdapter(newMainAdapter);
        tablayout.setupWithViewPager(viewpager);


    }

    @Override
    public void leftbarclick() {
        super.leftbarclick();
//        startActivity(OrderActivity.class);
        startActivity(OrderActivity.class);
        AreaBeans areaBean = AreaSelect.getArea(this);
        LogUtils.d("AAAa" + areaBean.getAreas().getArea());
    }

    @Override
    public void rightbasebarimageclick() {
        super.rightbasebarimageclick();
        startActivity(MessageActivity.class);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }

    @OnClick({R.id.publishbotton})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.publishbotton:
                startActivity(LoginActivity.class);
                break;
        }
    }
}
