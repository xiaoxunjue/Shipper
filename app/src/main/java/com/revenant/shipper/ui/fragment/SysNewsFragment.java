package com.revenant.shipper.ui.fragment;


import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.StringCallback;
import com.lzy.okgo.model.Response;
import com.revenant.shipper.R;
import com.revenant.shipper.adapter.NesItemAdapter;
import com.revenant.shipper.base.BaseFragment;
import com.revenant.shipper.bean.ShipperBean.NesListBean;
import com.revenant.shipper.ui.activity.MessageDetailActivity;
import com.revenant.shipper.utils.GsonUtil;
import com.revenant.shipper.utils.SPUtils;
import com.revenant.shipper.utils.Constants;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * A simple {@link Fragment} subclass.
 */
public class SysNewsFragment extends BaseFragment {


    @BindView(R.id.nomessage)
    ImageView nomessage;
    @BindView(R.id.newrecyclerview)
    RecyclerView newrecyclerview;
    private NesItemAdapter mNesItemAdapter;

    public SysNewsFragment() {
        // Required empty public constructor
    }

    public static SysNewsFragment newInstance(String param1) {
        SysNewsFragment fragment = new SysNewsFragment();
        Bundle args = new Bundle();
        args.putString(param1, param1);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    protected int setLayoutId() {
        return R.layout.fragment_sys_news;
    }

    @Override
    protected void initView() {
        newrecyclerview.setLayoutManager(new LinearLayoutManager(getActivity()));
        mNesItemAdapter = new NesItemAdapter();
        newrecyclerview.setAdapter(mNesItemAdapter);
        mNesItemAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                int id = mNesItemAdapter.getData().get(position).getId();
                Bundle bundle = new Bundle();
                bundle.putInt("newsid", id);
                bundle.putInt("newstype", 0);
                Intent intent = new Intent();
                intent.putExtras(bundle);
                intent.setClass(getActivity(), MessageDetailActivity.class);
                startActivity(intent);
            }
        });

    }

    @Override
    public void initData() {

        getdata();
    }

    private void getdata() {
        OkGo.<String>get(Constants.News_list)
                .params("accepter", SPUtils.getAccounId(getActivity()))
                .params("type", "0")
                .execute(new StringCallback() {
                    @Override
                    public void onSuccess(Response<String> response) {
                        NesListBean listDetailBean = GsonUtil.parseJsonWithGson(response.body(), NesListBean.class);
                        if (listDetailBean.getCode().equals("0")) {
                            if (listDetailBean.getData().getTotal() > 1) {
                                newrecyclerview.setVisibility(View.VISIBLE);
                                nomessage.setVisibility(View.GONE);
                                mNesItemAdapter.setNewData(listDetailBean.getData().getResult());

                            } else {
                                nomessage.setVisibility(View.VISIBLE);

                            }
                        }
                    }
                });
    }

    @Override
    public void onResume() {
        super.onResume();
        getdata();
    }
}
