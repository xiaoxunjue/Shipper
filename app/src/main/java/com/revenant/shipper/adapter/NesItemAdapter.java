package com.revenant.shipper.adapter;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.revenant.shipper.R;
import com.revenant.shipper.bean.ShipperBean.NesListBean;

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
public class NesItemAdapter extends BaseQuickAdapter<NesListBean.DataBean.ResultBean, BaseViewHolder> {
    public NesItemAdapter() {
        super(R.layout.item_news);
    }

    @Override
    protected void convert(BaseViewHolder helper, NesListBean.DataBean.ResultBean item) {
        if (item.getType()== 0) {
            helper.setText(R.id.type_news, "系统消息");

        } else {
            helper.setText(R.id.type_news, "私人消息");
        }
        helper.setText(R.id.times, item.getCtime());
        helper.setText(R.id.message_title, item.getShortMsg());

    }
}
