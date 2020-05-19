package com.revenant.shipper.adapter;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.revenant.shipper.R;
import com.revenant.shipper.bean.AdvicesBean;


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

public class AdviceListItemAdapter extends BaseQuickAdapter<AdvicesBean.DataBean.ResultBean, BaseViewHolder> {
    public AdviceListItemAdapter() {
        super(R.layout.advice_list_adapter);
    }

    @Override
    protected void convert(BaseViewHolder helper, AdvicesBean.DataBean.ResultBean item) {
        helper.setText(R.id.advice_time, item.getCtime());
        helper.setText(R.id.advice_title, item.getContent());
    }
}
