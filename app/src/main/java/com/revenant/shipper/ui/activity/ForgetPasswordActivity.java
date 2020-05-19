package com.revenant.shipper.ui.activity;

import android.content.Context;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.apkfuns.logutils.LogUtils;
import com.lljjcoder.style.citylist.Toast.ToastUtils;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.StringCallback;
import com.lzy.okgo.model.Response;
import com.revenant.shipper.App;
import com.revenant.shipper.R;
import com.revenant.shipper.base.BaseActivity;
import com.revenant.shipper.bean.ShipperBean.LoginUserBean;
import com.revenant.shipper.bean.ShipperBean.PersonalDetailBean;
import com.revenant.shipper.utils.ActivityUtils;
import com.revenant.shipper.utils.Constants;
import com.revenant.shipper.utils.GsonUtil;
import com.revenant.shipper.utils.SPUtils;
import com.revenant.shipper.utils.WebContentActivity;
import com.revenant.shipper.utils.apkUpdate.StringFormatUtil;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cn.hutool.core.date.DateUtil;
import cn.hutool.crypto.SecureUtil;

import static com.revenant.shipper.utils.Constants.Get_personal_info;

public class ForgetPasswordActivity extends BaseActivity {
    @BindView(R.id.login_telnum)
    EditText loginTelnum;
    @BindView(R.id.login_forgetpassword)
    TextView loginForgetpassword;
    @BindView(R.id.login_btn)
    Button loginBtn;
    @BindView(R.id.btn_verification_code)
    TextView RegisterCode;
    @BindView(R.id.login_back_btn)
    RelativeLayout loginBackBtn;
    @BindView(R.id.login_veri)
    EditText loginRegister;
    @BindView(R.id.login_pass)
    EditText loginPass;
    @BindView(R.id.login_pass_reconfirm)
    EditText loginPassReconfirm;
    @BindView(R.id.web)
    TextView web;
    @BindView(R.id.webcheck)
    CheckBox webcheck;
    private Context context;
    private String fogresttype;
    private String sendtype = "0";
    private boolean ischeck;
    @BindView(R.id.invite_code)
    EditText inviteCode;

    @Override
    public int getContentViewResId() {

//        getWindow().clearFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);
        context = this;
        return R.layout.activity_forget_password;
    }

    @Override
    public void initView() {
        Bundle bundle = this.getIntent().getExtras();
        fogresttype = bundle.getString("fogresttype");
        if (fogresttype.equals("0")) {
            loginBtn.setText("确定");
            sendtype = "1";
        } else if (fogresttype.equals("1")) {
            loginBtn.setText("注册");
            sendtype = "0";
        }
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

    }

    CountDownTimer timer = new CountDownTimer(60 * 1000, 1000) {
        @Override
        public void onTick(long millisUntilFinished) {
            RegisterCode.setText("还剩" + millisUntilFinished / 1000 + "秒");
        }

        @Override
        public void onFinish() {
            RegisterCode.setText("重新发送");
            RegisterCode.setClickable(true);
        }
    };

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

    @OnClick({R.id.login_btn, R.id.btn_verification_code,R.id.web})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.login_btn:
                if (fogresttype.equals("0")) {
                    if (loginTelnum.getText().toString().trim().isEmpty()) {
                        showToast("请输入手机号");
                    } else if (loginTelnum.getText().toString().trim().length() != 11) {
                        showToast("请输入完整手机号");
                    } else if (!StringFormatUtil.phoneCheck(loginTelnum.getText().toString().trim())) {
                        ToastUtils.showShortToast(context, "手机号码格式不正确");
                    } else if (loginRegister.getText().toString().trim().isEmpty()) {
                        showToast("请输入验证码");
                    } else if (loginPass.getText().toString().trim().isEmpty()) {
                        showToast("登录密码不能为空");
                    } else if (loginPassReconfirm.getText().toString().trim().isEmpty()) {
                        showToast("确认密码不能为空");
                    } else if (!loginPass.getText().toString().equals(loginPassReconfirm.getText().toString())) {
                        showToast("两次密码不一致");
                    } else if (!StringFormatUtil.passCheck(loginPass.getText().toString())) {
                        showToast("密码由6-20个英文字母、数字或特殊字符组成");
                    }else if (!ischeck) {
                        ToastUtils.showShortToast(context, "阅读并勾选协议");
                    } else {
                        String account = loginTelnum.getText().toString();
                        String code = loginRegister.getText().toString();
                        String newpass = loginPass.getText().toString();
                        OkGo.<String>post(Constants.User_Login_Forget_Pas)
                                .params("account", account)
                                .params("newPass", newpass)
                                .params("code", code)
                                .execute(new StringCallback() {
                                    @Override
                                    public void onSuccess(Response<String> response) {

                                        LoginUserBean s = GsonUtil.parseJsonWithGson(response.body(), LoginUserBean.class);
                                        if (s.getCode().equals("0")) {
                                            finish();
                                            showToast(s.getMsg());
                                            startActivity(LoginActivity.class);
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
                } else if (fogresttype.equals("1")) {
                    /*
                     * type 0 是司机,1是货主
                     * */
                    if (loginTelnum.getText().toString().trim().isEmpty()) {
                        showToast("请输入手机号");
                    } else if (loginTelnum.getText().toString().trim().length() != 11) {
                        showToast("请输入完整手机号");
                    } else if (!StringFormatUtil.phoneCheck(loginTelnum.getText().toString().trim())) {
                        ToastUtils.showShortToast(context, "手机号码格式不正确");
                    } else if (loginRegister.getText().toString().trim().isEmpty()) {
                        showToast("请输入验证码");
                    } else if (loginPass.getText().toString().trim().isEmpty()) {
                        showToast("登录密码不能为空");
                    } else if (loginPassReconfirm.getText().toString().trim().isEmpty()) {
                        showToast("确认密码不能为空");
                    } else if (!loginPass.getText().toString().equals(loginPassReconfirm.getText().toString())) {
                        showToast("两次密码不一致");
                    } else if (!StringFormatUtil.passCheck(loginPass.getText().toString())) {
                        showToast("密码由6-20个英文字母、数字或特殊字符组成");
                    } else if (!ischeck) {
                        ToastUtils.showShortToast(context, "阅读并勾选协议");
                    }else {
                        String mobile = loginTelnum.getText().toString();
                        String code = loginRegister.getText().toString();
                        String password = loginPass.getText().toString();
                        String invatecode=inviteCode.getText().toString().isEmpty()?"":inviteCode.getText().toString();
                        OkGo.<String>post(Constants.User_Login_Register)
                                .params("mobile", mobile)
                                .params("password", password)
                                .params("code", code)
                                .params("type", "1")
                                .params("inviteCode", invatecode)
                                .execute(new StringCallback() {
                                    @Override
                                    public void onSuccess(Response<String> response) {

                                        LoginUserBean s = GsonUtil.parseJsonWithGson(response.body(), LoginUserBean.class);
                                        if (s.getCode().equals("0")) {
                                            login(mobile, password, SPUtils.getJpRidToken(context));
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
                }

                break;
            case R.id.web:
                startActivity(WebContentActivity.class);
                break;
            case R.id.btn_verification_code:
                if (loginTelnum.getText().toString().isEmpty()) {
                    showToast("请输入手机号");
                } else if (loginTelnum.getText().toString().length() != 11) {
                    showToast("请输入完整手机号");
                } else if (!StringFormatUtil.phoneCheck(loginTelnum.getText().toString().trim())) {
                    ToastUtils.showShortToast(context, "手机号码格式不正确");
                } else {
                    timer.start();
                    RegisterCode.setClickable(false);
                    /*
              0.注册 1.忘记密码 2.忘记支付密码 3.手机验证码
                */
                    String phone = loginTelnum.getText().toString();
                    OkGo.<String>get(Constants.User_Send_Verification_Code)
                            .params("phone", phone)
                            .params("type", sendtype)
                            .execute(new StringCallback() {
                                @Override
                                public void onSuccess(Response<String> response) {

                                    LogUtils.d("AAAA", response.body());
                                    LoginUserBean s = GsonUtil.parseJsonWithGson(response.body(), LoginUserBean.class);
                                    if (s.getCode().equals("0")) {
                                        showToast(s.getMsg());

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
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        timer.cancel();
    }

    private void login(String account, String pwd, String Jpush_rid) {
        OkGo.<String>post(Constants.User_Login)
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
//                            SPUtils.setUserAuthentication(context, s.getData().getUser().getStatus());
                            SPUtils.setAccounId(context, s.getData().getUser().getId());
                            getpseronal();
//                            if (s.getData().getUser().getStatus().equals("0")) {
//                                finish();
//                                startActivity(NoIdentificationActivity.class);
//                            } else if (s.getData().getUser().getStatus().equals("1")) {
//                                finish();
//                                startActivity(ShipperMainActivity.class);
//                            }
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
                            SPUtils.setUserPhone(context, g.getData().getMobile());
                            App applicationContext = (App) getApplicationContext();
                            applicationContext.setUserStatus(Integer.valueOf(g.getData().getStatus()));
                            finish();
                            ActivityUtils.removeAllActivity();
                            startActivity(ShipperMainActivity.class);


                        } else {
                            showToast(g.getMsg());
                        }
                    }

                    @Override
                    public void onError(Response<String> response) {
                        super.onError(response);
                        showToast(response.body());
                    }
                });
    }

}
