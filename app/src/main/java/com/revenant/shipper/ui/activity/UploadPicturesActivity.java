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
import com.revenant.shipper.bean.BaseBean.MessageEvent;
import com.revenant.shipper.bean.ShipperBean.ChaKanBangDan;
import com.revenant.shipper.bean.ShipperBean.FileUploadBean;
import com.revenant.shipper.utils.EventBusUtil;
import com.revenant.shipper.utils.GlideEngine;
import com.revenant.shipper.utils.GsonUtil;
import com.revenant.shipper.utils.SPUtils;
import com.revenant.shipper.utils.Constants;
import com.revenant.shipper.utils.Utils;

import org.angmarch.views.NiceSpinner;
import org.angmarch.views.OnSpinnerItemSelectedListener;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

import static com.revenant.shipper.bean.BaseBean.MyEventCode.Closed_Goods_SIGAL;
import static com.revenant.shipper.bean.BaseBean.MyEventCode.Order_Type_SIGAL;

public class UploadPicturesActivity extends BaseActivity {
    @BindView(R.id.spinner_select_a)
    NiceSpinner spinnerSelect;
    @BindView(R.id.click_phone)
    Button clickPhone;
    @BindView(R.id.summit)
    Button summit;
    @BindView(R.id.image_one)
    ImageView imageOne;
    @BindView(R.id.click_one)
    LinearLayout clickOne;
    @BindView(R.id.spinner_select_b)
    NiceSpinner spinnerSelectB;
    @BindView(R.id.image_two)
    ImageView imageTwo;
    @BindView(R.id.spinner_select_c)
    NiceSpinner spinnerSelectC;
    @BindView(R.id.click_three)
    ImageView imageThree;
    @BindView(R.id.inputone)
    EditText inputone;
    @BindView(R.id.inputtwo)
    TextView inputtwo;
    @BindView(R.id.inputthree)
    TextView inputthree;
    private List<LocalMedia> selectList = new ArrayList<>();
    private int orderid = 0;
    private Context context;
    int selecttype = 0;
    String imageone = "";
    String imagetwo = "";
    String imagethree = "";
    String phone = "";
    List<String> dataset;

    @Override
    public int getContentViewResId() {
        return R.layout.activity_upload_pictures;
    }

    @Override
    public void initView() {
        setcenterTitle("上传磅单");
        context = this;
        orderid = getIntent().getIntExtra("orderid", 0);
//        imageTwo.enable();
//        imageThree.enable();

    }

    @Override
    public void initData() {
        dataset  = new LinkedList<>(Arrays.asList("吨", "方", "米", "件"));
        spinnerSelect.attachDataSource(dataset);
        spinnerSelect.setOnSpinnerItemSelectedListener(new OnSpinnerItemSelectedListener() {
            @Override
            public void onItemSelected(NiceSpinner parent, View view, int position, long id) {
                LogUtils.d("AAAAAAAAAA" + position);
            }
        });
        LookBangdan(orderid);

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }

    private void selectimage() {
        PictureSelector.create(this)
                .openGallery(PictureMimeType.ofImage())
                .maxSelectNum(1)
                .loadImageEngine(GlideEngine.createGlideEngine()) // 请参考Demo GlideEngine.java
                .forResult(PictureConfig.CHOOSE_REQUEST);
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
//                            uploadAvatar(url);
                            uploadAvatar(url, selecttype);
                        }
                        break;
                    }
            }
        }
    }

    @Override
    public void leftbarclick() {
        super.leftbarclick();
        finish();
    }

//    @OnClick({R.id.click_phone, R.id.summit})
//    public void onViewClicked(View view) {
//        switch (view.getId()) {
//            case R.id.click_phone:
//                callPhone(this, "123456");
//                break;
//            case R.id.summit:
//                break;
//        }
//    }

    private void uploadBangdan(
            int orderId,
            String shipperTon,
            String shipperImg
    ) {
        OkGo.<String>post(Constants.Upload_BangDan)
                .params("orderId", orderId)
                .params("shipperTon", shipperTon)
                .params("shipperImg", shipperImg).execute(new StringCallback() {
            @Override
            public void onSuccess(Response<String> response) {
                FileUploadBean fileUploadBean = GsonUtil.parseJsonWithGson(response.body(), FileUploadBean.class);
                if (fileUploadBean.getCode().equals("0")) {
                    MessageEvent event = new MessageEvent(Order_Type_SIGAL);
                    EventBusUtil.sendEvent(event);
                    showToast(fileUploadBean.getMsg());
                    finish();
                }else {
                    showToast(fileUploadBean.getMsg());
                }

            }
        });
    }

    @OnClick({R.id.click_one, R.id.summit, R.id.click_phone
//            ,R.id.image_two,R.id.click_three
    })
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.click_one:
                selectimage();
                selecttype = 0;
                break;
            case R.id.image_two:
//                selectimage();
//                selecttype = 1;
                Bundle bundle = new Bundle();
                bundle.putString("imageurl", imagetwo);
                startActivity(ZoomActivity.class, bundle);
                break;
            case R.id.click_three:
//                selectimage();
//                selecttype = 2;
                Bundle bundle3 = new Bundle();
                bundle3.putString("imageurl", imagethree);
                startActivity(ZoomActivity.class, bundle3);
                break;
            case R.id.summit:
                if (inputone.getText().toString().isEmpty()) {
                    showToast("请输入货物数量");
                } else if (imageone.isEmpty()) {
                    showToast("请选择图片");
                } else if (decimalcount(inputone, "重量填写完备")) {
                } else {
                    uploadBangdan(orderid,
                            inputone.getText().toString().trim(),
                            imageone
                    );
                }

                break;
            case R.id.click_phone:
                if (!phone.isEmpty()) {
                    Utils.callPhone(this, phone);

                }
                break;
        }
    }

    private void uploadAvatar(final String path, int type) {
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
//                            uploadurltwo = fileUploadBean.getData().getUrl();
                            switch (type) {
                                case 0:
                                    imageone = fileUploadBean.getData().getUrl();
                                    Glide.with(context).load(fileUploadBean.getData().getUrl()).centerCrop().
                                            into(imageOne);
                                    break;
                                case 1:
                                    imagetwo = fileUploadBean.getData().getUrl();
                                    Glide.with(context).load(fileUploadBean.getData().getUrl()).centerCrop().
                                            into(imageTwo);
                                    break;
                                case 2:
                                    imagethree = fileUploadBean.getData().getUrl();
                                    Glide.with(context).load(fileUploadBean.getData().getUrl()).centerCrop().
                                            into(imageThree);
                                    break;
                            }

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

    private void LookBangdan(int bangdanid) {
        OkGo.<String>get(Constants.Look_BangDan)
                .params("orderId", bangdanid)
                .execute(new StringCallback() {
                    @Override
                    public void onSuccess(Response<String> response) {
                        ChaKanBangDan chaKanBangDan = GsonUtil.parseJsonWithGson(response.body(), ChaKanBangDan.class);
                        phone = chaKanBangDan.getData().getPhone();

                        inputtwo.setText(chaKanBangDan.getData().getFirstbillContent() + " "+dataset.get(chaKanBangDan.getData().getDanWei()-1));
                        Glide.with(context).load(chaKanBangDan.getData().getFirstbillPhoto()).centerCrop().
                                into(imageTwo);
                        imagetwo = chaKanBangDan.getData().getFirstbillPhoto();

                        inputthree.setText(chaKanBangDan.getData().getSecondbillContent() + " "+dataset.get(chaKanBangDan.getData().getDanWei()-1));
                        Glide.with(context).load(chaKanBangDan.getData().getSecondbillPhoto()).centerCrop().
                                into(imageThree);
                        imagethree = chaKanBangDan.getData().getSecondbillPhoto();


                    }
                });
    }

    private boolean decimalcount(TextView textView, String msg) {
        if (textView.getText().toString().trim().endsWith(".")) {
            showToast(msg);
            return true;
        }
        return false;
    }
}
