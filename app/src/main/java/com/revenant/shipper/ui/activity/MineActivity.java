package com.revenant.shipper.ui.activity;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.core.content.ContextCompat;

import com.revenant.shipper.R;
import com.revenant.shipper.base.BaseActivity;
import com.revenant.shipper.bean.BigItemBean;
import com.revenant.shipper.ui.view.MineBigItem;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MineActivity extends BaseActivity {
    @BindView(R.id.TransportOrder)
    MineBigItem transportOrder;
    @BindView(R.id.mine_personal_image)
    ImageView minePersonalImage;
    @BindView(R.id.improve_driver_info)
    TextView improveDriverInfo;
    @BindView(R.id.ShipperAssistant)
    MineBigItem ShipperAssistant;
    @BindView(R.id.ShipperService)
    MineBigItem ShipperService;
    private List<BigItemBean> transportOrderList;
    private List<String> transportOrderTitles = Arrays.asList("订单管理", "运输中", "待评价", "取消/退款");
    private List<Drawable> transportOrderDrawable;
    private List<Class> transportOrderClassList;


    private List<BigItemBean> ShipperAssistantList;
    private List<String> ShipperAssistantTitles = Arrays.asList("发布货源", "我的发票", "历史发票", "我的模板", "我的熟车", "货主圈", "公司成员");
    private List<Drawable> ShipperAssistantDrawable;
    private List<Class> ShipperAssistantClassList;


    private List<BigItemBean> ShipperServiceList;
    private List<String> ShipperServiceTitles = Arrays.asList("客户服务", "处置与申述", "鲁通卡ETC", "优惠加油", "App分享", "财务统计", "我的加油卡", "设置");
    private List<Drawable> ShipperServiceDrawable;
    private List<Class> ShipperServiceClassList;

    @Override
    public int getContentViewResId() {
        return R.layout.activity_mine;
    }

    @Override
    public void initView() {
        transportOrder.titleone("我的订单");
        transportOrder.titletwo("其他订单 >");

        transportOrderDrawable = new ArrayList<Drawable>();
        transportOrderDrawable.add(ContextCompat.getDrawable(getApplicationContext(), R.mipmap.order));
        transportOrderDrawable.add(ContextCompat.getDrawable(getApplicationContext(), R.mipmap.supply));
        transportOrderDrawable.add(ContextCompat.getDrawable(getApplicationContext(), R.mipmap.comment));
        transportOrderDrawable.add(ContextCompat.getDrawable(getApplicationContext(), R.mipmap.refund));
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


        ShipperAssistant.titleone("货主助手");
        ShipperAssistant.titletwo("全部功能 >");

        ShipperAssistantDrawable = new ArrayList<Drawable>();
        ShipperAssistantDrawable.add(ContextCompat.getDrawable(getApplicationContext(), R.mipmap.supply));
        ShipperAssistantDrawable.add(ContextCompat.getDrawable(getApplicationContext(), R.mipmap.order));
        ShipperAssistantDrawable.add(ContextCompat.getDrawable(getApplicationContext(), R.mipmap.order));
        ShipperAssistantDrawable.add(ContextCompat.getDrawable(getApplicationContext(), R.mipmap.component));
        ShipperAssistantDrawable.add(ContextCompat.getDrawable(getApplicationContext(), R.mipmap.acquaintance));
        ShipperAssistantDrawable.add(ContextCompat.getDrawable(getApplicationContext(), R.mipmap.consignor));
        ShipperAssistantDrawable.add(ContextCompat.getDrawable(getApplicationContext(), R.mipmap.members));
        ShipperAssistantList = new ArrayList<>();

        for (int i = 0; i < ShipperAssistantTitles.size(); i++) {
            BigItemBean bigItemBean = new BigItemBean();
            bigItemBean.setTitle(ShipperAssistantTitles.get(i));
            bigItemBean.setDrawable(ShipperAssistantDrawable.get(i));
            ShipperAssistantList.add(bigItemBean);
        }

        ShipperAssistantClassList = new ArrayList<>();
        ShipperAssistantClassList.add(InitActivity.class);
        ShipperAssistantClassList.add(InitActivity.class);
        ShipperAssistant.setActivity(ShipperAssistantClassList);
        ShipperAssistant.setData(ShipperAssistantList);

        ShipperAssistant.item_mine_right_click(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(OrderActivity.class);
            }
        });


        ShipperService.titleone("货主服务");
        ShipperService.titletwo("全部功能 >");

        ShipperServiceDrawable = new ArrayList<Drawable>();
        ShipperServiceDrawable.add(ContextCompat.getDrawable(getApplicationContext(), R.mipmap.client));
        ShipperServiceDrawable.add(ContextCompat.getDrawable(getApplicationContext(), R.mipmap.dispose));
        ShipperServiceDrawable.add(ContextCompat.getDrawable(getApplicationContext(), R.mipmap.card));
        ShipperServiceDrawable.add(ContextCompat.getDrawable(getApplicationContext(), R.mipmap.discount));
        ShipperServiceDrawable.add(ContextCompat.getDrawable(getApplicationContext(), R.mipmap.share));
        ShipperServiceDrawable.add(ContextCompat.getDrawable(getApplicationContext(), R.mipmap.finance));
        ShipperServiceDrawable.add(ContextCompat.getDrawable(getApplicationContext(), R.mipmap.card));
        ShipperServiceDrawable.add(ContextCompat.getDrawable(getApplicationContext(), R.mipmap.setting));
        ShipperServiceList = new ArrayList<>();

        for (int i = 0; i < ShipperServiceTitles.size(); i++) {
            BigItemBean bigItemBean = new BigItemBean();
            bigItemBean.setTitle(ShipperServiceTitles.get(i));
            bigItemBean.setDrawable(ShipperServiceDrawable.get(i));
            ShipperServiceList.add(bigItemBean);
        }

        ShipperServiceClassList = new ArrayList<>();
        ShipperServiceClassList.add(InitActivity.class);
        ShipperServiceClassList.add(InitActivity.class);
        ShipperService.setActivity(ShipperServiceClassList);
        ShipperService.setData(ShipperServiceList);

        ShipperService.item_mine_right_click(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(OrderActivity.class);
            }
        });

        showrightimgae(true);
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

    @OnClick({R.id.improve_driver_info})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.improve_driver_info:
                /*
                 * 完善司机信息
                 * */
//                startActivity(ImproveDriverInformationActivity.class);
                /*
                 * 完善新司机信息
                 * */
//                startActivity(NewImproveDriverInformationActivity.class);
                /*
                 * 上传榜单
                 * */
                startActivity(UploadPicturesActivity.class);
                break;
        }
    }

    @Override
    public void leftbarclick() {
        super.leftbarclick();
        finish();
    }

    @Override
    public void rightbasebarimageclick() {
        super.rightbasebarimageclick();
        startActivity(NewsActivity.class);
    }
}
