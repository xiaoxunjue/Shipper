package com.revenant.shipper.base;


import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.revenant.shipper.R;
import com.revenant.shipper.ui.fragment.Blank1Fragment;
import com.revenant.shipper.ui.fragment.Blank2Fragment;
import com.revenant.shipper.ui.fragment.Blank3Fragment;
import com.revenant.shipper.ui.fragment.BlankFragment;
import com.yinglan.alphatabs.AlphaTabsIndicator;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class BottomBarActivity extends BaseActivity {
    @BindView(R.id.viewpager)
    ViewPager viewpager;
    @BindView(R.id.alphaIndicator)
    AlphaTabsIndicator alphaTabsIndicator;

    @Override
    public int getContentViewResId() {
        return R.layout.activity_bottom_bar;
    }

    @Override
    public void initView() {
        MainAdapter mainAdapter = new MainAdapter(getSupportFragmentManager());
        viewpager.setAdapter(mainAdapter);
        viewpager.setOffscreenPageLimit(3);
        alphaTabsIndicator.setViewPager(viewpager);
        viewpager.addOnPageChangeListener(mainAdapter);
        alphaTabsIndicator.getTabView(0).removeShow();
        alphaTabsIndicator.getTabView(1).removeShow();
        alphaTabsIndicator.getTabView(2).removeShow();
        alphaTabsIndicator.getTabView(3).removeShow();
    }

    @Override
    public void initData() {

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ButterKnife.bind(this);
    }
    private class MainAdapter extends FragmentPagerAdapter implements ViewPager.OnPageChangeListener {

        private List<Fragment> fragments = new ArrayList<>();
        private String[] titles = {"微信", "通讯录", "发现", "我"};

        public MainAdapter(FragmentManager fm) {
            super(fm);
            fragments.add(new BlankFragment());
            fragments.add(new Blank1Fragment());
            fragments.add(new Blank2Fragment());
            fragments.add(new Blank3Fragment());
        }

        @Override
        public Fragment getItem(int position) {
            return fragments.get(position);
        }

        @Override
        public int getCount() {
            return fragments.size();
        }

        @Override
        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

        }

        @Override
        public void onPageSelected(int position) {
//            if (0 == position) {
//                alphaTabsIndicator.getTabView(0).showNumber(alphaTabsIndicator.getTabView(0).getBadgeNumber() - 1);
//            } else if (2 == position) {
//                alphaTabsIndicator.getCurrentItemView().removeShow();
//            } else if (3 == position) {
//                alphaTabsIndicator.removeAllBadge();
//            }

            if (0 == position) {
                alphaTabsIndicator.getCurrentItemView().removeShow();
            } else if (1 == position) {
                alphaTabsIndicator.getCurrentItemView().removeShow();
            } else if (2 == position) {
                alphaTabsIndicator.getCurrentItemView().removeShow();
            }
        }

        @Override
        public void onPageScrollStateChanged(int state) {

        }
    }
}
