package com.revenant.shipper.base;

import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Environment;
import android.os.Handler;
import android.provider.Settings;

import androidx.appcompat.app.AlertDialog;

import com.apkfuns.logutils.LogUtils;
import com.blankj.utilcode.util.AppUtils;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.StringCallback;
import com.lzy.okgo.model.Response;
import com.lzy.okgo.request.GetRequest;
import com.revenant.shipper.App;
import com.revenant.shipper.BuildConfig;
import com.revenant.shipper.R;
import com.revenant.shipper.bean.ShipperBean.EmptyCarNoLoginBean;
import com.revenant.shipper.bean.UpdateBean;
import com.revenant.shipper.ui.activity.MineShuCheActivity;
import com.revenant.shipper.ui.activity.NoIdentificationActivity;
import com.revenant.shipper.ui.activity.ShipperMainActivity;
import com.revenant.shipper.utils.Constants;
import com.revenant.shipper.utils.GsonUtil;
import com.revenant.shipper.utils.PermissionConstants;
import com.revenant.shipper.utils.PermissionUtils;
import com.revenant.shipper.utils.RestartUtils;
import com.revenant.shipper.utils.SPUtils;
import com.revenant.shipper.utils.apkUpdate.ApkDownloadManager;
import com.revenant.shipper.utils.apkUpdate.ApkUpdateUtils;
import com.revenant.shipper.utils.apkUpdate.VersionUtils;

import java.io.File;
import java.util.List;

import cn.jpush.android.api.JPushInterface;
import constant.UiType;
import listener.OnBtnClickListener;
import listener.UpdateDownloadListener;
import model.UiConfig;
import model.UpdateConfig;
import update.UpdateAppUtils;

import static com.revenant.shipper.utils.RestartUtils.STATUS_NORMAL;

public class SplashActivity extends BaseActivity {

    private String apkUrl = "";
    private String updateTitle = "";
    private String updateContent = "";

//    private String apkUrl = "";
//    private String updateTitle = "";
//    private String updateContent = "";

    @Override
    public int getContentViewResId() {
        return R.layout.activity_splash;

    }

    @Override
    public void initView() {
        App.flag = 0;

        RestartUtils.getInstance().setAppStatus(STATUS_NORMAL);
        JPushInterface.setDebugMode(true);
        JPushInterface.init(this);
        String rid = JPushInterface.getRegistrationID(getApplicationContext());
        LogUtils.d("AAAAAAAAAAAAAA" + rid);
        LogUtils.d("SSSSSSSSSSSSS" + AppUtils.getAppPackageName());
        SPUtils.setUpdateNum(getApplication(), 1);
        if (SPUtils.getJpRidToken(getApplicationContext()).isEmpty()) {
            if (!rid.isEmpty()) {
                SPUtils.setJpRidToken(getApplicationContext(), rid);
            }
        }
//        updateApks();
    }

    @Override
    protected boolean isshowtitlebar() {
        return false;
    }

    @Override
    public void initData() {
        checkPermission();
    }

    /**
     * 请求权限
     */
    public void checkPermission() {
        PermissionUtils.permission(
                PermissionConstants.STORAGE,
                PermissionConstants.PHONE,
                PermissionConstants.LOCATION,
                PermissionConstants.MICROPHONE,
                PermissionConstants.CAMERA)
                .rationale(new PermissionUtils.OnRationaleListener() {
                    @Override
                    public void rationale(ShouldRequest shouldRequest) {
                        shouldRequest.again(true);
                    }
                })
                .callback(new PermissionUtils.FullCallback() {
                    @Override
                    public void onGranted(List<String> permissionsGranted) {
                        new Handler().postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                boolean testUpdate = false;
                                if (testUpdate) {
                                    updateApks();
                                } else {
                                    if (SPUtils.getAccounId(getApplicationContext()) == 0) {
                                        startActivity(NoIdentificationActivity.class);
                                    } else {
                                        startActivity(ShipperMainActivity.class);
                                    }
                                    finish();
                                }

                            }
                        }, 1000);
                    }

                    @Override
                    public void onDenied(List<String> permissionsDeniedForever, List<String> permissionsDenied) {
                        if (!permissionsDeniedForever.isEmpty()) {
                            showMissingPermissionDialog("必须");
                            return;
                        }
                        checkPermission();
                    }
                })
                .request();
    }

    public void showMissingPermissionDialog(String s) {
        AlertDialog.Builder builder = new AlertDialog.Builder(SplashActivity.this);
        final AlertDialog alertDialog = builder.create();
        builder.setMessage("当前应用缺少-" + s + "-权限。\n\n请点击\"设置\"-\"权限\"-打开所需权限。\n\n最后点击两次后退按钮，即可返回。");
        // 拒绝, 退出应用
        builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                alertDialog.dismiss();
                finish();
            }
        });
        builder.setPositiveButton("设置", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                alertDialog.dismiss();
                Intent intent = new Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
                intent.setData(Uri.parse("package:" + BuildConfig.APPLICATION_ID));
                startActivity(intent);
            }
        });
        builder.setCancelable(false);
        builder.show();
    }

    private void updateApks() {
        GetRequest<String> stringGetRequest = OkGo.<String>get(Constants.Update_Apk);
        stringGetRequest.params("appVersion", VersionUtils.getVersionCode());
        stringGetRequest.params("platform", "1");
        stringGetRequest.execute(new StringCallback() {
            @Override
            public void onSuccess(Response<String> response) {
                UpdateBean updateBean = GsonUtil.parseJsonWithGson(response.body(), UpdateBean.class);
                if (updateBean.getData() != null) {
                    Integer versioncode = Integer.valueOf(updateBean.getData().getVersion());
                    if (versioncode > VersionUtils.getVersionCode()) {
                        updateTitle = updateBean.getData().getTitle();
                        updateContent = updateBean.getData().getContent();
                        apkUrl = updateBean.getData().getUrl();
                        boolean isAuto = updateBean.getData().isIsAuto();
                        String name = "shipper";
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
                                .setCancelBtnClickListener(new OnBtnClickListener() {
                                    @Override
                                    public boolean onClick() {
                                        if (SPUtils.getAccounId(getApplicationContext()) == 0) {
                                            startActivity(NoIdentificationActivity.class);
                                        } else {
                                            startActivity(ShipperMainActivity.class);
                                        }
                                        finish();
                                        return false;
                                    }
                                })
                                .setUpdateBtnClickListener(new OnBtnClickListener() {
                                    @Override
                                    public boolean onClick() {
                                        return false;
                                    }
                                })
                                .update();
                    } else {
                        if (SPUtils.getAccounId(getApplicationContext()) == 0) {
                            startActivity(NoIdentificationActivity.class);
                        } else {
                            startActivity(ShipperMainActivity.class);
                        }
                        finish();
                    }

                } else {
                    if (SPUtils.getAccounId(getApplicationContext()) == 0) {
                        startActivity(NoIdentificationActivity.class);
                    } else {
                        startActivity(ShipperMainActivity.class);
                    }
                    finish();
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
