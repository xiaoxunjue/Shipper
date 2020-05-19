package com.revenant.shipper.base;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.apkfuns.logutils.LogUtils;
import com.github.nukc.stateview.StateView;
import com.revenant.shipper.bean.BaseBean.MessageEvent;
import com.revenant.shipper.utils.EventBusUtil;
import com.xiaoyezi.networkdetector.NetStateObserver;
import com.xiaoyezi.networkdetector.NetworkDetector;
import com.xiaoyezi.networkdetector.NetworkType;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by Administrator on 2018/8/21.
 */

public abstract class BaseFragment extends Fragment {
    protected Context mContext;
    protected StateView mStateView;
    public Unbinder unbinder;
    private NetStateObserver observer;
    protected abstract int setLayoutId();

    protected abstract void initView();

    public abstract void initData();

    protected boolean isRegisterEventBus() {
        return false;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mContext = getActivity();
        observer = new NetStateObserver() {
            @Override
            public void onDisconnected() {
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
                doNoCheckNetSomething();
            }
        };
        NetworkDetector.getInstance().addObserver(observer);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(setLayoutId(), container, false);
        unbinder = ButterKnife.bind(this, view);

        return view;
    }


    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        init();
        initView();
        initData();
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onEventBusCome(MessageEvent event) {
        if (event != null) {
            receiveEvent(event);
        }
    }

    private void init() {
        if (isRegisterEventBus()) {
            LogUtils.d("EBBaseFragment", "register");
            if(!EventBus.getDefault().isRegistered(this)){//
                EventBusUtil.register(this);// 加上判断
            }
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

    }

    /**
     * 接受到分发的粘性事件
     *
     * @param event 粘性事件
     */
    protected void receiveStickyEvent(MessageEvent event) {

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (isRegisterEventBus()) {
            EventBusUtil.unregister(this);
        }

        NetworkDetector.getInstance().removeObserver(observer);

    }

    public void doNoNetSomething() {

    }

    /*无网络状态事务*/

    public void doNoCheckNetSomething() {

    }
}
