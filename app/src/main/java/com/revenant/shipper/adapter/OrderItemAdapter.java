package com.revenant.shipper.adapter;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.revenant.shipper.R;
import com.revenant.shipper.bean.ShipperBean.OrderListBean;

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
public class OrderItemAdapter extends BaseQuickAdapter<OrderListBean.DataBean.ResultBean, BaseViewHolder> {
    /*
     * 运单状态，0:待装货 1:运输中 2:待结算 3:待评价4,待确认 5，完成 6.已卸货
     * */

    List<String> mList = Arrays.asList("待装货", "运输中", "待结算", "待评价", "待确认", "完成", "已卸货");
    List<String> sList = Arrays.asList("", "司机轨迹", "结算", "待评价", "待确认", "完成", "上传磅单");

    public OrderItemAdapter() {
        super(R.layout.item_order);
    }

    @Override
    protected void convert(BaseViewHolder helper, OrderListBean.DataBean.ResultBean item) {
        switch (item.getStatus()) {
            case 0:
                helper.setText(R.id.order_status, mList.get(item.getStatus()));
                helper.setText(R.id.order_num, item.getOrderNo());
                helper.setText(R.id.order_place_start, item.getLoading());
                helper.setText(R.id.order_place_end, item.getUnload());
                helper.setText(R.id.order_price, String.valueOf(item.getAmount()));
                helper.setText(R.id.order_type, item.getGoodsType());
                helper.setText(R.id.order_time, item.getCtime());


                helper.setText(R.id.order_notice, "等待司机装货");
                helper.setVisible(R.id.order_notice, true);
                helper.setVisible(R.id.baoxian, true);
                helper.addOnClickListener(R.id.baoxian);

                helper.setVisible(R.id.order_click, false);
                break;
            case 1:
                helper.setText(R.id.order_num, item.getOrderNo());
                helper.setText(R.id.order_status, mList.get(item.getStatus()));
                helper.setText(R.id.order_place_start, item.getLoading());
                helper.setText(R.id.order_place_end, item.getUnload());
                helper.setText(R.id.order_price, String.valueOf(item.getAmount()));
                helper.setText(R.id.order_type, item.getGoodsType());
                helper.setText(R.id.order_time, item.getCtime());


                helper.addOnClickListener(R.id.order_click);
                helper.setVisible(R.id.order_notice, true);
                helper.setText(R.id.order_notice, "货车正在拼命运输中");
                helper.setVisible(R.id.order_click, true);
                helper.setVisible(R.id.baoxian, true);
                helper.addOnClickListener(R.id.baoxian);
                helper.setText(R.id.order_click, "司机轨迹");
                break;
            case 2:
                helper.setText(R.id.order_num, item.getOrderNo());
                helper.setText(R.id.order_status, mList.get(item.getStatus()));
                helper.setText(R.id.order_place_start, item.getLoading());
                helper.setText(R.id.order_place_end, item.getUnload());
                helper.setText(R.id.order_price, String.valueOf(item.getAmount()));
                helper.setText(R.id.order_type, item.getGoodsType());
                helper.setText(R.id.order_time, item.getCtime());


                helper.setVisible(R.id.order_click, true);
                helper.setVisible(R.id.order_guiji, true);
                helper.setText(R.id.order_click, "结算");
                helper.addOnClickListener(R.id.order_click);
                helper.addOnClickListener(R.id.order_guiji);

                helper.setVisible(R.id.order_notice, false);

                break;
            case 3:
                helper.setText(R.id.order_num, item.getOrderNo());
                helper.setText(R.id.order_status, mList.get(item.getStatus()));
                helper.setText(R.id.order_place_start, item.getLoading());
                helper.setText(R.id.order_place_end, item.getUnload());
                helper.setText(R.id.order_price, String.valueOf(item.getAmount()));
                helper.setText(R.id.order_type, item.getGoodsType());
                helper.setText(R.id.order_time, item.getCtime());


                helper.setVisible(R.id.order_click, true);
                helper.setText(R.id.order_click, "评价");
                helper.addOnClickListener(R.id.order_click);

                helper.setVisible(R.id.order_notice, false);

                break;
            case 4:
                helper.setText(R.id.order_num, item.getOrderNo());
                helper.setText(R.id.order_status, mList.get(item.getStatus()));
                helper.setText(R.id.order_place_start, item.getLoading());
                helper.setText(R.id.order_place_end, item.getUnload());
                helper.setText(R.id.order_price, String.valueOf(item.getAmount()));
                helper.setText(R.id.order_type, item.getGoodsType());
                helper.setText(R.id.order_time, item.getCtime());


                helper.setVisible(R.id.order_click, true);
                helper.setText(R.id.order_click, "待确认");
                helper.addOnClickListener(R.id.order_click);

                helper.setVisible(R.id.order_notice, false);

                break;
            case 5:
//                helper.setVisible(R.id.order_click, false);
                helper.setText(R.id.order_num, item.getOrderNo());
                helper.setText(R.id.order_status, mList.get(item.getStatus()));
                helper.setText(R.id.order_place_start, item.getLoading());
                helper.setText(R.id.order_place_end, item.getUnload());
                helper.setText(R.id.order_price, String.valueOf(item.getAmount()));
                helper.setText(R.id.order_type, item.getGoodsType());
                helper.setText(R.id.order_time, item.getCtime());
                helper.setVisible(R.id.order_click, true);
                helper.setVisible(R.id.order_notice, true);
                helper.setText(R.id.order_click, "历史轨迹");
                helper.setText(R.id.order_notice, "订单已完成");
                helper.addOnClickListener(R.id.order_click);
                break;
            case 6:
                helper.setText(R.id.order_num, item.getOrderNo());
                helper.setText(R.id.order_status, mList.get(item.getStatus()));
                helper.setText(R.id.order_place_start, item.getLoading());
                helper.setText(R.id.order_place_end, item.getUnload());
                helper.setText(R.id.order_price, String.valueOf(item.getAmount()));
                helper.setText(R.id.order_type, item.getGoodsType());
                helper.setText(R.id.order_time, item.getCtime());
                helper.addOnClickListener(R.id.order_click);
                helper.setVisible(R.id.order_click, true);
                helper.setText(R.id.order_click, "上传磅单");
                helper.setVisible(R.id.order_notice, false);
                break;
        }

    }

}
