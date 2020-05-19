package com.revenant.shipper.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.StringCallback;
import com.lzy.okgo.model.Response;
import com.revenant.shipper.R;
import com.revenant.shipper.adapter.FaPiaoItemAdapter;
import com.revenant.shipper.base.BaseActivity;
import com.revenant.shipper.bean.FaPiaoListItemBean;
import com.revenant.shipper.bean.ShipperBean.FaPiaoItemBean;
import com.revenant.shipper.bean.ShipperBean.OrderListBean;
import com.revenant.shipper.utils.GsonUtil;
import com.revenant.shipper.utils.SPUtils;
import com.revenant.shipper.utils.Constants;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnLoadMoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ReceiptWriteActivity extends BaseActivity {
    @BindView(R.id.fapiaorecy)
    RecyclerView fapiaorecy;
    @BindView(R.id.fapiaosmart)
    SmartRefreshLayout fapiaosmart;
    private int pagernum = 1;
    private FaPiaoItemAdapter mFaPiaoItemAdapter;

    @Override
    public int getContentViewResId() {
        return R.layout.activity_fapiao_kaichu;
    }

    @Override
    public void initView() {
        setcenterTitle("开出发票");
        mFaPiaoItemAdapter = new FaPiaoItemAdapter();
        fapiaorecy.setLayoutManager(new LinearLayoutManager(this));
        fapiaorecy.setAdapter(mFaPiaoItemAdapter);
        fapiaosmart.autoRefresh();
    }

    @Override
    public void leftbarclick() {
        super.leftbarclick();
        finish();
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
                mFaPiaoItemAdapter.setNewData(null);
                pagernum = 1;
                initDataRecyclerview();
                fapiaosmart.finishRefresh(true);//刷新完成
            }
        });

        mFaPiaoItemAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                Bundle bundle = new Bundle();
                bundle.putInt("goodsid",mFaPiaoItemAdapter.getData().get(position).getId());
                startActivity(KaiChuFaPiaoActivity.class,bundle);
//                Bundle bundle = new Bundle();
//                bundle.putString("goods_id", String.valueOf(mFaPiaoItemAdapter.getData().get(position).getId()));
//                bundle.putInt("goods_type",1);
//                Intent intent = new Intent();
//                intent.putExtras(bundle);
//                intent.setClass(ReceiptWriteActivity.this,MyGoodsDetailActivity.class);
//                startActivity(intent);
            }
        });

    }

    private void initDataRecyclerview() {
        OkGo.<String>get(Constants.Fa_Piao_Write_list)

                .params("accountId", SPUtils.getAccounId(this))
                .params("isBill", "1")
                .params("pageNum", pagernum)
                .params("status", "5")
                .execute(new StringCallback() {
                    @Override
                    public void onSuccess(Response<String> response) {
                        FaPiaoListItemBean shipperPublishedBean = GsonUtil.parseJsonWithGson(response.body(), FaPiaoListItemBean.class);
                        int pageNum = shipperPublishedBean.getData().getPageNum();
                        int pages = shipperPublishedBean.getData().getPages();
                        if (shipperPublishedBean.getCode().equals("0")) {
                            if (pageNum == 1) {
                                mFaPiaoItemAdapter.setNewData(shipperPublishedBean.getData().getResult());
                            } else if (pageNum <= pages) {
                                mFaPiaoItemAdapter.addData(shipperPublishedBean.getData().getResult());
                            } else {
                                mFaPiaoItemAdapter.loadMoreEnd();
                            }
//

                        } else {
                            mFaPiaoItemAdapter.loadMoreFail();
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


    @Override
    protected void onResume() {
        super.onResume();
        fapiaosmart.autoRefresh();
    }
}
