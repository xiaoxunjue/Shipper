package com.revenant.shipper.ui.fragment;


import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.apkfuns.logutils.LogUtils;
import com.github.nukc.stateview.StateView;
import com.revenant.shipper.R;
import com.revenant.shipper.base.BaseFragment;
import com.revenant.shipper.bean.BaseBean.MessageEvent;
import com.revenant.shipper.bean.BaseBean.MyEventCode;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 *
 */
public class BlankFragment extends BaseFragment {
    @BindView(R.id.loadLayout)
    LinearLayout loadLayout;
    Unbinder unbinder;
    @BindView(R.id.ab)
    TextView ab;
    public static final String BUNDLE_TITLE = "title";

    @Override
    protected int setLayoutId() {
        return R.layout.fragment_blank;
    }

    @Override
    protected void initView() {

        mStateView = StateView.inject(loadLayout);
        mStateView.showLoading();
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                mStateView.showContent();
            }
        }, 2000);
    }


    @Override
    public void initData() {

    }

    @Override
    protected boolean isRegisterEventBus() {
        return true;
    }

    @Override
    protected void receiveStickyEvent(MessageEvent event) {
        super.receiveStickyEvent(event);
        switch (event.getCode()) {
//            case MyEventCode.CODE_B:
//                if (event.getData().toString().equals("aaa")) {
//                    LogUtils.d("数据是" + "55555555555555555");
//                    ab.setText("3333333333333333");
//                }
//                break;
            default:
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // TODO: inflate a fragment view
        View rootView = super.onCreateView(inflater, container, savedInstanceState);
        unbinder = ButterKnife.bind(this, rootView);
        return rootView;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    public static BlankFragment newInstance(String title) {
        Bundle bundle = new Bundle();
        bundle.putString(BUNDLE_TITLE, title);
        BlankFragment fragment = new BlankFragment();
        fragment.setArguments(bundle);
        return fragment;
    }
}
