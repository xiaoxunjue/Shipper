package com.revenant.shipper.ui.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

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
import com.revenant.shipper.App;
import com.revenant.shipper.base.BaseActivity;
import com.revenant.shipper.bean.ShipperBean.FileUploadBean;
import com.revenant.shipper.bean.ShipperBean.PersonalDetailBean;
import com.revenant.shipper.utils.ActivityUtils;
import com.revenant.shipper.utils.GlideEngine;
import com.revenant.shipper.utils.GsonUtil;
import com.revenant.shipper.utils.SPUtils;
import com.revenant.shipper.utils.Utils;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

import static com.revenant.shipper.utils.Constants.Get_personal_info;
import static com.revenant.shipper.utils.Constants.Name_header_token;
import static com.revenant.shipper.utils.Constants.Type_shipper_status;
import static com.revenant.shipper.utils.Constants.UploadFile;
import static com.revenant.shipper.utils.Constants.UploadIdCard;

public class UploadCertificatePhotoTwoActivity extends BaseActivity {
    @BindView(R.id.upload_text_type)
    TextView uploadTextType;
    @BindView(R.id.upload_second_title)
    TextView uploadSecondTitle;
    @BindView(R.id.upload_photo)
    ImageView uploadPhoto;
    @BindView(R.id.upload_type)
    Button uploadType;
    private List<LocalMedia> selectList = new ArrayList<>();
    private final static String TAG = UploadCertificatePhotoTwoActivity.class.getSimpleName();
    //    private String get_type_upload;
    private Context context;
    private String uploadurltwo = "";
    private String uploadurlone = "";

    @Override
    public int getContentViewResId() {
        return R.layout.activity_upload_certificate_phone_two;
    }

    @Override
    public void initView() {
        context = this;
        Bundle bundle = this.getIntent().getExtras();
        LogUtils.d("AAA","Test");
//        get_type_upload = bundle.getString("upload_type_info");
        uploadurlone = bundle.getString("upload_type_fileurl");

//        String get_type_upload = "0";
//        if (get_type_upload.equals("1")) {
//            setcenterTitle("企业认证");
//            uploadType.setText("下一步");
//        } else {
//        }

        setcenterTitle("个人认证");


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


    @OnClick({R.id.upload_photo, R.id.upload_type})
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
                if (uploadurltwo.isEmpty()) {
                    showToast("请选择图片");
                } else if (!uploadurlone.isEmpty()) {
//                    if (get_type_upload.equals("0")){
                    uploadId(uploadurlone, uploadurltwo, "0");
//                    }else {
//                        uploadId(uploadurlone, uploadurltwo,"1");
//                    }

                }

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
//                        if (companytype > 0) {
//                            companytype++;
//                        }
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

        OkGo.<String>post(UploadFile)
                .tag(this)
                .isMultipart(true)

                .params("file", newfile)
                .execute(new StringCallback() {
                    @Override
                    public void onSuccess(Response<String> response) {
                        FileUploadBean fileUploadBean = GsonUtil.parseJsonWithGson(response.body(), FileUploadBean.class);
                        if (fileUploadBean.getCode().equals("0")) {
                            uploadurltwo = fileUploadBean.getData().getUrl();
                            Glide.with(context).load(fileUploadBean.getData().getUrl()).centerCrop().
                                    into(uploadPhoto);
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

    private void uploadId(String fileone, String filetwo, String type) {

        OkGo.<String>post(UploadIdCard)
                .headers(Name_header_token, Utils.get64String() + "_" + SPUtils.getUserPhone(context) + "_" + "1")
                .params("frontUrl", fileone)
                .params("backUrl", filetwo)
                .params("accountId", SPUtils.getAccounId(context))
                .params("companyId", type)
                .execute(new StringCallback() {
                    @Override
                    public void onSuccess(Response<String> response) {

//                        LogUtils.d("AAAAA" + response.body());

                        FileUploadBean fileUploadBean = GsonUtil.parseJsonWithGson(response.body(), FileUploadBean.class);
                        if (fileUploadBean.getCode().equals("0")) {
                            getpseronal();
//                            Handler handler = new Handler();
//                            handler.postDelayed(new Runnable() {
//                                @Override
//                                public void run() {
//                                    //要执行的操作
//                                    getpseronal();
//                                }
//                            }, 4000);//2秒后执行Runnable中的run方法


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

    private void getpseronal() {

        OkGo.<String>get(Get_personal_info)

                .params("id", SPUtils.getAccounId(context))
                .execute(new StringCallback() {
                    @Override
                    public void onSuccess(Response<String> response) {
                        LogUtils.d("AAAA" + response.body());
                        PersonalDetailBean g = GsonUtil.parseJsonWithGson(response.body(), PersonalDetailBean.class);
                        if (g.getCode().equals("0")) {
                            switch (Integer.valueOf(g.getData().getStatus().trim())) {
                                case 0:
                                    showToast("您的身份认证未通过，请重新认证");
                                    startActivity(NewSecondImproveDriverInformationActivity.class);
                                    finish();
                                    break;
                                case 1:
                                    App applicationContext = (App) getApplicationContext();
                                    applicationContext.setUserStatus(Integer.valueOf(g.getData().getStatus()));
                                    finish();
                                    ActivityUtils.removeAllActivity();
                                    startActivity(ShipperMainActivity.class);
                                    break;
                                case 9:
                                    showLongToast("您的身份认证待审核，请稍后");
                                    startActivity(MineShipperActivity.class);
                                    finish();
                                    break;
                            }
                        }
                    }
                });
    }

}
