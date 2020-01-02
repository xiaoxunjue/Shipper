package com.revenant.shipper.ui.activity;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;

import androidx.core.content.ContextCompat;

import com.apkfuns.logutils.LogUtils;
import com.revenant.shipper.R;
import com.revenant.shipper.base.BaseActivity;
import com.revenant.shipper.bean.BigItemBean;
import com.revenant.shipper.ui.view.MineBigItem;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MineActivity extends BaseActivity {
    @BindView(R.id.TransportOrder)
    MineBigItem transportOrder;
    private List<BigItemBean> transportOrderList;
    private List<String> transportOrderTitles = Arrays.asList("我的运单", "运输中");
    private List<Drawable> transportOrderDrawable;
    private List<Class> transportOrderClassList;

    @Override
    public int getContentViewResId() {
        return R.layout.activity_mine;
    }

    @Override
    public void initView() {
        transportOrder.titleone("运输订单");
        transportOrder.titletwo("其他订单");

        transportOrderDrawable = new ArrayList<Drawable>();
        transportOrderDrawable.add(ContextCompat.getDrawable(getApplicationContext(), R.mipmap.ic_launcher));
        transportOrderDrawable.add(ContextCompat.getDrawable(getApplicationContext(), R.mipmap.ic_launcher));

        transportOrderList = new ArrayList<>();

        for (int i = 0; i < transportOrderTitles.size(); i++) {
            BigItemBean bigItemBean = new BigItemBean();
            bigItemBean.setTitle(transportOrderTitles.get(i));
            bigItemBean.setDrawable(transportOrderDrawable.get(i));
            transportOrderList.add(bigItemBean);
        }

        transportOrderClassList = new ArrayList<>();
        transportOrderClassList.add(InitActivity.class);
        transportOrderClassList.add(InitActivity.class);
        transportOrder.setActivity(transportOrderClassList);
        transportOrder.setData(transportOrderList);

        transportOrder.item_mine_right_click(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(OrderActivity.class);
            }
        });


    }

    @Override
    public void initData() {

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }
}
