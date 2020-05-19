package com.revenant.shipper.ui.activity;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.apkfuns.logutils.LogUtils;
import com.blankj.utilcode.util.ActivityUtils;
import com.lljjcoder.style.citylist.Toast.ToastUtils;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.StringCallback;
import com.lzy.okgo.model.Response;
import com.revenant.shipper.App;
import com.revenant.shipper.R;
import com.revenant.shipper.base.BaseActivity;
import com.revenant.shipper.bean.ShipperBean.LoginUserBean;
import com.revenant.shipper.bean.ShipperBean.PersonalDetailBean;
import com.revenant.shipper.utils.GsonUtil;
import com.revenant.shipper.utils.SPUtils;
import com.revenant.shipper.utils.WebContentActivity;
import com.revenant.shipper.utils.apkUpdate.StringFormatUtil;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cn.hutool.core.date.DateUtil;
import cn.hutool.crypto.SecureUtil;
import cn.jpush.android.api.JPushInterface;

import static com.revenant.shipper.utils.Constants.Get_personal_info;
import static com.revenant.shipper.utils.Constants.User_Login;

public class LoginActivity extends BaseActivity {
    @BindView(R.id.login_telnum)
    EditText loginTelnum;
    @BindView(R.id.login_forgetpassword)
    TextView loginForgetpassword;
    @BindView(R.id.login_btn)
    Button loginBtn;
    @BindView(R.id.login_verification)
    TextView loginVerification;
    @BindView(R.id.login_back_btn)
    RelativeLayout loginBackBtn;
    @BindView(R.id.new_account_register)
    TextView newAccountRegister;
    @BindView(R.id.login_pass)
    EditText loginPass;
    @BindView(R.id.web)
    TextView web;
    @BindView(R.id.webcheck)
    CheckBox webcheck;
    private Context context;
    private boolean ischeck;

    @Override
    public int getContentViewResId() {
//        getWindow().clearFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);
        return R.layout.activity_login;
    }

    @Override
    public void initView() {
        context = this;
//        getWindow().clearFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);

        loginBackBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
        ischeck=webcheck.isChecked();
        webcheck.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                ischeck=isChecked;
            }
        });
//        loginBackBtn.setListener(new CommonTitleBar.OnTitleBarListener() {
//            @Override
//            public void onClicked(View v, int action, String extra) {
//                if (action == CommonTitleBar.ACTION_LEFT_TEXT) {
//                }
//                onBackPressed();
//                // CommonTitleBar.ACTION_LEFT_TEXT;        // 左边TextView被点击
//                // CommonTitleBar.ACTION_LEFT_BUTTON;      // 左边ImageBtn被点击
//                // CommonTitleBar.ACTION_RIGHT_TEXT;       // 右边TextView被点击
//                // CommonTitleBar.ACTION_RIGHT_BUTTON;     // 右边ImageBtn被点击
//                // CommonTitleBar.ACTION_SEARCH;           // 搜索框被点击,搜索框不可输入的状态下会被触发
//                // CommonTitleBar.ACTION_SEARCH_SUBMIT;    // 搜索框输入状态下,键盘提交触发，此时，extra为输入内容
//                // CommonTitleBar.ACTION_SEARCH_VOICE;     // 语音按钮被点击
//                // CommonTitleBar.ACTION_SEARCH_DELETE;    // 搜索删除按钮被点击
//                // CommonTitleBar.ACTION_CENTER_TEXT;      // 中间文字点击
//            }
//        });


    }


    @Override
    public void initData() {
        JPushInterface.setDebugMode(true);
        JPushInterface.init(this);
        String rid = JPushInterface.getRegistrationID(getApplicationContext());
        LogUtils.d("AAAAAAAAAAAAAA" + rid);
        if (SPUtils.getJpRidToken(getApplicationContext()).isEmpty()) {
            if (!rid.isEmpty()) {
                SPUtils.setJpRidToken(getApplicationContext(), rid);
            }
        }
    }

    @Override
    protected boolean isshowtitlebar() {
        return false;
    }

    @Override
    public void leftbarclick() {
        super.leftbarclick();
        startActivity(MineDriverActivity.class);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }

    @OnClick({R.id.login_forgetpassword, R.id.login_btn, R.id.login_verification, R.id.new_account_register,R.id.web})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.login_forgetpassword:
                Bundle bundle0 = new Bundle();
                bundle0.putString("fogresttype", "0");
                startActivity(ForgetPasswordActivity.class, bundle0);
                break;
            case R.id.login_btn:
                if (loginTelnum.getText().toString().isEmpty()) {
                    showToast("请输入完整手机号");
                } else if (loginTelnum.getText().toString().length() != 11) {
                    showToast("请输入正确手机号");
                } else if (!StringFormatUtil.phoneCheck(loginTelnum.getText().toString().trim())) {
                    ToastUtils.showShortToast(context, "手机号码格式不正确");
                } else if (loginPass.getText().toString().isEmpty()) {
                    showToast("请输入密码");
                } else if (!ischeck) {
                    ToastUtils.showShortToast(context, "阅读并勾选协议");
                } else {
                    String account = loginTelnum.getText().toString();
                    String pwd = loginPass.getText().toString();
                    String Jpush_rid = SPUtils.getJpRidToken(context);
                    OkGo.<String>post(User_Login)
                            .params("account", account)
                            .params("password", pwd)
                            .params("auroraEquipment", Jpush_rid)
                            .params("type", "1")
                            .execute(new StringCallback() {
                                @Override
                                public void onSuccess(Response<String> response) {
                                    LoginUserBean s = GsonUtil.parseJsonWithGson(response.body(), LoginUserBean.class);
                                    if (s.getCode().equals("0")) {

                                        SPUtils.setUserToken(context, s.getData().getAccess_token());
                                        LogUtils.d("CCCCCCCC" + SPUtils.getUserToken(context));
                                        SPUtils.setAccounId(context, s.getData().getUserAccountSub().getAccountId());
                                        SPUtils.setUserPhone(context, s.getData().getUser().getMobile());
                                        getpseronal();
//                                        if (s.getData().getUser().getStatus().equals("0")) {
//                                            finish();
//                                            startActivity(NoIdentificationActivity.class);
//                                        } else {
//                                            ActivityUtils.removeAllActivity();
//                                            SPUtils.setUserToken(context, s.getData().getAccess_token());
//                                            SPUtils.setAccounId(context, s.getData().getUserAccountSub().getAccountId());
//                                            finish();
//                                            startActivity(ShipperMainActivity.class);
//                                        }
                                    } else {
                                        showToast(s.getMsg());
                                    }

                                }

                                @Override
                                public void onError(Response<String> response) {
                                    super.onError(response);
                                    showToast(response.body());
                                }
                            });
                }
                break;
            case R.id.login_verification:
                finish();
                startActivity(VerificationActivity.class);
                break;
            case R.id.web:
                startActivity(WebContentActivity.class);
                break;
            case R.id.new_account_register:
                Bundle bundle1 = new Bundle();
                bundle1.putString("fogresttype", "1");
                finish();
                startActivity(ForgetPasswordActivity.class, bundle1);
                break;
        }
    }

    private void getpseronal() {
        String timestamp = String.valueOf(DateUtil.current(false));//header不支持中文，不允许有特殊字符
        OkGo.<String>get(Get_personal_info)
                .headers("sign", SecureUtil.md5(SecureUtil.md5(SecureUtil.md5(timestamp) + timestamp) + timestamp)) //header不支持中文，不允许有特殊字符
                .headers("timestamp", timestamp)
                .headers("X-Token", SPUtils.getUserToken(context))
                .params("id", SPUtils.getAccounId(context))
                .execute(new StringCallback() {
                    @Override
                    public void onSuccess(Response<String> response) {
                        LogUtils.d("AAAA" + response.body());
                        PersonalDetailBean g = GsonUtil.parseJsonWithGson(response.body(), PersonalDetailBean.class);
                        if (g.getCode().equals("0")) {

                            App applicationContext = (App) getApplicationContext();
                            applicationContext.setUserStatus(Integer.valueOf(g.getData().getStatus()));
                            ActivityUtils.startActivity(ShipperMainActivity.class);
                            ActivityUtils.finishAllActivitiesExceptNewest();
//                            ActivityUtils.finishToActivity(ShipperMainActivity.class,true);
//                            finish();
//                            ActivityUtils.removeAllActivity();
//                            startActivity(ShipperMainActivity.class);

//                            finish();
                        }
                    }

                    @Override
                    public void onError(Response<String> response) {
                        super.onError(response);
                    }
                });
    }

}
