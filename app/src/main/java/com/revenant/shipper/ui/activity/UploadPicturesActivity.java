package com.revenant.shipper.ui.activity;

import android.os.Bundle;
import android.view.PointerIcon;
import android.view.View;

import com.apkfuns.logutils.LogUtils;
import com.revenant.shipper.R;
import com.revenant.shipper.base.BaseActivity;

import org.angmarch.views.NiceSpinner;
import org.angmarch.views.OnSpinnerItemSelectedListener;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class UploadPicturesActivity extends BaseActivity {
    @BindView(R.id.spinner_select)
    NiceSpinner spinnerSelect;

    @Override
    public int getContentViewResId() {
        return R.layout.activity_upload_pictures;
    }

    @Override
    public void initView() {

    }

    @Override
    public void initData() {
        List<String> dataset = new LinkedList<>(Arrays.asList("One", "Two", "Three", "Four", "Five"));
        spinnerSelect.attachDataSource(dataset);
        spinnerSelect.setOnSpinnerItemSelectedListener(new OnSpinnerItemSelectedListener() {
            @Override
            public void onItemSelected(NiceSpinner parent, View view, int position, long id) {
                LogUtils.d("AAAAAAAAAA" + position);
            }
        });

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }
}
