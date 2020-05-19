package com.revenant.shipper.ui.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;

import androidx.annotation.Nullable;

import com.apkfuns.logutils.LogUtils;
import com.bumptech.glide.Glide;
import com.luck.picture.lib.PictureSelector;
import com.luck.picture.lib.config.PictureConfig;
import com.luck.picture.lib.config.PictureMimeType;
import com.luck.picture.lib.entity.LocalMedia;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.StringCallback;
import com.lzy.okgo.model.Response;
import com.nanchen.compresshelper.CompressHelper;
import com.revenant.shipper.R;
import com.revenant.shipper.base.BaseActivity;
import com.revenant.shipper.bean.ShipperBean.FileUploadBean;
import com.revenant.shipper.utils.GlideEngine;
import com.revenant.shipper.utils.GsonUtil;
import com.revenant.shipper.utils.SPUtils;
import com.revenant.shipper.utils.Constants;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class UploadPhotoActivity extends BaseActivity {
    @BindView(R.id.submit_ok)
    Button submitOk;
    @BindView(R.id.upload_phone_line)
    LinearLayout uploadPhoneLine;
    @BindView(R.id.upload_image)
    ImageView uploadImage;
    private List<LocalMedia> selectList = new ArrayList<>();
    private Context context;
    private String imageurl = "";
    private int imagetype=0;

    @Override
    public int getContentViewResId() {
        context = this;
        return R.layout.activity_upload_phone;
    }

    @Override
    public void initView() {
        setcenterTitle("上传头像");
    }

    @Override
    public void initData() {

    }

    @Override
    public void leftbarclick() {
        super.leftbarclick();
        finish();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }

    @OnClick({R.id.confirm_oka, R.id.upload_phone_line})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.confirm_oka:
                if (imagetype==1) {
                    uploadHead(imageurl);
                }else {
                    showToast("请选择图片");
                }
                break;
            case R.id.upload_phone_line:
                PictureSelector.create(this)
                        .openGallery(PictureMimeType.ofImage())
                        .maxSelectNum(1)
                        .loadImageEngine(GlideEngine.createGlideEngine()) // 请参考Demo GlideEngine.java
                        .forResult(PictureConfig.CHOOSE_REQUEST);
                break;
        }
    }

    private void uploadHead(String imageurl) {
        OkGo.<String>post(Constants.Update_personal_image)
                .tag(this)

                .params("id", SPUtils.getAccounId(context))
                .params("photo", imageurl)
                .execute(new StringCallback() {
                    @Override
                    public void onSuccess(Response<String> response) {
                        LogUtils.d("WWWW" + response.body());
                        FileUploadBean fileUploadBean = GsonUtil.parseJsonWithGson(response.body(), FileUploadBean.class);
                        if (fileUploadBean.getCode().equals("0")) {
                            showToast(fileUploadBean.getMsg());
                            finish();
                        }

                    }
                });

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            switch (requestCode) {
                case PictureConfig.CHOOSE_REQUEST:
                    // 图片选择结果回调
                    selectList = PictureSelector.obtainMultipleResult(data);
                    for (LocalMedia media : selectList) {
                        if (!selectList.isEmpty()) {

//                            Glide.with(this).load(media.getPath()).centerCrop().
//                                    into(uploadImage);
                            String url = media.getPath().startsWith("content://") ? media.getAndroidQToPath() : media.getPath();
                            uploadAvatar(url);
                        }
                        break;
                    }
            }
        }
    }

    private void uploadAvatar(final String path) {
        File file = new File(path);
        File newfile = CompressHelper.getDefault(context).compressToFile(file);

        OkGo.<String>post(Constants.UploadFile)
                .tag(this)
                .isMultipart(true)

                .params("file", newfile)
                .execute(new StringCallback() {
                    @Override
                    public void onSuccess(Response<String> response) {
                        FileUploadBean fileUploadBean = GsonUtil.parseJsonWithGson(response.body(), FileUploadBean.class);
                        if (fileUploadBean.getCode().equals("0")) {
                            Glide.with(context).load(fileUploadBean.getData().getUrl()).centerCrop().
                                    into(uploadImage);
                            imageurl = fileUploadBean.getData().getUrl();
                            imagetype=1;
                        } else {
                            showToast(fileUploadBean.getMsg());
                        }

                    }

                    @Override
                    public void onError(Response<String> response) {
                        super.onError(response);
                        LogUtils.d("失败是" + response.message());
                    }
                });
    }


}
