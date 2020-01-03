package com.revenant.shipper.ui.activity;

import android.content.Context;
import android.os.Bundle;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.revenant.shipper.DriverItemImageSelectAdapter;
import com.revenant.shipper.R;
import com.revenant.shipper.adapter.DriverItemEditAdapter;
import com.revenant.shipper.adapter.MineBigItemAdapter;
import com.revenant.shipper.base.BaseActivity;
import com.revenant.shipper.bean.Driver_edit_item;
import com.revenant.shipper.bean.Driver_improve_info_image_select;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ImproveDriverInformationActivity extends BaseActivity {
    @BindView(R.id.driver_infoi_nput_edit_recyclerview)
    RecyclerView driverInfoiNputEditRecyclerview;
    @BindView(R.id.driver_image_input_edit_recyclerview)
    RecyclerView driverImageInputEditRecyclerview;
    private DriverItemEditAdapter driverItemEditAdapter;
    private Context context;
    private List<Driver_edit_item> driver_edit_itemList;
    private List<String> driver_lable = Arrays.asList("我的姓名", "身份证号", "联系电话");
    private List<String> driver_edit = Arrays.asList("请输入真实姓名", "请输入身份证号", "请输入手机号");

    private List<Driver_improve_info_image_select> image_selects;
    private List<String> drive_image_bottomlist =
            Arrays.asList("身份证正面", "身份证反面");
    private DriverItemImageSelectAdapter driverItemImageSelectAdapter;

    @Override
    public int getContentViewResId() {
        return R.layout.activity_improvedriverinfo;
    }

    @Override
    public void initView() {
        context = this;
        driverItemEditAdapter = new DriverItemEditAdapter();
        driver_edit_itemList = new ArrayList<>();
        for (int i = 0; i < driver_lable.size(); i++) {
            Driver_edit_item driver_edit_item = new Driver_edit_item();
            driver_edit_item.setDriver_lable(driver_lable.get(i));
            driver_edit_item.setDriver_edit(driver_edit.get(i));
            driver_edit_itemList.add(driver_edit_item);

        }
        driverItemEditAdapter.addData(driver_edit_itemList);
        driverInfoiNputEditRecyclerview.setLayoutManager(new LinearLayoutManager(context));
        driverInfoiNputEditRecyclerview.setAdapter(driverItemEditAdapter);


        driverItemImageSelectAdapter=new DriverItemImageSelectAdapter();
        image_selects=new ArrayList<>();
        for (int i = 0; i < drive_image_bottomlist.size(); i++) {
            Driver_improve_info_image_select driverImproveInfoImageSelect=new Driver_improve_info_image_select();
            driverImproveInfoImageSelect.setDriver_bottom_info(drive_image_bottomlist.get(i));
            image_selects.add(driverImproveInfoImageSelect);
        }

        driverItemImageSelectAdapter.addData(image_selects);
        driverImageInputEditRecyclerview.setLayoutManager(new LinearLayoutManager(context));
        driverImageInputEditRecyclerview.setAdapter(driverItemImageSelectAdapter);

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
}
