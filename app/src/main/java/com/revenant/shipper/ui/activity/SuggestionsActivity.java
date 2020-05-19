package com.revenant.shipper.ui.activity;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import com.google.android.material.tabs.TabLayout;
import com.revenant.shipper.R;
import com.revenant.shipper.adapter.NewMainAdapter;
import com.revenant.shipper.base.BaseActivity;
import com.revenant.shipper.ui.fragment.SuggestionHistoryFragment;
import com.revenant.shipper.ui.fragment.SuggestionWriteFragment;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class SuggestionsActivity extends BaseActivity {
    @BindView(R.id.tablayoutsuggestions)
    TabLayout tablayoutsuggestions;
    @BindView(R.id.viewpagersuggestions)
    ViewPager viewpagersuggestions;

    private List<Fragment> fragmentList;
    private List<String>   titleList;
    private NewMainAdapter newMainAdapter;

    @Override
    public int getContentViewResId() {
        return R.layout.activity_suggestion;
    }

    @Override
    public void initView() {
        fragmentList = new ArrayList<>();
        titleList = new ArrayList<>();

    }

    @Override
    public void initData() {

        fragmentList.add(SuggestionWriteFragment.newInstance("Write"));
        fragmentList.add(SuggestionHistoryFragment.newInstance("History"));

        titleList.add("写意见");
        titleList.add("历史");

        newMainAdapter = new NewMainAdapter(getSupportFragmentManager(), this, fragmentList, titleList);
//
        viewpagersuggestions.setAdapter(newMainAdapter);
        tablayoutsuggestions.setupWithViewPager(viewpagersuggestions);

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }
}
