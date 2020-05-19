package com.revenant.shipper.ui.activity;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.apkfuns.logutils.LogUtils;
import com.bumptech.glide.Glide;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.StringCallback;
import com.lzy.okgo.model.Response;
import com.revenant.shipper.R;
import com.revenant.shipper.App;
import com.revenant.shipper.base.BaseActivity;
import com.revenant.shipper.bean.ShipperBean.PersonalDetailBean;
import com.revenant.shipper.utils.GsonUtil;
import com.revenant.shipper.utils.SPUtils;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

import static com.revenant.shipper.utils.Constants.Get_personal_info;

public class NewSecondImproveDriverInformationActivity extends BaseActivity {
    @BindView(R.id.personal_authentication)
    TextView personalAuthentication;
    //    @BindView(R.id.enterprise_authentication)
//    TextView enterpriseAuthentication;
    private Context context;
    private int statustype = 0;

    @Override
    public int getContentViewResId() {
        context = this;
        return R.layout.activity_new_second_improve_driver_info;
    }

    @Override
    public void initView() {
        setcenterTitle("完善货主信息");
        getpseronal();

    }

    @Override
    public void leftbarclick() {
        super.leftbarclick();
        finish();
    }

    @Override
    public void initData() {

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }

    @OnClick({R.id.personal_authentication})
    public void onViewClicked(View view) {
//        switch (statustype) {
//            case 0:
//                switch (view.getId()) {
        /*
         * 认证信息类型，0是个人，1是企业
         * */
        switch (view.getId()) {
            case R.id.personal_authentication:
//                Bundle bundlea = new Bundle();
//                bundlea.putString("upload_type_info", "0");
//                startActivity(UploadCertificatePhotoActivity.class, bundlea);
                finish();
                startActivity(UploadCertificatePhotoActivity.class);
                break;
        }

//                    case R.id.enterprise_authentication:
//                        finish();
//                        Bundle bundleb = new Bundle();
//                        bundleb = new Bundle();
//                        bundleb.putString("upload_type_info", "1");
//                        startActivity(UploadCertificatePhotoActivity.class, bundleb);
//                        break;
//                }
//                break;
//            case 1:
//                break;
//            case 2:
//                break;
//            case 3:
//                showToast("等待认证");
//                break;
//            case 4:
//                showToast("企业认证失败,重新认证");
//                switch (view.getId()) {
//                    /*
//                     * 认证信息类型，0是个人，1是企业
//                     * */
//                    case R.id.personal_authentication:
//                        finish();
//                        Bundle bundlea = new Bundle();
//                        bundlea.putString("upload_type_info", "0");
//                        startActivity(UploadCertificatePhotoActivity.class, bundlea);
//                        break;
//                    case R.id.enterprise_authentication:
//                        finish();
//                        Bundle bundleb = new Bundle();
//                        bundleb = new Bundle();
//                        bundleb.putString("upload_type_info", "1");
//                        startActivity(UploadCertificatePhotoActivity.class, bundleb);
//                        break;
//                }
//                break;
//        }

    }

    private void getpseronal() {

        OkGo.<String>get(Get_personal_info)

                .params("id", SPUtils.getAccounId(context))
                .execute(new StringCallback() {
                    @Override
                    public void onSuccess(Response<String> response) {
                        LogUtils.d("DDDD" + response.body());
                        PersonalDetailBean g = GsonUtil.parseJsonWithGson(response.body(), PersonalDetailBean.class);
                        App applicationContext = (App) getApplicationContext();
                        applicationContext.setUserStatus(Integer.valueOf(g.getData().getStatus()));
//                        if (g.getCode().equals("0")) {
//                            statustype = Integer.valueOf(g.getData().getStatus());
//                        }
                    }
                });
    }

}
