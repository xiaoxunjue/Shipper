package com.revenant.shipper.ui.fragment;


import android.app.Fragment;
import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.github.nukc.stateview.StateView;
import com.revenant.shipper.R;
import com.revenant.shipper.base.BaseFragment;

import org.greenrobot.eventbus.EventBus;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * A simple {@link Fragment} subclass.
 */
public class PublishPlatformFragment extends BaseFragment {

    Unbinder unbinder;

    @Override
    protected int setLayoutId() {
        return R.layout.fragment_publishplatform;
    }

    @Override
    protected void initView() {


    }


    @Override
    public void initData() {

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
        EventBus.getDefault().unregister(this);

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }


    public static PublishPlatformFragment newInstance(String title) {
        Bundle bundle = new Bundle();
        bundle.putString(title, title);
        PublishPlatformFragment fragment = new PublishPlatformFragment();
        fragment.setArguments(bundle);
        return fragment;
    }
}
