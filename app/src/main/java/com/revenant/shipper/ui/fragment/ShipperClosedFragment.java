package com.revenant.shipper.ui.fragment;


import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.Toast;

import androidx.annotation.NonNull;
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
import com.revenant.shipper.adapter.ShipperPublishedItemAdapter;
import com.revenant.shipper.base.BaseFragment;
import com.revenant.shipper.bean.BaseBean.MessageEvent;
import com.revenant.shipper.bean.ShipperBean.ShipperPublishedBean;
import com.revenant.shipper.ui.activity.EditGoodsInfoNewActivity;
import com.revenant.shipper.ui.activity.LoginActivity;
import com.revenant.shipper.ui.activity.MyGoodsDetailActivity;
import com.revenant.shipper.ui.activity.PublishedSupplyOfGoodsActivity;
import com.revenant.shipper.ui.activity.ShuCheActivity;
import com.revenant.shipper.ui.activity.ShuCheEditActivity;
import com.revenant.shipper.utils.EventBusUtil;
import com.revenant.shipper.utils.GsonUtil;
import com.revenant.shipper.utils.SPUtils;
import com.revenant.shipper.utils.Constants;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnLoadMoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;

import java.util.Date;

import butterknife.BindView;
import cn.hutool.core.date.DateUtil;
import cn.pedant.SweetAlert.SweetAlertDialog;

import static com.revenant.shipper.bean.BaseBean.MyEventCode.Closed_Goods_SIGAL;
import static com.revenant.shipper.bean.BaseBean.MyEventCode.Published_Goods_SIGAL;
import static com.revenant.shipper.bean.BaseBean.MyEventCode.Token_LOGIN_SIGAL;

/**
 * A simple {@link Fragment} subclass.
 */
public class ShipperClosedFragment extends BaseFragment {

    @BindView(R.id.ShipperPublishedrecy)
    RecyclerView ShipperPublishedrecy;
    @BindView(R.id.ShipperPublishedsmart)
    SmartRefreshLayout ShipperPublishedsmart;
    ShipperPublishedItemAdapter shipperPublishedItemAdapter;
    private int pagernum = 1;
    @BindView(R.id.loadLayout)
    LinearLayout loadLayout;
    public ShipperClosedFragment() {
        // Required empty public constructor
    }


    public static ShipperClosedFragment newInstance(String param1) {
        ShipperClosedFragment fragment = new ShipperClosedFragment();
        Bundle args = new Bundle();
        args.putString(param1, param1);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    protected int setLayoutId() {
        return R.layout.fragment_closed;
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
        shipperPublishedItemAdapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
            @Override
            public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                switch (view.getId()) {
                    case R.id.publish_goods:
//                        Intent intent = new Intent();
//                        intent.setClass(getActivity(), PublishedSupplyOfGoodsActivity.class);
//                        startActivity(intent);

                        setGoodsState(String.valueOf(shipperPublishedItemAdapter.getData().get(position).getId()),
                                "1", position);
                        break;
                    case R.id.edit_goods:
                        int platform = Integer.valueOf(shipperPublishedItemAdapter.getData().get(position).isPlatform());
                        /*0指派，1平台，2熟车群*/

                        /*/*0 熟车指派 1平台**/


                        Intent intentshuche = new Intent();
                        Bundle bundleshuche = new Bundle();
                        bundleshuche.putInt("shuchetype", 3);
                        bundleshuche.putInt("goodsid", shipperPublishedItemAdapter.getData().get(position).getId());
                        bundleshuche.putInt("isPlatform", Integer.valueOf(shipperPublishedItemAdapter.getData().get(position).isPlatform()));
                        intentshuche.putExtras(bundleshuche);
//                        intentshuche.setClass(getActivity(), ShuCheEditActivity.class);
                        intentshuche.setClass(getActivity(), EditGoodsInfoNewActivity.class);
                        startActivity(intentshuche);
                        break;
                    case R.id.delete_goods:

                        new SweetAlertDialog(getContext(), SweetAlertDialog.NORMAL_TYPE)
                                .setTitleText("确定删除吗")
                                .setCancelText("取消")
                                .setConfirmText("确定")
                                .showCancelButton(true)
                                .setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() {
                                    @Override
                                    public void onClick(SweetAlertDialog sweetAlertDialog) {
                                        deletegoods(shipperPublishedItemAdapter.getData().get(position).getId(), position, sweetAlertDialog);
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
                    default:
                }
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

    }

    @Override
    public void initData() {
        //加载更多
        ShipperPublishedsmart.setOnLoadMoreListener(new OnLoadMoreListener() {
            @Override
            public void onLoadMore(@NonNull RefreshLayout refreshLayout) {
                pagernum++;
                initDataRecyclerview("2");
                ShipperPublishedsmart.finishLoadMore(true);//加载完成
            }
        });
        //刷新
        ShipperPublishedsmart.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(@NonNull RefreshLayout refreshLayout) {
                shipperPublishedItemAdapter.setNewData(null);
                pagernum = 1;
                initDataRecyclerview("2");

                ShipperPublishedsmart.finishRefresh(true);//刷新完成
            }
        });

    }

    @Override
    protected boolean isRegisterEventBus() {
        return true;
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
//                            if ()
//
//                            if (pagernum == 1) {
//                                shipperPublishedItemAdapter.setNewData(shipperPublishedBean.getData().getResult());
//                            } else if (pagernum < pages) {
//                                shipperPublishedItemAdapter.addData(shipperPublishedBean.getData().getResult());
//                            } else {
//                                shipperPublishedItemAdapter.loadMoreEnd();
//                            }

                        } else {
                            shipperPublishedItemAdapter.loadMoreFail();
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

    private void deletegoods(int id, int pos, SweetAlertDialog sweetAlertDialog) {
        OkGo.<String>post(Constants.Delete_Goods)

                .params("id", id)
                .execute(new StringCallback() {
                    @Override
                    public void onSuccess(Response<String> response) {
                        ShipperPublishedBean g = GsonUtil.parseJsonWithGson(response.body(), ShipperPublishedBean.class);
                        if (g.getCode().equals("0")) {
                            sweetAlertDialog.dismissWithAnimation();
                            ToastUtils.showShortToast(getActivity(), g.getMsg());
                            shipperPublishedItemAdapter.remove(pos);
                        } else {
                            ToastUtils.showShortToast(getActivity(), g.getMsg());
                        }

                    }
                });
    }

    private void setGoodsState(String goodsid, String isPublish, int posi) {
        OkGo.<String>post(Constants.Update_state_goods)

                .params("id", goodsid)
                .params("isPublish", isPublish)
                .params("mtime", DateUtil.currentSeconds())
                .execute(new StringCallback() {
                    @Override
                    public void onSuccess(Response<String> response) {
                        ShipperPublishedBean g = GsonUtil.parseJsonWithGson(response.body(), ShipperPublishedBean.class);
                        if (g.getCode().equals("0")) {
                            MessageEvent event = new MessageEvent(Published_Goods_SIGAL);
                            EventBusUtil.sendEvent(event);
                            Toast.makeText(getActivity(), "发布成功", Toast.LENGTH_SHORT).show();
                            shipperPublishedItemAdapter.remove(posi);
                        }else {
                            Toast.makeText(getActivity(), g.getMsg(), Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }

    @Override
    public void onResume() {
        super.onResume();
//        ShipperPublishedsmart.autoRefresh();
    }

    @Override
    protected void receiveEvent(MessageEvent event) {
        super.receiveEvent(event);
        LogUtils.d("BBBBB","关闭");
        switch (event.getCode()) {
            case Closed_Goods_SIGAL:
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
