package com.revenant.shipper.ui.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.StringCallback;
import com.lzy.okgo.model.Response;
import com.revenant.shipper.R;
import com.revenant.shipper.base.BaseActivity;
import com.revenant.shipper.bean.ShipperBean.EmptyDetailBean;
import com.revenant.shipper.utils.GsonUtil;
import com.revenant.shipper.utils.SPUtils;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

import static com.revenant.shipper.utils.Constants.Add_shu_car;

public class AddShuCheActivity extends BaseActivity {
    @BindView(R.id.search_phone)
    EditText searchPhone;
    @BindView(R.id.add_shuche)
    Button   addShuche;
    private String phone    = "";
    private int    driverid = 0;

    @Override
    public int getContentViewResId() {
        return R.layout.activity_addshuche;
    }

    @Override
    public void initView() {
        setcenterTitle("添加熟车");

        Bundle extras = getIntent().getExtras();
        phone = extras.getString("driverphone");
        searchPhone.setFocusable(false);
        searchPhone.setText(phone);
        driverid = extras.getInt("driverid");


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

    @OnClick({R.id.add_shuche})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.add_shuche:
                add_chu_car(SPUtils.getAccounId(this), driverid);
                break;
        }
    }

    private void add_chu_car(int accountId, int driverId) {
        OkGo.<String>post(Add_shu_car)
                .params("accountId", accountId)
                .params("driverId", driverId)
                .execute(new StringCallback() {
                    @Override
                    public void onSuccess(Response<String> response) {
                        EmptyDetailBean detailBean = GsonUtil.parseJsonWithGson(response.body(), EmptyDetailBean.class);
                        if (detailBean.getCode().equals("0")) {
                            showToast(detailBean.getMsg());
                            finish();
                        } else {
                            showToast(detailBean.getMsg());

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
