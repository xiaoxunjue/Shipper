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
import com.revenant.shipper.utils.GsonUtil;
import com.revenant.shipper.utils.SPUtils;
import com.revenant.shipper.utils.Constants;

import java.util.Arrays;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class EditReceiptTitleActivity extends BaseActivity {
    @BindView(R.id.title)
    TextView title;
    @BindView(R.id.shibiehao)
    TextView shibiehao;
    @BindView(R.id.address)
    EditText address;
    @BindView(R.id.phone)
    EditText phone;
    @BindView(R.id.bankname)
    EditText bankname;
    @BindView(R.id.bankaccount)
    EditText bankaccount;
    @BindView(R.id.editsave)
    Button   editsave;

    private List<String> mList      = Arrays.asList(Constants.Fa_Piao_TaiTou_Detail, Constants.Fa_Piao_TaiTou_EditDetail);
    private int          fapiaotype = 0;
    private int          fapiaoid   = 0;

    @Override
    public int getContentViewResId() {
        return R.layout.activity_edit_receipt_title;
    }

    @Override
    public void initView() {
        Bundle bundle = getIntent().getExtras();
        fapiaotype = bundle.getInt("fapiaotaitoutype");
        fapiaoid = bundle.getInt("fapiaotaitouid");

        switch (fapiaotype) {
            case 0:
                setcenterTitle("发票抬头");
                editsave.setVisibility(View.GONE);
                title.setFocusable(false);
                title.setFocusableInTouchMode(false);
                shibiehao.setFocusable(false);
                shibiehao.setFocusableInTouchMode(false);
                address.setFocusable(false);
                address.setFocusableInTouchMode(false);
                phone.setFocusable(false);
                phone.setFocusableInTouchMode(false);
                bankname.setFocusable(false);
                bankname.setFocusableInTouchMode(false);
                bankaccount.setFocusable(false);
                bankaccount.setFocusableInTouchMode(false);
                break;
            case 1:
                setcenterTitle("编辑发票抬头");
                editsave.setVisibility(View.VISIBLE);
                break;
        }
        look();
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
                lookoredit(
                        gettext(shibiehao),
                        gettext(phone),
                        gettext(bankname),
                        gettext(bankaccount),
                        gettext(address),
                        gettext(title)
                        );
                break;
        }
    }

    private void look() {
        OkGo.<String>get(mList.get(0))
                .params("id", fapiaoid)
                .execute(new StringCallback() {
                    @Override
                    public void onSuccess(Response<String> response) {
                        FaPiaoTaiTouDetailBen detailBen = GsonUtil.parseJsonWithGson(response.body(), FaPiaoTaiTouDetailBen.class);
                        if (detailBen.getCode().equals("0")) {
                            title.setText(detailBen.getData().getTitleName());
                            shibiehao.setText(detailBen.getData().getTaxNumber());
                            address.setText(detailBen.getData().getCompanyAddress());
                            phone.setText(detailBen.getData().getPhone());
                            bankname.setText(detailBen.getData().getBank());
                            bankaccount.setText(detailBen.getData().getBankAccount());
                        }

                    }
                });
    }

    private void lookoredit(
            String taxNumber,
            String phonenum,
            String bank,
            String bankAccount,
            String companyAddress,
            String tiltleName) {
        OkGo.<String>post(mList.get(fapiaotype))
                .params("taxNumber", taxNumber)
                .params("phone", phonenum)
                .params("bank", bank)
                .params("bankAccount", bankAccount)
                .params("companyAddress", companyAddress)
                .params("tiltleName", tiltleName)
                .params("accountId", SPUtils.getAccounId(this))
                .params("id", fapiaoid)
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

}
