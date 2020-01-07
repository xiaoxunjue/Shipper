package com.revenant.shipper.ui.activity;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.revenant.shipper.CustomerServiceAdapter;
import com.revenant.shipper.R;
import com.revenant.shipper.base.BaseActivity;
import com.revenant.shipper.bean.BigItemBean;
import com.revenant.shipper.ui.view.MineBigItem;
import com.revenant.shipper.utils.Utils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class CustomerServiceActivity extends BaseActivity {
    @BindView(R.id.custom_service_recyclerview)
    RecyclerView customServiceRecyclerview;
    @BindView(R.id.custom_service_online)
    TextView customServiceOnline;
    @BindView(R.id.custom_service_tel)
    TextView customServiceTel;

    private CustomerServiceAdapter customerServiceAdapter;
    private List<Drawable> drawableList = new ArrayList<Drawable>();
    private List<String> customer_bottom_text = Arrays.asList("发起投诉", "占位功能", "占位功能", "占位功能");
    private List<BigItemBean> mineBigItemList = new ArrayList<>();

    @Override
    public int getContentViewResId() {
        return R.layout.activity_customerservice;
    }

    @Override
    public void initView() {
        drawableList.add(ContextCompat.getDrawable(getApplicationContext(), R.mipmap.complaint));
        drawableList.add(ContextCompat.getDrawable(getApplicationContext(), R.mipmap.determinated_2));
        drawableList.add(ContextCompat.getDrawable(getApplicationContext(), R.mipmap.determinated));
        drawableList.add(ContextCompat.getDrawable(getApplicationContext(), R.mipmap.suggestion));

        for (int i = 0; i < customer_bottom_text.size(); i++) {
            BigItemBean bigItemBean = new BigItemBean();
            bigItemBean.setDrawable(drawableList.get(i));
            bigItemBean.setTitle(customer_bottom_text.get(i));
            mineBigItemList.add(bigItemBean);
        }
        customerServiceAdapter = new CustomerServiceAdapter();
        customerServiceAdapter.addData(mineBigItemList);
        customServiceRecyclerview.setLayoutManager(new GridLayoutManager(this, 4));
        customServiceRecyclerview.setAdapter(customerServiceAdapter);

        customerServiceAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                switch (position) {
                    case 0:
                        break;
                    case 1:
                        break;
                    case 2:
                        break;
                    case 3:
                        startActivity(SuggestionsActivity.class);
                        break;
                    default:
                }
            }
        });


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

    @OnClick({R.id.custom_service_online, R.id.custom_service_tel})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.custom_service_online:
                break;
            case R.id.custom_service_tel:
                Utils.callPhone(this, "15881354454");
                break;
        }
    }
}
