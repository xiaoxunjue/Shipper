package com.revenant.shipper.adapter;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.revenant.shipper.R;
import com.revenant.shipper.bean.AreaShowBean;

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
public class AreaSelectItemAdapter extends BaseQuickAdapter<AreaShowBean, BaseViewHolder> {
    public AreaSelectItemAdapter() {
        super(R.layout.item_area_recyvlerview);
    }

    @Override
    protected void convert(BaseViewHolder helper, AreaShowBean item) {
        helper.setText(R.id.area_item_text, item.getAreaname());
    }
}
