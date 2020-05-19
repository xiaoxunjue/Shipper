package com.revenant.shipper.ui.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import com.amap.api.location.AMapLocation;
import com.amap.api.location.AMapLocationClient;
import com.amap.api.location.AMapLocationClientOption;
import com.amap.api.location.AMapLocationListener;
import com.amap.api.location.AMapLocationQualityReport;
import com.apkfuns.logutils.LogUtils;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.tabs.TabLayout;
import com.lljjcoder.style.citylist.Toast.ToastUtils;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.StringCallback;
import com.lzy.okgo.model.Response;
import com.lzy.okgo.request.GetRequest;
import com.revenant.shipper.R;
import com.revenant.shipper.App;
import com.revenant.shipper.adapter.NewMainAdapter;
import com.revenant.shipper.base.BaseActivity;
import com.revenant.shipper.base.SplashActivity;
import com.revenant.shipper.bean.ShipperBean.PersonalDetailBean;
import com.revenant.shipper.bean.UpdateBean;
import com.revenant.shipper.ui.fragment.JieHuoCheLiangFragment;
import com.revenant.shipper.ui.fragment.ShipperClosedFragment;
import com.revenant.shipper.ui.fragment.ShipperPublishedFragment;
import com.revenant.shipper.utils.ActivityUtils;
import com.revenant.shipper.utils.Constants;
import com.revenant.shipper.utils.GsonUtil;
import com.revenant.shipper.utils.SPUtils;
import com.revenant.shipper.utils.Utils;
import com.revenant.shipper.utils.apkUpdate.VersionUtils;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cn.jpush.android.api.JPushInterface;
import constant.UiType;
import listener.OnBtnClickListener;
import model.UiConfig;
import model.UpdateConfig;
import update.UpdateAppUtils;

import static com.revenant.shipper.bean.BaseBean.MyEventCode.Xiao_Personal_Push_Code_NeWs;
import static com.revenant.shipper.bean.BaseBean.MyEventCode.Xiao_Sys_Push_Code_NeWs;
import static com.revenant.shipper.utils.Constants.Get_personal_info;
import static com.revenant.shipper.utils.Constants.Order_list;
import static com.revenant.shipper.utils.Constants.Type_shipper_message;
import static com.revenant.shipper.utils.Constants.Type_shipper_status;

public class ShipperMainActivity extends BaseActivity {
    @BindView(R.id.tablayout)
    TabLayout tablayout;
    @BindView(R.id.viewpager)
    ViewPager viewpager;
    @BindView(R.id.publishbotton)
    FloatingActionButton publishbotton;
    @BindView(R.id.left_base)
    ImageView leftBase;
    @BindView(R.id.right_base)
    ImageView rightBase;

    private List<Fragment> fragmentList;
    private List<String> titleList;
    private NewMainAdapter newMainAdapter;

    private Context context;
    private AMapLocationClient locationClient = null;
    private AMapLocationClientOption locationOption = null;
    private long exitTime = 0;
    private int defaultselect = 0;
    private String apkUrl = "";
    private String updateTitle = "";
    private String updateContent = "";

    @Override
    public int getContentViewResId() {
        return R.layout.activity_shipper_main;
    }

    @Override
    public void initView() {
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            defaultselect = extras.getInt("typeselect");
        }
        context = this;
//        initLocation();
        fragmentList = new ArrayList<>();

        titleList = new ArrayList<>();
        publishbotton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                App applicationContext = (App) getApplicationContext();
                int userStatus = applicationContext.getUserStatus();
                if (userStatus == 0) {
                    showToast("没有认证请去认证");
                    startActivity(NewSecondImproveDriverInformationActivity.class);
                } else if (userStatus == Type_shipper_status) {
                    ToastUtils.showShortToast(context, Type_shipper_message);
                } else {
                    startActivity(PublishedSupplyOfGoodsActivity.class);
                }


            }
        });
//        if (SPUtils.getAccounId(context) != 0) {
//            initLocation();
////            startLocation();
//        }
        JPushInterface.setDebugMode(true);
        JPushInterface.init(this);
        String rid = JPushInterface.getRegistrationID(getApplicationContext());
        LogUtils.d("AAAAAAAAAAAAAA" + rid);
        if (SPUtils.getJpRidToken(getApplicationContext()).isEmpty()) {
            if (!rid.isEmpty()) {
                SPUtils.setJpRidToken(getApplicationContext(), rid);
            }
        }
    }

    private void initLocation() {
        locationClient = new AMapLocationClient(this);
        locationOption = getDefaultOption();
        //设置定位参数
        locationClient.setLocationOption(locationOption);
        // 设置定位监听
        locationClient.setLocationListener(locationListener);
    }

    @Override
    protected boolean isshowtitlebar() {
        return false;
    }

    @Override
    public void initData() {
        getpseronal();
        updateApks();
        fragmentList.add(JieHuoCheLiangFragment.newInstance("JieHuoCheLiang"));
        fragmentList.add(ShipperPublishedFragment.newInstance("ShipperPublished"));
        fragmentList.add(ShipperClosedFragment.newInstance("ShipperClosed"));

        titleList.add("接货车辆");
        titleList.add("已发布");
        titleList.add("已关闭");

        newMainAdapter = new NewMainAdapter(getSupportFragmentManager(), this, fragmentList, titleList);
//
        viewpager.setAdapter(newMainAdapter);
        tablayout.setupWithViewPager(viewpager);
        tablayout.getTabAt(defaultselect).select();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }


    @OnClick({R.id.left_base, R.id.right_base})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.left_base:
                startActivity(MineShipperActivity.class);
                break;
            case R.id.right_base:
                startActivity(NewsActivity.class);
                break;
        }
    }

    private AMapLocationClientOption getDefaultOption() {
        AMapLocationClientOption mOption = new AMapLocationClientOption();
        mOption.setLocationMode(AMapLocationClientOption.AMapLocationMode.Hight_Accuracy);//可选，设置定位模式，可选的模式有高精度、仅设备、仅网络。默认为高精度模式
        mOption.setGpsFirst(false);//可选，设置是否gps优先，只在高精度模式下有效。默认关闭
        mOption.setHttpTimeOut(30000);//可选，设置网络请求超时时间。默认为30秒。在仅设备模式下无效
        mOption.setInterval(1000 * 60 * 5 * 1);//可选，设置定位间隔。默认为2秒
        mOption.setNeedAddress(true);//可选，设置是否返回逆地理地址信息。默认是true
        mOption.setOnceLocation(false);//可选，设置是否单次定位。默认是false
        mOption.setOnceLocationLatest(false);//可选，设置是否等待wifi刷新，默认为false.如果设置为true,会自动变为单次定位，持续定位时不要使用
        AMapLocationClientOption.setLocationProtocol(AMapLocationClientOption.AMapLocationProtocol.HTTP);//可选， 设置网络请求的协议。可选HTTP或者HTTPS。默认为HTTP
        mOption.setSensorEnable(false);//可选，设置是否使用传感器。默认是false
        mOption.setWifiScan(true); //可选，设置是否开启wifi扫描。默认为true，如果设置为false会同时停止主动刷新，停止以后完全依赖于系统刷新，定位位置可能存在误差
        mOption.setLocationCacheEnable(true); //可选，设置是否使用缓存定位，默认为true
        mOption.setGeoLanguage(AMapLocationClientOption.GeoLanguage.DEFAULT);//可选，设置逆地理信息的语言，默认值为默认语言（根据所在地区选择语言）
        return mOption;
    }

    AMapLocationListener locationListener = new AMapLocationListener() {
        @Override
        public void onLocationChanged(AMapLocation location) {
            if (null != location) {

                StringBuffer sb = new StringBuffer();
                //errCode等于0代表定位成功，其他的为定位失败，具体的可以参照官网定位错误码说明
                if (location.getErrorCode() == 0) {
                    sb.append("定位成功" + "\n");
                    sb.append("定位类型: " + location.getLocationType() + "\n");
                    sb.append("经    度    : " + location.getLongitude() + "\n");
                    sb.append("纬    度    : " + location.getLatitude() + "\n");
                    sb.append("精    度    : " + location.getAccuracy() + "米" + "\n");
                    sb.append("提供者    : " + location.getProvider() + "\n");

                    sb.append("速    度    : " + location.getSpeed() + "米/秒" + "\n");
                    sb.append("角    度    : " + location.getBearing() + "\n");
                    // 获取当前提供定位服务的卫星个数
                    sb.append("星    数    : " + location.getSatellites() + "\n");
                    sb.append("国    家    : " + location.getCountry() + "\n");
                    sb.append("省            : " + location.getProvince() + "\n");
                    sb.append("市            : " + location.getCity() + "\n");
                    sb.append("城市编码 : " + location.getCityCode() + "\n");
                    sb.append("区            : " + location.getDistrict() + "\n");
                    sb.append("区域 码   : " + location.getAdCode() + "\n");
                    sb.append("地    址    : " + location.getAddress() + "\n");
                    sb.append("兴趣点    : " + location.getPoiName() + "\n");

                    //定位完成的时间
                    sb.append("定位时间: " + Utils.formatUTC(location.getTime(), "yyyy-MM-dd HH:mm:ss") + "\n");
                } else {
                    //定位失败
                    sb.append("定位失败" + "\n");
                    sb.append("错误码:" + location.getErrorCode() + "\n");
                    sb.append("错误信息:" + location.getErrorInfo() + "\n");
                    sb.append("错误描述:" + location.getLocationDetail() + "\n");
                }
                sb.append("***定位质量报告***").append("\n");
                sb.append("* WIFI开关：").append(location.getLocationQualityReport().isWifiAble() ? "开启" : "关闭").append("\n");
                sb.append("* GPS状态：").append(getGPSStatusString(location.getLocationQualityReport().getGPSStatus())).append("\n");
                sb.append("* GPS星数：").append(location.getLocationQualityReport().getGPSSatellites()).append("\n");
                sb.append("* 网络类型：" + location.getLocationQualityReport().getNetworkType()).append("\n");
                sb.append("* 网络耗时：" + location.getLocationQualityReport().getNetUseTime()).append("\n");
                sb.append("****************").append("\n");
                //定位之后的回调时间
                sb.append("回调时间: " + Utils.formatUTC(System.currentTimeMillis(), "yyyy-MM-dd HH:mm:ss") + "\n");

                //解析定位结果，
                String result = sb.toString();
//                tvResult.setText(result);
                LogUtils.d("定位数据是" + result);
            } else {
                LogUtils.d("定位失败，loc is null");
            }
        }
    };


    private String getGPSStatusString(int statusCode) {
        String str = "";
        switch (statusCode) {
            case AMapLocationQualityReport.GPS_STATUS_OK:
                str = "GPS状态正常";
                break;
            case AMapLocationQualityReport.GPS_STATUS_NOGPSPROVIDER:
                str = "手机中没有GPS Provider，无法进行GPS定位";
                break;
            case AMapLocationQualityReport.GPS_STATUS_OFF:
                str = "GPS关闭，建议开启GPS，提高定位质量";
                break;
            case AMapLocationQualityReport.GPS_STATUS_MODE_SAVING:
                str = "选择的定位模式中不包含GPS定位，建议选择包含GPS定位的模式，提高定位质量";
                break;
            case AMapLocationQualityReport.GPS_STATUS_NOGPSPERMISSION:
                str = "没有GPS定位权限，建议开启gps定位权限";
                break;
        }
        return str;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        destroyLocation();
    }

    private void destroyLocation() {
        if (null != locationClient) {
            /**
             * 如果AMapLocationClient是在当前Activity实例化的，
             * 在Activity的onDestroy中一定要执行AMapLocationClient的onDestroy
             */
            locationClient.onDestroy();
            locationClient = null;
            locationOption = null;
        }
    }

    private void startLocation() {
        locationClient.setLocationOption(locationOption);
        // 启动定位
        locationClient.startLocation();
    }

    private void stopLocation() {
        // 停止定位
        locationClient.stopLocation();
    }

    private void Upload(String x, String y, String orderid) {
        OkGo.<String>get(Order_list)

                .params("orderId", orderid)
                .params("x", x)
                .params("y", y)
                .execute(new StringCallback() {
                    @Override
                    public void onSuccess(Response<String> response) {


                    }
                });
    }

    @Override
    public void onBackPressed() {

        System.out.println(System.currentTimeMillis());

        if (System.currentTimeMillis() - exitTime < 2000) {
            finish();
        } else {
            Toast.makeText(this, "再按一次退出程序", Toast.LENGTH_LONG).show();
            exitTime = System.currentTimeMillis();
        }

    }

    private void getpseronal() {

        OkGo.<String>get(Get_personal_info)

                .params("id", SPUtils.getAccounId(context))
                .execute(new StringCallback() {
                    @Override
                    public void onSuccess(Response<String> response) {
                        LogUtils.d("AAAA" + response.body());
                        PersonalDetailBean a = GsonUtil.parseJsonWithGson(response.body(), PersonalDetailBean.class);
                        if (a.getCode().equals("0")) {
                            App applicationContext = (App) getApplicationContext();
                            applicationContext.setUserStatus(Integer.valueOf(a.getData().getStatus()));
                            if (applicationContext.getUserStatus() != 0) {
//                                initLocation();
//                                startLocation();
                            }
                        }
                    }
                });
    }

    @Override
    protected void onResume() {
        super.onResume();
        switch (SPUtils.getPushCode(context)) {
            case Xiao_Sys_Push_Code_NeWs:
                jumpPush(NewsActivity.class);
//                startActivity(NewsActivity.class);
//                SPUtils.setPushCode(context, 0);
                break;

            case Xiao_Personal_Push_Code_NeWs:
                jumpPush(NewsActivity.class);
//                startActivity(NewsActivity.class);
//                SPUtils.setPushCode(context, 0);
                break;

        }
        getpseronal();
    }

    private void updateApks() {
        if (SPUtils.getUpdateNum(context) == 1) {
            GetRequest<String> stringGetRequest = OkGo.<String>get(Constants.Update_Apk);
            stringGetRequest.params("appVersion", VersionUtils.getVersionCode());
            stringGetRequest.params("platform", "1");
            stringGetRequest.execute(new StringCallback() {
                @Override
                public void onSuccess(Response<String> response) {
                    UpdateBean updateBean = GsonUtil.parseJsonWithGson(response.body(), UpdateBean.class);
                    if (updateBean.getData() != null) {
                        Integer versioncode = Integer.valueOf(updateBean.getData().getVersion());
                        if (versioncode > VersionUtils.getVersionCode()) {
                            updateTitle = updateBean.getData().getTitle();
                            updateContent = updateBean.getData().getContent();
                            apkUrl = updateBean.getData().getUrl();
                            boolean isAuto = updateBean.getData().isIsAuto();
                            String name = "shipper";
                            File filePictures = getExternalFilesDir(Environment.DIRECTORY_DOWNLOADS);
                            String absolutePath1 = filePictures.getAbsolutePath();
                            String androidQpath = absolutePath1 + "/" + name;

                            UpdateConfig updateConfig = new UpdateConfig();
                            updateConfig.setCheckWifi(false);
                            updateConfig.setNeedCheckMd5(false);
                            updateConfig.setShowNotification(true);
                            updateConfig.setShowDownloadingToast(true);
                            updateConfig.setAlwaysShowDownLoadDialog(true);
                            updateConfig.setApkSaveName(name);
                            updateConfig.setApkSavePath(androidQpath);
                            updateConfig.setNotifyImgRes(R.mipmap.icon);
                            updateConfig.setForce(isAuto);


                            final UiConfig uiConfig = new UiConfig();
                            uiConfig.setUiType(UiType.PLENTIFUL);
                            uiConfig.setCancelBtnText("下次再说");
                            uiConfig.setUpdateBtnText("立即升级");

                            UpdateAppUtils
                                    .getInstance()
                                    .apkUrl(apkUrl)
                                    .updateTitle(updateTitle)
                                    .updateContent(updateContent)
                                    .uiConfig(uiConfig)
                                    .updateConfig(updateConfig)
                                    .setCancelBtnClickListener(new OnBtnClickListener() {
                                        @Override
                                        public boolean onClick() {
                                            return false;
                                        }
                                    })
                                    .setUpdateBtnClickListener(new OnBtnClickListener() {
                                        @Override
                                        public boolean onClick() {
                                            return false;
                                        }
                                    })
                                    .update();
                        }

                    }
                }

                @Override
                public void onError(Response<String> response) {
                    super.onError(response);
                    String msgerror = response.getException().getMessage();
                    showLongToast(msgerror);
                }

                @Override
                public void onFinish() {
                    super.onFinish();
                }
            });
        }

        SPUtils.setUpdateNum(context, 2);

    }

    private void jumpPush(Class<?> clz) {
        startActivity(clz);
        SPUtils.setPushCode(context, 0);
    }

    private void jumpPush(Class<?> clz, Bundle bundle) {
        startActivity(clz, bundle);
        SPUtils.setPushCode(context, 0);
    }

    @Override
    public void doNoNetSomething() {
//        showErrorView();
    }

    @Override
    public void doNoCheckNetSomething() {
        LogUtils.d("whatnet2");

    }

    //    @Override
//    protected void protectApp() {
//        startActivity(new Intent(this, SplashActivity.class));
//        finish();
//    }
}
