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
import com.revenant.shipper.bean.YanZhengma;
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
import static com.revenant.shipper.utils.Constants.User_Login_Phone_Verification_;
import static com.revenant.shipper.utils.Constants.User_Send_Verification_Code;

public class VerificationActivity extends BaseActivity {
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
    @BindView(R.id.switch_login_btn)
    TextView switchLoginBtn;
    @BindView(R.id.login_register)
    EditText loginRegister;
    @BindView(R.id.web)
    TextView web;
    @BindView(R.id.webcheck)
    CheckBox webcheck;
    private Context context;
    private boolean ischeck;
    @Override
    public int getContentViewResId() {
        context = this;
        return R.layout.activity_verification;
    }

    @Override
    public void initView() {

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
////        getWindow().clearFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);
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

    @OnClick({R.id.login_forgetpassword, R.id.login_btn, R.id.btn_verification_code, R.id.switch_login_btn,R.id.web})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.login_forgetpassword:
                Bundle bundle0 = new Bundle();
                bundle0.putString("fogresttype", "0");
                startActivity(ForgetPasswordActivity.class, bundle0);
                break;
            case R.id.login_btn:
                if (loginTelnum.getText().toString().isEmpty()) {
                    showToast("请输入手机号");
                } else if (loginTelnum.getText().toString().length() != 11) {
                    showToast("请输入完整手机号");
                } else if (!StringFormatUtil.phoneCheck(loginTelnum.getText().toString().trim())) {
                    ToastUtils.showShortToast(context, "手机号码格式不正确");
                } else if (loginRegister.getText().toString().isEmpty()) {
                    showToast("请输入验证码");
                }  else if (!ischeck) {
                    ToastUtils.showShortToast(context, "阅读并勾选协议");
                }else {
                    String account = loginTelnum.getText().toString();
                    String code = loginRegister.getText().toString();
                    OkGo.<String>post(User_Login_Phone_Verification_)
                            .params("account", account)
                            .params("code", code)
                            .params("type", "1")
                            .params("auroraEquipment", SPUtils.getJpRidToken(context))
                            .execute(new StringCallback() {
                                @Override
                                public void onSuccess(Response<String> response) {
                                    YanZhengma s = GsonUtil.parseJsonWithGson(response.body(), YanZhengma.class);
                                    if (s.getCode().equals("0")) {
                                        SPUtils.setUserToken(context, s.getData().getAccess_token());
                                        SPUtils.setAccounId(context, s.getData().getUser().getId());
                                        SPUtils.setUserPhone(context, s.getData().getUser().getMobile());
                                        getpseronal();
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
            case R.id.btn_verification_code:
                if (loginTelnum.getText().toString().trim().isEmpty()) {
                    showToast("请输入手机号");
                } else if (loginTelnum.getText().toString().trim().length() != 11) {
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
                    int type = 3;
                    OkGo.<String>get(User_Send_Verification_Code)
                            .params("phone", phone)
                            .params("type", type)
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
            case R.id.switch_login_btn:
                startActivity(LoginActivity.class);
                finish();
                break;
            case R.id.web:
                startActivity(WebContentActivity.class);
                break;
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        timer.cancel();
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
                        PersonalDetailBean g = GsonUtil.parseJsonWithGson(response.body(), PersonalDetailBean.class);
                        if (g.getCode().equals("0")) {
                            App applicationContext = (App) getApplicationContext();
                            applicationContext.setUserStatus(Integer.valueOf(g.getData().getStatus()));
                            startActivity(ShipperMainActivity.class);
                        }
                    }
                });
    }
}
