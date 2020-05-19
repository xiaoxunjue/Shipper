package com.revenant.shipper.adapter;

import android.widget.TextView;

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
public class VerticalTypeItemAdapter extends BaseQuickAdapter<AreaShowBean, BaseViewHolder> {
    public VerticalTypeItemAdapter() {
        super(R.layout.item_vertical_type);
    }

    @Override
    protected void convert(BaseViewHolder helper, AreaShowBean item) {

        TextView textView = helper.getView(R.id.typeselecttext);
        if (item.getSelect() == 0) {
            textView.setBackgroundResource(R.drawable.gray_border_btn);
            textView.setTextColor(mContext.getResources().getColor(R.color.tab_select_news_color));
//            textView.setTextAppearance(R.style.gray_border_gray_text);
        } else if (item.getSelect() == 1) {
            textView.setTextColor(mContext.getResources().getColor(R.color.blue));
            textView.setBackgroundResource(R.drawable.blue_border_btn);
//            textView.setTextAppearance(R.style.blue_border_blue_text);

        }

        helper.setText(R.id.typeselecttext, item.getAreaname());


    }
}
