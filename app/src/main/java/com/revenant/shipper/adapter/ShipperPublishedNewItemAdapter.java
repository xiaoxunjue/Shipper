package com.revenant.shipper.adapter;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.revenant.shipper.R;
import com.revenant.shipper.bean.ShipperBean.ShipperPublishedBean;

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
public class ShipperPublishedNewItemAdapter extends BaseQuickAdapter<ShipperPublishedBean.DataBean.ResultBean, BaseViewHolder> {
    public ShipperPublishedNewItemAdapter() {
        super(R.layout.item_shipper_published);
    }

    @Override
    protected void convert(BaseViewHolder helper, ShipperPublishedBean.DataBean.ResultBean item) {
        helper.setText(R.id.start_place, item.getLoading());
        helper.setText(R.id.end_place, item.getUnload());
        if (item.isIsOnline()) {
            helper.setText(R.id.onoroff, "线下");
        } else {
            helper.setText(R.id.onoroff, "线上");
        }
        helper.setText(R.id.price_published, String.valueOf(item.getPrice()));
        helper.setText(R.id.shengyu, "剩余" + String.valueOf(item.getTotal()));
        helper.setText(R.id.time_published, String.valueOf(item.getCtime()));
        helper.setText(R.id.companyName, item.getCompanyName());
        helper.setText(R.id.type_and_weight, item.getVehicleLeader() + item.getWeight()+"吨");

        helper.addOnClickListener(R.id.publish_goods_push);
//        helper.setVisible(R.id.conrifm_diver, true);
        helper.setVisible(R.id.push_show_shipper, true);
        if (!item.isIsAuto()) {
            helper.setText(R.id.isauto_confirm, "自动确认");
        } else {
            helper.setText(R.id.isauto_confirm, "手动确认");
        }

//        if (item.getIsPublish() == 2) {
//            helper.setVisible(R.id.show_closed_shipper, true);
//            helper.addOnClickListener(R.id.publish_goods);
//            helper.addOnClickListener(R.id.edit_goods);
//            helper.addOnClickListener(R.id.delete_goods);
//        } else if (item.getIsPublish() == 1) {
//            helper.setVisible(R.id.push_show_shipper, true);
//            helper.addOnClickListener(R.id.publish_goods_push);
//            helper.setVisible(R.id.conrifm_diver, true);
//            if (!item.isIsAuto()) {
//                helper.setText(R.id.isauto_confirm, "自动确认");
//            } else {
//                helper.setText(R.id.isauto_confirm, "手动确认");
//            }
//        }


    }
}
