package com.revenant.shipper.ui.activity;

import android.content.Context;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.apkfuns.logutils.LogUtils;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.StringCallback;
import com.lzy.okgo.model.Response;
import com.lzy.okgo.request.GetRequest;
import com.revenant.shipper.R;
import com.revenant.shipper.adapter.EmptyNoLoginItemAdapter;
import com.revenant.shipper.base.BaseActivity;
import com.revenant.shipper.bean.ShipperBean.NoLoginEmptyBean;
import com.revenant.shipper.bean.UpdateBean;
import com.revenant.shipper.utils.Constants;
import com.revenant.shipper.utils.GsonUtil;
import com.revenant.shipper.utils.SPUtils;
import com.revenant.shipper.utils.apkUpdate.VersionUtils;
import com.umeng.socialize.ShareAction;
import com.umeng.socialize.UMAuthListener;
import com.umeng.socialize.UMShareAPI;
import com.umeng.socialize.UMShareListener;
import com.umeng.socialize.bean.SHARE_MEDIA;

import java.io.File;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import constant.UiType;
import listener.OnBtnClickListener;
import model.UiConfig;
import model.UpdateConfig;
import update.UpdateAppUtils;

public class NoIdentificationActivity extends BaseActivity {


    @BindView(R.id.left_base)
    ImageView leftBase;
    @BindView(R.id.right_base)
    ImageView rightBase;
    @BindView(R.id.basetitle_show_or_hide)
    LinearLayout basetitleShowOrHide;
    @BindView(R.id.nologinrecyclerview)
    RecyclerView nologinrecyclerview;
    @BindView(R.id.nologinpublishbotton)
    FloatingActionButton nologinpublishbotton;
    @BindView(R.id.networkshow)
    LinearLayout networkshow;
    @BindView(R.id.testshare)
    Button testshare;
    private EmptyNoLoginItemAdapter itemAdapter;
    private Context context;
    private String apkUrl = "";
    private String updateTitle = "";
    private String updateContent = "";
//    private int loginType = 0;
//    private int pagernum = 1;
    /*  0未登录，1，登录未验证，2验证  */


    @Override
    public int getContentViewResId() {
        context = this;
        return R.layout.activity_noidentification;
    }

    @Override
    public void initView() {
//        if (SPUtils.getAccounId(context) != 0) {
//
//        }
//        String a = SPUtils.getUserToken(context);
//        String b = SPUtils.getUserAuthentication(context);
//        String jpRidToken = SPUtils.getJpRidToken(context);
//        LogUtils.d("AAAAAAAAAA" + a + b + jpRidToken);
//        if (SPUtils.getUserToken(context).isEmpty()) {
//            loginType = 0;
//        } else if (SPUtils.getUserAuthentication(context).equals("0")) {
//            loginType = 1;
//        }
        itemAdapter = new EmptyNoLoginItemAdapter();
        nologinrecyclerview.setAdapter(itemAdapter);
        nologinrecyclerview.setLayoutManager(new LinearLayoutManager(context));
        itemAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                if (SPUtils.getAccounId(context) == 0) {
                    startActivity(LoginActivity.class);
                }
//                getLogin();
            }
        });

        itemAdapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
            @Override
            public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                switch (view.getId()) {
                    case R.id.nologinphone:
//                        getLogin();
                        break;
                    default:
                }
            }
        });


    }

    @Override
    protected boolean isshowtitlebar() {
        return false;

    }

    @Override
    public void initData() {
//        if (loginType == 0 || loginType == 1) {
//            getNologinData();
//        }
        updateApks();
        getNologinData();
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }

    @OnClick({R.id.left_base, R.id.right_base, R.id.nologinpublishbotton, R.id.testshare})
    public void onViewClickedId(View view) {
        switch (view.getId()) {
            case R.id.left_base:
                if (SPUtils.getAccounId(context) == 0) {
                    startActivity(LoginActivity.class);
                }
//                if (loginType == 0) {
//                    getLogin();
//                } else {
//                    startActivity(MineShipperActivity.class);
//                }
//                startActivity(LoginActivity.class);
//                startActivity(MineDriverActivity.class);
//                getMine(MineShipperActivity.class);
//                startActivity(MineShipperActivity.class);

//                startActivity(MineDriverActivity.class);
//                startActivity(TemplateActivity.class);
//                startActivity(MineReceiptActivity.class);
//                startActivity(DriverTrackActivity.class);
                break;
            case R.id.right_base:
                if (SPUtils.getAccounId(context) == 0) {
                    startActivity(LoginActivity.class);
                }
//                if (loginType == 0) {
//                    getLogin();
//                } else if (loginType == 1) {
//                    getauthentication();
//                }
//                getLogin(NewsActivity.class);
//                startActivity(NewsActivity.class);
                break;
            case R.id.nologinpublishbotton:
                if (SPUtils.getAccounId(context) == 0) {
                    startActivity(LoginActivity.class);
                }

//                if (loginType == 0) {
//                    getLogin();
//                } else if(loginType == 1){
//                    getauthentication();
//                }

//                getLogin();
//                Utils.callPhone(this, "15881354454");
//                startActivity(LoginActivity.class);
                break;
            case R.id.testshare:
//                /*分享*/
//                new ShareAction(NoIdentificationActivity.this).withText("hello").setDisplayList(SHARE_MEDIA.WEIXIN)
//                        .setCallback(shareListener).open();
                /*登录*/

                UMShareAPI.get(context).getPlatformInfo(NoIdentificationActivity.this,SHARE_MEDIA.WEIXIN,authListener);
                break;
        }
    }

    UMAuthListener authListener = new UMAuthListener() {
        /**
         * @desc 授权开始的回调
         * @param platform 平台名称
         */
        @Override
        public void onStart(SHARE_MEDIA platform) {
            Toast.makeText(context, "成功了", Toast.LENGTH_LONG).show();
        }

        /**
         * @desc 授权成功的回调
         * @param platform 平台名称
         * @param action 行为序号，开发者用不上
         * @param data 用户资料返回
         */
        @Override
        public void onComplete(SHARE_MEDIA platform, int action, Map<String, String> data) {

            Toast.makeText(context, "成功了", Toast.LENGTH_LONG).show();

        }

        /**
         * @desc 授权失败的回调
         * @param platform 平台名称
         * @param action 行为序号，开发者用不上
         * @param t 错误原因
         */
        @Override
        public void onError(SHARE_MEDIA platform, int action, Throwable t) {

            Toast.makeText(context, "失败：" + t.getMessage(), Toast.LENGTH_LONG).show();
        }

        /**
         * @desc 授权取消的回调
         * @param platform 平台名称
         * @param action 行为序号，开发者用不上
         */
        @Override
        public void onCancel(SHARE_MEDIA platform, int action) {
            Toast.makeText(context, "取消了", Toast.LENGTH_LONG).show();
        }
    };
    private UMShareListener shareListener = new UMShareListener() {
        /**
         * @descrption 分享开始的回调
         * @param platform 平台类型
         */
        @Override
        public void onStart(SHARE_MEDIA platform) {

        }

        /**
         * @descrption 分享成功的回调
         * @param platform 平台类型
         */
        @Override
        public void onResult(SHARE_MEDIA platform) {
            Toast.makeText(NoIdentificationActivity.this, "成功了", Toast.LENGTH_LONG).show();
        }

        /**
         * @descrption 分享失败的回调
         * @param platform 平台类型
         * @param t 错误原因
         */
        @Override
        public void onError(SHARE_MEDIA platform, Throwable t) {
            Toast.makeText(NoIdentificationActivity.this, "失败" + t.getMessage(), Toast.LENGTH_LONG).show();
        }

        /**
         * @descrption 分享取消的回调
         * @param platform 平台类型
         */
        @Override
        public void onCancel(SHARE_MEDIA platform) {
            Toast.makeText(NoIdentificationActivity.this, "取消了", Toast.LENGTH_LONG).show();

        }
    };

    private void getNologinData() {
        OkGo.<String>get(Constants.List_Empty_No_Login)
                .execute(new StringCallback() {
                    @Override
                    public void onSuccess(Response<String> response) {
                        LogUtils.d("SSSSSSS" + response.body());
                        NoLoginEmptyBean s = GsonUtil.parseJsonWithGson(response.body(), NoLoginEmptyBean.class);
                        if (s != null && s.getCode() != null) {
                            if (s.getCode().equals("0")) {
                                itemAdapter.setNewData(s.getData());
                            }
                        }

                    }

                    @Override
                    public void onError(Response<String> response) {
                        super.onError(response);
                    }

                    @Override
                    public void onFinish() {
                        super.onFinish();
                    }
                });
    }

    public void getLogin() {
        if (SPUtils.getUserToken(context).isEmpty()) {
            showToast("请先登录");
            startActivity(LoginActivity.class);
        }
    }


    public void getMine() {
        if (SPUtils.getUserToken(context).isEmpty()) {
            showToast("请先登录");
            finish();
            startActivity(LoginActivity.class);
        }
    }


//    public void getauthentication() {
//        if (SPUtils.getUserAuthentication(context).equals("0")) {
//            showToast("请先认证");
//            startActivity(NewSecondImproveDriverInformationActivity.class);
//        }
//    }

    private void getauth() {
//        OkGo.<String>get(List_Empty_No_Authentication)
//
//                .params("isAuthentication", "1")
//                .params("pageNum", pagernum)
//                .execute(new StringCallback() {
//                    @Override
//                    public void onSuccess(Response<String> response) {
//                    }
//
//                    @Override
//                    public void onFinish() {
//                        super.onFinish();
//                    }
//                });
//    }}
    }

    private void updateApks() {
        if (SPUtils.getUpdateNum(context) == 1) {
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
                            updateConfig.setForce(isAuto);


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

        SPUtils.setUpdateNum(context, 2);

    }
}
