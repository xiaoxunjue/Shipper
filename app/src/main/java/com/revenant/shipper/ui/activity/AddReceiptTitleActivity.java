package com.revenant.shipper.ui.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.StringCallback;
import com.lzy.okgo.model.Response;
import com.revenant.shipper.R;
import com.revenant.shipper.base.BaseActivity;
import com.revenant.shipper.bean.ShipperBean.FaPiaoTaiTouDetailBen;
import com.revenant.shipper.utils.Constants;
import com.revenant.shipper.utils.GsonUtil;
import com.revenant.shipper.utils.SPUtils;

import java.util.Arrays;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class AddReceiptTitleActivity extends BaseActivity {
    @BindView(R.id.title)
    EditText title;
    @BindView(R.id.shibiehao)
    EditText shibiehao;
    @BindView(R.id.address)
    EditText address;
    @BindView(R.id.phone)
    EditText phone;
    @BindView(R.id.bankname)
    EditText bankname;
    @BindView(R.id.bankaccount)
    EditText bankaccount;
    @BindView(R.id.editsave)
    Button editsave;

    private List<String> mList = Arrays.asList(Constants.Add_FaPiao);

    @Override
    public int getContentViewResId() {
        return R.layout.activity_add_receipt_title;
    }

    @Override
    public void initView() {
        setcenterTitle("添加发票抬头");
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

    @OnClick({R.id.editsave})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.editsave:
                if (isemptytext(title)
                        || isemptytext(shibiehao)
                        || isemptytext(address)
                        || isemptytext(phone)
                        || isemptytext(bankname)
                        || isemptytext(bankaccount)

                ) {
                    showToast("请填写信息");

                } else if (gettext(phone).length() != 11) {
                    showToast("请输入正确手机号");
                } else {
                    addReceiptTitle(
                            gettext(shibiehao),
                            gettext(phone),
                            gettext(bankname),
                            gettext(bankaccount),
                            gettext(address),
                            gettext(title)
                    );
                }
                break;
        }


    }

    private void addReceiptTitle(
            String taxNumber,
            String phonenum,
            String bank,
            String bankAccount,
            String companyAddress,
            String tiltleName) {
        OkGo.<String>post(mList.get(0))

                .params("taxNumber", taxNumber)
                .params("phone", phonenum)
                .params("bank", bank)
                .params("bankAccount", bankAccount)
                .params("companyAddress", companyAddress)
                .params("titleName", tiltleName)
                .params("accountId", SPUtils.getAccounId(this))
                .execute(new StringCallback() {
                    @Override
                    public void onSuccess(Response<String> response) {
                        FaPiaoTaiTouDetailBen detailBen = GsonUtil.parseJsonWithGson(response.body(), FaPiaoTaiTouDetailBen.class);
                        if (detailBen.getCode().equals("0")) {
                            showToast(detailBen.getMsg());
                            finish();
                        }

                    }
                });
    }

    private String gettext(TextView textView) {
        return textView.getText().toString().trim();
    }

    private boolean isemptytext(TextView textView) {
        return textView.getText().toString().trim().isEmpty();
    }

}
