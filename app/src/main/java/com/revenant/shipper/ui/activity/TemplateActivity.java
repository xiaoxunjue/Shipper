package com.revenant.shipper.ui.activity;

import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AlertDialog;

import com.apkfuns.logutils.LogUtils;
import com.revenant.shipper.R;
import com.revenant.shipper.base.BaseActivity;

public class TemplateActivity extends BaseActivity {
    @Override
    public int getContentViewResId() {
        return R.layout.activity_template;
    }

    @Override
    public void initView() {


    }

    @Override
    public void initData() {

    }

    @Override
    public void leftbarclick() {
        super.leftbarclick();
        alertdialog();

    }

    public void alertdialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("确认删除吗");
        View view = getLayoutInflater().inflate(R.layout.alert_dialog_template, null);
        builder.setView(view);
        final Button deletebtn = view.findViewById(R.id.delete_btn);
        final AlertDialog alertDialog = builder.show();
        deletebtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LogUtils.d("AAAAAAAA");
                alertDialog.dismiss();
            }
        });


        final Button cancel = view.findViewById(R.id.cancel_btn);
        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                alertDialog.dismiss();
            }
        });

    }
}
