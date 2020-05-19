package com.revenant.shipper.ui.fragment;


import android.os.Bundle;
import android.view.View;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.apkfuns.logutils.LogUtils;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.revenant.shipper.R;
import com.revenant.shipper.adapter.AreaItemAdapter;
import com.revenant.shipper.adapter.AreaSelectItemAdapter;
import com.revenant.shipper.adapter.NewMainAdapter;
import com.revenant.shipper.base.BaseFragment;
import com.revenant.shipper.bean.AreaBeans;
import com.revenant.shipper.bean.AreaShowBean;
import com.revenant.shipper.utils.AreaSelect;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * A simple {@link Fragment} subclass.
 */
public class EditGoodsTypeAFragment extends BaseFragment {



    public EditGoodsTypeAFragment() {
        // Required empty public constructor
    }

    public static EditGoodsTypeAFragment newInstance(String param1) {
        EditGoodsTypeAFragment fragment = new EditGoodsTypeAFragment();
        Bundle args = new Bundle();
        args.putString(param1, param1);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    protected int setLayoutId() {
        return R.layout.fragment_goodstypea;
    }

    @Override
    protected void initView() {

    }

    @Override
    public void initData() {

    }



}
