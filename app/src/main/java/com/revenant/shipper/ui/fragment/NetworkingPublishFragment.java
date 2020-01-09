package com.revenant.shipper.ui.fragment;


import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.revenant.shipper.R;
import com.revenant.shipper.base.BaseFragment;

import org.greenrobot.eventbus.EventBus;

import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * A simple {@link Fragment} subclass.
 */
public class NetworkingPublishFragment extends BaseFragment {
    Unbinder unbinder;

    @Override
    protected int setLayoutId() {
        return R.layout.fragment_networking_published;
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


    public static NetworkingPublishFragment newInstance(String title) {
        Bundle bundle = new Bundle();
        bundle.putString(title, title);
        NetworkingPublishFragment fragment = new NetworkingPublishFragment();
        fragment.setArguments(bundle);
        return fragment;
    }
}
