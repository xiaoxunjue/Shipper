package com.revenant.shipper.ui.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.blankj.utilcode.util.ToastUtils;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.StringCallback;
import com.lzy.okgo.model.Response;

import com.revenant.shipper.R;
import com.revenant.shipper.adapter.AdviceListItemAdapter;
import com.revenant.shipper.base.BaseFragment;
import com.revenant.shipper.bean.AdvicesBean;
import com.revenant.shipper.bean.BaseBean.MessageEvent;
import com.revenant.shipper.ui.activity.AdviceDetailActivity;
import com.revenant.shipper.utils.Constants;
import com.revenant.shipper.utils.GsonUtil;
import com.revenant.shipper.utils.SPUtils;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;

import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;

import static com.revenant.shipper.bean.BaseBean.MyEventCode.Advice_Add_Success;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link AdviceOneFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class AdviceOneFragment extends BaseFragment {
    @BindView(R.id.shownodata)
    ImageView shownodata;
    @BindView(R.id.recyclerview)
    RecyclerView recyclerview;
    @BindView(R.id.smartrefresh)
    SmartRefreshLayout smartrefresh;
    private AdviceListItemAdapter listItemAdapter;

    public AdviceOneFragment() {
        // Required empty public constructor
    }

    public static AdviceOneFragment newInstance(String param1) {
        AdviceOneFragment fragment = new AdviceOneFragment();
        Bundle args = new Bundle();
        args.putString(param1, param1);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    protected int setLayoutId() {
        return R.layout.fragment_advice_one;
    }

    @Override
    protected void initView() {

    }

    @Override
    public void initData() {
        listItemAdapter = new AdviceListItemAdapter();
        recyclerview.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerview.setAdapter(listItemAdapter);
        smartrefresh.autoRefresh();
        smartrefresh.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(@NonNull RefreshLayout refreshLayout) {

                Map<String, String> map = new HashMap<>();
                map.put("accountId", String.valueOf(SPUtils.getAccounId(getActivity())));
                map.put("status", "0");
                JSONObject jsonObject = new JSONObject(map);
                getListAdvices(jsonObject);
                smartrefresh.finishRefresh(true);
            }
        });

        listItemAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                Bundle bundle = new Bundle();
                bundle.putString("adviceid", String.valueOf(listItemAdapter.getData().get(position).getId()));
                Intent intent = new Intent();
                intent.putExtras(bundle);
                intent.setClass(getActivity(), AdviceDetailActivity.class);
                startActivity(intent);

            }
        });

    }


    private void getListAdvices(JSONObject json) {
        OkGo.<String>post(Constants.Complaint_SelectList)
                .headers("Content-Type", "application/json")
                .upJson(json)
                .execute(new StringCallback() {
                    @Override
                    public void onSuccess(Response<String> response) {
                        if (response.code() == 200) {
                            AdvicesBean args = GsonUtil.parseJsonWithGson(response.body(), AdvicesBean.class);
                            if (Integer.valueOf(args.getCode()) == 0) {
                                if (args.getData().getResult().size() > 0) {
                                    listItemAdapter.setNewData(args.getData().getResult());
                                    shownodata.setVisibility(View.GONE);
                                }

                            } else {
                                ToastUtils.showShort(args.getMsg());
                            }
                        }

                    }

                    @Override
                    public void onError(Response<String> response) {
                        super.onError(response);

                    }
                });
    }

    @Override
    protected boolean isRegisterEventBus() {
        return true;
    }

    @Override
    protected void receiveEvent(MessageEvent event) {
        super.receiveEvent(event);
        switch (event.getCode()) {
            case Advice_Add_Success:
                smartrefresh.autoRefresh();
                break;
        }
    }

}
