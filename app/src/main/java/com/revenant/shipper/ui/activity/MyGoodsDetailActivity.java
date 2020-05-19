package com.revenant.shipper.ui.activity;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.StringCallback;
import com.lzy.okgo.model.Response;
import com.revenant.shipper.R;
import com.revenant.shipper.base.BaseActivity;
import com.revenant.shipper.bean.PersonalCommentShowBean;
import com.revenant.shipper.bean.ShipperBean.FaPiaoDetailBean;
import com.revenant.shipper.bean.ShipperBean.MyGoodsDetailBean;
import com.revenant.shipper.utils.Constants;
import com.revenant.shipper.utils.GsonUtil;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

import static com.revenant.shipper.utils.Constants.Comment_PerSonal_Show;

public class MyGoodsDetailActivity extends BaseActivity {
    @BindView(R.id.strat_place)
    TextView stratPlace;
    @BindView(R.id.end_place)
    TextView endPlace;
    @BindView(R.id.car_num)
    TextView carNum;
    @BindView(R.id.car_type)
    TextView carType;
    @BindView(R.id.car_pho)
    TextView carPho;
    @BindView(R.id.car_name)
    TextView carName;
    @BindView(R.id.show_driver_info)
    LinearLayout showDriverInfo;
    @BindView(R.id.big_start)
    TextView bigStart;
    @BindView(R.id.big_end)
    TextView bigEnd;
    @BindView(R.id.shippername)
    TextView shippername;
    @BindView(R.id.driver_info_click)
    TextView driverInfoClick;
    @BindView(R.id.timere)
    TextView timere;
    @BindView(R.id.goods_name)
    TextView goodsName;
    @BindView(R.id.goods_show_name)
    LinearLayout goodsShowName;
    @BindView(R.id.commentscore)
    TextView commentscore;
    @BindView(R.id.shipperscore)
    TextView shipperscore;
    @BindView(R.id.jiaoyi)
    TextView jiaoyi;
    @BindView(R.id.pingjia)
    TextView pingjia;
    @BindView(R.id.shippernameimage)
    ImageView shippernameimage;
    @BindView(R.id.single_type)
    TextView singleType;
    @BindView(R.id.single_price)
    TextView singlePrice;
    private String data;
    private int type = 0;
    private int driver_id = 0;
    private List<String> urlist = Arrays.asList(Constants.Detail_Goods_Publish_Or_closed, Constants.Fa_Piao_Write_Detail);
    private Context context;

    @Override
    public int getContentViewResId() {
        return R.layout.activity_goods_detail;
    }

    @Override
    public void initView() {
        setcenterTitle("货物详情");
        context = this;
        Bundle bundle = getIntent().getExtras(); //得到传过来的bundle

        data = bundle.getString("goods_id");
        type = bundle.getInt("goods_type", 0);
        if (type == 0) {
            shippername.setText("货主信息");
        }
    }

    @Override
    public void leftbarclick() {
        super.leftbarclick();
        finish();
    }

    @Override
    public void initData() {
        getGoodsDetail(data);
    }

    private void getGoodsDetail(String dataid) {
        OkGo.<String>get(urlist.get(type))

                .params("id", dataid)
                .execute(new StringCallback() {
                    @Override
                    public void onSuccess(Response<String> response) {
                        switch (type) {
                            case 0:
                                MyGoodsDetailBean detailBean = GsonUtil.parseJsonWithGson(response.body(), MyGoodsDetailBean.class);
                                if (detailBean.getCode().equals("0")) {
                                    List<String> danwei = Arrays.asList("吨", "方", "米", "件");
                                    goodsShowName.setVisibility(View.VISIBLE);
                                    goodsName.setText(detailBean.getData().getInfo().getName());
                                    carNum.setText(detailBean.getData().getInfo().getVehicleLeader());
                                    carType.setText(String.valueOf(detailBean.getData().getInfo().getWeight()) + danwei.get(detailBean.getData().getInfo().getDanWei() - 1));
                                    bigStart.setText(detailBean.getData().getInfo().getLoading());
                                    bigEnd.setText(detailBean.getData().getInfo().getUnload());
                                    carName.setText(detailBean.getData().getInfo().getCompanyName());
                                    timere.setText(detailBean.getData().getInfo().getLoadDate());
                                    stratPlace.setText(detailBean.getData().getInfo().getLoadingDetail());
                                    endPlace.setText(detailBean.getData().getInfo().getUnloadingDetail());
                                    singleType.setText("每" + danwei.get(Integer.valueOf(detailBean.getData().getInfo().getDanWei()) - 1) + "价格");
                                    BigDecimal bDecimal = new BigDecimal(Double.valueOf(detailBean.getData().getInfo().getPrice()) / Double.valueOf(detailBean.getData().getInfo().getWeight()));
                                    singlePrice.setText(String.valueOf(bDecimal.setScale(2, BigDecimal.ROUND_HALF_UP)));
                                    driver_id = detailBean.getData().getInfo().getAccountId();
                                    getScore(driver_id);

                                }
                                break;
                            case 1:

                                FaPiaoDetailBean detailsBean = GsonUtil.parseJsonWithGson(response.body(), FaPiaoDetailBean.class);
                                if (detailsBean.getCode().equals("0")) {
                                    stratPlace.setText(detailsBean.getData().getAddress());
                                    endPlace.setText(detailsBean.getData().getAddressee());
                                    driver_id = detailsBean.getData().getAccountId();
                                }
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

    @OnClick(R.id.driver_info_click)
    public void onViewClicked() {
        if (driver_id != 0) {
            Bundle bundlea = new Bundle();
            bundlea.putString("driverid", String.valueOf(driver_id));
            startActivity(DriverInfoActivity.class, bundlea);
        }
    }

    private void getScore(int id) {
        OkGo.<String>get(Comment_PerSonal_Show)

                .params("id", id)
                .execute(new StringCallback() {
                    @Override
                    public void onSuccess(Response<String> response) {
                        if (response.code() == 200) {
                            PersonalCommentShowBean personal = GsonUtil.parseJsonWithGson(response.body(), PersonalCommentShowBean.class);
                            if (Integer.valueOf(personal.getCode()) == 0) {

                                Glide.with(context).load(personal.getData().getPhoto()).into(shippernameimage);
                                shipperscore.setText(personal.getData().getRate());
                                jiaoyi.setText(String.valueOf(personal.getData().getOrderCount()));
                                pingjia.setText(String.valueOf(String.valueOf(personal.getData().getEvaluateCount())));
                                commentscore.setText(String.valueOf(String.valueOf(personal.getData().getScore())));
                            }

                        }

                    }

                    @Override
                    public void onError(Response<String> response) {
                        super.onError(response);
                        showToast(response.message());
                    }
                });
    }
}
