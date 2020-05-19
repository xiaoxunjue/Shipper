package com.revenant.shipper.adapter;

import com.apkfuns.logutils.LogUtils;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.revenant.shipper.R;
import com.revenant.shipper.bean.ShipperBean.JieHuoCheLiangBean;

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
public class EmptyLoginItemAdapter extends BaseQuickAdapter<JieHuoCheLiangBean.DataBean.ResultBean, BaseViewHolder> {
    public EmptyLoginItemAdapter() {
        super(R.layout.item_noidentification);
    }
    List<String> danwei= Arrays.asList("吨","方","米","件");

    @Override
    protected void convert(BaseViewHolder helper, JieHuoCheLiangBean.DataBean.ResultBean item) {
        LogUtils.d("danwei"+item.getDanwei());
        helper.setText(R.id.car_number, item.getCarNo());
        helper.setText(R.id.car_info, item.getCarInfo()+danwei.get(item.getDanwei()-1));
        if (item.getLoadCode().equals("0")) {
            helper.setText(R.id.startingplace, "无线");
        } else {
            helper.setText(R.id.startingplace, item.getLoading());
        }

        if (item.getUnloadCode().equals("0")) {
            helper.setText(R.id.endingplace, "无线");
        } else {
            helper.setText(R.id.endingplace, item.getUnloading());
        }

        helper.addOnClickListener(R.id.nologinphone);

    }
}
