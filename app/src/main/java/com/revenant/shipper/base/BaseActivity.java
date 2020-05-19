package com.revenant.shipper.base;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.LayoutRes;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.apkfuns.logutils.LogUtils;
import com.github.nukc.stateview.StateView;
import com.google.android.material.appbar.MaterialToolbar;
import com.gyf.barlibrary.ImmersionBar;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.cache.CacheEntity;
import com.lzy.okgo.cache.CacheMode;
import com.lzy.okgo.interceptor.HttpLoggingInterceptor;
import com.lzy.okgo.model.HttpHeaders;
import com.revenant.shipper.R;
import com.revenant.shipper.App;
import com.revenant.shipper.bean.BaseBean.MessageEvent;
import com.revenant.shipper.ui.activity.LoginActivity;
import com.revenant.shipper.ui.activity.ShipperMainActivity;
import com.revenant.shipper.utils.ActivityUtils;
import com.revenant.shipper.utils.EventBusUtil;
import com.revenant.shipper.utils.InterceptorUtil;
import com.revenant.shipper.utils.LoadDialog;
import com.revenant.shipper.utils.RestartUtils;
import com.revenant.shipper.utils.SPUtils;
import com.revenant.shipper.utils.TokenHeadeInterceptor;
import com.revenant.shipper.utils.TokenInterceptor;
import com.xiaoyezi.networkdetector.NetStateObserver;
import com.xiaoyezi.networkdetector.NetworkDetector;
import com.xiaoyezi.networkdetector.NetworkType;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.concurrent.TimeUnit;
import java.util.logging.Level;

import butterknife.ButterKnife;
import butterknife.Unbinder;
import cn.hutool.core.date.DateUtil;
import cn.hutool.crypto.SecureUtil;
import okhttp3.OkHttpClient;

import static com.revenant.shipper.bean.BaseBean.MyEventCode.Order_Type_SIGAL;
import static com.revenant.shipper.bean.BaseBean.MyEventCode.Token_LOGIN_SIGAL;
import static com.revenant.shipper.utils.RestartUtils.START_LAUNCH_ACTION;
import static com.revenant.shipper.utils.RestartUtils.STATUS_FORCE_KILLED;
import static com.revenant.shipper.utils.RestartUtils.STATUS_NORMAL;

public abstract class BaseActivity extends AppCompatActivity implements StateView.OnRetryClickListener, View.OnClickListener {
    private final String TAG = this.getClass().getSimpleName();
    private ImmersionBar mImmersionBar;
    private ImageView leftBaseBar, right_base_bar_image;
    private TextView centerBaseBarTitle;
    private TextView rightBaseBar;
    private View view;
    private LinearLayout linearLayoutbarshoworhide;
    private LinearLayout networkshowli;
    private StateView networkStateView;
    public static BaseActivity mCurrentActivity = null;
    private Unbinder mUnbinder;
    protected LoadDialog dialog;
    private NetStateObserver observer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        if (android.os.Build.VERSION.SDK_INT < Build.VERSION_CODES.O) {
            setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        }
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(getContentViewResId());


//        if (App.flag == -1) {//flag为-1说明程序被杀掉
//            protectApp();
//        }
//
//        switch (RestartUtils.getInstance().getAppStatus()) {
//            case STATUS_FORCE_KILLED:
//                Log.d("thisttt" , "BaseModuledActivity-STATUS_FORCE_KILLED");
//                restartApp();
//                break;
//            case STATUS_NORMAL:
//                Log.d("thisttt" , "BaseModuledActivity-STATUS_NORMAL");
//                break;
//            default:
//                break;
//        }


        if (isImmersionBarEnabled()) {
            initImmersionBar();
        }


        LogUtils.d(TAG, "onCreate()");
        mUnbinder = ButterKnife.bind(this);

        ActivityUtils.addActivity(this);
        new TokenHeadeInterceptor(this);
//        initOkGo();
        initView();
        initData();
        if (isRegisterEventBus()) {
            LogUtils.d("----  response code --- :", "register");
            if (!EventBus.getDefault().isRegistered(this)) {//
                EventBusUtil.register(this);// 加上判断
            }
        }

    }

    private void restartApp() {
        Intent intent = new Intent(this, ShipperMainActivity.class);
        intent.putExtra(START_LAUNCH_ACTION, STATUS_FORCE_KILLED);
        startActivity(intent);
    }

    protected void protectApp() {
        Intent intent = new Intent(this, ShipperMainActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);//清空栈里MainActivity之上的所有activty
        startActivity(intent);
        finish();

    }

    ;


    protected boolean isImmersionBarEnabled() {
        return true;
    }

    @Override
    public void setContentView(@LayoutRes int layoutResID) {
        int height = getStatusBarHeight(BaseActivity.this);
        LogUtils.d("高度是:" + height);
//        getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);
        view = getLayoutInflater().inflate(R.layout.activity_base, null);
        view.setPadding(0, height, 0, 0);
        linearLayoutbarshoworhide = view.findViewById(R.id.basetitle_show_or_hide);
        networkshowli = view.findViewById(R.id.networkshow);

//        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, height);
//        linearLayoutbarshoworhide.setLayoutParams(params);

//        LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(linearLayoutbarshoworhide.getLayoutParams());
//        lp.setMargins(0, height, 0, 0);
//        linearLayoutbarshoworhide.setLayoutParams(lp);

        leftBaseBar = view.findViewById(R.id.left_base_bar);
        centerBaseBarTitle = view.findViewById(R.id.center_base_bar_title);
        rightBaseBar = view.findViewById(R.id.right_base_bar);
        right_base_bar_image = view.findViewById(R.id.right_base_bar_image);

        if (!isshowtitlebar()) {
            linearLayoutbarshoworhide.setVisibility(View.GONE);
        }
        leftBaseBar.setOnClickListener(this);
        rightBaseBar.setOnClickListener(this);
        right_base_bar_image.setOnClickListener(this);

        //设置填充activity_base布局
        super.setContentView(view);

        if (Build.VERSION.SDK_INT == Build.VERSION_CODES.KITKAT) {
            if (isBraShow()) {
                view.setFitsSystemWindows(true);

            } else {
//                getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE);
                view.setFitsSystemWindows(false);
            }
        }

        //加载子类Activity的布局
        initDefaultView(layoutResID);

        observer = new NetStateObserver() {
            @Override
            public void onDisconnected() {
                Log.d(TAG, "onDisconnected: ");
                showToast("网络状态异常");
                doNoNetSomething();
//                showErrorView();
//                new Handler().postDelayed(new Runnable() {
//                    @Override
//                    public void run() {
//                        showContentView();
//
//                    }
//                }, 2000);

//                textView.setText("onDisconnected");
            }

            @Override
            public void onConnected(NetworkType networkType) {
                Log.d(TAG, "onConnected: " + networkType.toString());
                doNoCheckNetSomething();
            }
        };
        NetworkDetector.getInstance().addObserver(observer);


//        ButterKnife.bind(this);
    }

    /*
     * 初始化布局
     * */
    public abstract int getContentViewResId();

    /*
     * 设置布局
     * */
    public abstract void initView();

    /**
     * 设置数据
     */
    public abstract void initData();

    /*
     * 标题栏的点击事件
     * */
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.right_base_bar:
                rightbarclick();
                break;
            case R.id.left_base_bar:
                leftbarclick();
                break;
            case R.id.right_base_bar_image:
                rightbasebarimageclick();
                break;
            default:
        }

    }

    public void leftbarclick() {

    }

    public void rightbarclick() {

    }

    public void rightbasebarimageclick() {
    }

    protected boolean isshowtitlebar() {
        return true;
    }

    public void setleftTitleImage(Drawable drawable) {
        leftBaseBar.setImageDrawable(drawable);
    }


    public void setrightTitleImage(Drawable drawable) {
        right_base_bar_image.setImageDrawable(drawable);
    }

    public void setcenterTitle(String title) {
        centerBaseBarTitle.setText(title);
    }

    public void setrightTitle(String title) {
        rightBaseBar.setText(title);
    }

    public void showrighttext(boolean showrightbar) {
        if (showrightbar == true) {
            rightBaseBar.setVisibility(View.VISIBLE);
        } else {
            rightBaseBar.setVisibility(View.GONE);
        }
    }

    public void showrightimgae(boolean showrightbar) {
        if (showrightbar == true) {
            right_base_bar_image.setVisibility(View.VISIBLE);
        } else {
            right_base_bar_image.setVisibility(View.GONE);
        }
    }

    public void setLeftBarHide(boolean showright) {
        if (showright) {
            leftBaseBar.setVisibility(View.VISIBLE);
        } else {
            leftBaseBar.setVisibility(View.INVISIBLE);
        }
    }

    private void initDefaultView(int layoutResID) {
        networkStateView = (StateView) findViewById(R.id.stateview);
        FrameLayout container = (FrameLayout) findViewById(R.id.fl_activity_child_container);
        View childView = LayoutInflater.from(this).inflate(layoutResID, null);
        container.addView(childView, 0);
    }

    /*diaglog
     * */
    /*
     * 操作状态栏
     * */


    protected void initImmersionBar() {
        //在BaseActivity里初始化
        mImmersionBar = ImmersionBar.with(this);
        mImmersionBar.
//                 transparentStatusBar().
//                transparentBar()
        statusBarColor(R.color.o2oman)
//                transparentBar().
//        titleBar(linearLayoutbarshoworhide).
                .keyboardEnable(true)  //解决软键盘与底部输入框冲突问题，默认为false，还有一个重载方法，可以指定软键盘mode
                .keyboardMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE).
                init();
        LogUtils.d("高度数据是");
    }

    public void showLoadingView() {
        networkStateView.showLoading();
    }

    public void showErrorView() {
        networkStateView.showRetry();
        networkStateView.setOnRetryClickListener(this);
    }

    public void showEmptyView() {
        networkStateView.showEmpty();
        networkStateView.setOnRetryClickListener(this);
    }

    public void showContentView() {
        networkStateView.showContent();
    }

    /*
     * 再次点击的显示
     * */
    @Override
    public void onRetryClick() {
        onNetworkViewRefresh();
    }

    public void onNetworkViewRefresh() {
    }


    /*Eventbus的使用*/
    protected boolean isRegisterEventBus() {
        return false;
    }


    /*状态栏的使用*/
    protected boolean isBraShow() {
        return true;
    }

    /**
     * [页面跳转]
     *
     * @param clz
     */
    public void startActivity(Class<?> clz) {
        Intent intent = new Intent();
        intent.setClass(BaseActivity.this, clz);
//        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
    }

    /**
     * [携带数据的页面跳转]
     *
     * @param clz
     * @param bundle
     */
    public void startActivity(Class<?> clz, Bundle bundle) {
        Intent intent = new Intent();
        intent.setClass(this, clz);
//        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        if (bundle != null) {
            intent.putExtras(bundle);
        }
        startActivity(intent);
    }

    /**
     * [含有Bundle通过Class打开编辑界面]
     *
     * @param cls
     * @param bundle
     * @param requestCode
     */
    public void startActivityForResult(Class<?> cls, Bundle bundle,
                                       int requestCode) {
        Intent intent = new Intent();
        intent.setClass(this, cls);
        if (bundle != null) {
            intent.putExtras(bundle);
        }
        startActivityForResult(intent, requestCode);

    }

    @Override
    protected void onResume() {
        super.onResume();
        mCurrentActivity = this;
    }

    @Override
    protected void onPause() {
        super.onPause();

    }

    @Override
    protected void onRestart() {
        super.onRestart();

    }

    @Override
    protected void onStop() {
        super.onStop();

    }

    @Override
    protected void onDestroy() {

        super.onDestroy();
        if (isRegisterEventBus()) {
            EventBusUtil.unregister(this);
        }
        if (mImmersionBar != null) {
            mImmersionBar.destroy();
        }
        mUnbinder.unbind();
        ActivityUtils.removeActivity(this);
        NetworkDetector.getInstance().removeObserver(observer);
    }

    protected void exitall() {
        ActivityUtils.removeAllActivity();
    }


    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onEventBusCome(MessageEvent event) {
        if (event != null) {
            receiveEvent(event);
        }
    }

    @Subscribe(threadMode = ThreadMode.MAIN, sticky = true)
    public void onStickyEventBusCome(MessageEvent event) {
        if (event != null) {
            receiveStickyEvent(event);
        }
    }

    /**
     * 接收到分发到事件
     *
     * @param event 事件
     */
    protected void receiveEvent(MessageEvent event) {
        switch (event.getCode()) {
            case Token_LOGIN_SIGAL:
//                SPUtils.clearAccounId(mCurrentActivity);
//                SPUtils.clearUserToken(mCurrentActivity);
//                ActivityUtils.removeAllActivity();
//                startActivity(SplashActivity.class);
                LogUtils.d("----  response code --- EventBusReceive");
                break;
        }
    }

    /**
     * 接受到分发的粘性事件
     *
     * @param event 粘性事件
     */
    protected void receiveStickyEvent(MessageEvent event) {
        switch (event.getCode()) {
            case Token_LOGIN_SIGAL:
                LogUtils.d("----  response code --- EventBusReceiveSecond");
                break;
        }

    }

    protected void showToast(String msg) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
    }

    protected void showLongToast(String msg) {
        Toast.makeText(this, msg, Toast.LENGTH_LONG).show();
    }


    private int getStatusBarHeight(Context context) {
        int result = 0;
        int resourceId = context.getResources().getIdentifier("status_bar_height", "dimen", "android");
        if (resourceId > 0) {
            result = context.getResources().getDimensionPixelSize(resourceId);
        }
        return result;
    }


    // 设置返回按钮的监听事件
    private long exitTime = 0;


    /**
     * 设置当前屏幕方向为横屏
     */
    private void setHorizontalScreen(Activity activity) {
        if (activity.getRequestedOrientation() != ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE) {
            activity.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
        }
    }

    /**
     * 设置当前屏幕方向为竖屏
     */
    private void setVerticalScreen(Activity activity) {
        if (activity.getRequestedOrientation() != ActivityInfo.SCREEN_ORIENTATION_PORTRAIT) {
            activity.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        }
    }

    protected void showDig() {
        dialog = new LoadDialog.Builder(this).build();
        dialog.show();
    }

    protected void showDig(boolean canCancel) {
        dialog = new LoadDialog.Builder(this).canCancel(canCancel).build();
        dialog.show();
    }

    protected void showDig(String msg) {
        dialog = new LoadDialog.Builder(this).loadText(msg).build();
        dialog.show();
    }

    protected void showDig(String msg, boolean canCancel) {
        dialog = new LoadDialog.Builder(this).loadText(msg).canCancel(canCancel).build();
        dialog.show();
    }


    protected void dismissDig() {
        if (dialog != null && dialog.isShowing()) {
            dialog.dismiss();
        }
    }

    private void initOkGo() {
        HttpHeaders headers = new HttpHeaders();
        if (!SPUtils.getUserToken(this).isEmpty()) {
            String timestamp = String.valueOf(DateUtil.current(false));//header不支持中文，不允许有特殊字符
            headers.put("sign", SecureUtil.md5(SecureUtil.md5(SecureUtil.md5(timestamp) + timestamp) + timestamp));   //header不支持中文，不允许有特殊字符
            headers.put("timestamp", timestamp);
            headers.put("X-Token", SPUtils.getUserToken(this));

        }
        OkHttpClient.Builder builder = new OkHttpClient.Builder();
        HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor("OkGo");
        loggingInterceptor.setPrintLevel(HttpLoggingInterceptor.Level.BODY);        //log打印级别，决定了log显示的详细程度
        loggingInterceptor.setColorLevel(Level.INFO);                               //log颜色级别，决定了log在控制台显示的颜色
        builder.addInterceptor(loggingInterceptor);
        builder.addInterceptor(new TokenInterceptor());
        builder.readTimeout(OkGo.DEFAULT_MILLISECONDS, TimeUnit.MILLISECONDS);      //全局的读取超时时间
        builder.writeTimeout(OkGo.DEFAULT_MILLISECONDS, TimeUnit.MILLISECONDS);     //全局的写入超时时间
        builder.connectTimeout(OkGo.DEFAULT_MILLISECONDS, TimeUnit.MILLISECONDS);

        OkGo.getInstance().init(App.getApplication())                           //必须调用初始化
                .setOkHttpClient(builder.build())
                .setCacheMode(CacheMode.NO_CACHE)               //全局统一缓存模式，默认不使用缓存，可以不传
                .setCacheTime(CacheEntity.CACHE_NEVER_EXPIRE)   //全局统一缓存时间，默认永不过期，可以不传
                .setRetryCount(3)                               //全局统一超时重连次数，默认为三次，那么最差的情况会请求4次(一次原始请求，三次重连请求)，不需要可以设置为0
                .addCommonHeaders(headers)//全局公共头
        ;
    }


    public void doNoNetSomething() {

    }

    /*无网络状态事务*/

    public void doNoCheckNetSomething() {

    }

}
