package com.revenant.shipper.adapter;

import android.widget.EditText;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.revenant.shipper.R;
import com.revenant.shipper.bean.Driver_edit_item;

/**
 * @ProjectName: Shipper
 * @Package: com.revenant.shipper.adapter
 * @ClassName: NewMainAdapter
 * @Description: java类作用描述
 * @Author: revenant
 * @CreateDate: 2019-12-30 10:44
 * @UpdateUser: 更新者
 * @UpdateDate: 2019-12-30 10:44
 * @UpdateRemark: 更新说明
 * @Version: 1.0
 */

public class DriverItemEditAdapter extends BaseQuickAdapter<Driver_edit_item, BaseViewHolder> {
    public DriverItemEditAdapter() {
        super(R.layout.item_driver_improve_info_input);
    }

    @Override
    protected void convert(BaseViewHolder helper, Driver_edit_item item) {
        helper.setText(R.id.item_driver_info_lab, item.getDriver_lable());
        EditText view = helper.getView(R.id.item_driver_info_edit);
        view.setHint(item.getDriver_edit());
    }
}
