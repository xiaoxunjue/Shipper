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
import com.revenant.shipper.bean.GoodsInfoEditBean;
import com.revenant.shipper.bean.ShipperBean.ShuCheGroupBean;
import com.revenant.shipper.ui.activity.GroupActivity;
import com.revenant.shipper.ui.activity.ShuCheActivity;
import com.revenant.shipper.ui.activity.ShuCheEditNewActivity;
import com.revenant.shipper.utils.Constants;
import com.revenant.shipper.utils.GsonUtil;
import com.revenant.shipper.utils.SPUtils;

import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;
import butterknife.Unbinder;

/**
 * A simple {@link Fragment} subclass.
 */
public class EditShuCheFragment extends BaseFragment {

    private static GoodsInfoEditBean editgoodsinfo;
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

    public EditShuCheFragment() {
        // Required empty public constructor
    }

    public static EditShuCheFragment newInstance(Bundle bundle) {
        editgoodsinfo = (GoodsInfoEditBean) bundle.getParcelable("editgoodsinfo");
        EditShuCheFragment fragment = new EditShuCheFragment();
        fragment.setArguments(bundle);
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
                                if (editgoodsinfo.getData().getInfo().getIsPlatform()==2){
                                    for (int i = 0; i < editgoodsinfo.getData().getGroupIdList().size(); i++) {
                                    for (int k = 0; k < groupBean.getData().getResult().size(); k++) {
                                        if (editgoodsinfo.getData().getGroupIdList().get(i) == groupBean.getData().getResult().get(k).getId()) {
                                            groupBean.getData().getResult().get(k).setIsselect(true);
                                            break;
                                        }
                                    }

                                }
                                }
//                                for (int i = 0; i < editgoodsinfo.getData().getGroupIdList().size(); i++) {
//                                    for (int k = 0; k < groupBean.getData().getResult().size(); k++) {
//                                        if (editgoodsinfo.getData().getGroupIdList().get(i) == groupBean.getData().getResult().get(k).getId()) {
//                                            groupBean.getData().getResult().get(k).setIsselect(true);
//                                            break;
//                                        }
//                                    }
//
//                                }
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
//        get_shuche_group();

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
                GoodsInfoEditBean bean = editgoodsinfo;
                Intent intentshuche = new Intent();
                Bundle bundleshuche = new Bundle();
                bundleshuche.putInt("shuchetype", 2);
                bundleshuche.putInt("shuchetypeId", bean.getData().getInfo().getIsPlatform());
                bundleshuche.putString("shuchegroups", ids);
                bundleshuche.putString("goodsid", String.valueOf(bean.getData().getInfo().getId()));
//                bundleshuche.putParcelable("editgoodsinfo", editgoodsinfo);
                intentshuche.putExtras(bundleshuche);
                intentshuche.setClass(getActivity(), ShuCheEditNewActivity.class);
                startActivity(intentshuche);
                break;
        }
    }
}
