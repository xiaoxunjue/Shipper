package com.revenant.shipper;

import android.widget.EditText;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.revenant.shipper.bean.Driver_edit_item;
import com.revenant.shipper.bean.Driver_improve_info_image_select;

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

public class DriverItemImageSelectAdapter extends BaseQuickAdapter<Driver_improve_info_image_select, BaseViewHolder> {
    public DriverItemImageSelectAdapter() {
        super(R.layout.item_driver_improve_image_select);
    }

    @Override
    protected void convert(BaseViewHolder helper, Driver_improve_info_image_select item) {
        helper.setText(R.id.driver_improve_image_select_bottom, item.getDriver_bottom_info());
    }
}
