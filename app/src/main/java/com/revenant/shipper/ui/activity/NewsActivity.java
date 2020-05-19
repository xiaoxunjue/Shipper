package com.revenant.shipper.ui.activity;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import com.google.android.material.tabs.TabLayout;
import com.revenant.shipper.R;
import com.revenant.shipper.adapter.NewMainAdapter;
import com.revenant.shipper.base.BaseActivity;
import com.revenant.shipper.ui.fragment.PersonalFragment;
import com.revenant.shipper.ui.fragment.SysNewsFragment;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class NewsActivity extends BaseActivity {


    @BindView(R.id.publishtablayout)
    TabLayout tablayoutnews;
    @BindView(R.id.publishviewpager)
    ViewPager viewpagernews;
    private List<Fragment> fragmentList;
    private List<String> titleList;
    private NewMainAdapter newMainAdapter;
    @Override
    public int getContentViewResId() {
        return R.layout.activity_news;
    }

    @Override
    public void initView() {
        fragmentList = new ArrayList<>();

        titleList = new ArrayList<>();

    }

    @Override
    public void initData() {

        fragmentList.add(SysNewsFragment.newInstance("SysNews"));
        fragmentList.add(PersonalFragment.newInstance("Personal"));

        titleList.add("系统消息");
        titleList.add("私人消息");

        newMainAdapter = new NewMainAdapter(getSupportFragmentManager(), this, fragmentList, titleList);
//
        viewpagernews.setAdapter(newMainAdapter);
        tablayoutnews.setupWithViewPager(viewpagernews);
        setcenterTitle("消息");

    }

    @Override
    public void leftbarclick() {
        super.leftbarclick();
        finish();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }

}
