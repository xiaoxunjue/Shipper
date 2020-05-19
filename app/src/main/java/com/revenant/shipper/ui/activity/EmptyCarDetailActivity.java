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
import com.revenant.shipper.bean.ShipperBean.EmptyDetailBean;
import com.revenant.shipper.utils.Constants;
import com.revenant.shipper.utils.GsonUtil;
import com.revenant.shipper.utils.SPUtils;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

import static com.revenant.shipper.utils.Constants.Comment_PerSonal_Show;

public class EmptyCarDetailActivity extends BaseActivity {
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
    @BindView(R.id.add_shuche)
    TextView addShuche;
    @BindView(R.id.push_goods)
    TextView pushGoods;
    @BindView(R.id.shippernameimage)
    ImageView shippernameimage;
    @BindView(R.id.commentscore)
    TextView commentscore;
    @BindView(R.id.driver_info_click)
    TextView driverInfoClick;
    @BindView(R.id.shipperscore)
    TextView shipperscore;
    @BindView(R.id.jiaoyi)
    TextView jiaoyi;
    @BindView(R.id.pingjia)
    TextView pingjia;
    @BindView(R.id.personal)
    LinearLayout personal;
    private String data;
    private String driver_id = "";
    private String accountId = "";
    private String goodsId = "";
    private Context context;

    @Override
    public int getContentViewResId() {
        context = this;
        return R.layout.activity_empty_car_detail;
    }

    @Override
    public void initView() {
        setcenterTitle("空车详情");
        Bundle bundle = getIntent().getExtras(); //得到传过来的bundle

        data = bundle.getString("empty_id");
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
        OkGo.<String>get(Constants.Get_Empty_Details)
                .params("id", dataid)
                .execute(new StringCallback() {
                    @Override
                    public void onSuccess(Response<String> response) {
                        EmptyDetailBean detailBean = GsonUtil.parseJsonWithGson(response.body(), EmptyDetailBean.class);
                        if (detailBean.getCode().equals("0")) {
                            stratPlace.setText(detailBean.getData().getLoading());
                            endPlace.setText(detailBean.getData().getUnloading());
                            carNum.setText(detailBean.getData().getVehicle_number());
                            carType.setText(detailBean.getData().getCar_info() + "吨");
                            carPho.setText(detailBean.getData().getMobile());
                            carName.setText(detailBean.getData().getUsername());
                            driver_id = String.valueOf(detailBean.getData().getDriver_id());
                            accountId = String.valueOf(detailBean.getData().getAccountId());
                            goodsId = String.valueOf(detailBean.getData().getId());
                            getScore(accountId);
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

    @OnClick({R.id.add_shuche, R.id.push_goods, R.id.driver_info_click})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.add_shuche:
                if (!accountId.isEmpty() && !driver_id.isEmpty()) {
//                    add_chu_car(String.valueOf(SPUtils.getAccounId(context)), driver_id);
                    add_chu_car(String.valueOf(SPUtils.getAccounId(context)), driver_id);
                }
                break;
            case R.id.push_goods:
                Bundle bundle = new Bundle();
                bundle.putString("driverid", driver_id);
                startActivity(PushGoodsListActivity.class, bundle);
                break;
            case R.id.driver_info_click:
                Bundle bundlea = new Bundle();
                bundlea.putString("driverid", driver_id);
                startActivity(DriverInfoActivity.class, bundlea);
                break;
        }
    }

    private void add_chu_car(String accountId, String driverId) {
        OkGo.<String>post(Constants.Add_shu_car)
                .params("accountId", accountId)
                .params("driverId", driverId)
                .execute(new StringCallback() {
                    @Override
                    public void onSuccess(Response<String> response) {
                        EmptyDetailBean detailBean = GsonUtil.parseJsonWithGson(response.body(), EmptyDetailBean.class);
                        showToast(detailBean.getMsg());
                    }
                });
    }

    private void getScore(String id) {
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
