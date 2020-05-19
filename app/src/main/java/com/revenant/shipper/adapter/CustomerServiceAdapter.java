package com.revenant.shipper.adapter;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.revenant.shipper.R;

import com.revenant.shipper.bean.BigItemBean;

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
public class CustomerServiceAdapter extends BaseQuickAdapter<BigItemBean, BaseViewHolder> {
    public CustomerServiceAdapter() {
        super(R.layout.item_recyclerview_customer);
    }

    @Override
    protected void convert(BaseViewHolder helper, BigItemBean item) {
        helper.setText(R.id.customer_item_text, item.getTitle());
        helper.setImageDrawable(R.id.customer_item_image, item.getDrawable());
    }
}
