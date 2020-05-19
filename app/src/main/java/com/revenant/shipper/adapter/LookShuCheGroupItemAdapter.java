package com.revenant.shipper.adapter;

import android.widget.RadioButton;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.revenant.shipper.R;
import com.revenant.shipper.bean.ShipperBean.ShuCheGroupBean;

/**
 * @ProjectName: Shipper
 * @Package: com.revenant.shipper.adapter
 * @ClassName: MineBigItemAdapter
 * @Description: java类作用描述
 * @Author: revenant
 * @CreateDate: 2019-12-30 14:18
 * @UpdateUser: 更新者
 * @UpdateDate: 2019-12-30 14:18
 * @UpdateRemark: 更新说明
 * @Version: 1.0
 */
public class LookShuCheGroupItemAdapter extends BaseQuickAdapter<ShuCheGroupBean.DataBean.ResultBean, BaseViewHolder> {
    public LookShuCheGroupItemAdapter() {
        super(R.layout.item_acquainted_order);
    }

    @Override
    protected void convert(BaseViewHolder helper, ShuCheGroupBean.DataBean.ResultBean item) {
        helper.setText(R.id.group_item_name, item.getName());
        RadioButton designateRadion = helper.getView(R.id.select_group);
        if (item.isIsselect()) {
            designateRadion.setChecked(true);
        } else {
            designateRadion.setChecked(false);
        }
        helper.addOnClickListener(R.id.select_group);

    }
}
