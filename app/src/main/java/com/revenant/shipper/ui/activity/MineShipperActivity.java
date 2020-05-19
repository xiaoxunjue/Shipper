package com.revenant.shipper.ui.activity;

import android.app.Activity;
import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.core.content.ContextCompat;

import com.apkfuns.logutils.LogUtils;
import com.blankj.utilcode.util.AppUtils;
import com.bumptech.glide.Glide;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.StringCallback;
import com.lzy.okgo.model.Response;
import com.revenant.shipper.R;
import com.revenant.shipper.App;
import com.revenant.shipper.base.BaseActivity;
import com.revenant.shipper.bean.BigItemBean;
import com.revenant.shipper.bean.ShipperBean.PersonalDetailBean;
import com.revenant.shipper.ui.view.MineBigItem;
import com.revenant.shipper.utils.GsonUtil;
import com.revenant.shipper.utils.SPUtils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

import static com.revenant.shipper.utils.Constants.Get_personal_info;
import static com.revenant.shipper.utils.Constants.Type_shipper_message;
import static com.revenant.shipper.utils.Constants.Type_shipper_status;

public class MineShipperActivity extends BaseActivity {
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
    @BindView(R.id.improve_driver_info_btn)
    RelativeLayout improveDriverInfoBtn;
    @BindView(R.id.mine_bigitem_titleone)
    TextView mineBigitemTitleone;
    @BindView(R.id.mine_bigitem_titletwo)
    TextView mineBigitemTitletwo;
    @BindView(R.id.item_mine_right_click)
    LinearLayout itemMineRightClick;
    @BindView(R.id.mybacket_text)
    TextView mybacketText;
    @BindView(R.id.show_money_click)
    TextView showMoneyClick;
    @BindView(R.id.big_back)
    ImageView bigBack;
    @BindView(R.id.edit_personal_info)
    TextView editPersonalInfo;
    @BindView(R.id.phone_num)
    TextView phoneNum;
    @BindView(R.id.personal_status)
    TextView personalStatus;
    private List<BigItemBean> transportOrderList;
    private List<String> transportOrderTitles = Arrays.asList("订单管理",
            "待装货",
            "运输中",
            "待结算",
//            "待评价",
//            "待确认",
            "已完成",
            "已卸货"
    );
    private List<Drawable> transportOrderDrawable;
    private List<Class> transportOrderClassList;


    private List<BigItemBean> ShipperAssistantList;
    private List<String> ShipperAssistantTitles = Arrays.asList("发布货源", "我的发票", "银行卡管理", "评价管理", "我的熟车", "货主圈", "城市合伙人");
    //    private List<String> ShipperAssistantTitles = Arrays.asList("发布货源", "我的发票", "历史发票", "我的模板", "我的熟车", "货主圈", "公司成员");
    private List<Drawable> ShipperAssistantDrawable;
    private List<Class> ShipperAssistantClassList;


    private List<BigItemBean> ShipperServiceList;
    private List<String> ShipperServiceTitles = Arrays.asList("客户服务", "鲁通卡ETC", "优惠加油", "App分享", "设置");
    private List<Drawable> ShipperServiceDrawable;
    private List<Class> ShipperServiceClassList;
    private boolean isshowimprove = false;
    private boolean isshowwallet = false;
    private String showwallet = "";
    private Context context;
    private String imageUrl = "";
//    private boolean isyanzheng = false;

    @Override
    public int getContentViewResId() {
        context = this;
        return R.layout.activity_mine;
    }

    @Override
    public void initView() {
//        if (isshowimprove) {
//            improveDriverInfoBtn.setVisibility(View.VISIBLE);
//        } else {
//            improveDriverInfoBtn.setVisibility(View.GONE);
//        }
//


        getpseronal();
        transportOrder.titleone("我的订单");
        transportOrder.titletwo("其他订单 >");

        transportOrderDrawable = new ArrayList<Drawable>();
        transportOrderDrawable.add(ContextCompat.getDrawable(getApplicationContext(), R.mipmap.order));
        transportOrderDrawable.add(ContextCompat.getDrawable(getApplicationContext(), R.mipmap.order1));
        transportOrderDrawable.add(ContextCompat.getDrawable(getApplicationContext(), R.mipmap.order2));
        transportOrderDrawable.add(ContextCompat.getDrawable(getApplicationContext(), R.mipmap.order3));
        transportOrderDrawable.add(ContextCompat.getDrawable(getApplicationContext(), R.mipmap.order4));
        transportOrderDrawable.add(ContextCompat.getDrawable(getApplicationContext(), R.mipmap.order5));
        transportOrderDrawable.add(ContextCompat.getDrawable(getApplicationContext(), R.mipmap.order6));
        transportOrderDrawable.add(ContextCompat.getDrawable(getApplicationContext(), R.mipmap.order7));
        transportOrderList = new ArrayList<>();

        for (int i = 0; i < transportOrderTitles.size(); i++) {
            BigItemBean bigItemBean = new BigItemBean();
            bigItemBean.setTitle(transportOrderTitles.get(i));
            bigItemBean.setDrawable(transportOrderDrawable.get(i));
            transportOrderList.add(bigItemBean);
        }

        transportOrderClassList = new ArrayList<>();
        transportOrderClassList.add(OrderActivity.class);
        transportOrderClassList.add(OrderActivity.class);
        transportOrderClassList.add(OrderActivity.class);
        transportOrderClassList.add(OrderActivity.class);
//        transportOrderClassList.add(OrderActivity.class);
//        transportOrderClassList.add(OrderActivity.class);
        transportOrderClassList.add(OrderActivity.class);
        transportOrderClassList.add(OrderActivity.class);
        transportOrder.setActivity(transportOrderClassList);
        transportOrder.setData(transportOrderList);

        transportOrder.item_mine_right_click(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                JumpClass(OrderActivity.class);
//                if (isauthentication()) {
//                    startActivity(OrderActivity.class);
//                } else {
//                    showToast("请去认证");
//                    startActivity(NewSecondImproveDriverInformationActivity.class);
//                }

            }
        });


        mineBigitemTitletwo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                JumpClass(WalletWebActivity.class);
//                if (isauthentication()) {
//                    startActivity(WalletWebActivity.class);
//                } else {
//                    showToast("请去认证");
//                    startActivity(NewSecondImproveDriverInformationActivity.class);
//                }
            }
        });


        ShipperAssistant.titleone("货主助手");
        ShipperAssistant.titletwo("全部功能 >");

        ShipperAssistantDrawable = new ArrayList<Drawable>();
        ShipperAssistantDrawable.add(ContextCompat.getDrawable(getApplicationContext(), R.mipmap.supply));
        ShipperAssistantDrawable.add(ContextCompat.getDrawable(getApplicationContext(), R.mipmap.order));
        ShipperAssistantDrawable.add(ContextCompat.getDrawable(getApplicationContext(), R.mipmap.card));
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
        ShipperAssistantClassList.add(PublishedSupplyOfGoodsActivity.class);
        ShipperAssistantClassList.add(MineReceiptActivity.class);
        ShipperAssistantClassList.add(WalletWebActivity.class);
        ShipperAssistantClassList.add(CommentActivity.class);
        ShipperAssistantClassList.add(PublishedSupplyOfGoodsActivityShu.class);/*我的熟车*/
        ShipperAssistantClassList.add(PublishedSupplyOfGoodsActivityHuo.class);/*货主圈*/

        ShipperAssistantClassList.add(null);/*货主圈*/
//        ShipperAssistantClassList.add(ShipperMapActivity.class);/*货主圈*/

        boolean debugVersion = isDebugVersion(context);
        if (debugVersion) {
            LogUtils.d("AAAAAAAA");
        } else {
            LogUtils.d("BBBBBBBB");
        }

        ShipperAssistant.setActivity(ShipperAssistantClassList);
        ShipperAssistant.setData(ShipperAssistantList);

        ShipperAssistant.item_mine_right_click(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                JumpClass(OrderActivity.class);

//                if (isauthentication()) {
//                    startActivity(OrderActivity.class);
//                } else {
//                    showToast("请去认证");
//                    startActivity(NewSecondImproveDriverInformationActivity.class);
//                }

            }
        });


        ShipperService.titleone("货主服务");
        ShipperService.titletwo("全部功能 >");

        ShipperServiceDrawable = new ArrayList<Drawable>();
        ShipperServiceDrawable.add(ContextCompat.getDrawable(getApplicationContext(), R.mipmap.client));
        ShipperServiceDrawable.add(ContextCompat.getDrawable(getApplicationContext(), R.mipmap.dispose));
        ShipperServiceDrawable.add(ContextCompat.getDrawable(getApplicationContext(), R.mipmap.card));
        ShipperServiceDrawable.add(ContextCompat.getDrawable(getApplicationContext(), R.mipmap.share));
        ShipperServiceDrawable.add(ContextCompat.getDrawable(getApplicationContext(), R.mipmap.setting));
        ShipperServiceList = new ArrayList<>();

        for (int i = 0; i < ShipperServiceTitles.size(); i++) {
            BigItemBean bigItemBean = new BigItemBean();
            bigItemBean.setTitle(ShipperServiceTitles.get(i));
            bigItemBean.setDrawable(ShipperServiceDrawable.get(i));
            ShipperServiceList.add(bigItemBean);
        }

        ShipperServiceClassList = new ArrayList<>();
        ShipperServiceClassList.add(AdvicesActivity.class);
//        ShipperServiceClassList.add(SuggestionsActivity.class);

//        ShipperServiceClassList.add(ETCStartActivity.class); /*鲁通卡ETC*/
//        ShipperServiceClassList.add(JiaYouKaWebActivity.class); /*优惠加油*/
        ShipperServiceClassList.add(null); /*鲁通卡ETC*/
        ShipperServiceClassList.add(null); /*优惠加油*/
        ShipperServiceClassList.add(null); /*APP分享*/
        ShipperServiceClassList.add(SettingActivity.class);
        ShipperService.setActivity(ShipperServiceClassList);
        ShipperService.setData(ShipperServiceList);

        ShipperService.item_mine_right_click(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                JumpClass(OrderActivity.class);

//                if (isauthentication()) {
//                    startActivity(OrderActivity.class);
//                } else {
//                    showToast("请去认证");
//                    startActivity(NewSecondImproveDriverInformationActivity.class);
//                }

            }
        });

        showrightimgae(true);

        setLeftBarHide(false);
    }

    @Override
    public void initData() {

    }

    private void getpseronal() {
        showLoadingView();
        OkGo.<String>get(Get_personal_info)

                .params("id", SPUtils.getAccounId(context))
                .execute(new StringCallback() {
                    @Override
                    public void onSuccess(Response<String> response) {
                        LogUtils.d("AAAA" + response.body());
                        PersonalDetailBean g = GsonUtil.parseJsonWithGson(response.body(), PersonalDetailBean.class);
                        if (g.getCode().equals("0")) {
                            SPUtils.setUserPhone(context, String.valueOf(g.getData().getMobile()));
                            StringBuilder stringBuilder = new StringBuilder(g.getData().getMobile());
                            StringBuilder replacetext = stringBuilder.replace(3, 7, "****");
                            if (replacetext.length()>0){
                                phoneNum.setText(stringBuilder.toString());
                            }

                            imageUrl = g.getData().getPhoto();
                            Glide.with(context).load(g.getData().getPhoto()).placeholder(R.mipmap.picture).centerCrop().
                                    into(minePersonalImage);
                            if (g.getData().getStatus().equals("0")) {
                                personalStatus.setText("个人未认证");
                                improveDriverInfoBtn.setVisibility(View.VISIBLE);
                                mybacketText.setText("0.00");
                            } else if (g.getData().getStatus().equals("1")) {
                                personalStatus.setText("个人已认证");
                                improveDriverInfoBtn.setVisibility(View.GONE);
//                                isyanzheng = true;
                                mybacketText.setText(String.valueOf(g.getData().getBalance()));
                                showwallet = g.getData().getBalance();
                                isshowwallet(SPUtils.getUserShowwWallet(context));
                            } else if (Integer.valueOf(g.getData().getStatus()).equals(Integer.valueOf(Type_shipper_status))) {
                                personalStatus.setText(Type_shipper_message);
                                improveDriverInfoBtn.setVisibility(View.GONE);
//                                isyanzheng = true;
                                mybacketText.setText("0.00");
                            } else {
                                switch (Integer.valueOf(g.getData().getStatus())) {
                                    case 2:
                                        personalStatus.setText("企业升级待审核");
                                        improveDriverInfoBtn.setVisibility(View.GONE);
//                                isyanzheng = true;
                                        mybacketText.setText(String.valueOf(g.getData().getBalance()));
                                        showwallet = g.getData().getBalance();
                                        isshowwallet(SPUtils.getUserShowwWallet(context));
                                        break;
                                    case 3:
                                        personalStatus.setText("企业认证已通过");
                                        improveDriverInfoBtn.setVisibility(View.GONE);
//                                isyanzheng = true;
                                        mybacketText.setText(String.valueOf(g.getData().getBalance()));
                                        showwallet = g.getData().getBalance();
                                        isshowwallet(SPUtils.getUserShowwWallet(context));
                                        break;
                                    case 4:
                                        personalStatus.setText("企业认证不通过");
                                        improveDriverInfoBtn.setVisibility(View.GONE);
//                                isyanzheng = true;
                                        mybacketText.setText(String.valueOf(g.getData().getBalance()));
                                        showwallet = g.getData().getBalance();
                                        isshowwallet(SPUtils.getUserShowwWallet(context));
                                        break;
                                }

                            }

                            showContentView();
                        }
                    }

                    @Override
                    public void onError(Response<String> response) {
                        super.onError(response);
                        showErrorView();
                    }
                });
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }

    @OnClick({R.id.improve_driver_info_btn, R.id.show_money_click, R.id.big_back, R.id.edit_personal_info, R.id.mine_personal_image})
    public void onViewClicked(View view) {
        switch (view.getId()) {
//            case R.id.improve_driver_info:
            /*
             * 完善司机信息
             * */
//                startActivity(ImproveDriverInformationActivity.class);
            /*
             * 完善新司机信息
             * */
//                startActivity(NewImproveDriverInformationActivity.class);
            /*
             * 上传磅单
             * */

//                startActivity(UploadPicturesActivity.class);
            /*完善货主信息第二版*/
//                startActivity(NewSecondImproveDriverInformationActivity.class);
//                break;
            case R.id.improve_driver_info_btn:
                startActivity(NewSecondImproveDriverInformationActivity.class);
                break;
            case R.id.show_money_click:
                App applicationContext = (App) getApplicationContext();
                int userStatus = applicationContext.getUserStatus();
                if (userStatus == 0) {
                    showToast("请去认证");
                    startActivity(NewSecondImproveDriverInformationActivity.class);
                }
                if (userStatus == Integer.valueOf(Type_shipper_status)) {
                    showToast(Type_shipper_message);
                } else {
                    boolean showwWallet = SPUtils.getUserShowwWallet(context);
                    SPUtils.setUserShowwWallet(context, !showwWallet);
                    isshowwallet(SPUtils.getUserShowwWallet(context));
                }
//                if (isauthentication()) {
//                    boolean showwWallet = SPUtils.getUserShowwWallet(context);
//                    SPUtils.setUserShowwWallet(context, !showwWallet);
//                    isshowwallet(SPUtils.getUserShowwWallet(context));
//                } else {
//                    showToast("请去认证");
//                    startActivity(NewSecondImproveDriverInformationActivity.class);
//                }


                break;
            case R.id.big_back:
                finish();
                break;
            case R.id.mine_personal_image:
            case R.id.edit_personal_info:
                JumpClass(PersonalActivity.class);
//                if (isauthentication()) {
//                    startActivity(PersonalActivity.class);
//                } else {
//                    showToast("请去认证");
//                    startActivity(NewSecondImproveDriverInformationActivity.class);
//                }
                break;
        }
    }

    @Override
    public void leftbarclick() {
        super.leftbarclick();
//        finish();
    }

    @Override
    public void rightbasebarimageclick() {
        super.rightbasebarimageclick();
        startActivity(NewsActivity.class);
    }

    public void isshowwallet(boolean isshowwallet) {
        if (isshowwallet) {
            mybacketText.setText(showwallet);
        } else {
            mybacketText.setText("*****");

        }
    }

//    public boolean isauthentication() {
//        boolean isauthentication = true;
//        if (SPUtils.getUserAuthentication(context).equals("0")) {
//            return false;
//        }
//        return isauthentication;
//
//    }

    @Override
    protected void onResume() {
        super.onResume();
        getpseronal();
    }

    private void JumpClass(Class jumpActivity) {
        App applicationContext = (App) getApplicationContext();
        int userStatus = applicationContext.getUserStatus();
        switch (userStatus) {
            case 0:
                showToast("没有认证请去认证");
                startActivity(NewSecondImproveDriverInformationActivity.class);
                break;
            case 1:
                startActivity(jumpActivity);
                break;
            case 2:
                startActivity(jumpActivity);
                break;
            case 3:
                startActivity(jumpActivity);
                break;
            case 4:
                startActivity(jumpActivity);
                break;
            case 9:
                showToast(Type_shipper_message);
                break;
        }
    }


    public static boolean isDebugVersion(Context context) {
        try {
            ApplicationInfo info = context.getApplicationInfo();
            return (info.flags & ApplicationInfo.FLAG_DEBUGGABLE) != 0;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }


    /*无网络*/
    @Override
    public void doNoNetSomething() {
        showErrorView();
        LogUtils.d("whatnetnonet");
    }


    /*有网络*/
    @Override
    public void doNoCheckNetSomething() {
        LogUtils.d("whatnetnet");
        getpseronal();
    }

    /*重新网络*/
    @Override
    public void onNetworkViewRefresh() {
        LogUtils.d("whatnetrefreshnet");
        getpseronal();
    }
}
