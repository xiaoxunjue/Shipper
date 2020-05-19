package com.revenant.shipper.ui.fragment;


import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.apkfuns.logutils.LogUtils;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.github.nukc.stateview.StateView;
import com.lljjcoder.Interface.OnCityItemClickListener;
import com.lljjcoder.bean.CityBean;
import com.lljjcoder.bean.DistrictBean;
import com.lljjcoder.bean.ProvinceBean;
import com.lljjcoder.citywheel.CityConfig;
import com.lljjcoder.style.citylist.Toast.ToastUtils;
import com.lljjcoder.style.citypickerview.CityPickerView;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.StringCallback;
import com.lzy.okgo.model.Response;
import com.revenant.shipper.App;
import com.revenant.shipper.R;
import com.revenant.shipper.adapter.EmptyLoginItemAdapter;
import com.revenant.shipper.base.BaseFragment;
import com.revenant.shipper.bean.ShipperBean.JieHuoCheLiangBean;
import com.revenant.shipper.ui.activity.EmptyCarDetailActivity;
import com.revenant.shipper.ui.activity.NewSecondImproveDriverInformationActivity;
import com.revenant.shipper.utils.Constants;
import com.revenant.shipper.utils.GsonUtil;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnLoadMoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;

import butterknife.BindView;
import butterknife.OnClick;

import static com.revenant.shipper.utils.Constants.Type_shipper_message;
import static com.revenant.shipper.utils.Constants.Type_shipper_status;

/**
 * A simple {@link Fragment} subclass.
 */
public class JieHuoCheLiangFragment extends BaseFragment {


    @BindView(R.id.start_place)
    TextView startPlace;
    @BindView(R.id.end_place)
    TextView endPlace;
    @BindView(R.id.jiehuocheliangrecy)
    RecyclerView jiehuocheliangrecy;
    @BindView(R.id.jiehuocheliangsmart)
    SmartRefreshLayout jiehuocheliangsmart;
    CityPickerView mPickerstart = new CityPickerView();
    CityPickerView mPickerend = new CityPickerView();
    @BindView(R.id.loadLayout)
    LinearLayout loadLayout;
    private EmptyLoginItemAdapter itemAdapter;

    private String startcode = "110101";
    private String endcode = "110100";

    private int pagernum = 1;

    public JieHuoCheLiangFragment() {
        // Required empty public constructor
    }


    public static JieHuoCheLiangFragment newInstance(String param1) {
        JieHuoCheLiangFragment fragment = new JieHuoCheLiangFragment();
        Bundle args = new Bundle();
        args.putString(param1, param1);
        fragment.setArguments(args);
        return fragment;
    }


    @Override
    protected int setLayoutId() {
        return R.layout.fragment_jiehuoccheliang;
    }

    @Override
    protected void initView() {
        mStateView = StateView.inject(loadLayout);
        mPickerstart.init(getActivity());
        mPickerend.init(getActivity());
        itemAdapter = new EmptyLoginItemAdapter();
        jiehuocheliangrecy.setLayoutManager(new LinearLayoutManager(getActivity()));
        jiehuocheliangrecy.setAdapter(itemAdapter);
        jiehuocheliangsmart.autoRefresh();
        mStateView.setOnRetryClickListener(new StateView.OnRetryClickListener() {
            @Override
            public void onRetryClick() {
                //do something
                jiehuocheliangsmart.autoRefresh();
            }
        });
    }

    @Override
    public void initData() {

        jiehuocheliangsmart.setOnLoadMoreListener(new OnLoadMoreListener() {
            @Override
            public void onLoadMore(@NonNull RefreshLayout refreshLayout) {
                pagernum++;
                initDataRecyclerview(startcode, endcode);
                jiehuocheliangsmart.finishLoadMore(true);//加载完成
            }
        });
        //刷新
        jiehuocheliangsmart.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(@NonNull RefreshLayout refreshLayout) {
                itemAdapter.setNewData(null);
                pagernum = 1;
                initDataRecyclerview(startcode, endcode);

                jiehuocheliangsmart.finishRefresh(true);//刷新完成
            }
        });

        itemAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                App applicationContext = App.getApplication();
                int userStatus = applicationContext.getUserStatus();
                if (userStatus == 0) {
                    ToastUtils.showShortToast(getContext(), "没有认证请去认证");
                    Intent intent = new Intent();
                    intent.setClass(getActivity(), NewSecondImproveDriverInformationActivity.class);
                    startActivity(intent);
                } else if (userStatus == Type_shipper_status) {
                    ToastUtils.showShortToast(getActivity(), Type_shipper_message);
                } else {
                    Bundle bundle = new Bundle();
                    bundle.putString("empty_id", String.valueOf(itemAdapter.getData().get(position).getId()));
                    Intent intent = new Intent();
                    intent.putExtras(bundle);
                    intent.setClass(getActivity(), EmptyCarDetailActivity.class);
                    startActivity(intent);
                }


            }
        });

        itemAdapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
            @Override
            public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                if (view.getId() == R.id.nologinphone) {
                    App applicationContext = App.getApplication();
                    int userStatus = applicationContext.getUserStatus();
                    if (userStatus == 0) {
                        ToastUtils.showShortToast(getContext(), "没有认证请去认证");
                        Intent intent = new Intent();
                        intent.setClass(getActivity(), NewSecondImproveDriverInformationActivity.class);
                        startActivity(intent);
                    } else if (userStatus == Type_shipper_status) {
                        ToastUtils.showShortToast(mContext, Type_shipper_message);
                    } else {
                        callPhone(itemAdapter.getData().get(position).getPhone());
                    }
                }

            }
        });

    }

    @OnClick({R.id.start_place, R.id.end_place})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.start_place:
                alertareastrat();
                break;
            case R.id.end_place:
                alertareaend();
                break;
        }
    }

    private void alertareastrat() {
        CityConfig cityConfig = new CityConfig.Builder()
                .confirTextColor("#1E90FF")
                .cancelTextColor("#1E90FF")
                .districtCyclic(false)
                .cityCyclic(false)
                .provinceCyclic(false)
                .build();
        mPickerstart.setConfig(cityConfig);

        mPickerstart.setOnCityItemClickListener(new OnCityItemClickListener() {
            @Override
            public void onSelected(ProvinceBean province, CityBean city, DistrictBean district) {
                startcode = district.getId();
                startPlace.setText(city.getName());
                jiehuocheliangsmart.autoRefresh();
            }

            @Override
            public void onCancel() {
                ToastUtils.showLongToast(getActivity(), "已取消");
            }
        });

        mPickerstart.showCityPicker();
    }

    private void alertareaend() {
        CityConfig cityConfig = new CityConfig.Builder()
                .confirTextColor("#1E90FF")
                .districtCyclic(false)
                .cityCyclic(false)
                .provinceCyclic(false)
                .cancelTextColor("#1E90FF").build();
        mPickerend.setConfig(cityConfig);

        mPickerend.setOnCityItemClickListener(new OnCityItemClickListener() {
            @Override
            public void onSelected(ProvinceBean province, CityBean city, DistrictBean district) {
                endcode = district.getId();

                endPlace.setText(city.getName());
                jiehuocheliangsmart.autoRefresh();
            }

            @Override
            public void onCancel() {
                ToastUtils.showLongToast(getActivity(), "已取消");
            }
        });

        mPickerend.showCityPicker();
    }

    private void initDataRecyclerview(String loadCode, String unLoadCode) {
        OkGo.<String>get(Constants.List_Empty_No_Authentication)
                .params("isAuthentication", "1")
                .params("pageNum", pagernum)
//                .params("loadCode", loadCode)
//                .params("unLoadCode", unLoadCode)
                .execute(new StringCallback() {
                    @Override
                    public void onSuccess(Response<String> response) {
                        LogUtils.d("SSSSSSSS" + response.body());
                        JieHuoCheLiangBean shipperPublishedBean = GsonUtil.parseJsonWithGson(response.body(), JieHuoCheLiangBean.class);
                        int pageNum = shipperPublishedBean.getData().getPageNum();
                        int pages = shipperPublishedBean.getData().getPages();
                        if (shipperPublishedBean.getCode().equals("0")) {
                            if (pageNum == 1) {
                                itemAdapter.setNewData(shipperPublishedBean.getData().getResult());
                            } else if (pageNum <= pages) {
                                itemAdapter.addData(shipperPublishedBean.getData().getResult());
                            } else {
                                itemAdapter.loadMoreEnd();
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
                            itemAdapter.loadMoreFail();
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

    public void callPhone(String phoneNum) {
        Intent intent = new Intent(Intent.ACTION_DIAL);
        Uri data = Uri.parse("tel:" + phoneNum);
        intent.setData(data);
        getContext().startActivity(intent);
    }

    private void JumpClass(Class jumpActivity) {
        App applicationContext = App.getApplication();
        int userStatus = applicationContext.getUserStatus();
        switch (userStatus) {
            case 0:
                ToastUtils.showShortToast(getContext(), "没有认证请去认证");
                Intent intent = new Intent();
                intent.setClass(getActivity(), NewSecondImproveDriverInformationActivity.class);
                startActivity(intent);
                break;
            case 9:
                ToastUtils.showShortToast(getContext(), Type_shipper_message);
                break;

        }
    }

    @Override
    public void onResume() {
        super.onResume();
        jiehuocheliangsmart.autoRefresh();
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
//        jiehuocheliangsmart.autoRefresh();
    }
}
