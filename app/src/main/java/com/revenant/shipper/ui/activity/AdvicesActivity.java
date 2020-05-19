package com.revenant.shipper.ui.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import com.apkfuns.logutils.LogUtils;
import com.google.android.material.tabs.TabLayout;
import com.revenant.shipper.R;
import com.revenant.shipper.adapter.NewMainAdapter;
import com.revenant.shipper.base.BaseActivity;
import com.revenant.shipper.ui.fragment.AdviceOneFragment;
import com.revenant.shipper.ui.fragment.AdviceTwoFragment;


import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * @ProjectName: Driver
 * @Package: com.revenant.driver.ui.activity
 * @ClassName: AdvicesActivity
 * @Description: java类作用描述
 * @Author: Revenant
 * @CreateDate: 2020/4/15 10:59
 * @UpdateUser: 更新者
 * @UpdateDate: 2020/4/15 10:59
 * @UpdateRemark: 更新说明
 * @Version: 1.0
 */
public class AdvicesActivity extends BaseActivity {
    @BindView(R.id.tablayoutsuggestions)
    TabLayout tablayoutsuggestions;
    @BindView(R.id.viewpagersuggestions)
    ViewPager viewpagersuggestions;


    private List<Fragment> fragmentList;
    private List<String> titleList;
    private NewMainAdapter newMainAdapter;

    private TextView centerview;
    private ImageView rightiImageView;
    private ImageView leftImageView;

    @Override
    public int getContentViewResId() {
        return R.layout.activity_advices;
    }

    @Override
    public void initView() {
        fragmentList = new ArrayList<>();
        titleList = new ArrayList<>();
        centerview = findViewById(R.id.center_title);
        rightiImageView=findViewById(R.id.righ_bar_image);
        leftImageView=findViewById(R.id.left_b_bar);
        centerview.setText("意见反馈");
        rightiImageView.setImageResource(R.mipmap.advicesadd);
        leftImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LogUtils.d("AAAAAAAAAAAAA");
                finish();
            }
        });

        rightiImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(AddAdvicesActivity.class);
            }
        });

//        centerBaseBarTitle.setText("意见反馈");
//        rightBaseBarImage.setImageDrawable(ContextCompat.getDrawable(getApplicationContext(), R.mipmap.advicesadd));

    }

    @Override
    public void initData() {
        fragmentList.add(AdviceOneFragment.newInstance("AdviceOne"));
        fragmentList.add(AdviceTwoFragment.newInstance("AdviceTwo"));

        titleList.add("未处理");
        titleList.add("已处理");

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

    @Override
    protected boolean isshowtitlebar() {
        return false;
    }

}
