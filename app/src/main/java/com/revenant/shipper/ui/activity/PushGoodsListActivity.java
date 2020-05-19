package com.revenant.shipper.ui.activity;

import android.os.Bundle;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.apkfuns.logutils.LogUtils;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.StringCallback;
import com.lzy.okgo.model.Response;
import com.revenant.shipper.R;
import com.revenant.shipper.adapter.ShipperPublishedNewItemAdapter;
import com.revenant.shipper.base.BaseActivity;
import com.revenant.shipper.bean.ShipperBean.ShipperPublishedBean;
import com.revenant.shipper.utils.GsonUtil;
import com.revenant.shipper.utils.SPUtils;
import com.revenant.shipper.utils.Constants;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnLoadMoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;

import butterknife.BindView;
import butterknife.ButterKnife;

public class PushGoodsListActivity extends BaseActivity {
    @BindView(R.id.publish_goods_recyclerview)
    RecyclerView       publishGoodsRecyclerview;
    @BindView(R.id.smartrefresh)
    SmartRefreshLayout smartrefresh;
    private int pagernum = 1;
    ShipperPublishedNewItemAdapter shipperPublishedItemAdapter;
    private String  driver_id = "";
    private String  goodsId   = "";
    @Override
    public int getContentViewResId() {
        return R.layout.activity_push_goods_list;
    }

    @Override
    public void initView() {
        setcenterTitle("推送货源");
        shipperPublishedItemAdapter = new ShipperPublishedNewItemAdapter();
        publishGoodsRecyclerview.setLayoutManager(new LinearLayoutManager(this));
        publishGoodsRecyclerview.setAdapter(shipperPublishedItemAdapter);
        smartrefresh.autoRefresh();
        Bundle extras = getIntent().getExtras();
        driver_id=extras.getString("driverid");

    }

    @Override
    public void leftbarclick() {
        super.leftbarclick();
        finish();
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
                shipperPublishedItemAdapter.setNewData(null);
                pagernum = 1;
                initDataRecyclerview("1");

                smartrefresh.finishRefresh(true);//刷新完成
            }
        });

        shipperPublishedItemAdapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
            @Override
            public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                switch (view.getId()) {
                    case R.id.publish_goods_push:
                        ShipperPublishedBean.DataBean.ResultBean resultBean =
                                shipperPublishedItemAdapter.getData().get(position);

                        LogUtils.d("aaaaa");
                        pushGoods(String.valueOf(resultBean.getId()),driver_id);
                        break;
                }
            }
        });

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }

    private void initDataRecyclerview(String isPublish) {
        OkGo.<String>get(Constants.Goods_Publish_Or_closed)
                .params("isPublish", isPublish)
                .params("pageNum", pagernum)
                .params("accountId", SPUtils.getAccounId(this))
                .execute(new StringCallback() {
                    @Override
                    public void onSuccess(Response<String> response) {
                        ShipperPublishedBean shipperPublishedBean = GsonUtil.parseJsonWithGson(response.body(), ShipperPublishedBean.class);
                        int pageNum = shipperPublishedBean.getData().getPageNum();
                        int pages = shipperPublishedBean.getData().getPages();
                        if (shipperPublishedBean.getCode().equals("0")) {
                            if (pageNum == 1) {
                                shipperPublishedItemAdapter.setNewData(shipperPublishedBean.getData().getResult());
                            } else if (pageNum <= pages) {
                                shipperPublishedItemAdapter.addData(shipperPublishedBean.getData().getResult());
                            } else {
                                shipperPublishedItemAdapter.loadMoreEnd();
                            }
//

                        } else {
                            shipperPublishedItemAdapter.loadMoreFail();
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

    private void pushGoods(String goodsId, String driverId) {
        OkGo.<String>post(Constants.Push_Supply)
                .params("accountId", SPUtils.getAccounId(this))
                .params("goodsId", goodsId)
                .params("driverId", driverId)
                .execute(new StringCallback() {
                    @Override
                    public void onSuccess(Response<String> response) {
                        ShipperPublishedBean shipperPublishedBean = GsonUtil.parseJsonWithGson(response.body(), ShipperPublishedBean.class);
                        if (shipperPublishedBean.getCode().equals("0")) {
                            showToast(shipperPublishedBean.getMsg());
                            finish();
                        }else {
                            showToast(shipperPublishedBean.getMsg());
                        }

                    }
                });
    }
}
