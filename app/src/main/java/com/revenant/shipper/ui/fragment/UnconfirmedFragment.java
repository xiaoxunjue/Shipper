package com.revenant.shipper.ui.fragment;


import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.StringCallback;
import com.lzy.okgo.model.Response;
import com.revenant.shipper.R;
import com.revenant.shipper.adapter.OrderItemAdapter;
import com.revenant.shipper.base.BaseFragment;
import com.revenant.shipper.bean.BaseBean.MessageEvent;
import com.revenant.shipper.bean.ShipperBean.OrderListBean;
import com.revenant.shipper.ui.activity.DriverTrackActivity;
import com.revenant.shipper.ui.activity.OrderCarDetailActivity;
import com.revenant.shipper.ui.activity.PayFirstActivity;
import com.revenant.shipper.ui.activity.UploadPicturesActivity;
import com.revenant.shipper.utils.GsonUtil;
import com.revenant.shipper.utils.SPUtils;
import com.revenant.shipper.utils.Constants;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnLoadMoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;

import butterknife.BindView;
import butterknife.Unbinder;

import static com.revenant.shipper.bean.BaseBean.MyEventCode.Order_Type_SIGAL;

/**
 * A simple {@link Fragment} subclass.
 */
public class UnconfirmedFragment extends BaseFragment {
    Unbinder unbinder;
    @BindView(R.id.order_recyclerview)
    RecyclerView       orderRecyclerview;
    @BindView(R.id.smartrefresh)
    SmartRefreshLayout smartrefresh;
    private OrderItemAdapter mOrderItemAdapter;
    private int              pagernum = 1;

    public UnconfirmedFragment() {
        // Required empty public constructor
    }

    public static UnconfirmedFragment newInstance(String param1) {
        UnconfirmedFragment fragment = new UnconfirmedFragment();
        Bundle args = new Bundle();
        args.putString(param1, param1);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    protected int setLayoutId() {
        return R.layout.fragment_unconfirmed;
    }

    @Override
    protected void initView() {
        mOrderItemAdapter = new OrderItemAdapter();
        orderRecyclerview.setLayoutManager(new LinearLayoutManager(getActivity()));
        orderRecyclerview.setAdapter(mOrderItemAdapter);
        smartrefresh.autoRefresh();

    }

    @Override
    public void initData() {
        smartrefresh.setOnLoadMoreListener(new OnLoadMoreListener() {
            @Override
            public void onLoadMore(@NonNull RefreshLayout refreshLayout) {
                pagernum++;
                initDataRecyclerview("1");
                smartrefresh.finishLoadMore(true);//加载完成
            }
        });
        //刷新
        smartrefresh.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(@NonNull RefreshLayout refreshLayout) {
                mOrderItemAdapter.setNewData(null);
                pagernum = 1;
                initDataRecyclerview("1");

                smartrefresh.finishRefresh(true);//刷新完成
            }
        });

        mOrderItemAdapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
            @Override
            public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                switch (view.getId()) {
                    case R.id.order_click:
                        int status = mOrderItemAdapter.getData().get(position).getStatus();
                        switch (status) {
                            case 0:

                                break;
                            case 1:
                                Bundle bundledrive = new Bundle();
                                startActivity(1, bundledrive);
                                break;
                            case 2:
                                Bundle bundlepay = new Bundle();
                                bundlepay.putInt("orderid", mOrderItemAdapter.getData().get(position).getId());
                                startActivity(2, bundlepay);
                                break;
                            case 3:
                                break;
                            case 4:
                                break;
                            case 5:
                                /*测试的*/
                                Bundle bundletestb = new Bundle();
                                bundletestb.putInt("orderid", mOrderItemAdapter.getData().get(position).getId());
                                startActivity(0, bundletestb);
                                break;
                            case 6:
                                Bundle bundleuploadbangdan = new Bundle();
                                bundleuploadbangdan.putInt("orderid", mOrderItemAdapter.getData().get(position).getId());
                                startActivity(6, bundleuploadbangdan);
                                break;
                        }
                        break;
                }
            }
        });

        mOrderItemAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                Bundle bundle = new Bundle();
                bundle.putInt("goods_id", mOrderItemAdapter.getData().get(position).getId());
                Intent intent = new Intent();
                intent.putExtras(bundle);
                intent.setClass(getActivity(), OrderCarDetailActivity.class);
                startActivity(intent);
            }
        });

    }

    private void initDataRecyclerview(String isBill) {
        OkGo.<String>get(Constants.Order_list)
                .params("accountId", SPUtils.getAccounId(getActivity()))
                .params("pageNum", pagernum)
                .params("status", "4")
                .execute(new StringCallback() {
                    @Override
                    public void onSuccess(Response<String> response) {

                        OrderListBean listBean = GsonUtil.parseJsonWithGson(response.body(), OrderListBean.class);
                        int pageNum = listBean.getData().getPageNum();
                        int pages = listBean.getData().getPages();
                        if (listBean.getCode().equals("0")) {
                            if (pageNum == 1) {
                                mOrderItemAdapter.setNewData(listBean.getData().getResult());
                            } else if (pageNum <= pages) {
                                mOrderItemAdapter.addData(listBean.getData().getResult());
                            } else {
                                mOrderItemAdapter.loadMoreEnd();
                            }
//

                        } else {
                            mOrderItemAdapter.loadMoreFail();
                        }

                    }
                });
    }


    public void startActivity(int activitytye, Bundle bundle) {
        Class type = PayFirstActivity.class;
        if (activitytye == 2) {
            type = PayFirstActivity.class;
        } else if (activitytye == 1) {
            type = DriverTrackActivity.class;
        } else if (activitytye == 6) {
            type = UploadPicturesActivity.class;
        }

        Intent intent = new Intent();
        intent.putExtras(bundle);
        intent.setClass(getActivity(), type);
        startActivity(intent);
    }

    @Override
    public void onResume() {
        super.onResume();
//        smartrefresh.autoRefresh();
    }

    @Override
    protected boolean isRegisterEventBus() {
        return true;
    }

    @Override
    protected void receiveEvent(MessageEvent event) {
        super.receiveEvent(event);
        switch (event.getCode()) {
            case Order_Type_SIGAL:
                smartrefresh.autoRefresh();
                break;
        }
    }
}
