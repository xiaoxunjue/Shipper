package com.revenant.shipper.adapter;

import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.revenant.shipper.R;
import com.revenant.shipper.bean.SuggestionTypeTextBean;

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
public class SuggestionItemAdapter extends BaseQuickAdapter<SuggestionTypeTextBean, BaseViewHolder> {
    public SuggestionItemAdapter() {
        super(R.layout.item_suggestions_type);
    }

    @Override
    protected void convert(BaseViewHolder helper, SuggestionTypeTextBean item) {
        TextView helperView = helper.getView(R.id.suggesttext);
        if (item.isIsselected()) {
            helperView.setTextAppearance(R.style.suggestionstypeselected);
        } else {
            helperView.setTextAppearance(R.style.suggestionstypenormal);
        }
        helper.setText(R.id.suggesttext, item.getContent());


    }
}
