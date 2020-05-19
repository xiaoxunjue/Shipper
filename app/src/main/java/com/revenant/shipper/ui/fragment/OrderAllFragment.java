package com.revenant.shipper.ui.fragment;


import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.StringCallback;
import com.lzy.okgo.model.Response;
import com.revenant.shipper.R;
import com.revenant.shipper.adapter.OrderItemAdapter;
import com.revenant.shipper.base.BaseFragment;
import com.revenant.shipper.bean.BaseBean.MessageEvent;
import com.revenant.shipper.bean.ShipperBean.OrderListBean;
import com.revenant.shipper.ui.activity.BaoXianActivity;
import com.revenant.shipper.ui.activity.DriverTrackActivity;
import com.revenant.shipper.ui.activity.OrderCarDetailActivity;
import com.revenant.shipper.ui.activity.PayFirstActivity;
import com.revenant.shipper.ui.activity.ShipperMapActivity;
import com.revenant.shipper.ui.activity.ShipperTrackActivity;
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
import static com.revenant.shipper.utils.Constants.isSecond;

/**
 * A simple {@link Fragment} subclass.
 */
public class OrderAllFragment extends BaseFragment {

    Unbinder unbinder;
    @BindView(R.id.testBottom)
    Button testBottom;
    @BindView(R.id.order_recyclerview)
    RecyclerView orderRecyclerview;
    @BindView(R.id.smartrefresh)
    SmartRefreshLayout smartrefresh;
    private OrderItemAdapter mOrderItemAdapter;
    private int pagernum = 1;

    public OrderAllFragment() {
        // Required empty public constructor
    }

    public static OrderAllFragment newInstance(String param1) {
        OrderAllFragment fragment = new OrderAllFragment();
        Bundle args = new Bundle();
        args.putString(param1, param1);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    protected int setLayoutId() {
        return R.layout.fragment_order_all;
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


        mOrderItemAdapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
            @Override
            public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                switch (view.getId()) {
                    case R.id.order_guiji:
                        Bundle bundletestbc = new Bundle();
                        bundletestbc.putInt("orderid", mOrderItemAdapter.getData().get(position).getId());
                        startActivity(1, bundletestbc);
                        break;
                    case R.id.baoxian:
                        Bundle bundlebaoxian = new Bundle();
                        bundlebaoxian.putInt("orderid", mOrderItemAdapter.getData().get(position).getId());
                        Intent intent = new Intent();
                        intent.putExtras(bundlebaoxian);
                        intent.setClass(getActivity(), BaoXianActivity.class);
                        startActivity(intent);
                        break;
                    case R.id.order_click:
                        int status = mOrderItemAdapter.getData().get(position).getStatus();
                        switch (status) {
                            case 0:

                                break;
                            case 1:
                                Bundle bundledrive = new Bundle();
                                bundledrive.putInt("orderid", mOrderItemAdapter.getData().get(position).getId());
                                startActivity(1, bundledrive);
                                break;
                            case 2:
                                Bundle bundlepay = new Bundle();
                                bundlepay.putInt("orderid", mOrderItemAdapter.getData().get(position).getId());
                                bundlepay.putString("ordernum", mOrderItemAdapter.getData().get(position).getOrderNo());
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
                                startActivity(1, bundletestb);

//                                Bundle bundletsest = new Bundle();
//                                bundletsest.putInt("orderid", mOrderItemAdapter.getData().get(position).getId());
//                                bundletsest.putString("ordernum", mOrderItemAdapter.getData().get(position).getOrderNo());
//                                startActivity(2, bundletsest);
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

    }

    private void initDataRecyclerview(String isBill) {
        OkGo.<String>get(Constants.Order_list)

                .params("accountId", SPUtils.getAccounId(getActivity()))
                .params("pageNum", pagernum)
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
            if (isSecond) {
                type = ShipperTrackActivity.class;
            } else {
                type = DriverTrackActivity.class;

            }

//            type = ShipperMapActivity.class;
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
