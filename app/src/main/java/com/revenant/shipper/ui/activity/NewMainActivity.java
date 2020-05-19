package com.revenant.shipper.ui.activity;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;

import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import com.amap.api.location.AMapLocation;
import com.amap.api.location.AMapLocationClient;
import com.amap.api.location.AMapLocationClientOption;
import com.amap.api.location.AMapLocationListener;
import com.amap.api.location.AMapLocationQualityReport;
import com.apkfuns.logutils.LogUtils;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.tabs.TabLayout;
import com.revenant.shipper.R;
import com.revenant.shipper.adapter.AreaItemAdapter;
import com.revenant.shipper.adapter.NewMainAdapter;
import com.revenant.shipper.base.BaseActivity;
import com.revenant.shipper.bean.AreaBeans;
import com.revenant.shipper.bean.AreaShowBean;
import com.revenant.shipper.ui.fragment.AssignmentFragment;
import com.revenant.shipper.ui.fragment.NetworkingFragment;
import com.revenant.shipper.ui.fragment.PlatformFragment;
import com.revenant.shipper.utils.AreaSelect;
import com.revenant.shipper.utils.Utils;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class NewMainActivity extends BaseActivity {
    @BindView(R.id.tablayout)
    TabLayout tablayout;
    @BindView(R.id.viewpager)
    ViewPager viewpager;
    @BindView(R.id.publishbotton)
    FloatingActionButton publishbotton;
    @BindView(R.id.recyclerviewareaselect)
    RecyclerView recyclerviewareaselect;
    private List<Fragment> fragmentList;
    private List<String>   titleList;
    private NewMainAdapter newMainAdapter;

    private AreaItemAdapter areaItemAdapter;
    private int             areatype = 0; // areatype=0,省，1市，2区
    private int             areaposition = 0;


    private AMapLocationClient locationClient = null;
    private AMapLocationClientOption locationOption = null;
    private String areainfo;


    @Override
    public int getContentViewResId() {
        return R.layout.activity_newmain;
    }

    @Override
    public void initView() {

        fragmentList = new ArrayList<>();

        titleList = new ArrayList<>();

        Drawable lefftdrawable = ContextCompat.getDrawable(getApplicationContext(), R.mipmap.ic_launcher);
        setleftTitleImage(lefftdrawable);

        showrightimgae(true);
        Drawable righttdrawable = ContextCompat.getDrawable(getApplicationContext(), R.mipmap.ic_launcher);
        setrightTitleImage(righttdrawable);


        areaItemAdapter = new AreaItemAdapter();
        recyclerviewareaselect.setLayoutManager(new GridLayoutManager(this, 4));
        List<AreaShowBean> showBeanList = new ArrayList<>();
        AreaBeans areaBean = AreaSelect.getArea(this);
        for (int i = 0; i < areaBean.getAreas().getArea().size(); i++) {
            AreaShowBean showBean = new AreaShowBean();
            showBean.setAreaname(areaBean.getAreas().getArea().get(i).getName());
            showBeanList.add(showBean);
        }
        areaItemAdapter.addData(showBeanList);
        recyclerviewareaselect.setAdapter(areaItemAdapter);
        areaItemAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                LogUtils.d("AAAA" + adapter.getData().get(position).toString());
                areatype++;
                if (areatype == 1) {
                    areaposition = position;
                    AreaBeans.AreasBean.AreaBean areaBean1 = areaBean.getAreas().getArea().get(position);
                    showBeanList.clear();
                    for (int i = 0; i < areaBean1.getCity().size(); i++) {
                        AreaShowBean showBean = new AreaShowBean();
                        showBean.setAreaname(areaBean1.getCity().get(i).getName());
                        showBeanList.add(showBean);
                    }
                    areaItemAdapter.setNewData(showBeanList);
                } else if (areatype == 2) {
                    List<String> cityBeans = areaBean.getAreas().getArea().get(areaposition).getCity().get(position).getArea();
                    showBeanList.clear();
                    for (int i = 0; i < cityBeans.size(); i++) {
                        AreaShowBean showBean = new AreaShowBean();
                        showBean.setAreaname(cityBeans.get(i));
                        showBeanList.add(showBean);
                    }
                    areaItemAdapter.setNewData(showBeanList);
                }

            }
        });


    }

    @Override
    public void initData() {

        fragmentList.add(PlatformFragment.newInstance("Platform"));
        fragmentList.add(AssignmentFragment.newInstance("Assignment"));
        fragmentList.add(NetworkingFragment.newInstance("Networking"));

        titleList.add("平台");
        titleList.add("指派");
        titleList.add("联网");

        newMainAdapter = new NewMainAdapter(getSupportFragmentManager(), this, fragmentList, titleList);
//
        viewpager.setAdapter(newMainAdapter);
        tablayout.setupWithViewPager(viewpager);
//        initLocation();
//
//        startLocation();
    }

    private void initLocation() {
        locationClient = new AMapLocationClient(this);
        locationOption = getDefaultOption();
        //设置定位参数
        locationClient.setLocationOption(locationOption);
        // 设置定位监听
        locationClient.setLocationListener(locationListener);
    }

    private AMapLocationClientOption getDefaultOption() {
        AMapLocationClientOption mOption = new AMapLocationClientOption();
        mOption.setLocationMode(AMapLocationClientOption.AMapLocationMode.Hight_Accuracy);//可选，设置定位模式，可选的模式有高精度、仅设备、仅网络。默认为高精度模式
        mOption.setGpsFirst(false);//可选，设置是否gps优先，只在高精度模式下有效。默认关闭
        mOption.setHttpTimeOut(30000);//可选，设置网络请求超时时间。默认为30秒。在仅设备模式下无效
        mOption.setInterval(2000);//可选，设置定位间隔。默认为2秒
        mOption.setNeedAddress(true);//可选，设置是否返回逆地理地址信息。默认是true
        mOption.setOnceLocation(true);//可选，设置是否单次定位。默认是false
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

                    areainfo = location.getProvince() + location.getCity() + location.getDistrict();
                    LogUtils.d("数据是" + areainfo);
//                    LogUtils.d("数据是"+location.getAddress());
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

    private void startLocation() {
        locationClient.setLocationOption(locationOption);
        // 启动定位
        locationClient.startLocation();
    }

    private void stopLocation() {
        // 停止定位
        locationClient.stopLocation();
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

    @Override
    public void leftbarclick() {
        super.leftbarclick();

        /*
         * 进入登录页面
         * */
//        startActivity(LoginActivity.class);
        /*
         * 进入我的页面
         *
         * */

        /*
         * 进入我的
         * */
        startActivity(MineDriverActivity.class);

//        startActivity(TemplateActivity.class);
    }

    @Override
    public void rightbasebarimageclick() {
        super.rightbasebarimageclick();
        startActivity(MessageActivity.class);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }

    @OnClick({R.id.publishbotton})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.publishbotton:
                startActivity(LoginActivity.class);
                break;
        }
    }
}
