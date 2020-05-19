package com.revenant.shipper.ui.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.apkfuns.logutils.LogUtils;
import com.bigkoo.pickerview.builder.TimePickerBuilder;
import com.bigkoo.pickerview.listener.OnTimeSelectListener;
import com.bigkoo.pickerview.view.TimePickerView;
import com.bumptech.glide.Glide;
import com.lljjcoder.style.citypickerview.CityPickerView;
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
import com.revenant.shipper.bean.ShipperBean.YingYeZhiZhaoBean;
import com.revenant.shipper.utils.GlideEngine;
import com.revenant.shipper.utils.GsonUtil;
import com.revenant.shipper.utils.SPUtils;
import com.revenant.shipper.utils.Constants;
import com.revenant.shipper.utils.Utils;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import okhttp3.internal.Util;

import static com.revenant.shipper.utils.Constants.Name_header_token;

public class UploadCertificatePhotoThreeActivity extends BaseActivity {
    @BindView(R.id.upload_text_type)
    TextView uploadTextType;
    @BindView(R.id.upload_second_title)
    TextView uploadSecondTitle;
    @BindView(R.id.upload_photo)
    ImageView uploadPhoto;
    @BindView(R.id.upload_type)
    Button uploadType;
    @BindView(R.id.show_or_hide)
    LinearLayout showOrHide;
    @BindView(R.id.identifier)
    EditText identifier;
    @BindView(R.id.name)
    EditText name;
    @BindView(R.id.legalerName)
    EditText legalerName;
    @BindView(R.id.address)
    EditText address;
    @BindView(R.id.createDate)
    TextView createDate;
    private String companyPhoto = "";
    private List<LocalMedia> selectList = new ArrayList<>();
    private Context context;
    private final static String TAG = UploadCertificatePhotoThreeActivity.class.getSimpleName();
    private boolean isQiYeRenZheng = false;
    private String Name_header;

    @Override
    public int getContentViewResId() {
        context = this;
        return R.layout.activity_upload_certificate_phone_three;
    }

    @Override
    public void initView() {

        setcenterTitle("企业认证");
        showOrHide.setVisibility(View.GONE);
        Name_header = Utils.get64String() + "_" + SPUtils.getUserPhone(context) + "_" + "1";

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


    @OnClick({R.id.upload_photo, R.id.upload_type, R.id.createDate})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.upload_photo:
                PictureSelector.create(this)
                        .openGallery(PictureMimeType.ofImage())
                        .maxSelectNum(1)
                        .loadImageEngine(GlideEngine.createGlideEngine()) // 请参考Demo GlideEngine.java
                        .forResult(PictureConfig.CHOOSE_REQUEST);
                break;
            case R.id.upload_type:
                if (isQiYeRenZheng == false) {
                    showToast("请上传正确营业执照");
                } else if (name.getText().toString().isEmpty()) {
                    showToast("不能为空");
                } else if (legalerName.getText().toString().isEmpty()) {
                    showToast("不能为空");
                } else if (address.getText().toString().isEmpty()) {
                    showToast("不能为空");
                } else if (identifier.getText().toString().isEmpty()) {
                    showToast("不能为空");
                } else if (createDate.getText().toString().isEmpty()) {
                    showToast("不能为空");
                } else if (companyPhoto.isEmpty()) {
                    showToast("不能为空");
                } else {
                    uploadId(name.getText().toString(),
                            identifier.getText().toString(),
                            address.getText().toString(),
                            legalerName.getText().toString()
                            , companyPhoto,
                            createDate.getText().toString()
                    );
                }

                break;
            case R.id.createDate:
                alerttime(createDate);
                break;

        }
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            switch (requestCode) {
                case PictureConfig.CHOOSE_REQUEST:
                    // 图片选择结果回调
                    selectList = PictureSelector.obtainMultipleResult(data);
                    // 例如 LocalMedia 里面返回五种path
                    // 1.media.getPath(); 原图path
                    // 2.media.getCutPath();裁剪后path，需判断media.isCut();切勿直接使用
                    // 3.media.getCompressPath();压缩后path，需判断media.isCompressed();切勿直接使用
                    // 4.media.getOriginalPath()); media.isOriginal());为true时此字段才有值
                    // 5.media.getAndroidQToPath();Android Q版本特有返回的字段，但如果开启了压缩或裁剪还是取裁剪或压缩路径；注意：.isAndroidQTransform 为false 此字段将返回空
                    // 如果同时开启裁剪和压缩，则取压缩路径为准因为是先裁剪后压缩
                    for (LocalMedia media : selectList) {
                        if (!selectList.isEmpty()) {
                            String url = media.getPath().startsWith("content://") ? media.getAndroidQToPath() : media.getPath();
                            uploadAvatar(url);
                        }
//                        Log.i(TAG, "是否压缩:" + media.isCompressed());
//                        Log.i(TAG, "压缩:" + media.getCompressPath());
//                        Log.i(TAG, "原图:" + media.getPath());
//                        Log.i(TAG, "是否裁剪:" + media.isCut());
//                        Log.i(TAG, "裁剪:" + media.getCutPath());
//                        Log.i(TAG, "是否开启原图:" + media.isOriginal());
//                        Log.i(TAG, "原图路径:" + media.getOriginalPath());
//                        Log.i(TAG, "Android Q 特有Path:" + media.getAndroidQToPath());
                    }
                    break;
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
                                    into(uploadPhoto);
                            showDig();
                            uploadId(fileUploadBean.getData().getUrl());
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

    private void uploadId(String fileone) {

        OkGo.<String>get(Constants.UploadEnterpriseAuthentication)

                .params("frontUrl", fileone)
                .params("accountId", SPUtils.getAccounId(context))
                .execute(new StringCallback() {
                    @Override
                    public void onSuccess(Response<String> response) {
                        YingYeZhiZhaoBean yingYeZhiZhaoBean = GsonUtil.parseJsonWithGson(response.body(), YingYeZhiZhaoBean.class);
                        if (yingYeZhiZhaoBean.getCode().equals("0")) {
                            isQiYeRenZheng = true;
                            showOrHide.setVisibility(View.VISIBLE);
                            address.setText(yingYeZhiZhaoBean.getData().getAddress());
                            name.setText(yingYeZhiZhaoBean.getData().getName());
                            identifier.setText(yingYeZhiZhaoBean.getData().getIdentifier());
                            legalerName.setText(yingYeZhiZhaoBean.getData().getLegalerName());
                            createDate.setText(yingYeZhiZhaoBean.getData().getCreateDate());
                            companyPhoto = yingYeZhiZhaoBean.getData().getCompanyPhoto();
                            dismissDig();

                        } else {
                            dismissDig();
                            showToast(yingYeZhiZhaoBean.getMsg());

                        }


                    }

                    @Override
                    public void onError(Response<String> response) {
                        super.onError(response);
                        showToast(response.body());
                        dismissDig();
//                        LogUtils.d("失败是" + response.message());
                    }
                });

    }

    private void uploadId(String name, String identifier, String address, String legalerName, String companyPhoto, String createDate) {

        OkGo.<String>post(Constants.SaveEnterpriseAuthentication)
                .headers(Name_header_token, Name_header)
                .params("name", name)
                .params("identifier", identifier)
                .params("address", address)
                .params("legalerName", legalerName)
                .params("companyPhoto", companyPhoto)
                .params("createDate", createDate)
                .params("accountId", SPUtils.getAccounId(context))
                .execute(new StringCallback() {
                    @Override
                    public void onSuccess(Response<String> response) {
                        YingYeZhiZhaoBean yingYeZhiZhaoBean = GsonUtil.parseJsonWithGson(response.body(), YingYeZhiZhaoBean.class);
                        if (yingYeZhiZhaoBean.getCode().equals("0")) {
                            showToast(yingYeZhiZhaoBean.getMsg());
                            finish();
                        }


                    }

                    @Override
                    public void onError(Response<String> response) {
                        super.onError(response);
                        showToast(response.body());
//                        LogUtils.d("失败是" + response.message());
                    }
                });

    }

    private void alerttime(TextView textView) {
        TimePickerView pvTime = new TimePickerBuilder(this, new OnTimeSelectListener() {
            @Override
            public void onTimeSelect(Date date, View v) {
                textView.setText(getTime(date));
            }
        }).setType(new boolean[]{true, true, true, false, false, false})
                .build();
        pvTime.show();

    }

    private String getTime(Date date) {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        return format.format(date);
    }
}
