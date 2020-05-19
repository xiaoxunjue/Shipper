package com.revenant.shipper.adapter;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.revenant.shipper.R;
import com.revenant.shipper.bean.CommentListIteamBean;


/**
 * @ProjectName: Driver
 * @Package: com.revenant.driver.adapter
 * @ClassName: CommentListItemAdapter
 * @Description: java类作用描述
 * @Author: Revenant
 * @CreateDate: 2020/4/16 17:56
 * @UpdateUser: 更新者
 * @UpdateDate: 2020/4/16 17:56
 * @UpdateRemark: 更新说明
 * @Version: 1.0
 */
public class CommentListItemAdapter extends BaseQuickAdapter<CommentListIteamBean.DataBean.ResultBean, BaseViewHolder> {
    public CommentListItemAdapter() {
        super(R.layout.comment_list_item);
    }

    @Override
    protected void convert(BaseViewHolder helper, CommentListIteamBean.DataBean.ResultBean item) {
        helper.setText(R.id.comment_order_no,item.getOrderNo());
        helper.setText(R.id.comment_order_start,item.getLoading());
        helper.setText(R.id.comment_order_end,item.getUnload());
        helper.setText(R.id.comment_order_car,item.getVehicleNumber());
        helper.setText(R.id.comment_order_type,item.getGoodsInfo());
        helper.setText(R.id.comment_order_price,String.valueOf(item.getAmount()));
        helper.setText(R.id.comment_order_driver_name,String.valueOf(item.getDriverName()));
        helper.addOnClickListener(R.id.comment_order_submit);

    }
}