package com.revenant.shipper.ui.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.StringCallback;
import com.lzy.okgo.model.Response;
import com.revenant.shipper.R;
import com.revenant.shipper.base.BaseActivity;
import com.revenant.shipper.bean.OrderDetailBean;
import com.revenant.shipper.utils.GsonUtil;
import com.revenant.shipper.utils.Utils;


import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

import static com.revenant.shipper.utils.Constants.Driver_JustOrder_Order_Detail;


public class CommentDetailActivity extends BaseActivity {


    @BindView(R.id.left_b_bar)
    ImageView leftBBar;
    @BindView(R.id.center_title)
    TextView centerTitle;
    @BindView(R.id.righ_bar_image)
    ImageView righBarImage;
    @BindView(R.id.goodstime)
    TextView goodstime;
    @BindView(R.id.strat_place)
    TextView stratPlace;
    @BindView(R.id.end_place)
    TextView endPlace;
    @BindView(R.id.comment_add)
    Button commentAdd;
    @BindView(R.id.timer_start)
    TextView timerStart;
    @BindView(R.id.timer_end)
    TextView timerEnd;
    @BindView(R.id.comment_d_no)
    TextView commentDNo;
    @BindView(R.id.comment_d_start)
    TextView commentDStart;
    @BindView(R.id.comment_d_end)
    TextView commentDEnd;
    @BindView(R.id.comment_d_car)
    TextView commentDCar;
    @BindView(R.id.comment_d_type)
    TextView commentDType;
    @BindView(R.id.comment_d_price)
    TextView commentDPrice;
    @BindView(R.id.comment_d_head)
    ImageView commentDHead;
    @BindView(R.id.comment_d_name)
    TextView commentDName;
    @BindView(R.id.comment_d_score)
    TextView commentDScore;
    @BindView(R.id.one)
    TextView one;
    @BindView(R.id.two)
    TextView two;
    @BindView(R.id.three)
    TextView three;

    private String orderid = "";

    @Override
    public int getContentViewResId() {
        return R.layout.activity_comment_detail;
    }

    @Override
    public void initView() {
        centerTitle.setText("查看详情");
        righBarImage.setImageResource(R.mipmap.kefu);
        orderid = getIntent().getStringExtra("orderid");
        getGoodsDetail(orderid);
    }

    @Override
    public void initData() {

    }

    @Override
    protected boolean isshowtitlebar() {
        return false;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }

    @OnClick({R.id.left_b_bar, R.id.comment_add,R.id.righ_bar_image})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.left_b_bar:
                finish();
                break;
            case R.id.comment_add:
                Bundle bundle = new Bundle();
                bundle.putString("orderid", orderid);
                bundle.putString("orderno", getIntent().getStringExtra("orderno"));
                bundle.putString("orderstart", getIntent().getStringExtra("orderstart"));
                bundle.putString("orderend", getIntent().getStringExtra("orderend"));
                bundle.putString("orderprice",getIntent().getStringExtra("orderprice"));
                bundle.putString("ordercar", getIntent().getStringExtra("ordercar"));
                bundle.putString("ordertype", getIntent().getStringExtra("ordertype"));
                bundle.putString("ordername", String.valueOf(getIntent().getStringExtra("ordername")));
                bundle.putString("orderphoto", String.valueOf(getIntent().getStringExtra("orderphoto")));
                startActivity(AddCommentActivity.class, bundle);
                break;
            case R.id.righ_bar_image:
                Utils.callPhone(CommentDetailActivity.this, "0412-8882888");
                break;
        }
    }

    private void getGoodsDetail(String dataid) {
        OkGo.<String>get(Driver_JustOrder_Order_Detail)

                .params("id", dataid)
                .execute(new StringCallback() {
                    @Override
                    public void onSuccess(Response<String> response) {
                        OrderDetailBean detailBean = GsonUtil.parseJsonWithGson(response.body(), OrderDetailBean.class);
                        if (detailBean.getCode().equals("0")) {
                            timerStart.setText(detailBean.getData().getLoad());
                            timerEnd.setText(detailBean.getData().getUnload());

                            commentDStart.setText("发货时间:"+detailBean.getData().getDespathTime());
                            commentDEnd.setText("卸货时间"+detailBean.getData().getReceipTime());

                            commentDNo.setText(String.valueOf(detailBean.getData().getOrderNo()));

                            stratPlace.setText(detailBean.getData().getLoadingDetail());
                            endPlace.setText(detailBean.getData().getUnloadingDetail());

                            commentDPrice.setText(detailBean.getData().getAmount());

                            commentDCar.setText(detailBean.getData().getCarInfo());
                            one.setText("交易:"+String.valueOf(detailBean.getData().getDiverOrderNum()));
//                            two.setText("发货:"+detailBean.getData().getShipperGoodsNum());
                            three.setText("好评率:"+detailBean.getData().getDiverRate());
                            Glide.with(CommentDetailActivity.this).load(detailBean.getData().getDiverPhoto()).centerCrop().
                                    into(commentDHead);
                            commentDName.setText(detailBean.getData().getDiverName());
                            commentDScore.setText(detailBean.getData().getDiverScore());
                        }
                    }
                });
    }
}
