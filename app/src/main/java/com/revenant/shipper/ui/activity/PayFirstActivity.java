package com.revenant.shipper.ui.activity;

import android.app.Dialog;
import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AlertDialog;

import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.StringCallback;
import com.lzy.okgo.model.Response;
import com.revenant.shipper.R;
import com.revenant.shipper.base.BaseActivity;
import com.revenant.shipper.bean.PayPasBean;
import com.revenant.shipper.bean.ShipperBean.PayShowFirstBean;
import com.revenant.shipper.ui.view.NumberKeyboardView;
import com.revenant.shipper.ui.view.PasswordView;
import com.revenant.shipper.utils.GsonUtil;
import com.revenant.shipper.utils.SPUtils;
import com.revenant.shipper.utils.Constants;
import com.revenant.shipper.utils.Utils;

import org.bouncycastle.asn1.esf.SPuri;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

import static com.revenant.shipper.utils.Constants.Name_header_token;

public class PayFirstActivity extends BaseActivity {
    @BindView(R.id.click_confirm_pay)
    Button clickConfirmPay;
    int orderid;
    @BindView(R.id.total_money)
    TextView totalMoney;
    @BindView(R.id.wallet)
    TextView wallet;
    private String subAccount = "";
    private String totalmoney = "";
    private String orderNo = "";
    private Context context;

    @Override
    public int getContentViewResId() {
        return R.layout.activity_payfirst;
    }

    @Override
    public void initView() {
        setcenterTitle("支付页面");
        context=this;
        orderid = getIntent().getIntExtra("orderid", 0);
        orderNo = getIntent().getStringExtra("ordernum");
    }

    @Override
    public void initData() {
        Map<String, String> map = new HashMap<>();
        map.put("orderId", String.valueOf(orderid));
        JSONObject jsonObject = new JSONObject(map);
        getPay(jsonObject);

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

    @OnClick(R.id.click_confirm_pay)
    public void onViewClicked() {
        alertDialog();
    }

    public void alertDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        View view = getLayoutInflater().inflate(R.layout.alert_pay_dialog, null);

        ImageView crossbtn = view.findViewById(R.id.cross_btn);
        TextView textView = view.findViewById(R.id.tv);
        textView.setText(totalmoney);

        final PasswordView mPasswordView = view.findViewById(R.id.pwdInput);
        final NumberKeyboardView mKeyboardView = view.findViewById(R.id.keyboard);

        builder.setView(view);
        final AlertDialog alertDia = builder.show();

        final Window window = alertDia.getWindow();
        window.setBackgroundDrawable(new ColorDrawable(0));
        crossbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                alertDia.dismiss();
            }
        });
        mKeyboardView.setOnKeyboardClickListener(new NumberKeyboardView.OnKeyboardClickListener() {
            @Override
            public void onClick(int keyCode, String insert) {

                if (keyCode == NumberKeyboardView.KEYCODE_BOTTOM_RIGHT) {
                    mPasswordView.delete();
                }
                // 左下角按键和数字按键的点击事件，输入文字
                else {
                    mPasswordView.append(insert);
                }
            }
        });

        mPasswordView.setOnInputCompleteListener(new PasswordView.OnInputCompleteListener() {
            @Override
            public void onInputComplete(String password) {
                Map<String, String> map = new HashMap<>();
                map.put("subAccount", subAccount);
                map.put("oldPwd", password);
                map.put("accountId", String.valueOf(SPUtils.getAccounId(context)));
                JSONObject jsonObject = new JSONObject(map);
//                getPay(jsonObject);
                getCheckPay(jsonObject,mPasswordView);
//                Toast.makeText(PayFirstActivity.this, password, Toast.LENGTH_SHORT).show();
            }
        });

    }

    private void getPay(JSONObject json) {
        showDig();
        OkGo.<String>post(Constants.Pay_Show)
                .upJson(json)
                .execute(new StringCallback() {
                    @Override
                    public void onSuccess(Response<String> response) {

                        PayShowFirstBean payShowFirstBean = GsonUtil.parseJsonWithGson(response.body(), PayShowFirstBean.class);
                        if (payShowFirstBean.getCode().equals("0")) {
                            totalMoney.setText(String.valueOf(payShowFirstBean.getData().getAmount()));
                            totalmoney = String.valueOf(payShowFirstBean.getData().getAmount());
                            wallet.setText(String.valueOf(payShowFirstBean.getData().getBalance()));
                            subAccount = payShowFirstBean.getData().getSubAccount();
                            dismissDig();
                        } else {
                            dismissDig();
                        }

                    }

                    @Override
                    public void onError(Response<String> response) {
                        super.onError(response);
                        dismissDig();
                    }
                });
    }

    private void getCheckPay(JSONObject json,PasswordView mPasswordView) {
        OkGo.<String>post(Constants.Pay_Input_Check)
                .upJson(json)
                .execute(new StringCallback() {
                    @Override
                    public void onSuccess(Response<String> response) {
                        PayPasBean payShowFirstBean = GsonUtil.parseJsonWithGson(response.body(), PayPasBean.class);
                        if (payShowFirstBean.getCode().equals("0")) {
                            if (payShowFirstBean.getData().isTruePwd()) {
                                showDig();
//                                showToast(payShowFirstBean.getMsg());
                                getMakeDeal(orderid);
                            } else {
                                mPasswordView.deleteAll();
                                showToast(payShowFirstBean.getMsg());
                            }


                        }

                    }
                });
    }

    private void getMakeDeal(int id) {
        OkGo.<String>post(Constants.Pay_Make_Deal)
                .headers(Name_header_token, orderNo)
                .params("id", id)
                .execute(new StringCallback() {
                    @Override
                    public void onSuccess(Response<String> response) {
                        PayShowFirstBean payShowFirstBean = GsonUtil.parseJsonWithGson(response.body(), PayShowFirstBean.class);
                        if (payShowFirstBean.getCode().equals("0")) {
                            dismissDig();

                            Bundle bundle = new Bundle();
                            bundle.putString("money", payShowFirstBean.getData().getAmount());
                            startActivity(PaySuccessActivity.class, bundle);
                            finish();
                        } else {
                            dismissDig();
                            showToast(payShowFirstBean.getMsg());
                        }


                    }
                });
    }
}
