package com.revenant.shipper.adapter;

import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.revenant.shipper.R;
import com.revenant.shipper.bean.PersonData;

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
public class ShuCheMemberAdapter extends BaseQuickAdapter<PersonData, BaseViewHolder> {
    public ShuCheMemberAdapter() {
        super(R.layout.item_group_member);
    }

    @Override
    protected void convert(BaseViewHolder helper, PersonData item) {
        helper.setText(R.id.name, item.getName());
        helper.addOnClickListener(R.id.member_delete);
        Glide.with(mContext).load(item.getPhotourl()).placeholder(R.mipmap.picture).centerCrop().
                into((ImageView) helper.getView(R.id.photo_url));

    }
}
