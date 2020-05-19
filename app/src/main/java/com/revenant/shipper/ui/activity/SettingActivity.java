package com.revenant.shipper.ui.activity;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.widget.Button;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.StringCallback;
import com.lzy.okgo.model.Response;
import com.revenant.shipper.R;
import com.revenant.shipper.adapter.SettingItemAdapter;
import com.revenant.shipper.base.BaseActivity;
import com.revenant.shipper.bean.AreaShowBean;
import com.revenant.shipper.bean.UpdateBean;
import com.revenant.shipper.utils.ActivityUtils;
import com.revenant.shipper.utils.Constants;
import com.revenant.shipper.utils.GsonUtil;
import com.revenant.shipper.utils.SPUtils;
import com.revenant.shipper.utils.apkUpdate.VersionUtils;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import constant.UiType;
import listener.OnBtnClickListener;
import model.UiConfig;
import model.UpdateConfig;
import update.UpdateAppUtils;

public class SettingActivity extends BaseActivity {
    @BindView(R.id.setting_recyclerview)
    RecyclerView settingRecyclerview;
    @BindView(R.id.exit_account)
    Button exitAccount;
    private SettingItemAdapter settingItemAdapter;
    private List<AreaShowBean> beanList;
    private Context context;
    private List<String> titleList = Arrays.asList("插件管理", "清除缓存", "隐私设置", "客户端权限说明", "账户与安全", "故障上报", "关于我们", "版本升级");

    @Override
    public int getContentViewResId() {
        return R.layout.activity_setting;
    }

    @Override
    public void initView() {
        setcenterTitle("设置");
        context = this;
        beanList = new ArrayList<>();
        for (int i = 0; i < titleList.size(); i++) {
            AreaShowBean bean = new AreaShowBean();
            bean.setAreaname(titleList.get(i));
            beanList.add(bean);
        }


        settingItemAdapter = new SettingItemAdapter();
        settingItemAdapter.addData(beanList);
        settingRecyclerview.setAdapter(settingItemAdapter);
        settingRecyclerview.setLayoutManager(new LinearLayoutManager(this));

        settingItemAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                int size = settingItemAdapter.getData().size();
                if (position == size - 1) {
                    showToast("当前版本是:"+VersionUtils.getVersionName());
                    updateApks();
                }
            }
        });
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

    @OnClick(R.id.exit_account)
    public void onViewClicked() {


        SPUtils.clearAccounId(context);
        SPUtils.clearUserToken(context);
        SPUtils.clearUsermobile(context);
        SPUtils.clearJpRidToken(context);
        ActivityUtils.removeAllActivity();
        startActivity(NoIdentificationActivity.class);
    }

    private void updateApks() {

        OkGo.<String>get(Constants.Update_Apk)
                .params("appVersion", VersionUtils.getVersionCode())
                .params("platform", "1")
                .execute(new StringCallback() {
                    @Override
                    public void onSuccess(Response<String> response) {
                        UpdateBean updateBean = GsonUtil.parseJsonWithGson(response.body(), UpdateBean.class);
                        if (updateBean.getData() != null) {
                            Integer versioncode = Integer.valueOf(updateBean.getData().getVersion());
                            if (versioncode > VersionUtils.getVersionCode()) {
                                String updateTitle = updateBean.getData().getTitle();
                                String updateContent = updateBean.getData().getContent();
                                String apkUrl = updateBean.getData().getUrl();
                                boolean isAuto = updateBean.getData().isIsAuto();
                                String name = "driver";
                                File filePictures = getExternalFilesDir(Environment.DIRECTORY_DOWNLOADS);
                                String absolutePath1 = filePictures.getAbsolutePath();
                                String androidQpath = absolutePath1 + "/" + name;

                                UpdateConfig updateConfig = new UpdateConfig();
                                updateConfig.setCheckWifi(false);
                                updateConfig.setNeedCheckMd5(false);
                                updateConfig.setShowNotification(true);
                                updateConfig.setShowDownloadingToast(true);
                                updateConfig.setAlwaysShowDownLoadDialog(true);
                                updateConfig.setApkSaveName(name);
                                updateConfig.setApkSavePath(androidQpath);
                                updateConfig.setNotifyImgRes(R.mipmap.icon);
                                updateConfig.setForce(false);
                                final UiConfig uiConfig = new UiConfig();
                                uiConfig.setUiType(UiType.PLENTIFUL);
                                uiConfig.setCancelBtnText("下次再说");
                                uiConfig.setUpdateBtnText("立即升级");

                                UpdateAppUtils
                                        .getInstance()
                                        .apkUrl(apkUrl)
                                        .updateTitle(updateTitle)
                                        .updateContent(updateContent)
                                        .uiConfig(uiConfig)
                                        .updateConfig(updateConfig)
                                        .setUpdateBtnClickListener(new OnBtnClickListener() {
                                            @Override
                                            public boolean onClick() {
                                                return false;
                                            }
                                        })
                                        .setCancelBtnClickListener(new OnBtnClickListener() {
                                            @Override
                                            public boolean onClick() {
                                                return false;

                                            }
                                        })
                                        .update();
                            }

                        }
                    }


                    @Override
                    public void onError(Response<String> response) {
                        super.onError(response);
                        String msgerror = response.getException().getMessage();
                        showLongToast(msgerror);
                    }

                    @Override
                    public void onFinish() {
                        super.onFinish();
                    }
                });
    }
}
