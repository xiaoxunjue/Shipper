package com.revenant.shipper.ui.activity;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bigkoo.pickerview.builder.TimePickerBuilder;
import com.bigkoo.pickerview.listener.OnTimeSelectListener;
import com.bigkoo.pickerview.view.TimePickerView;
import com.revenant.shipper.R;
import com.revenant.shipper.base.BaseActivity;

import org.angmarch.views.NiceSpinner;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class BaoXianActivity extends BaseActivity {


    @BindView(R.id.baoxianbaseone)
    LinearLayout baoxianbaseone;
    @BindView(R.id.goods_type_spinner)
    NiceSpinner goodsTypeSpinner;
    @BindView(R.id.baoxian_start)
    TextView baoxianStart;
    @BindView(R.id.baoxian_end)
    TextView baoxianEnd;
    @BindView(R.id.baoxianone)
    TextView baoxianone;
    @BindView(R.id.baoxiantwo)
    TextView baoxiantwo;
    @BindView(R.id.baoxianthree)
    TextView baoxianthree;
    private Context context;
    private String weburl="https://www.baidu.com/";

    @Override
    public int getContentViewResId() {
        return R.layout.activity_bao_xian;
    }

    @Override
    public void initView() {
        setcenterTitle("保险投保");

        List<String> dataset = new LinkedList<>(Arrays.asList("吨", "方", "米", "件"));
        goodsTypeSpinner.attachDataSource(dataset);
        context = this;
    }

    @Override
    public void leftbarclick() {
        super.leftbarclick();
        finish();
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


    private void alerttime(TextView textView) {


        Calendar startDate = Calendar.getInstance();
        int year = startDate.get(Calendar.YEAR);
        int month = startDate.get(Calendar.MONTH);
        int day = startDate.get(Calendar.DAY_OF_MONTH);

        startDate.set(year, month, day);

        Calendar endDate = Calendar.getInstance();
        endDate.set(2050, 12, 31);

        TimePickerView pvTime = new TimePickerBuilder(context, new OnTimeSelectListener() {
            @Override
            public void onTimeSelect(Date date, View v) {
                textView.setText(getTime(date));
            }
        }).setType(new boolean[]{true, true, true, false, false, false})
                .setRangDate(startDate, endDate)
                .build();
        pvTime.show();

    }

    private String getTime(Date date) {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        return format.format(date);
    }

    @OnClick({R.id.baoxianone, R.id.baoxiantwo, R.id.baoxianthree, R.id.baoxian_start, R.id.baoxian_end})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.baoxianone:
                jumpWeb(weburl);
                break;
            case R.id.baoxiantwo:
                jumpWeb(weburl);
                break;
            case R.id.baoxianthree:
                jumpWeb(weburl);
                break;
            case R.id.baoxian_start:
                alerttime(baoxianStart);
                break;
            case R.id.baoxian_end:
                alerttime(baoxianEnd);
                break;
        }
    }

    private void jumpWeb(String weburl) {
        Bundle bundle = new Bundle();
        bundle.putString("weburl", weburl);
        startActivity(WebActivity.class, bundle);
    }
}
