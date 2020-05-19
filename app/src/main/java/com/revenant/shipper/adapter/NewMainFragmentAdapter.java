package com.revenant.shipper.adapter;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import java.util.List;

/**
 * @ProjectName: Shipper
 * @Package: com.revenant.shipper.adapter
 * @ClassName: NewMainAdapter
 * @Description: java类作用描述
 * @Author: revenant
 * @CreateDate: 2019-12-30 10:44
 * @UpdateUser: 更新者
 * @UpdateDate: 2019-12-30 10:44
 * @UpdateRemark: 更新说明
 * @Version: 1.0
 */
public class NewMainFragmentAdapter extends FragmentPagerAdapter {
    private List<Fragment> fragmentList;
    private List<String> mtitlelist;
    private Context mcontext;

    public NewMainFragmentAdapter(@NonNull FragmentManager fm, Context context, List<Fragment> fragmentList, List<String> stringList) {
        super(fm);
        mcontext = context;
        this.fragmentList = fragmentList;
        this.mtitlelist = stringList;

    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        return fragmentList.get(position);
    }

    @Override
    public int getCount() {
        return fragmentList.size();
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return mtitlelist.get(position);
    }
}
