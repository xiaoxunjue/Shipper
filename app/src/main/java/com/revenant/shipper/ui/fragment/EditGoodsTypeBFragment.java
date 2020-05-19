package com.revenant.shipper.ui.fragment;


import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.RadioButton;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.appcompat.widget.SearchView;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.StringCallback;
import com.lzy.okgo.model.Response;
import com.revenant.shipper.R;
import com.revenant.shipper.adapter.LookShuCheGroupItemAdapter;
import com.revenant.shipper.base.BaseFragment;
import com.revenant.shipper.bean.ShipperBean.ShuCheGroupBean;
import com.revenant.shipper.ui.activity.GroupActivity;
import com.revenant.shipper.utils.Constants;
import com.revenant.shipper.utils.GsonUtil;
import com.revenant.shipper.utils.SPUtils;

import butterknife.BindView;
import butterknife.Unbinder;

/**
 * A simple {@link Fragment} subclass.
 */
public class EditGoodsTypeBFragment extends BaseFragment {


    Unbinder unbinder;
    @BindView(R.id.publish_search_phone)
    SearchView publishSearchPhone;
    @BindView(R.id.buildgroup)
    TextView buildgroup;
    @BindView(R.id.publish_goods_search)
    RecyclerView publishGoodsSearch;
    @BindView(R.id.confirm)
    TextView confirm;
    @BindView(R.id.all_confirm)
    RelativeLayout allConfirm;
    @BindView(R.id.all_select)
    RadioButton allSelect;
    private boolean isallselect = false;
    private LookShuCheGroupItemAdapter lookShuCheGroupAdapter;
    public EditGoodsTypeBFragment() {
        // Required empty public constructor
    }

    public static EditGoodsTypeBFragment newInstance(String param1) {
        EditGoodsTypeBFragment fragment = new EditGoodsTypeBFragment();
        Bundle args = new Bundle();
        args.putString(param1, param1);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    protected int setLayoutId() {
        return R.layout.fragment_goodstypeb;
    }

    @Override
    protected void initView() {

    }

    @Override
    public void initData() {
        lookShuCheGroupAdapter = new LookShuCheGroupItemAdapter();
        publishGoodsSearch.setAdapter(lookShuCheGroupAdapter);
        publishGoodsSearch.setLayoutManager(new LinearLayoutManager(getActivity()));
        lookShuCheGroupAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                Intent intent = new Intent();
                Bundle bundle = new Bundle();
                bundle.putString("group_type", "1");
                bundle.putString("group_id", String.valueOf(lookShuCheGroupAdapter.getData().get(position).getId()));
                intent.putExtras(bundle);
                intent.setClass(getActivity(), GroupActivity.class);
                startActivity(intent);

            }
        });

        lookShuCheGroupAdapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
            @Override
            public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                lookShuCheGroupAdapter.getData().get(position).setIsselect(!lookShuCheGroupAdapter.getData().get(position).isIsselect());
                lookShuCheGroupAdapter.notifyItemChanged(position);
            }
        });
        get_shuche_group();
    }

    private void get_shuche_group() {
        OkGo.<String>get(Constants.Look_ShuChe_Group)

                .params("accountId", SPUtils.getAccounId(getActivity()))
                .execute(new StringCallback() {
                    @Override
                    public void onSuccess(Response<String> response) {
                        ShuCheGroupBean groupBean = GsonUtil.parseJsonWithGson(response.body(), ShuCheGroupBean.class);
                        if (groupBean.getCode().equals("0")) {
                            if (groupBean.getData() != null && groupBean.getData().getTotal() > 0) {
                                allConfirm.setVisibility(View.VISIBLE);
                                lookShuCheGroupAdapter.setNewData(groupBean.getData().getResult());
                            } else {
                                allConfirm.setVisibility(View.GONE);
                            }
                        }

                    }
                });
    }

    @Override
    public void onResume() {
        super.onResume();
        get_shuche_group();

    }

}
