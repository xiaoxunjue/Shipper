package com.revenant.shipper.ui.activity;

import android.content.Context;
import android.icu.text.DecimalFormat;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.core.widget.NestedScrollView;

import com.bm.library.Info;
import com.bm.library.PhotoView;
import com.bumptech.glide.Glide;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.StringCallback;
import com.lzy.okgo.model.Response;
import com.revenant.shipper.R;
import com.revenant.shipper.base.BaseActivity;
import com.revenant.shipper.bean.OrderDetailBean;
import com.revenant.shipper.bean.PersonalCommentShowBean;
import com.revenant.shipper.utils.GsonUtil;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

import static com.revenant.shipper.utils.Constants.Comment_PerSonal_Show;
import static com.revenant.shipper.utils.Constants.Driver_JustOrder_Order_Detail;


public class OrderCarDetailActivity extends BaseActivity {

    @BindView(R.id.strat_place)
    TextView stratPlace;
    @BindView(R.id.end_place)
    TextView endPlace;
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
    @BindView(R.id.car_num)
    TextView carNum;
    @BindView(R.id.timere)
    TextView timere;
    @BindView(R.id.shippername)
    TextView shippername;
    @BindView(R.id.driver_info_click)
    TextView driverInfoClick;
    @BindView(R.id.goods_name)
    TextView goodsName;
    @BindView(R.id.goods_show_name)
    LinearLayout goodsShowName;
    @BindView(R.id.shippernameimage)
    ImageView shippernameimage;
    @BindView(R.id.commentscore)
    TextView commentscore;
    @BindView(R.id.shipperscore)
    TextView shipperscore;
    @BindView(R.id.jiaoyi)
    TextView jiaoyi;
    @BindView(R.id.pingjia)
    TextView pingjia;
    @BindView(R.id.bangdanone)
    TextView bangdanone;
    @BindView(R.id.bangdanoneimage)
    ImageView bangdanoneimage;
    @BindView(R.id.bangdantwo)
    TextView bangdantwo;
    @BindView(R.id.bangdantwoimage)
    ImageView bangdantwoimage;
    @BindView(R.id.bangdanthree)
    TextView bangdanthree;
    @BindView(R.id.bangdanthreeimage)
    ImageView bangdanthreeimage;
    @BindView(R.id.show_bangdan)
    LinearLayout showBangdan;
    @BindView(R.id.huowuxinxi)
    TextView huowuxinxi;
    @BindView(R.id.photview)
    PhotoView photview;
    @BindView(R.id.location)
    ImageView location;
    @BindView(R.id.nestscroll)
    NestedScrollView nestscroll;
    @BindView(R.id.single_type)
    TextView singleType;
    @BindView(R.id.single_price)
    TextView singlePrice;
    private int data = 0;
    private int type = 0;
    private Context context;
    Info mInfo;

    @Override
    public int getContentViewResId() {
        Bundle bundle = getIntent().getExtras(); //得到传过来的bundle

        data = bundle.getInt("goods_id");
        return R.layout.activity_goods_detail;
    }

    @Override
    public void initView() {
        context = this;
        setcenterTitle("订单详情");

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

    private void getGoodsDetail(int dataid) {
        OkGo.<String>get(Driver_JustOrder_Order_Detail)
                .params("id", dataid)
                .execute(new StringCallback() {
                    @Override
                    public void onSuccess(Response<String> response) {
                        OrderDetailBean detailBean = GsonUtil.parseJsonWithGson(response.body(), OrderDetailBean.class);
                        if (detailBean.getCode().equals("0")) {
                            goodsShowName.setVisibility(View.VISIBLE);
                            goodsName.setText(detailBean.getData().getName());
                            carType.setText(detailBean.getData().getCarInfo());
                            bigStart.setText(detailBean.getData().getLoad());
                            bigEnd.setText(detailBean.getData().getUnload());
                            stratPlace.setText(detailBean.getData().getLoadingDetail());
                            endPlace.setText(detailBean.getData().getUnloadingDetail());
                            carNum.setText(detailBean.getData().getCarInfo());
                            carType.setText(String.valueOf(detailBean.getData().getWeight()));
                            timere.setText(String.valueOf(detailBean.getData().getLoadDate()));
                            carName.setText(String.valueOf(detailBean.getData().getDiverName()));

                            List<String> danwei = Arrays.asList("吨", "方", "米", "件");
                            singleType.setText("每" + danwei.get(Integer.valueOf(detailBean.getData().getDanWei()) - 1) + "价格");
                            BigDecimal bDecimal = new BigDecimal(Double.valueOf(detailBean.getData().getAmount())/Double.valueOf(detailBean.getData().getWeight()));
                            singlePrice.setText(String.valueOf(bDecimal.setScale(2,BigDecimal.ROUND_HALF_UP)));

                            if (detailBean.getData().getFirstbillPhoto() != null && !detailBean.getData().getFirstbillPhoto().isEmpty()) {
                                showBangdan.setVisibility(View.VISIBLE);
                                photview.enable();
                                if (detailBean.getData().getFirstbillPhoto() != null && !detailBean.getData().getFirstbillPhoto().isEmpty()) {
                                    bangdanone.setText("装货磅单:" + detailBean.getData().getFirstbillContent() + danwei.get(Integer.valueOf(detailBean.getData().getDanWei()) - 1));
                                    Glide.with(context)
                                            .load(detailBean.getData().getFirstbillPhoto())
                                            .centerInside()
                                            .into(bangdanoneimage);
                                    bangdanoneimage.setOnClickListener(new View.OnClickListener() {
                                        @Override
                                        public void onClick(View v) {
                                            show(bangdanoneimage, detailBean.getData().getFirstbillPhoto());
                                        }
                                    });
                                }

                                if (detailBean.getData().getSecondbillPhoto() != null && !detailBean.getData().getSecondbillPhoto().isEmpty()) {
                                    bangdantwo.setText("卸货磅单:" + detailBean.getData().getSecondbillContent() + danwei.get(Integer.valueOf(detailBean.getData().getDanWei()) - 1));
                                    Glide.with(context)
                                            .load(detailBean.getData().getFirstbillPhoto())
                                            .centerInside()
                                            .into(bangdantwoimage);
                                    bangdantwoimage.setOnClickListener(new View.OnClickListener() {
                                        @Override
                                        public void onClick(View v) {
                                            show(bangdantwoimage, detailBean.getData().getSecondbillPhoto());
                                        }
                                    });
                                }


                                if (detailBean.getData().getShipperbillPhoto() != null && !detailBean.getData().getShipperbillPhoto().isEmpty()) {
                                    bangdanthree.setText("货主磅单:" + detailBean.getData().getShipperContent() + danwei.get(Integer.valueOf(detailBean.getData().getDanWei()) - 1));
                                    Glide.with(context)
                                            .load(detailBean.getData().getShipperbillPhoto())
                                            .centerInside()
                                            .into(bangdanthreeimage);
                                    bangdanthreeimage.setOnClickListener(new View.OnClickListener() {
                                        @Override
                                        public void onClick(View v) {
                                            show(bangdanthreeimage, detailBean.getData().getShipperbillPhoto());
                                        }
                                    });
                                }


                                photview.setOnClickListener(new View.OnClickListener() {
                                    @Override
                                    public void onClick(View v) {
                                        photview.animaTo(mInfo, new Runnable() {
                                            @Override
                                            public void run() {
                                                photview.setVisibility(View.GONE);
                                            }
                                        });
                                    }
                                });

                            }

                            getScore(detailBean.getData().getDiverId());


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

    private void getScore(String id) {
        OkGo.<String>get(Comment_PerSonal_Show)

                .params("id", id)
                .execute(new StringCallback() {
                    @Override
                    public void onSuccess(Response<String> response) {
                        if (response.code() == 200) {
                            PersonalCommentShowBean personal = GsonUtil.parseJsonWithGson(response.body(), PersonalCommentShowBean.class);
                            if (Integer.valueOf(personal.getCode()) == 0) {


//                                shipperscore.setText(detailBean.getData().getShipperRate());
//                                jiaoyi.setText(String.valueOf(detailBean.getData().getShipperOrderNum()));
//                                commentscore.setText(String.valueOf(String.valueOf(detailBean.getData().getShipperScore())));
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

    private void show(ImageView imageview, String url) {
        Glide.with(this)
                .load(url)
                .placeholder(R.mipmap.uoko_guide_foreground_3)
//                .into((PhotoView) findViewById(R.id.photview));
                .dontAnimate()
                .into(photview);
//                .into(new SimpleTarget<Drawable>() {
//                    @Override
//                    public void onResourceReady(@NonNull Drawable resource, @Nullable Transition<? super Drawable> transition) {
//                        ((PhotoView) findViewById(R.id.photview)).setImageDrawable(resource);
//                    }
//                });

//        Picasso.with(this).load(url)
//                .into(((PhotoView) findViewById(R.id.photview)));
        mInfo = PhotoView.getImageViewInfo(imageview);
        photview.animaFrom(mInfo);
        photview.setVisibility(View.VISIBLE);
    }
}
