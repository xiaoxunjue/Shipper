package com.revenant.shipper.ui.activity;

import android.os.Bundle;
import android.view.View;

import com.bm.library.Info;
import com.bm.library.PhotoView;
import com.revenant.shipper.R;
import com.revenant.shipper.base.BaseActivity;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ZoomActivity extends BaseActivity {
    @BindView(R.id.img)
    PhotoView img;
    Info mInfo;
    String imageurl = "https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1588923714963&di=7fc3c87918b1f36e01c8029badcafd35&imgtype=0&src=http%3A%2F%2Fpics3.baidu.com%2Ffeed%2F838ba61ea8d3fd1ff435ae2865a7551994ca5fd7.jpeg%3Ftoken%3D0a89872762a7502afb8b5aa19fb9453e";
    @BindView(R.id.imgshow)
    PhotoView imgshow;

    @Override
    public int getContentViewResId() {
        return R.layout.activity_zoom;
    }

    @Override
    public void initView() {
        setcenterTitle("图片展示");

        img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mInfo = PhotoView.getImageViewInfo(img);
                imgshow.setVisibility(View.VISIBLE);
                imgshow.animaFrom(mInfo);
            }
        });
        imgshow.enable();
        imgshow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                imgshow.animaTo(mInfo, new Runnable() {
                    @Override
                    public void run() {
                        imgshow.setVisibility(View.GONE);
                    }
                });
            }
        });
//        Glide.with(ZoomActivity.this).load(imageurl).centerCrop().
//                into(img);
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
}
