package com.revenant.shipper.adapter;

import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

import com.revenant.shipper.R;
import com.revenant.shipper.bean.PersonalCommentShowBean;

/**
 * @ProjectName: Driver
 * @Package: com.revenant.driver.adapter
 * @ClassName: PersonalCommentAdapter
 * @Description: java类作用描述
 * @Author: Revenant
 * @CreateDate: 2020/4/17 13:40
 * @UpdateUser: 更新者
 * @UpdateDate: 2020/4/17 13:40
 * @UpdateRemark: 更新说明
 * @Version: 1.0
 */
public class PersonalCommentAdapter extends BaseQuickAdapter<PersonalCommentShowBean.DataBean.ListBean, BaseViewHolder> {

    public PersonalCommentAdapter() {
        super(R.layout.personal_comment_show);
    }

    @Override
    protected void convert(BaseViewHolder helper, PersonalCommentShowBean.DataBean.ListBean item) {

        helper.setText(R.id.personal_name_c,item.getDriverName());
        helper.setText(R.id.personal_time_c,item.getDriverTime());
        helper.setText(R.id.personal_comment_c,item.getShipperContent());
        Glide.with(mContext).load(item.getDriverPhoto()).into((ImageView) helper.getView(R.id.personal_head_c));

    }
}
