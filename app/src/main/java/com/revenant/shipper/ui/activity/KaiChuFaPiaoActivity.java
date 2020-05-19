package com.revenant.shipper.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.StringCallback;
import com.lzy.okgo.model.Response;
import com.revenant.shipper.R;
import com.revenant.shipper.base.BaseActivity;
import com.revenant.shipper.bean.ShipperBean.FaPiaoTaiTouDetailBen;
import com.revenant.shipper.utils.GsonUtil;
import com.revenant.shipper.utils.SPUtils;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

import static com.revenant.shipper.ui.activity.ReceiptTitleActivity.FaPiaoRESULT_CODE;
import static com.revenant.shipper.utils.Constants.Save_FaPiao;

public class KaiChuFaPiaoActivity extends BaseActivity {
    @BindView(R.id.name)
    TextView name;
    @BindView(R.id.clickshow_title)
    LinearLayout clickshowTitle;
    @BindView(R.id.taxnum)
    TextView taxnum;
    @BindView(R.id.phone)
    TextView phone;
    @BindView(R.id.companyaddress)
    TextView companyaddress;
    @BindView(R.id.useraccount)
    TextView useraccount;
    @BindView(R.id.bankaccount)
    TextView bankaccount;
    @BindView(R.id.show_title)
    LinearLayout showTitle;
    @BindView(R.id.remarks)
    EditText remarks;
    @BindView(R.id.receiver)
    EditText receiver;
    @BindView(R.id.receiverphone)
    EditText receiverphone;
    @BindView(R.id.sendaddress)
    EditText sendaddress;
    @BindView(R.id.click_btn)
    Button clickBtn;
    private int titleid = 0;
    private final static int FaPiaoREQUEST_CODE = 1101;
    private int goodsid = 0;

    @Override
    public int getContentViewResId() {
        return R.layout.activity_receipt_write;
    }

    @Override
    public void initView() {
        goodsid = getIntent().getIntExtra("goodsid", 0);
        setcenterTitle("开出发票");

    }

    @Override
    public void initData() {

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }

    @OnClick({R.id.clickshow_title, R.id.click_btn})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.clickshow_title:
                Bundle bundle = new Bundle();
                bundle.putInt("fapiaotitle", 1);
                Intent intent = new Intent(this, ReceiptTitleActivity.class);
                intent.putExtras(bundle);
                startActivityForResult(intent, FaPiaoREQUEST_CODE);
                break;
            case R.id.click_btn:
                if (receiver.getText().toString().trim().isEmpty()) {
                    showToast("输入收件人姓名");
                } else if (receiverphone.getText().toString().trim().isEmpty()) {
                    showToast("输入收件人电话");
                } else if (sendaddress.getText().toString().trim().isEmpty()) {
                    showToast("输入收件人详细地址");
                } else if (showTitle.getVisibility() == View.VISIBLE) {
                    addReceipt(
                            taxnum.getText().toString().trim(),
                            phone.getText().toString().trim(),
                            useraccount.getText().toString().trim(),
                            bankaccount.getText().toString().trim(),
                            companyaddress.getText().toString().trim(),
                            name.getText().toString().trim(),
                            String.valueOf(goodsid),
                            remarks.getText().toString().trim().isEmpty() ? "" : remarks.getText().toString().trim(),
                            receiver.getText().toString().trim(),
                            receiverphone.getText().toString().trim(),
                            sendaddress.getText().toString().trim(),
                            String.valueOf(titleid)
                    );
                } else {
                    showToast("请选择发票抬头");
                }
                break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == FaPiaoREQUEST_CODE) {

            if (resultCode == FaPiaoRESULT_CODE) {
                name.setText(data.getStringExtra("fapiaotaitou"));
                taxnum.setText(data.getStringExtra("shuihao"));
                phone.setText(data.getStringExtra("phone"));
                companyaddress.setText(data.getStringExtra("companyaddreess"));
                useraccount.setText(data.getStringExtra("useraccount"));
                bankaccount.setText(data.getStringExtra("bankaccount"));
                showTitle.setVisibility(View.VISIBLE);
                titleid = Integer.valueOf(data.getStringExtra("fapiaotaitouid"));

            }
        }
    }


    private void addReceipt(
            String taxNumber,
            String phonenum,
            String bank,
            String bankAccount,
            String companyAddress,
            String tiltleName,
            String orderId,
            String remark,
            String addressee,
            String telePhone,
            String address,
            String titleId

    ) {
        OkGo.<String>post(Save_FaPiao)

                .params("taxNumber", taxNumber)
                .params("phone", phonenum)
                .params("bank", bank)
                .params("bankAccount", bankAccount)
                .params("companyAddress", companyAddress)
                .params("tiltleName", tiltleName)
                .params("accountId", SPUtils.getAccounId(this))
                .params("orderId", orderId)
                .params("status", "1")
                .params("remark", remark)
                .params("addressee", addressee)
                .params("telePhone", telePhone)
                .params("address", address)
                .params("titleId", titleId)
                .execute(new StringCallback() {
                    @Override
                    public void onSuccess(Response<String> response) {
                        FaPiaoTaiTouDetailBen detailBen = GsonUtil.parseJsonWithGson(response.body(), FaPiaoTaiTouDetailBen.class);
                        if (detailBen.getCode().equals("0")) {
                            showToast(detailBen.getMsg());
                            finish();
                            startActivity(ReceiptHistoryActivity.class);
                        }

                    }
                });
    }

    @Override
    public void leftbarclick() {
        super.leftbarclick();
        finish();
    }
}
