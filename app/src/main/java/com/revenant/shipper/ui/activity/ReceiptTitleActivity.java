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
import com.revenant.shipper.adapter.FaPiaoTaiTouItemAdapter;
import com.revenant.shipper.base.BaseActivity;
import com.revenant.shipper.bean.ShipperBean.FaPiaoTaiTouListBean;
import com.revenant.shipper.utils.GsonUtil;
import com.revenant.shipper.utils.SPUtils;
import com.revenant.shipper.utils.Constants;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnLoadMoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;

import butterknife.BindView;
import cn.pedant.SweetAlert.SweetAlertDialog;

public class ReceiptTitleActivity extends BaseActivity {
    @BindView(R.id.publish_goods_recyclerview)
    RecyclerView publishGoodsRecyclerview;
    @BindView(R.id.smartrefresh)
    SmartRefreshLayout smartrefresh;

    private FaPiaoTaiTouItemAdapter mAdapter;
    private int pagernum = 1;
    private int fapiaotype = 0;
    public final static int FaPiaoRESULT_CODE = 1102;

    @Override
    public int getContentViewResId() {
        return R.layout.activity_receipt_title;
    }

    @Override
    public void initView() {
        Bundle bundle = getIntent().getExtras();
        if (bundle != null) {
            fapiaotype = bundle.getInt("fapiaotitle");
        }
        setcenterTitle("发票抬头");
        mAdapter = new FaPiaoTaiTouItemAdapter();
        publishGoodsRecyclerview.setLayoutManager(new LinearLayoutManager(this));
        publishGoodsRecyclerview.setAdapter(mAdapter);
        smartrefresh.autoRefresh();
        setrightTitle("添加");
        showrighttext(true);
    }

    @Override
    public void initData() {
        smartrefresh.setOnLoadMoreListener(new OnLoadMoreListener() {
            @Override
            public void onLoadMore(@NonNull RefreshLayout refreshLayout) {
                pagernum++;
                initDataRecyclerview();
                smartrefresh.finishLoadMore(true);//加载完成
            }
        });
        //刷新
        smartrefresh.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(@NonNull RefreshLayout refreshLayout) {
                mAdapter.setNewData(null);
                initDataRecyclerview();
                pagernum = 1;
                smartrefresh.finishRefresh(true);//刷新完成
            }
        });

        mAdapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
            @Override
            public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                switch (view.getId()) {
                    case R.id.edit:
                        Bundle bundle = new Bundle();
                        bundle.putInt("fapiaotaitoutype", 1);
                        bundle.putInt("fapiaotaitouid", mAdapter.getData().get(position).getId());
                        Intent intent = new Intent();
                        intent.setClass(ReceiptTitleActivity.this, EditReceiptTitleActivity.class);
                        intent.putExtras(bundle);
                        startActivity(intent);
                        break;
                    case R.id.delete:

                        new SweetAlertDialog(ReceiptTitleActivity.this, SweetAlertDialog.NORMAL_TYPE)
                                .setTitleText("确定删除吗")
                                .setCancelText("取消")
                                .setConfirmText("确定")
                                .showCancelButton(true)
                                .setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() {
                                    @Override
                                    public void onClick(SweetAlertDialog sweetAlertDialog) {
                                        deleteid(mAdapter.getData().get(position).getId(), position, sweetAlertDialog);
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
        mAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                if (fapiaotype == 0) {
                    Bundle bundle = new Bundle();
                    bundle.putInt("fapiaotaitoutype", 0);
                    bundle.putInt("fapiaotaitouid", mAdapter.getData().get(position).getId());
                    Intent intent = new Intent();
                    intent.setClass(ReceiptTitleActivity.this, EditReceiptTitleActivity.class);
                    intent.putExtras(bundle);
                    startActivity(intent);
                } else {
                    Bundle bundle=new Bundle();
                    bundle.putString("fapiaotaitouid", String.valueOf(mAdapter.getData().get(position).getId()));
                    bundle.putString("fapiaotaitou", mAdapter.getData().get(position).getTitleName());
                    bundle.putString("shuihao", mAdapter.getData().get(position).getTaxNumber());
                    bundle.putString("phone", mAdapter.getData().get(position).getPhone());
                    bundle.putString("companyaddreess", mAdapter.getData().get(position).getCompanyAddress());
                    bundle.putString("useraccount", mAdapter.getData().get(position).getBank());
                    bundle.putString("bankaccount", mAdapter.getData().get(position).getBankAccount());
                    Intent intent = new Intent();
                    intent.putExtras(bundle);
                    setResult(FaPiaoRESULT_CODE, intent);
                    finish();
                }


            }
        });
    }

    private void initDataRecyclerview() {
        OkGo.<String>get(Constants.Fa_Piao_TaiTou_List)
                .params("accountId", SPUtils.getAccounId(this))
                .execute(new StringCallback() {
                    @Override
                    public void onSuccess(Response<String> response) {
                        FaPiaoTaiTouListBean shipperPublishedBean = GsonUtil.parseJsonWithGson(response.body(), FaPiaoTaiTouListBean.class);
                        int pageNum = shipperPublishedBean.getData().getPageNum();
                        int pages = shipperPublishedBean.getData().getPages();
                        if (shipperPublishedBean.getCode().equals("0")) {
                            if (pageNum == 1) {
                                mAdapter.setNewData(shipperPublishedBean.getData().getResult());
                            } else if (pageNum <= pages) {
                                mAdapter.addData(shipperPublishedBean.getData().getResult());
                            } else {
                                mAdapter.loadMoreEnd();
                            }
//

                        } else {
                            mAdapter.loadMoreFail();
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
    public void leftbarclick() {
        super.leftbarclick();
        finish();
    }

    @Override
    public void rightbarclick() {
        super.rightbarclick();
        startActivity(AddReceiptTitleActivity.class);
    }

    private void deleteid(int id, int posi, SweetAlertDialog sweetAlertDialog) {
        OkGo.<String>post(Constants.Fa_Piao_TaiTou_Delete)
                .params("id", id)
                .execute(new StringCallback() {
                    @Override
                    public void onSuccess(Response<String> response) {
                        FaPiaoTaiTouListBean shipperPublishedBean = GsonUtil.parseJsonWithGson(response.body(), FaPiaoTaiTouListBean.class);

                        if (shipperPublishedBean.getCode().equals("0")) {
                            sweetAlertDialog.dismissWithAnimation();
                            showToast(shipperPublishedBean.getMsg());
                            mAdapter.remove(posi);
//

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
    protected void onResume() {
        super.onResume();
        smartrefresh.autoRefresh();
    }
}
