package com.revenant.shipper.adapter;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.revenant.shipper.R;
import com.revenant.shipper.bean.AreaBeans;
import com.revenant.shipper.bean.AreaShowBean;
import com.revenant.shipper.bean.BigItemBean;
import com.revenant.shipper.utils.AreaSelect;

import java.util.List;

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
public class AreaItemAdapter extends BaseQuickAdapter<AreaShowBean, BaseViewHolder> {
    public AreaItemAdapter() {
        super(R.layout.item_area);
    }

    @Override
    protected void convert(BaseViewHolder helper, AreaShowBean item) {
        helper.setText(R.id.areaselecttext, item.getAreaname());

    }
}
