package com.revenant.shipper.ui.activity;

import android.content.Context;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.apkfuns.logutils.LogUtils;
import com.bumptech.glide.Glide;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.StringCallback;
import com.lzy.okgo.model.Response;
import com.revenant.shipper.R;
import com.revenant.shipper.base.BaseActivity;
import com.revenant.shipper.bean.ShipperBean.PersonalDetailBean;
import com.revenant.shipper.utils.GsonUtil;
import com.revenant.shipper.utils.SPUtils;

import butterknife.BindView;
import butterknife.ButterKnife;

import static com.revenant.shipper.utils.Constants.Get_personal_info;

public class DriverInfoActivity extends BaseActivity {
    @BindView(R.id.name)
    TextView name;
    @BindView(R.id.time)
    TextView time;
    @BindView(R.id.a)
    TextView a;
    @BindView(R.id.b)
    TextView b;
    @BindView(R.id.c)
    TextView c;
    @BindView(R.id.image)
    ImageView image;
    private Context context;
    private String  driver_id = "";
    @Override
    public int getContentViewResId() {
        return R.layout.activity_driver_info;
    }

    @Override
    public void initView() {
        setcenterTitle("司机简介");
        context = this;
        Bundle extras = getIntent().getExtras();
        driver_id=extras.getString("driverid");

    }

    @Override
    public void leftbarclick() {
        super.leftbarclick();
        finish();
    }

    @Override
    public void initData() {
        getpseronal(driver_id);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }

    private void getpseronal(String id) {

        OkGo.<String>get(Get_personal_info)
                .params("id", id)
                .execute(new StringCallback() {
                    @Override
                    public void onSuccess(Response<String> response) {
                        LogUtils.d("AAAA" + response.body());
                        PersonalDetailBean g = GsonUtil.parseJsonWithGson(response.body(), PersonalDetailBean.class);
                        name.setText(g.getData().getUsername());
                        time.setText(g.getData().getCtime());
                        Glide.with(context).load(g.getData().getPhoto()).placeholder(R.mipmap.picture).centerCrop().
                                into(image);
                    }
                });
    }
}
