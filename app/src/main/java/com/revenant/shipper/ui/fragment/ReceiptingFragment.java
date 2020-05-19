package com.revenant.shipper.ui.fragment;


import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.lljjcoder.style.citylist.Toast.ToastUtils;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.StringCallback;
import com.lzy.okgo.model.Response;
import com.revenant.shipper.R;
import com.revenant.shipper.adapter.FaPiaoHistoryItemAdapter;
import com.revenant.shipper.base.BaseFragment;
import com.revenant.shipper.bean.ShipperBean.FaPiaoHistoryBean;
import com.revenant.shipper.bean.ShipperBean.FaPiaoTaiTouListBean;
import com.revenant.shipper.ui.activity.FaPiaoDetailActivity;
import com.revenant.shipper.utils.GsonUtil;
import com.revenant.shipper.utils.SPUtils;
import com.revenant.shipper.utils.Constants;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnLoadMoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import cn.pedant.SweetAlert.SweetAlertDialog;

/**
 * A simple {@link Fragment} subclas
 */
public class ReceiptingFragment extends BaseFragment {

    @BindView(R.id.fapiaorecy)
    RecyclerView fapiaorecy;
    @BindView(R.id.fapiaosmart)
    SmartRefreshLayout fapiaosmart;
    private FaPiaoHistoryItemAdapter mHistoryItemAdapter;
    private int pagernum = 1;

    public ReceiptingFragment() {
        // Required empty public constructor
    }


    public static ReceiptingFragment newInstance(String param1) {
        ReceiptingFragment fragment = new ReceiptingFragment();
        Bundle args = new Bundle();
        args.putString(param1, param1);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    protected int setLayoutId() {
        return R.layout.fragment_receipting;
    }

    @Override
    protected void initView() {
        mHistoryItemAdapter = new FaPiaoHistoryItemAdapter();
        fapiaorecy.setLayoutManager(new LinearLayoutManager(getActivity()));
        fapiaorecy.setAdapter(mHistoryItemAdapter);
        fapiaosmart.autoRefresh();
    }

    @Override
    public void initData() {
        fapiaosmart.setOnLoadMoreListener(new OnLoadMoreListener() {
            @Override
            public void onLoadMore(@NonNull RefreshLayout refreshLayout) {
                pagernum++;
                initDataRecyclerview();
                fapiaosmart.finishLoadMore(true);//加载完成
            }
        });
        //刷新
        fapiaosmart.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(@NonNull RefreshLayout refreshLayout) {
                mHistoryItemAdapter.setNewData(null);
                pagernum = 1;
                initDataRecyclerview();

                fapiaosmart.finishRefresh(true);//刷新完成
            }
        });

        mHistoryItemAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                Bundle bundle = new Bundle();
                bundle.putInt("fapiaodetailid", mHistoryItemAdapter.getData().get(position).getId());
                Intent intent = new Intent();
                intent.setClass(getActivity(), FaPiaoDetailActivity.class);
                intent.putExtras(bundle);
                startActivity(intent);
            }
        });

        mHistoryItemAdapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
            @Override
            public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                switch (view.getId()) {
                    case R.id.delete:

                        new SweetAlertDialog(getActivity(), SweetAlertDialog.NORMAL_TYPE)
                                .setTitleText("确定删除吗")
                                .setCancelText("取消")
                                .setConfirmText("确定")
                                .showCancelButton(true)
                                .setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() {
                                    @Override
                                    public void onClick(SweetAlertDialog sweetAlertDialog) {
                                        deleteid(mHistoryItemAdapter.getData().get(position).getId(), position, sweetAlertDialog);
                                    }
                                })
                                .setCancelClickListener(new SweetAlertDialog.OnSweetClickListener() {
                                    @Override
                                    public void onClick(SweetAlertDialog sDialog) {
                                        sDialog.dismissWithAnimation();
                                    }
                                })
                                .show();

                        break;
                }
            }
        });
    }

    private void initDataRecyclerview() {
        OkGo.<String>get(Constants.Fa_Piao_History_List)
                .params("accountId", SPUtils.getAccounId(getActivity()))
                .execute(new StringCallback() {
                    @Override
                    public void onSuccess(Response<String> response) {
                        FaPiaoHistoryBean shipperPublishedBean = GsonUtil.parseJsonWithGson(response.body(), FaPiaoHistoryBean.class);
                        int pageNum = shipperPublishedBean.getData().getPageNum();
                        int pages = shipperPublishedBean.getData().getPages();
                        if (shipperPublishedBean.getCode().equals("0")) {
                            List<FaPiaoHistoryBean.DataBean.ResultBean> listdata = new ArrayList<>();
                            if (pageNum == 1) {


                                for (int i = 0; i < shipperPublishedBean.getData().getSize(); i++) {
                                    if (shipperPublishedBean.getData().getResult().get(i).getStatus() == 2) {
                                        listdata.add(shipperPublishedBean.getData().getResult().get(i));
                                    }

                                }
                                mHistoryItemAdapter.setNewData(listdata);
                            } else if (pageNum <= pages) {
                                mHistoryItemAdapter.addData(listdata);
                            } else {
                                mHistoryItemAdapter.loadMoreEnd();
                            }
//

                        } else {
                            mHistoryItemAdapter.loadMoreFail();
                        }
                    }
                });

    }

    private void deleteid(int id, int posi, SweetAlertDialog sweetAlertDialog) {
        OkGo.<String>post(Constants.Fa_Piao_History_Delete)

                .params("id", id)
                .execute(new StringCallback() {
                    @Override
                    public void onSuccess(Response<String> response) {
                        FaPiaoTaiTouListBean shipperPublishedBean = GsonUtil.parseJsonWithGson(response.body(), FaPiaoTaiTouListBean.class);

                        if (shipperPublishedBean.getCode().equals("0")) {
                            ToastUtils.showShortToast(getActivity(), shipperPublishedBean.getMsg());
                            mHistoryItemAdapter.remove(posi);
                            sweetAlertDialog.dismissWithAnimation();

                        }
                    }

                    @Override
                    public void onError(Response<String> response) {
                        super.onError(response);
                    }

                    @Override
                    public void onFinish() {
                        super.onFinish();
                    }
                });
    }

    @Override
    public void onResume() {
        super.onResume();
        fapiaosmart.autoRefresh();
    }
}
