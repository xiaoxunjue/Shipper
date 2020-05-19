package com.revenant.shipper.adapter;

import com.apkfuns.logutils.LogUtils;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.revenant.shipper.R;
import com.revenant.shipper.bean.ShipperBean.ShipperPublishedBean;

import java.util.Arrays;
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
public class ShipperPublishedItemAdapter extends BaseQuickAdapter<ShipperPublishedBean.DataBean.ResultBean, BaseViewHolder> {
    public ShipperPublishedItemAdapter() {
        super(R.layout.item_shipper_published);
    }
    List<String> danwei= Arrays.asList("吨","方","米","件");
    @Override
    protected void convert(BaseViewHolder helper, ShipperPublishedBean.DataBean.ResultBean item) {
        LogUtils.d("danWei"+item.getDanWei());
        helper.setText(R.id.start_place, item.getLoading());
        helper.setText(R.id.end_place, item.getUnload());
        if (item.isIsOnline()) {
            helper.setText(R.id.onoroff, "线下");
        } else {
            helper.setText(R.id.onoroff, "线上");
        }

        helper.setText(R.id.price_published, String.valueOf(item.getPrice()));
        helper.setText(R.id.shengyu, "剩余" + String.valueOf(item.getTotal()) + "辆");
        helper.setText(R.id.time_published, String.valueOf(item.getCtime()));
        helper.setText(R.id.companyName, item.getCompanyName());
        helper.setText(R.id.type_and_weight, item.getVehicleLeader() + " | " + item.getWeight()+danwei.get(item.getDanWei()-1));
        if (item.getIsPublish() == 2) {
            helper.setVisible(R.id.show_closed_shipper, true);
            helper.addOnClickListener(R.id.publish_goods);
            helper.addOnClickListener(R.id.edit_goods);
            helper.addOnClickListener(R.id.delete_goods);
            if (!item.isIsAuto()) {
                helper.setText(R.id.isauto_confirm, "自动确认");
            } else {
                helper.setText(R.id.isauto_confirm, "手动确认");
            }
        } else if (item.getIsPublish() == 1) {
            helper.addOnClickListener(R.id.closed_goods_source);
            helper.setVisible(R.id.show_published_shipper, true);
            if (!item.isIsAuto()) {
                helper.setText(R.id.isauto_confirm, "自动确认");
                helper.setVisible(R.id.conrifm_diver, false);

            } else {
                helper.setText(R.id.isauto_confirm, "手动确认");
                helper.setVisible(R.id.conrifm_diver, true);
                helper.addOnClickListener(R.id.conrifm_diver);
            }
        }


    }
}
