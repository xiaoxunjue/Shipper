package com.revenant.shipper.ui.fragment;


import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.apkfuns.logutils.LogUtils;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.github.nukc.stateview.StateView;
import com.lljjcoder.style.citylist.Toast.ToastUtils;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.StringCallback;
import com.lzy.okgo.model.Response;
import com.revenant.shipper.R;
import com.revenant.shipper.adapter.QueRenSijiAdapter;
import com.revenant.shipper.adapter.ShipperPublishedItemAdapter;
import com.revenant.shipper.base.BaseFragment;
import com.revenant.shipper.bean.BaseBean.MessageEvent;
import com.revenant.shipper.bean.ShipperBean.QueRenSiBean;
import com.revenant.shipper.bean.ShipperBean.ShipperPublishedBean;
import com.revenant.shipper.ui.activity.MyGoodsDetailActivity;
import com.revenant.shipper.ui.activity.OrderActivity;
import com.revenant.shipper.utils.EventBusUtil;
import com.revenant.shipper.utils.GsonUtil;
import com.revenant.shipper.utils.SPUtils;
import com.revenant.shipper.utils.Constants;
import com.revenant.shipper.utils.Utils;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnLoadMoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;

import java.util.Date;

import butterknife.BindView;
import cn.hutool.core.date.DateTime;
import cn.hutool.core.date.DateUtil;

import static com.revenant.shipper.bean.BaseBean.MyEventCode.Closed_Goods_SIGAL;
import static com.revenant.shipper.bean.BaseBean.MyEventCode.Published_Goods_SIGAL;
import static com.revenant.shipper.utils.Constants.Name_header_token;

/**
 * A simple {@link Fragment} subclass.
 */
public class ShipperPublishedFragment extends BaseFragment {


    @BindView(R.id.ShipperPublishedrecy)
    RecyclerView ShipperPublishedrecy;
    @BindView(R.id.ShipperPublishedsmart)
    SmartRefreshLayout ShipperPublishedsmart;
    ShipperPublishedItemAdapter shipperPublishedItemAdapter;
    private int pagernum = 1;
    private QueRenSijiAdapter mQueRenSijiAdapter;
    private boolean isDriver = false;
    private String Name_header;
    @BindView(R.id.loadLayout)
    LinearLayout loadLayout;
    public ShipperPublishedFragment() {
        // Required empty public constructor
    }


    public static ShipperPublishedFragment newInstance(String param1) {
        ShipperPublishedFragment fragment = new ShipperPublishedFragment();
        Bundle args = new Bundle();
        args.putString(param1, param1);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    protected int setLayoutId() {
        return R.layout.fragment_published;
    }

    @Override
    protected void initView() {
        mStateView = StateView.inject(loadLayout);
        shipperPublishedItemAdapter = new ShipperPublishedItemAdapter();
        ShipperPublishedrecy.setLayoutManager(new LinearLayoutManager(getActivity()));
        ShipperPublishedrecy.setAdapter(shipperPublishedItemAdapter);
        ShipperPublishedsmart.autoRefresh();
        mStateView.setOnRetryClickListener(new StateView.OnRetryClickListener() {
            @Override
            public void onRetryClick() {
                //do something
                ShipperPublishedsmart.autoRefresh();
            }
        });

    }

    @Override
    public void initData() {
        //加载更多
        ShipperPublishedsmart.setOnLoadMoreListener(new OnLoadMoreListener() {
            @Override
            public void onLoadMore(@NonNull RefreshLayout refreshLayout) {
                pagernum++;
                initDataRecyclerview("1");
                ShipperPublishedsmart.finishLoadMore(true);//加载完成
            }
        });
        //刷新
        ShipperPublishedsmart.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(@NonNull RefreshLayout refreshLayout) {
                shipperPublishedItemAdapter.setNewData(null);
                initDataRecyclerview("1");
                pagernum = 1;
                ShipperPublishedsmart.finishRefresh(true);//刷新完成
            }
        });

        shipperPublishedItemAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {


                Bundle bundle = new Bundle();
                bundle.putString("goods_id", String.valueOf(shipperPublishedItemAdapter.getData().get(position).getId()));
                Intent intent = new Intent();
                intent.putExtras(bundle);
                intent.setClass(getActivity(), MyGoodsDetailActivity.class);
                startActivity(intent);
            }
        });
        shipperPublishedItemAdapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
            @Override
            public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                switch (view.getId()) {
                    case R.id.closed_goods_source:
                        setGoodsState(String.valueOf(shipperPublishedItemAdapter.getData().get(position).getId()),
                                "2", position);
                        break;
                    case R.id.conrifm_diver:
                        alertTypeSelect(String.valueOf(shipperPublishedItemAdapter.getData().get(position).getId()));
                        break;
                }
            }
        });


    }

    private void setGoodsState(String goodsid, String isPublish, int posi) {
        LogUtils.d("DDDDDDDDDDD---" + DateUtil.current(true));




        OkGo.<String>post(Constants.Update_state_goods)

                .params("id", goodsid)
                .params("isPublish", isPublish)
                .params("mtime", DateUtil.currentSeconds())
                .execute(new StringCallback() {
                    @Override
                    public void onSuccess(Response<String> response) {
                        ShipperPublishedBean g = GsonUtil.parseJsonWithGson(response.body(), ShipperPublishedBean.class);
                        if (g.getCode().equals("0")) {
                            MessageEvent event = new MessageEvent(Closed_Goods_SIGAL);
                            EventBusUtil.sendEvent(event);
                            Toast.makeText(getActivity(), g.getMsg(), Toast.LENGTH_SHORT).show();
                            shipperPublishedItemAdapter.remove(posi);
                        } else {
                            Toast.makeText(getActivity(), g.getMsg(), Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }

    private void initDataRecyclerview(String isPublish) {
        OkGo.<String>get(Constants.Goods_Publish_Or_closed)

                .params("isPublish", isPublish)
                .params("pageNum", pagernum)
                .params("accountId", SPUtils.getAccounId(getActivity()))
                .execute(new StringCallback() {
                    @Override
                    public void onSuccess(Response<String> response) {
                        LogUtils.d("SSSSSSSS"+response.body());
                        ShipperPublishedBean shipperPublishedBean = GsonUtil.parseJsonWithGson(response.body(), ShipperPublishedBean.class);
                        if (shipperPublishedBean.getData() != null) {
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

                            } else {
                                shipperPublishedItemAdapter.loadMoreFail();
                            }
                        }
                        mStateView.showContent();

                    }

                    @Override
                    public void onError(Response<String> response) {
                        super.onError(response);
                        mStateView.showRetry();
                    }

                    @Override
                    public void onFinish() {
                        super.onFinish();
                    }
                });
    }


    private void querensijilist(String id) {
        OkGo.<String>get(Constants.Que_RenSiJi_List)

                .params("id", id)
                .execute(new StringCallback() {
                    @Override
                    public void onSuccess(Response<String> response) {
                        QueRenSiBean queRenSiBean = GsonUtil.parseJsonWithGson(response.body(), QueRenSiBean.class);
                        if (queRenSiBean.getData() != null) {
                            if (queRenSiBean.getData().size() > 0) {
                                mQueRenSijiAdapter = new QueRenSijiAdapter();
                                mQueRenSijiAdapter.setNewData(queRenSiBean.getData());
                                isDriver = true;
                                AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
                                View view = getLayoutInflater().inflate(R.layout.querensiji, null);
                                RecyclerView type = view.findViewById(R.id.recyclerview);
                                TextView cancel = view.findViewById(R.id.cancel);
                                type.setLayoutManager(new LinearLayoutManager(getActivity()));
                                type.setAdapter(mQueRenSijiAdapter);
                                builder.setView(view);
                                if (isDriver) {
                                    final AlertDialog alertDialog = builder.show();
                                    final Window window = alertDialog.getWindow();
                                    window.setBackgroundDrawable(new ColorDrawable(0));
                                    mQueRenSijiAdapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
                                        @Override
                                        public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                                            switch (view.getId()) {
                                                case R.id.clicknum:
                                                    querensiconfirm(
                                                            mQueRenSijiAdapter.getData().get(position).getCarNo(),
                                                            String.valueOf(mQueRenSijiAdapter.getData().get(position).getGoodsId()), alertDialog);
                                                    break;
                                            }
                                        }
                                    });
                                    cancel.setOnClickListener(new View.OnClickListener() {
                                        @Override
                                        public void onClick(View v) {
                                            alertDialog.dismiss();

                                        }
                                    });
                                }
                            } else {
                                ToastUtils.showShortToast(getActivity(), "请稍后");
                            }
                        } else {
                            ToastUtils.showShortToast(getActivity(), "请稍后");
                        }

                    }
                });

    }

    private void querensiconfirm(String carNo, String id, AlertDialog alertDialog) {
        Name_header = Utils.get64String()+ "_&" + "1";
        OkGo.<String>post(Constants.Que_RenSiJi_Confirm)
                .headers(Name_header_token, Name_header)
                .params("id", id)
                .params("carNo", carNo)
                .execute(new StringCallback() {
                    @Override
                    public void onSuccess(Response<String> response) {
                        QueRenSiBean queRenSiBean = GsonUtil.parseJsonWithGson(response.body(), QueRenSiBean.class);
                        if (queRenSiBean.getCode().equals("0")) {
                            ToastUtils.showShortToast(getActivity(), queRenSiBean.getMsg());
                            Intent intent = new Intent();
                            intent.setClass(getActivity(), OrderActivity.class);
                            startActivity(intent);
                            alertDialog.dismiss();

                        } else {
                            ToastUtils.showShortToast(getActivity(), queRenSiBean.getMsg());
                        }

                    }
                });
    }

    private void alertTypeSelect(String ids) {
        querensijilist(ids);
    }

    @Override
    public void onResume() {
        super.onResume();
//        ShipperPublishedsmart.autoRefresh();
    }

    @Override
    protected boolean isRegisterEventBus() {
        return true;
    }

    @Override
    protected void receiveEvent(MessageEvent event) {
        super.receiveEvent(event);
        LogUtils.d("BBBBB","发布");
        switch (event.getCode()) {
            case Published_Goods_SIGAL:
                ShipperPublishedsmart.autoRefresh();
                break;

        }
    }

    /*无网络*/
    @Override
    public void doNoNetSomething() {
        LogUtils.d("whatnetnonet");
        mStateView.showRetry();
    }


    /*有网络*/
    @Override
    public void doNoCheckNetSomething() {
        LogUtils.d("whatnetnet");
//        ShipperPublishedsmart.autoRefresh();
    }
}
