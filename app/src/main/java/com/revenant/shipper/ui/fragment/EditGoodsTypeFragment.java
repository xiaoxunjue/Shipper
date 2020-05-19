package com.revenant.shipper.ui.fragment;


import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import com.google.android.material.tabs.TabLayout;
import com.revenant.shipper.R;
import com.revenant.shipper.adapter.NewMainAdapter;
import com.revenant.shipper.adapter.NewMainFragmentAdapter;
import com.revenant.shipper.base.BaseFragment;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * A simple {@link Fragment} subclass.
 */
public class EditGoodsTypeFragment extends BaseFragment {


    @BindView(R.id.tablayoutnews)
    TabLayout tablayoutnews;
    @BindView(R.id.viewpagernews)
    ViewPager viewpagernews;
    private List<Fragment> fragmentList;
    private List<String> titleList;
    private NewMainFragmentAdapter newMainAdapter;

    public EditGoodsTypeFragment() {
        // Required empty public constructor
    }

    public static EditGoodsTypeFragment newInstance(String param1) {
        EditGoodsTypeFragment fragment = new EditGoodsTypeFragment();
        Bundle args = new Bundle();
        args.putString(param1, param1);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    protected int setLayoutId() {
        return R.layout.fragment_editgoodstype;
    }

    @Override
    protected void initView() {
        fragmentList = new ArrayList<>();

        titleList = new ArrayList<>();

    }

    @Override
    public void initData() {

        EditGoodsTypeAFragment editGoodsTypeA = EditGoodsTypeAFragment.newInstance("EditGoodsTypeA");
        Bundle bundleTypea = new Bundle();
        editGoodsTypeA.setArguments(bundleTypea);
        fragmentList.add(editGoodsTypeA);

        EditGoodsTypeBFragment editGoodsTypeBFragment = EditGoodsTypeBFragment.newInstance("EditGoodsTypeB");
        Bundle bundletypeb = new Bundle();
        editGoodsTypeBFragment.setArguments(bundletypeb);
        fragmentList.add(editGoodsTypeBFragment);

        EditGoodsTypeCFragment editGoodsTypeCFragment = EditGoodsTypeCFragment.newInstance("EditGoodsTypeC");
        Bundle bundletypec = new Bundle();
        editGoodsTypeCFragment.setArguments(bundletypec);
        fragmentList.add(editGoodsTypeCFragment);

        titleList.add("编辑平台");
        titleList.add("编辑熟车群");
        titleList.add("编辑指派");

        newMainAdapter = new NewMainFragmentAdapter(getChildFragmentManager(), getActivity(), fragmentList, titleList);
//
        viewpagernews.setAdapter(newMainAdapter);
        tablayoutnews.setupWithViewPager(viewpagernews);
    }


    @Override
    public void onResume() {
        super.onResume();

    }
}
