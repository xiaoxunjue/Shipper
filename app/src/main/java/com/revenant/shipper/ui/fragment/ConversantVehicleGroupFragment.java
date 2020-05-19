package com.revenant.shipper.ui.fragment;


import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioButton;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.appcompat.widget.SearchView;
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
import com.revenant.shipper.ui.activity.ShuCheActivity;
import com.revenant.shipper.utils.Constants;
import com.revenant.shipper.utils.GsonUtil;
import com.revenant.shipper.utils.SPUtils;

import org.greenrobot.eventbus.EventBus;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

/**
 * A simple {@link Fragment} subclass.
 */
public class ConversantVehicleGroupFragment extends BaseFragment {
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

    @Override
    protected int setLayoutId() {
        return R.layout.fragment_conversant_vehicle_group;
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

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // TODO: inflate a fragment view
        View rootView = super.onCreateView(inflater, container, savedInstanceState);
        unbinder = ButterKnife.bind(this, rootView);
        return rootView;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }


    public static ConversantVehicleGroupFragment newInstance(String title) {
        Bundle bundle = new Bundle();
        bundle.putString(title, title);
        ConversantVehicleGroupFragment fragment = new ConversantVehicleGroupFragment();
        fragment.setArguments(bundle);
        return fragment;
    }

    @OnClick({R.id.buildgroup, R.id.confirm, R.id.all_select})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.all_select:
                isallselect = !isallselect;
                allSelect.setChecked(isallselect);
                for (int i = 0; i < lookShuCheGroupAdapter.getData().size(); i++) {
                    lookShuCheGroupAdapter.getData().get(i).setIsselect(isallselect);
                }
                lookShuCheGroupAdapter.notifyDataSetChanged();
                break;
            case R.id.buildgroup:
                Intent intent = new Intent();
                Bundle bundle = new Bundle();
                bundle.putString("group_type", "0");
                intent.putExtras(bundle);
                intent.setClass(getActivity(), GroupActivity.class);
                startActivity(intent);
                break;
            case R.id.confirm:
                List<ShuCheGroupBean.DataBean.ResultBean> data = lookShuCheGroupAdapter.getData();
                String ids = "";
                for (int i = 0, j = 0; i < data.size(); i++) {
                    if (data.get(i).isIsselect()) {
                        if (j == 0) {
                            ids = String.valueOf(data.get(i).getId());
                            j++;
                        } else {
                            ids += "," + data.get(i).getId();
                        }
                    }
                }

                Intent intentshuche = new Intent();
                Bundle bundleshuche = new Bundle();
                bundleshuche.putInt("shuchetype", 2);
                bundleshuche.putString("shuchegroups", ids);
                intentshuche.putExtras(bundleshuche);
                intentshuche.setClass(getActivity(), ShuCheActivity.class);
                startActivity(intentshuche);
                break;
        }
    }

    @Override
    public void onResume() {
        super.onResume();
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
}
